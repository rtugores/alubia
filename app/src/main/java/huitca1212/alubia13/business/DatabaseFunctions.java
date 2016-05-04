package huitca1212.alubia13.business;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import huitca1212.alubia13.AlubiaApplication;
import huitca1212.alubia13.model.common.DatabaseHelper;
import huitca1212.alubia13.model.forum.Comment;
import huitca1212.alubia13.model.news.News;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;

public class DatabaseFunctions {
	private static DatabaseHelper databaseCommentsHelper = null;

	public static void setDatabaseNewsValues(final ArrayList<News> data) {
		try {
			final RuntimeExceptionDao<News, Integer> newsDao = getDbHelper().getNewsDao();
			newsDao.callBatchTasks(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					newsDao.deleteBuilder().delete();
					for (News aData : data) {
						newsDao.create(aData);
					}
					return null;
				}
			});
		} catch (SQLException e) {
			Log.e("DatabaseFunctions", "Error in setDatabaseNewsValues");
		}
	}

	public static void setDatabaseScheduleWrapper(final ScheduleWrapper data) {
		try {
			final RuntimeExceptionDao<ScheduleWrapper, Integer> scheduleWrapperDao = getDbHelper().getScheduleWrapperDao();
			scheduleWrapperDao.callBatchTasks(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					scheduleWrapperDao.deleteBuilder().delete();
					scheduleWrapperDao.createIfNotExists(data);
					return null;
				}
			});
		} catch (SQLException e) {
			Log.e("DatabaseFunctions", "Error in setDatabaseScheduleWrapper");
		}
	}

	public static void setDatabaseComments(final ArrayList<Comment> data) {
		try {
			final RuntimeExceptionDao<Comment, Integer> commentsDao = getDbHelper().getCommentsDao();
			commentsDao.callBatchTasks(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					commentsDao.deleteBuilder().delete();
					for (Comment aData : data) {
						commentsDao.create(aData);
					}
					return null;
				}
			});
		} catch (SQLException e) {
			Log.e("DatabaseFunctions", "Error in setDatabaseComments");
		}
	}

	public static void setDatabaseLastComment(final Comment data) {
		try {
			final RuntimeExceptionDao<Comment, Integer> commentsDao = getDbHelper().getCommentsDao();
			commentsDao.create(data);
		} catch (SQLException e) {
			Log.e("DatabaseFunctions", "Error in setDatabaseLastComment");
		}
	}

	static DatabaseHelper getDbHelper() {
		if (databaseCommentsHelper == null) {
			databaseCommentsHelper = OpenHelperManager.getHelper(AlubiaApplication.getInstance(), DatabaseHelper.class);
		}
		return databaseCommentsHelper;
	}

}
