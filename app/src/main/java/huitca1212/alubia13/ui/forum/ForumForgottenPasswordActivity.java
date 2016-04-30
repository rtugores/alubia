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

public class ForumForgottenPasswordActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String email;
	@Bind(R.id.progressbar_view) LinearLayout progressbarView;
	@Bind(R.id.forgotten_email_editText) EditText forgottenEmailEditText;
	@Bind(R.id.recover_password_button) Button recoverPasswordButton;

	public static void startActivity(Activity activity) {
		Intent intent = new Intent(activity, ForumForgottenPasswordActivity.class);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_forgotten_password);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_default);

		forgottenEmailEditText.setOnEditorActionListener(this);
		forgottenEmailEditText.addTextChangedListener(this);

		recoverPasswordButton.setOnClickListener(this);
	}

	private void recoverPassword() {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(forgottenEmailEditText.getWindowToken(), 0);

		email = forgottenEmailEditText.getText().toString().trim();
		if (Checkers.isRightEmail(email)) {
			accessWebService();
		} else {
			forgottenEmailEditText.setError(getString(R.string.forum_error_bad_email));
		}
	}

	private void accessWebService() {
		blockScreen();
		ForumLoginRegisterBusiness.retriveForgottenPasswd(email, new AllBusinessListener<String>() {
			@Override
			public void onServerSuccess(String result) {
				unblockScreen();
				Notifications.showToast(ForumForgottenPasswordActivity.this, getString(R.string.forum_forgot_email_success));
				finish();
			}

			@Override
			public void onFailure(String result) {
				unblockScreen();
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(ForumForgottenPasswordActivity.this, getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(ForumForgottenPasswordActivity.this, getString(R.string.forum_error_different_email));
						break;
				}
			}
		});
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.forgotten_email_editText) {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				recoverPassword();
				return true;
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.recover_password_button) {
			recoverPassword();
		}
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
			recoverPasswordButton.setEnabled(true);
			recoverPasswordButton.setBackgroundResource(R.drawable.d_button_blue);
		} else {
			recoverPasswordButton.setEnabled(false);
			recoverPasswordButton.setBackgroundResource(R.drawable.d_button_gray);
		}
	}

	private void blockScreen() {
		recoverPasswordButton.setEnabled(false);
		forgottenEmailEditText.setEnabled(false);
		progressbarView.setVisibility(View.VISIBLE);
	}

	private void unblockScreen() {
		recoverPasswordButton.setEnabled(true);
		forgottenEmailEditText.setEnabled(true);
		progressbarView.setVisibility(View.GONE);
	}
}
