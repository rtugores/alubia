package huitca1212.alubia13.ui.forum;

import android.content.Context;
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
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.ResultBusinessListener;
import huitca1212.alubia13.model.forum.Comment;
import huitca1212.alubia13.ui.forum.adapter.ForumAdapter;
import huitca1212.alubia13.ui.more.settings.SettingsActivity;
import huitca1212.alubia13.utils.AdsAndAnalytics;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String INVITED_USER = "invitado";
	private String invited;
	public ForumAdapter adapter;
	public LinearLayoutManager mLayoutManager;
	public ResultBusinessListener resultListener;
	@Bind(R.id.progressbar_view) ViewGroup progressbarView;
	@Bind(R.id.comment_bar) ViewGroup commentBar;
	@Bind(R.id.recycler_view) RecyclerView recyclerView;
	@Bind(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
	@Bind(R.id.comment) EditText commentBox;
	@Bind(R.id.update_button) View updateButton;
	@Bind(R.id.send_button) View sendButton;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, ForumActivity.class);
		ctx.startActivity(intent);
	}

	public static void startActivity(Context ctx, String invited) {
		Intent intent = new Intent(ctx, ForumActivity.class);
		intent.putExtra(INVITED_USER, invited);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);
		ButterKnife.bind(this);

		commentBox.requestFocus();
		getWindow().setBackgroundDrawableResource(R.drawable.background_forum);

		AdsAndAnalytics.setAnalytics(this);
		setResultReportListener();
		hideCommentBarIfInvited();
		setDefaultAdapter();

		retrieveForumContent();

		updateButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
	}

	private void setDefaultAdapter() {
		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);
		adapter = new ForumAdapter(ForumActivity.this, invited, resultListener);
		recyclerView.setAdapter(adapter);
	}

	private void hideCommentBarIfInvited() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			invited = extras.getString(INVITED_USER);
			if (invited != null && invited.equals("OK")) {
				commentBar.setVisibility(View.GONE);
			}
		}
	}

	private void setResultReportListener() {
		resultListener = new ResultBusinessListener() {
			@Override
			public void onResult(String result) {
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_OK:
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.settings_reported_ok));
						break;
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.settings_error_comment_deleted));
						break;
				}
			}
		};
	}

	private void retrieveForumContent() {
		blockScreenForEvent();
		ForumBusiness.getForumContent(new AllBusinessListener<ArrayList<Comment>>() {
			@Override
			public void onDatabaseSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					unblockScreenForEvent();
					updateForumList(list, false, false);
				}
			}

			@Override
			public void onServerSuccess(ArrayList<Comment> list) {
				unblockScreenForEvent();
				if (list.size() > 0) {
					updateForumList(list, false, true);
				}
			}

			@Override
			public void onFailure(String result) {
				unblockScreenForEvent();
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showSnackBar(coordinatorLayout, getString(R.string.common_no_internet));
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.update_button) {
			onUpdateButtonPressed();
		} else if (id == R.id.send_button) {
			onSendButtonPressed();
		}
	}

	private void onUpdateButtonPressed() {
		blockScreenForEvent();
		ForumBusiness.getBackendForumContent(new AllBusinessListener<ArrayList<Comment>>() {
			@Override
			public void onServerSuccess(ArrayList<Comment> list) {
				unblockScreenForEvent();
				if (list.size() > 0) {
					updateForumList(list, true, true);
				}
			}

			@Override
			public void onFailure(String result) {
				unblockScreenForEvent();
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showSnackBar(coordinatorLayout, getString(R.string.common_no_internet));
				}
			}
		});
	}

	private void onSendButtonPressed() {
		String comment = commentBox.getText().toString().trim();
		if (Checkers.isRightComment(this, comment, commentBox)) {
			String user = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", "");
			sendCommentAndRefresh(user, comment);
		}
	}

	private void sendCommentAndRefresh(String user, String comment) {
		blockScreenForEvent();
		ForumBusiness.sendCommentToBackend(user, comment, new AllBusinessListener<Comment>() {
			@Override
			public void onServerSuccess(Comment comment) {
				unblockScreenForEvent();
				if (comment != null) {
					addItemForumList(comment);
					commentBox.setText("");
				}
			}

			@Override
			public void onFailure(String result) {
				unblockScreenForEvent();
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR:
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.common_no_internet));
						break;
					case DefaultAsyncTask.ASYNC_TASK_USER_NOT_PERMITED_ERROR:
						commentBox.setText("");
						Notifications.showSnackBar(coordinatorLayout, getString(R.string.forum_error_user_blocked));
						break;
				}
			}
		});
	}

	private void blockScreenForEvent() {
		progressbarView.setVisibility(View.VISIBLE);
		updateButton.setEnabled(false);
		sendButton.setEnabled(false);
		commentBox.setEnabled(false);
	}

	private void unblockScreenForEvent() {
		//Animations.crossfadeViews(progressbarView, recyclerView, ForumActivity.this);
		progressbarView.setVisibility(View.GONE);
		updateButton.setEnabled(true);
		sendButton.setEnabled(true);
		commentBox.setEnabled(true);
	}

	private void updateForumList(ArrayList<Comment> comments, boolean fromButton, boolean fromServerSuccess) {
		if (adapter.update(comments) && fromServerSuccess) {
			DatabaseFunctions.setDatabaseComments(comments);
		}
		if (fromButton) {
			recyclerView.setAdapter(adapter);
		}
	}

	private void addItemForumList(Comment comment) {
		adapter.add(comment, recyclerView);
		DatabaseFunctions.setDatabaseLastComment(comment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (invited != null && invited.equals("OK")) {
			return false;
		}
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.settings_menu:
				SettingsActivity.startActivityForResult(ForumActivity.this);
				return true;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (SettingsActivity.SETTINGS_ACTIVITY_REQUEST_CODE == requestCode) {
			if (RESULT_OK == resultCode) {
				finish();
			}
		}
	}
}