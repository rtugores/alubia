package huitca1212.alubia13.business;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.service.AlubiaService;

public class ScheduleBusiness {

	public static void getBackendScheduleContent(final AllBusinessListener<ScheduleWrapper> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			ScheduleWrapper data;

			@Override
			public String onBackground() {
				String url = "/schedule_alubia.json";
				data = AlubiaService.getDataFromRequest(url, ScheduleWrapper.class);
				if (data == null) {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				} else {
					return DefaultAsyncTask.ASYNC_TASK_OK;
				}
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onServerSuccess(data);
				} else {
					listener.onFailure(result);
				}
			}
		}).execute();
	}
}
