package huitca1212.alubia13.business;

import android.os.AsyncTask;

import java.io.IOException;

import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;

public class DefaultAsyncTask extends AsyncTask<String, Void, String> {
	public static final String ASYNC_TASK_OK = "1";
	public static final String ASYNC_TASK_ERROR = "-1";
	public static final String ASYNC_TASK_DB_ERROR = "-2";
	public static final String ASYNC_TASK_SERVER_ERROR = "-3";
	public static final String ASYNC_TASK_USER_NOT_PERMITED_ERROR = "-4";

	private AsyncTaskBusinessListener listener;

	public DefaultAsyncTask(AsyncTaskBusinessListener listener) {
		this.listener = listener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		listener.onStart();
	}

	@Override
	protected String doInBackground(String... params) {
		String result;
		try {
			result = listener.onBackground();
		} catch (IOException e) {
			result = ASYNC_TASK_ERROR;
		}
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		listener.onFinish(result);
	}
}