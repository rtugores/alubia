package huitca1212.alubia13.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import huitca1212.alubia13.model.Comment;
import huitca1212.alubia13.model.CommentWrapper;
import huitca1212.alubia13.model.CommentsWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForumBusiness {

	public static void getForumContent(AllListenerBusiness<Comment> listener) {
		getDatabaseForumContent(listener);
	}

	public static void getDatabaseForumContent(final AllListenerBusiness<Comment> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
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

	public static void getBackendForumContent(final AllListenerBusiness<Comment> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			CommentsWrapper data;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();
				String url = "http://rjapps.x10host.com/descargar_comentarios.php";

				Request request = new Request.Builder()
						.url(url)
						.build();
				try {
					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					data = gson.fromJson(jsonResult, CommentsWrapper.class);
					return data.getResult();
				} catch (IOException e) {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				}
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

	public static void sendCommentToBackend(final String mUrl, final AllListenerResultBusiness<Comment> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			CommentWrapper data;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();

				Request request = new Request.Builder()
						.url(mUrl)
						.build();
				try {
					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					data = gson.fromJson(jsonResult, CommentWrapper.class);
					return data.getResult();
				} catch (IOException e) {
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						listener.onFailure(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR);
					case "-2":
						listener.onFailure(DefaultAsyncTask.ASYNC_TASK_USER_NOT_PERMITED_ERROR);
					default:
						listener.onServerSuccess(data.getComment());
						break;
				}
			}
		}).execute();
	}
}
