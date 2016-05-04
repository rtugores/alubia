package huitca1212.alubia13.model.common;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

import huitca1212.alubia13.model.forum.Comment;
import huitca1212.alubia13.model.news.News;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private RuntimeExceptionDao<Comment, Integer> commentsDao;
	private RuntimeExceptionDao<News, Integer> newsDao;
	private RuntimeExceptionDao<ScheduleWrapper, Integer> scheduleWrapperDao;

	public DatabaseHelper(Context context) {
		super(context, "dbAlubia", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Comment.class);
			TableUtils.createTable(connectionSource, News.class);
			TableUtils.createTable(connectionSource, ScheduleWrapper.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to create databases", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int previousVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Comment.class, true);
			TableUtils.dropTable(connectionSource, News.class, true);
			TableUtils.dropTable(connectionSource, ScheduleWrapper.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + previousVersion + " to new " + newVersion, e);
		}
	}

	public RuntimeExceptionDao<Comment, Integer> getCommentsDao() throws SQLException {
		if (commentsDao == null) {
			commentsDao = getRuntimeExceptionDao(Comment.class);
		}
		return commentsDao;
	}

	public RuntimeExceptionDao<News, Integer> getNewsDao() throws SQLException {
		if (newsDao == null) {
			newsDao = getRuntimeExceptionDao(News.class);
		}
		return newsDao;
	}

	public RuntimeExceptionDao<ScheduleWrapper, Integer> getScheduleWrapperDao() throws SQLException {
		if (scheduleWrapperDao == null) {
			scheduleWrapperDao = getRuntimeExceptionDao(ScheduleWrapper.class);
		}
		return scheduleWrapperDao;
	}
}