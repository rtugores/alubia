package huitca1212.alubia13.business;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.service.AlubiaService;

public class ScheduleBusiness {

	public static void getScheduleContent(AllBusinessListener<ScheduleWrapper> listener) {
		getDatabaseScheduleContent(listener);
	}

	public static void getDatabaseScheduleContent(final AllBusinessListener<ScheduleWrapper> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			ScheduleWrapper scheduleWrapper;

			@Override
			public String onBackground() {
				RuntimeExceptionDao<ScheduleWrapper, Integer> scheduleWrapperDao;
				try {
					scheduleWrapperDao = DatabaseFunctions.getDbHelper().getScheduleWrapperDao();
				} catch (SQLException e) {
					return DefaultAsyncTask.ASYNC_TASK_DB_ERROR;
				}
				scheduleWrapper = new ScheduleWrapper();
				try {
					if (scheduleWrapperDao != null) {
						scheduleWrapper = (ScheduleWrapper)scheduleWrapperDao.queryBuilder().query();
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
					listener.onDatabaseSuccess(scheduleWrapper);
				} else {
					listener.onFailure(result);
				}
				getBackendScheduleContent(listener);
			}
		}).execute();
	}

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
