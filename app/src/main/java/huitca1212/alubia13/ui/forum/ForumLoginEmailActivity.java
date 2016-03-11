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

public class ForumLoginEmailActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener, TextWatcher {

	private String email;
	public static Activity forumLoginEmailActivity;
	@Bind(R.id.progressbar_view_login) LinearLayout progressbarView;
	@Bind(R.id.email) EditText emailBox;
	@Bind(R.id.register_button) Button sendLogin;
	@Bind(R.id.politica2_text) TextView politicText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_login_email);
		ButterKnife.bind(this);

		forumLoginEmailActivity = this;
		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		emailBox.addTextChangedListener(this);
		emailBox.setOnEditorActionListener(this);

		politicText.setText(R.string.forum_privacy_2);

		politicText.setOnClickListener(this);
		sendLogin.setOnClickListener(this);
	}

	private void checkEmail() {
		email = emailBox.getText().toString().trim();
		if (Checkers.isRightEmail(email)) {
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(emailBox.getWindowToken(), 0);
			accessWebService();
		} else {
			emailBox.setError(getString(R.string.forum_error_bad_email));
		}
	}

	private void accessWebService() {
		progressbarView.setVisibility(View.VISIBLE);
		ForumLoginRegisterBusiness.checkEmailForum(email, true, new AllBusinessListener<String>() {
			@Override
			public void onDatabaseSuccess(String object) {

			}

			@Override
			public void onServerSuccess(String result) {
				progressbarView.setVisibility(View.GONE);
				Intent intent = new Intent(ForumLoginEmailActivity.this, ForumLoginPasswordActivity.class);
				intent.putExtra("email", email);
				startActivity(intent);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(ForumLoginEmailActivity.this, getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(ForumLoginEmailActivity.this, getString(R.string.forum_error_different_email));
						break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.register_button) {
			checkEmail();
		} else if (id == R.id.politica2_text) {
			Intent intent = new Intent(ForumLoginEmailActivity.this, ForumPrivacyActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.email) {
			if (actionId == EditorInfo.IME_ACTION_NEXT) {
				checkEmail();
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
			sendLogin.setEnabled(true);
			sendLogin.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			sendLogin.setEnabled(false);
			sendLogin.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}

	public void onDestroy() {
		forumLoginEmailActivity = null;
		super.onDestroy();
	}
}