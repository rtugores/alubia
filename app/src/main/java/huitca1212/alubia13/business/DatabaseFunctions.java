package huitca1212.alubia13.business;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import huitca1212.alubia13.AlubiaApplication;
import huitca1212.alubia13.model.Comment;
import huitca1212.alubia13.model.DatabaseHelper;
import huitca1212.alubia13.model.News;

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
			//NOOP
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
			//NOOP
		}
	}

	public static void setDatabaseLastComment(final Comment data) {
		try {
			final RuntimeExceptionDao<Comment, Integer> commentsDao = getDbHelper().getCommentsDao();
			commentsDao.callBatchTasks(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					commentsDao.create(data);
					return null;
				}
			});
		} catch (SQLException e) {
			//NOOP
		}
	}

	static DatabaseHelper getDbHelper() {
		if (databaseCommentsHelper == null) {
			databaseCommentsHelper = OpenHelperManager.getHelper(AlubiaApplication.getInstance(), DatabaseHelper.class);
		}
		return databaseCommentsHelper;
	}

}
