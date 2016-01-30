package huitca1212.alubia13.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URLEncoder;

import huitca1212.alubia13.model.CommentWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SettingsBusiness {

	public static void sendDeleteAccountToBackend(final String id, final ServerListenerBusiness<String> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			CommentWrapper data;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();

				try {
					String mUrl = "http://rjapps.x10host.com/denunciar_comentario.php?id=" + URLEncoder.encode(id, "UTF-8");

					Request request = new Request.Builder()
							.url(mUrl)
							.build();

					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					data = gson.fromJson(jsonResult, CommentWrapper.class);
					return data.getResult();
				} catch (Exception e) {
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
						listener.onServerSuccess(null);
						break;
				}
			}
		}).execute();
	}
}
