package huitca1212.alubia13.ui.forum;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconGridFragment;
import com.rockerhieu.emojicon.EmojiconsFragment;
import com.rockerhieu.emojicon.emoji.Emojicon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.AllListenerInterface;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumBusiness;
import huitca1212.alubia13.business.ResultListenerInterface;
import huitca1212.alubia13.business.ServerListenerInterface;
import huitca1212.alubia13.model.Comment;
import huitca1212.alubia13.ui.forum.adapter.ForumAdapter;
import huitca1212.alubia13.ui.more.settings.SettingsActivity;
import huitca1212.alubia13.utils.Animations;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener,
		EmojiconGridFragment.OnEmojiconClickedListener {

	public static final String INVITED_USER = "invitado";
	public static Activity forumActivity;
	private String invited;
	public ForumAdapter adapter;
	public LinearLayoutManager mLayoutManager;
	public ResultListenerInterface resultListener;
	@Bind(R.id.progressbar_view) ViewGroup progressbarView;
	@Bind(R.id.comment_bar) ViewGroup commentBar;
	@Bind(R.id.emojicons_layout) ViewGroup emojiconsViewGroup;
	@Bind(R.id.recycler_view) RecyclerView recyclerView;
	@Bind(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
	@Bind(R.id.comment) EmojiconEditText commentBox;
	@Bind(R.id.emojicons_button) View emojiconsButton;
	@Bind(R.id.update_button) View updateButton;
	@Bind(R.id.send_button) View sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);
		ButterKnife.bind(this);

		forumActivity = this;
		getWindow().setBackgroundDrawableResource(R.drawable.forum_background);

		setAnalytics();
		setResultReportListener();
		hideCommentBarIfInvited();
		setDefaultAdapter();

		retrieveForumContent();

		updateButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
		emojiconsButton.setOnClickListener(this);

		commentBox.requestFocus();
		commentBox.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (emojiconsViewGroup.getVisibility() == View.VISIBLE) {
					emojiconsViewGroup.setVisibility(View.GONE);
				}
				return false;
			}
		});
	}

	private void setAnalytics() {
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);
		Tracker tracker = analytics.newTracker("UA-42496077-1");
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
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
		invited = extras.getString(INVITED_USER);
		if (invited != null && invited.equals("OK")) {
			commentBar.setVisibility(View.GONE);
		}
	}

	private void setResultReportListener() {
		resultListener = new ResultListenerInterface() {
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

	private void retrieveForumContent() {
		blockScreenForEvent();
		ForumBusiness.getForumContent(new AllListenerInterface<Comment>() {
			@Override
			public void onDatabaseSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					updateForumList(list, false, false);
				}
				unblockScreenForEvent();
			}

			@Override
			public void onServerSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					updateForumList(list, false, true);
				}
			}

			@Override
			public void onFailure(String result) {
				Animations.crossfadeViews(progressbarView, recyclerView, ForumActivity.this);
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
		} else if (id == R.id.emojicons_button) {
			onEmojiconsPressed();
		}
	}

	private void onEmojiconsPressed() {
		if (emojiconsViewGroup.getVisibility() == View.VISIBLE) {
			emojiconsViewGroup.setVisibility(View.GONE);
		} else {
			View view = this.getCurrentFocus();
			if (view != null) {
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
			emojiconsViewGroup.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onBackPressed() {
		if (emojiconsViewGroup.getVisibility() == View.VISIBLE) {
			emojiconsViewGroup.setVisibility(View.GONE);
		} else {
			super.onBackPressed();
		}
	}

	private void onUpdateButtonPressed() {
		blockScreenForEvent();
		ForumBusiness.getBackendForumContent(new AllListenerInterface<Comment>() {
			@Override
			public void onDatabaseSuccess(ArrayList<Comment> list) {
			}

			@Override
			public void onServerSuccess(ArrayList<Comment> list) {
				if (list.size() > 0) {
					updateForumList(list, true, true);
				}
				unblockScreenForEvent();
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
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
		ForumBusiness.sendCommentToBackend(user, comment, new ServerListenerInterface<Comment>() {
			@Override
			public void onServerSuccess(Comment comment) {
				if (comment != null) {
					addItemForumList(comment);
					commentBox.setText("");
					unblockScreenForEvent();
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
		Animations.crossfadeViews(progressbarView, recyclerView, ForumActivity.this);
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
		adapter.add(comment);

		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);

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
				Intent intent = new Intent(ForumActivity.this, SettingsActivity.class);
				startActivity(intent);
				return true;
		}
		return false;
	}

	@Override
	public void onDestroy() {
		forumActivity = null;
		super.onDestroy();
	}

	@Override
	public void onEmojiconClicked(Emojicon emojicon) {
		EmojiconsFragment.input(commentBox, emojicon);
	}

	@Override
	public void onEmojiconBackspaceClicked(View v) {
		EmojiconsFragment.backspace(commentBox);
	}
}