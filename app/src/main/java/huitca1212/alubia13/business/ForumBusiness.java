package huitca1212.alubia13.business;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.business.listener.ResultBusinessListener;
import huitca1212.alubia13.model.Comment;
import huitca1212.alubia13.model.CommentWrapper;
import huitca1212.alubia13.model.CommentsWrapper;
import huitca1212.alubia13.model.Result;
import huitca1212.alubia13.service.AlubiaService;

public class ForumBusiness {

	public static void getForumContent(AllBusinessListener<ArrayList<Comment>> listener) {
		getDatabaseForumContent(listener);
	}

	public static void getDatabaseForumContent(final AllBusinessListener<ArrayList<Comment>> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			ArrayList<Comment> list;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				RuntimeExceptionDao<Comment, Integer> simpleDao;
				try {
					simpleDao = DatabaseFunctions.getDbHelper().getCommentsDao();
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
				list = new ArrayList<>();
				try {
					if (simpleDao != null) {
						list = (ArrayList<Comment>)simpleDao.queryBuilder().orderBy("id", true).query();
					}
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
				return DefaultAsyncTask.ASYNC_TASK_OK;
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onDatabaseSuccess(list);
					getBackendForumContent(listener);
				} else {
					listener.onFailure(result);
				}
			}
		}).execute();

	}

	public static void getBackendForumContent(final AllBusinessListener<ArrayList<Comment>> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			CommentsWrapper data;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				String url = "/descargar_comentarios.php";
				data = AlubiaService.getDataFromRequest(url, CommentsWrapper.class);
				if (data == null) {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				}
				return data.getResult();
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onServerSuccess(data.getComments());
				} else {
					listener.onFailure(result);
				}
			}
		}).execute();
	}

	public static void sendCommentToBackend(final String user, final String comment, final AllBusinessListener<Comment> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			CommentWrapper data;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/anhadir_comentario.php?usuario=" + URLEncoder.encode(user, "UTF-8") +
							"&comentario=" + URLEncoder.encode(comment, "UTF-8").replace(" ", "%20");
					data = AlubiaService.getDataFromRequest(url, CommentWrapper.class);
					if (data == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return data.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumBusiness", "sendCommentToBackend() drops an error while encoding");
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						listener.onFailure(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR);
						break;
					case "-2":
						listener.onFailure(DefaultAsyncTask.ASYNC_TASK_USER_NOT_PERMITED_ERROR);
						break;
					default:
						listener.onServerSuccess(data.getComment());
						break;
				}
			}
		}).execute();
	}

	public static void sendReportToBackend(final String id, final ResultBusinessListener listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			Result result;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/denunciar_comentario.php?id=" + URLEncoder.encode(id, "UTF-8");
					result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumBusiness", "sendReportToBackend() drops an error while encoding");
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				listener.onResult(result);
			}
		}).execute();
	}

	public static void deleteForumAccount(final String user, final AllBusinessListener<String> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			Result result;

			@Override
			public void onStart() {

			}

			@Override
			public String onBackground() {
				try {
					String url = "/borrar_cuenta.php?usuario=" + URLEncoder.encode(user, "UTF-8");
					result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumBusiness", "deleteForumAccount() drops an error while encoding");
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onServerSuccess(result);
				} else {
					listener.onFailure(result);
				}
			}
		}).execute();
	}
}
