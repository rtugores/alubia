package huitca1212.alubia13.ui.forum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumLoginRegisterBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumRegisterUserActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	public static Activity forumRegisterUserActivity;
	private String user;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.usuario) EditText registerUserEditText;
	@Bind(R.id.register_button) Button registerUserButton;
	@Bind(R.id.politica2_text) TextView politicText;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_user);
		ButterKnife.bind(this);

		forumRegisterUserActivity = this;

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		registerUserEditText.setOnEditorActionListener(this);
		registerUserEditText.addTextChangedListener(this);

		politicText.setText(R.string.forum_privacy_2);

		politicText.setOnClickListener(this);
		registerUserButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.register_button) {
			registerUser();
		} else if (id == R.id.politica2_text) {
			Intent intent = new Intent(ForumRegisterUserActivity.this, ForumPrivacyActivity.class);
			startActivity(intent);
		}
	}

	private void registerUser() {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(registerUserEditText.getWindowToken(), 0);

		user = registerUserEditText.getText().toString().trim();
		if (user.length() < 3) {
			Notifications.showToast(ForumRegisterUserActivity.this, getString(R.string.forum_error_bad_user));
		} else if (Checkers.hasStringBadWords(user)) {
			Notifications.showToast(ForumRegisterUserActivity.this, getString(R.string.forum_error_bad_words));
		} else {
			accessWebService();
		}
	}

	private void accessWebService() {
		progressbarView.setVisibility(View.VISIBLE);
		ForumLoginRegisterBusiness.checkUserRegisterForum(user, new AllBusinessListener<String>() {
			@Override
			public void onDatabaseSuccess(String object) {

			}

			@Override
			public void onServerSuccess(String result) {
				progressbarView.setVisibility(View.GONE);
				Intent intent = new Intent(ForumRegisterUserActivity.this, ForumRegisterEmailActivity.class);
				intent.putExtra("usuario", user);
				startActivity(intent);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(ForumRegisterUserActivity.this, getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(ForumRegisterUserActivity.this, getString(R.string.forum_error_user_repeated));
						break;
				}
			}
		});
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.usuario) {
			if (actionId == EditorInfo.IME_ACTION_NEXT) {
				registerUser();
				return true;
			}
		}
		return false;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().trim().length() > 0) {
			registerUserButton.setEnabled(true);
			registerUserButton.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			registerUserButton.setEnabled(false);
			registerUserButton.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}

	public void onDestroy() {
		forumRegisterUserActivity = null;
		super.onDestroy();
	}
}
