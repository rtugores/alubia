package huitca1212.alubia13.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URLEncoder;

import huitca1212.alubia13.model.Result;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForumLoginRegisterBusiness {

	public static void checkEmailForum(final String email, final boolean fromLogin, final ServerListenerInterface<String> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			Result result;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();

				try {
					String mUrl = "http://rjapps.x10host.com/comprobar_email.php?email=" + URLEncoder.encode(email, "UTF-8");

					Request request = new Request.Builder()
							.url(mUrl)
							.build();

					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					result = gson.fromJson(jsonResult, Result.class);
					return result.getResult();
				} catch (Exception e) {
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				if (fromLogin) {
					if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
						listener.onServerSuccess(result);
					} else {
						listener.onFailure(result);
					}
				} else {
					if (result.equals("-2")) {
						listener.onServerSuccess(result);
					} else {
						// Receive a 1 is an error when registering, cause we are reusing login_email code in server
						listener.onFailure(result);
					}
				}
			}
		}).execute();
	}

	public static void checkPasswordLoginForum(final String email, final String password, final ServerListenerInterface<String> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			Result result;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();

				try {

					String mUrl = "http://rjapps.x10host.com/comprobar_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8") +
							"&contrasenya=" + URLEncoder.encode(password, "UTF-8");

					Request request = new Request.Builder()
							.url(mUrl)
							.build();

					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					result = gson.fromJson(jsonResult, Result.class);
					return result.getResult();
				} catch (Exception e) {
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				if (!result.equals("-1") && !result.equals("-2")) {
					listener.onServerSuccess(result);
				} else {
					listener.onFailure(result);
				}
			}
		}).execute();
	}

	public static void retriveForgottenPasswd(final String email, final ServerListenerInterface<String> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			Result result;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();

				try {
					String mUrl = "http://rjapps.x10host.com/olvide_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8");

					Request request = new Request.Builder()
							.url(mUrl)
							.build();

					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					result = gson.fromJson(jsonResult, Result.class);
					return result.getResult();
				} catch (Exception e) {
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

	public static void checkUserRegisterForum(final String user, final ServerListenerInterface<String> listener) {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			Result result;

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				OkHttpClient client = new OkHttpClient();

				try {
					String mUrl = "http://rjapps.x10host.com/comprobar_usuario.php?usuario=" + URLEncoder.encode(user, "UTF-8").replace(" ", "%20");

					Request request = new Request.Builder()
							.url(mUrl)
							.build();

					Response response = client.newCall(request).execute();
					String jsonResult = response.body().string();
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();
					result = gson.fromJson(jsonResult, Result.class);
					return result.getResult();
				} catch (Exception e) {
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
