package huitca1212.alubia13.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import huitca1212.alubia13.model.News;
import huitca1212.alubia13.model.NewsWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsBusiness {

	public static void getNewsContent(AllListenerBusiness<News> listener) {
		getDatabaseNewsContent(listener);
	}

	public static void getDatabaseNewsContent(final AllListenerBusiness<News> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				RuntimeExceptionDao<News, Integer> simpleDao;
				try {
					simpleDao = DatabaseFunctions.getDbHelper().getNewsDao();
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
				ArrayList<News> list = new ArrayList<>();
				try {
					if (simpleDao != null) {
						list = (ArrayList<News>)simpleDao.queryBuilder().orderBy("id", false).query();
					}
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
				listener.onDatabaseSuccess(list);
				return DefaultAsyncTask.ASYNC_TASK_OK;
			}

			@Override
			public void onFinish(String result) {
				listener.onFailure(result);
				getBackendNewsContent(listener);
			}
		}).execute();

	}

	public static void getBackendNewsContent(final AllListenerBusiness<News> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();
				String url = "http://rjapps.x10host.com/descargar_novedades.php";

				Request request = new Request.Builder()
						.url(url)
						.build();
				try {
					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					NewsWrapper data = gson.fromJson(jsonResult, NewsWrapper.class);
					listener.onServerSuccess(data.getNews());
				} catch (IOException e) {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				}
				return DefaultAsyncTask.ASYNC_TASK_OK;
			}

			@Override
			public void onFinish(String result) {
				listener.onFailure(result);
			}
		}).execute();
	}
}
