package huitca1212.alubia13.business;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.Result;
import huitca1212.alubia13.service.AlubiaService;

public class ForumLoginRegisterBusiness {

	public static void checkEmailForum(final String email, final boolean fromLogin, final AllBusinessListener<String> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/comprobar_email.php?email=" + URLEncoder.encode(email, "UTF-8");
					Result result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumLoginRegisterBus", "checkEmailForum() drops an error while encoding");
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

	public static void checkPasswordLoginForum(final String email, final String password, final AllBusinessListener<String> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/comprobar_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8") +
							"&contrasenya=" + URLEncoder.encode(password, "UTF-8");
					Result result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumLoginRegisterBus", "checkPasswordLoginForum() drops an error while encoding");
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

	public static void retriveForgottenPasswd(final String email, final AllBusinessListener<String> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/olvide_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8");

					Result result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumLoginRegisterBus", "retriveForgottenPasswd() drops an error while encoding");
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

	public static void checkUserRegisterForum(final String user, final AllBusinessListener<String> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/comprobar_usuario.php?usuario=" + URLEncoder.encode(user, "UTF-8").replace(" ", "%20");

					Result result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumLoginRegisterBus", "checkUserRegisterForum() drops an error while encoding");
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

	public static void performRegistrationForum(final String user, final String password, final String email, final String code, final String mobileId,
			final AllBusinessListener<String> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {

			@Override
			public void onStart() {
			}

			@Override
			public String onBackground() {
				try {
					String url = "/comprobar_codigo.php?usuario=" + URLEncoder.encode(user, "UTF-8") +
							"&contrasenya=" + URLEncoder.encode(password, "UTF-8") + "&email=" + URLEncoder.encode(email, "UTF-8") +
							"&codigo_penya=" + URLEncoder.encode(code, "UTF-8") + "&mobile_id=" + URLEncoder.encode(mobileId, "UTF-8");
					Result result = AlubiaService.getDataFromRequest(url, Result.class);
					if (result == null) {
						return DefaultAsyncTask.ASYNC_TASK_ERROR;
					}
					return result.getResult();
				} catch (UnsupportedEncodingException e) {
					Log.e("ForumLoginRegisterBus", "performRegistrationForum() drops an error while encoding");
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
