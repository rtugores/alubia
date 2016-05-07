package huitca1212.alubia13.business;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.service.AlubiaService;

public class ScheduleBusiness {
	private static final String SCHEDULE_FILENAME = "schedule_alubia.json";
	private static boolean dbOperationSuccess = false;

	public static void getScheduleContent(Context ctx, AllBusinessListener<ScheduleWrapper> listener) {
		getDatabaseScheduleContent(ctx, listener);
	}

	public static void getDatabaseScheduleContent(final Context ctx, final AllBusinessListener<ScheduleWrapper> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			ScheduleWrapper scheduleWrapper;

			@Override
			public String onBackground() {
				String data = readFileToData();
				if (data != null) {
					scheduleWrapper = AlubiaService.convertStringToObject(data, ScheduleWrapper.class);
					if (scheduleWrapper != null) {
						return DefaultAsyncTask.ASYNC_TASK_OK;
					}
				}
				return DefaultAsyncTask.ASYNC_TASK_ERROR;
			}

			private String readFileToData() {
				FileInputStream fis = null;
				BufferedReader br = null;
				try {
					fis = ctx.openFileInput(SCHEDULE_FILENAME);
					br = new BufferedReader(new InputStreamReader(fis, "utf8"));
					StringBuilder builder = new StringBuilder();
					int ch;

					while ((ch = br.read()) != -1) {
						builder.append((char)ch);
					}
					return builder.toString();
				} catch (FileNotFoundException e) {
					// Goes here when there is no file yet
					return null;
				} catch (IOException e) {
					Log.e(ScheduleBusiness.class.getName(), "Exception in read to file: " + e.getMessage(), e);
					return null;
				} finally {
					try {
						if (br != null) {
							br.close();
						}
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						//NOOP
					}
				}
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onDatabaseSuccess(scheduleWrapper);
					dbOperationSuccess = true;
				}
				getBackendScheduleContent(ctx, listener);
			}
		}).execute();
	}

	public static void getBackendScheduleContent(final Context ctx, final AllBusinessListener<ScheduleWrapper> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			String dataString;
			ScheduleWrapper dataObject;

			@Override
			public String onBackground() {
				String url = "/schedule_alubia.json";
				dataString = AlubiaService.getStringFromRequest(url);
				if (dataString != null && writeDataToFile(dataString)) {
					dataObject = AlubiaService.convertStringToObject(dataString, ScheduleWrapper.class);
					if (dataObject != null) {
						return DefaultAsyncTask.ASYNC_TASK_OK;
					} else {
						return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
					}
				} else {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				}
			}

			private boolean writeDataToFile(String dataString) {
				FileOutputStream fos = null;
				BufferedWriter bw = null;
				try {
					fos = ctx.openFileOutput(SCHEDULE_FILENAME, Context.MODE_PRIVATE);
					bw = new BufferedWriter(new OutputStreamWriter(fos, "utf8"));
					bw.write(dataString);
					return true;
				} catch (IOException e) {
					Log.e(ScheduleBusiness.class.getName(), "Exception in write to file: " + e.getMessage(), e);
					return false;
				} finally {
					try {
						if (bw != null) {
							bw.close();
						}
						if (fos != null) {
							fos.close();
						}
					} catch (IOException e) {
						//NOOP
					}
				}
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onServerSuccess(dataObject);
				} else {
					if (!dbOperationSuccess) {
						listener.onFailure(result);
					}
				}
			}
		}).execute();
	}
}
