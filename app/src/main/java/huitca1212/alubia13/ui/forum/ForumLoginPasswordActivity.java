package huitca1212.alubia13.ui.forum;

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

public class ForumLoginPasswordActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String email, password;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.password_edit_text) EditText passwordEditText;
	@Bind(R.id.register_button) Button registerButton;
	@Bind(R.id.forget_password_button) Button forgottenPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_login_password);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_default);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		passwordEditText.setOnEditorActionListener(this);
		passwordEditText.addTextChangedListener(this);

		forgottenPassword.setOnClickListener(this);
		registerButton.setOnClickListener(this);

		email = getIntent().getExtras().getString("email");
	}

	private void checkPassword() {

		password = passwordEditText.getText().toString().trim();
		if (Checkers.isRightPassword(password)) {
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
			accessWebService();
		} else {
			passwordEditText.setError(getString(R.string.forum_error_bad_passwd));
		}
	}

	private void accessWebService() {

		progressbarView.setVisibility(View.VISIBLE);
		ForumLoginRegisterBusiness.checkPasswordLoginForum(email, password, new AllBusinessListener<String>() {
			@Override
			public void onDatabaseSuccess(String object) {

			}

			@Override
			public void onServerSuccess(String result) {
				progressbarView.setVisibility(View.GONE);
				getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("username", result).commit();
				getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("notregister", false).commit();

				Intent intent = new Intent(ForumLoginPasswordActivity.this, ForumActivity.class);
				intent.putExtra(ForumActivity.INVITED_USER, "NOK");
				startActivity(intent);
				try {
					ForumMenuActivity.forumMenuActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				try {
					ForumLoginEmailActivity.forumLoginEmailActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				finish();
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(ForumLoginPasswordActivity.this, getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(ForumLoginPasswordActivity.this, getString(R.string.forum_error_different_passwd));
						break;
				}
			}
		});
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			checkPassword();
			return true;
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
			registerButton.setEnabled(true);
			registerButton.setBackgroundResource(R.drawable.d_button_blue);
		} else {
			registerButton.setEnabled(false);
			registerButton.setBackgroundResource(R.drawable.d_button_gray);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.forget_password_button) {
			Intent intent = new Intent(ForumLoginPasswordActivity.this, ForumForgottenPasswordActivity.class);
			startActivity(intent);
		} else if (id == R.id.register_button) {
			checkPassword();
		}
	}

}
