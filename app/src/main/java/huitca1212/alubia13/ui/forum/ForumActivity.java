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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.AllListenerBusiness;
import huitca1212.alubia13.business.AllListenerResultBusiness;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumBusiness;
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
	@Bind(R.id.progressbar_view) LinearLayout progressbarView;
	@Bind(R.id.comment_bar) LinearLayout commentBar;
	@Bind(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
	@Bind(R.id.recycler_view) RecyclerView recyclerView;
	@Bind(R.id.comment) EditText commentBox;
	@Bind(R.id.update_button) Button updateButton;
	@Bind(R.id.send_button) Button sendButton;

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

		commentBox.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.comment) {
			//smoothScrollToPosition(adapter.getItemCount() - 1)
			//recyclerView.setStackFromBottom(true);
		} else if (id == R.id.update_button) {
			updateButtonAction();

		} else if (id == R.id.send_button) {
			onSendClick();
		}
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
					Notifications.showSnackBar(ForumActivity.this, coordinatorLayout, getString(R.string.internet_news_advise));
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

		String usuario = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", "");
		try {
			String mURL = "http://rjapps.x10host.com/anhadir_comentario.php?usuario=" + URLEncoder.encode(usuario, "UTF-8") +
					"&comentario=" + URLEncoder.encode(comment, "UTF-8").replace(" ", "%20");
			sendCommentAndRefresh(mURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
					Notifications.showSnackBar(ForumActivity.this, coordinatorLayout, "H: Conéctate a Internet para tener los comentarios actualizados");
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
			Notifications.showToast(ForumActivity.this, getString(R.string.no_bad_words));
			return false;
		}
		return true;
	}

	private void sendCommentAndRefresh(String mURL) {
		progressbarView.setVisibility(View.VISIBLE);
		sendButton.setEnabled(false);
		commentBox.setEnabled(false);
		ForumBusiness.sendCommentToBackend(mURL, new AllListenerResultBusiness<Comment>() {
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
						Notifications.showSnackBar(ForumActivity.this, coordinatorLayout, "H: debes tener Internet");
						break;
					case DefaultAsyncTask.ASYNC_TASK_USER_NOT_PERMITED_ERROR:
						commentBox.setText("");
						Notifications.showSnackBar(ForumActivity.this, coordinatorLayout, getString(R.string.user_bloqued));
						break;
				}
			}
		});
	}

	private void drawForumList(ArrayList<Comment> comments) {
		adapter = new ForumAdapter(comments, ForumActivity.this, invited);
		recyclerView.setAdapter(adapter);

		DatabaseFunctions.setDatabaseComments(comments);
	}

	private void updateForumList(ArrayList<Comment> comments, boolean fromButton) {
		if (adapter == null) {
			adapter = new ForumAdapter(comments, ForumActivity.this, invited);
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