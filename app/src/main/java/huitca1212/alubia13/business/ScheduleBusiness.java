package huitca1212.alubia13.business;

import android.content.Context;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.service.AlubiaService;
import huitca1212.alubia13.utils.FileUtils;

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
				String data = FileUtils.readFileToData(ctx, SCHEDULE_FILENAME);
				if (data != null) {
					scheduleWrapper = AlubiaService.convertStringToObject(data, ScheduleWrapper.class);
					if (scheduleWrapper != null) {
						return DefaultAsyncTask.ASYNC_TASK_OK;
					}
				}
				return DefaultAsyncTask.ASYNC_TASK_ERROR;
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
				if (dataString != null && FileUtils.writeDataToFile(ctx, SCHEDULE_FILENAME, dataString)) {
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
