package huitca1212.alubia13.ui.forum;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.AllListenerBusiness;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumBusiness;
import huitca1212.alubia13.business.ResultListenerBussiness;
import huitca1212.alubia13.business.ServerListenerBusiness;
import huitca1212.alubia13.model.Comment;
import huitca1212.alubia13.model.CommentsWrapper;
import huitca1212.alubia13.ui.forum.adapter.ForumAdapter;
import huitca1212.alubia13.ui.more.settings.SettingsActivity;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String INVITED_USER = "invitado";
	public static Activity forumActivity;
	private String invited;
	public CommentsWrapper data = new CommentsWrapper();
	public ForumAdapter adapter;
	public LinearLayoutManager mLayoutManager;
	public ResultListenerBussiness resultListener;
	@Bind(R.id.progressbar_view) ViewGroup progressbarView;
	@Bind(R.id.comment_bar) ViewGroup commentBar;
	@Bind(R.id.recycler_view) RecyclerView recyclerView;
	@Bind(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
	@Bind(R.id.comment) EditText commentBox;
	@Bind(R.id.update_button) View updateButton;
	@Bind(R.id.send_button) View sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);
		ButterKnife.bind(this);

		forumActivity = this;
		setAnalytics();

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);

		// Is an invited user?
		if (savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			invited = extras.getString(INVITED_USER);
		} else {
			invited = (String)savedInstanceState.getSerializable(INVITED_USER);
		}
		if (invited != null && invited.equals("OK")) {
			commentBar.setVisibility(View.GONE);
		}

		accessWebService();
		setResultReportListener();

		updateButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.update_button) {
			updateButtonAction();

		} else if (id == R.id.send_button) {
			onSendClick();
		}
	}

	private void setResultReportListener(){
		resultListener = new ResultListenerBussiness() {
			@Override
			public void onResult(String result) {
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_OK:
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.settings_reported_ok));
						break;
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.settings_error_comment_deleted));
						break;
					case "-2":
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.common_internet_error));
						break;
				}
			}
		};
	}

	private void updateButtonAction() {
		progressbarView.setVisibility(View.VISIBLE);
		ForumBusiness.getBackendForumContent(new AllListenerBusiness<Comment>() {
			@Override
			public void onDatabaseSuccess(ArrayList<Comment> list) {

			}

			@Override
			public void onServerSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					updateForumList(list, true);
				}
				progressbarView.setVisibility(View.GONE);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showSnackBar(coordinatorLayout, getString(R.string.news_internet_advise));
				}
				progressbarView.setVisibility(View.GONE);
			}
		});
	}

	private void onSendClick() {
		String comment = commentBox.getText().toString().trim();
		if (!isRightComment(comment)) {
			return;
		}
		String user = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", "");
		sendCommentAndRefresh(user, comment);
	}

	private void setAnalytics() {
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);
		Tracker tracker = analytics.newTracker("UA-42496077-1");
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (invited != null && invited.equals("OK")) {
			return false;
		}
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void accessWebService() {
		progressbarView.setVisibility(View.VISIBLE);
		ForumBusiness.getForumContent(new AllListenerBusiness<Comment>() {
			@Override
			public void onDatabaseSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					drawForumList(list);
				}
				progressbarView.setVisibility(View.GONE);
			}

			@Override
			public void onServerSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					updateForumList(list, false);
				}
				progressbarView.setVisibility(View.GONE);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showSnackBar(coordinatorLayout, getString(R.string.forum_error_conection));
				}
				progressbarView.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.settings_menu:
				Intent intent = new Intent(ForumActivity.this, SettingsActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private boolean isRightComment(String comment) {
		if (comment.matches("")) {
			commentBox.setText("");
			return false;
		}
		if (Checkers.hasStringBadWords(comment)) {
			Notifications.showToast(ForumActivity.this, getString(R.string.forum_error_bad_words));
			return false;
		}
		return true;
	}

	private void sendCommentAndRefresh(String user, String comment) {
		progressbarView.setVisibility(View.VISIBLE);
		sendButton.setEnabled(false);
		commentBox.setEnabled(false);
		ForumBusiness.sendCommentToBackend(user, comment, new ServerListenerBusiness<Comment>() {
			@Override
			public void onServerSuccess(Comment comment) {
				addItemForumList(comment);
				commentBox.setText("");
				progressbarView.setVisibility(View.GONE);
				sendButton.setEnabled(true);
				commentBox.setEnabled(true);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				sendButton.setEnabled(true);
				commentBox.setEnabled(true);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR:
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.forum_error_conection));
						break;
					case DefaultAsyncTask.ASYNC_TASK_USER_NOT_PERMITED_ERROR:
						commentBox.setText("");
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.forum_error_user_blocked));
						break;
				}
			}
		});
	}

	private void drawForumList(ArrayList<Comment> comments) {
		adapter = new ForumAdapter(comments, ForumActivity.this, invited, resultListener);
		recyclerView.setAdapter(adapter);

		DatabaseFunctions.setDatabaseComments(comments);
	}

	private void updateForumList(ArrayList<Comment> comments, boolean fromButton) {
		if (adapter == null) {
			adapter = new ForumAdapter(comments, ForumActivity.this, invited, resultListener);
			recyclerView.setAdapter(adapter);
		} else if (adapter.update(comments)) {
			DatabaseFunctions.setDatabaseComments(comments);
		}
		if (fromButton) {
			recyclerView.setAdapter(adapter);
		}
	}

	private void addItemForumList(Comment comment) {
		adapter.add(comment);
		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);

		DatabaseFunctions.setDatabaseLastComment(comment);
	}
}