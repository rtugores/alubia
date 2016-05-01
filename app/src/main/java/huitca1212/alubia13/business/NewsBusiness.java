package huitca1212.alubia13.business;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.news.News;
import huitca1212.alubia13.model.news.NewsWrapper;
import huitca1212.alubia13.service.AlubiaService;

public class NewsBusiness {

	public static void getNewsContent(AllBusinessListener<ArrayList<News>> listener) {
		getDatabaseNewsContent(listener);
	}

	public static void getDatabaseNewsContent(final AllBusinessListener<ArrayList<News>> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			ArrayList<News> list;

			@Override
			public String onBackground() {
				RuntimeExceptionDao<News, Integer> simpleDao;
				try {
					simpleDao = DatabaseFunctions.getDbHelper().getNewsDao();
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
				list = new ArrayList<>();
				try {
					if (simpleDao != null) {
						list = (ArrayList<News>)simpleDao.queryBuilder().orderBy("id", false).query();
						return DefaultAsyncTask.ASYNC_TASK_OK;
					} else {
						return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
					}
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onDatabaseSuccess(list);
				} else {
					listener.onFailure(result);
				}
				getBackendNewsContent(listener);
			}
		}).execute();

	}

	public static void getBackendNewsContent(final AllBusinessListener<ArrayList<News>> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			NewsWrapper data;

			@Override
			public String onBackground() {
				String url = "/descargar_novedades.php";
				data = AlubiaService.getDataFromRequest(url, NewsWrapper.class);
				if (data == null) {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				}
				return DefaultAsyncTask.ASYNC_TASK_OK;
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onServerSuccess(data.getNews());
				} else {
					listener.onFailure(result);
				}
			}
		}).execute();
	}
}