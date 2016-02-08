package huitca1212.alubia13.ui.forum;

import android.content.Context;
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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumLoginRegisterBusiness;
import huitca1212.alubia13.business.ServerListenerInterface;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumForgottenPasswordActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String email;
	@Bind(R.id.progressbar_view) LinearLayout progressbarView;
	@Bind(R.id.forgotten_email) EditText emailBox;
	@Bind(R.id.forgotten_button) Button forgottenAction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_forgotten_password);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);

		emailBox.setOnEditorActionListener(this);
		emailBox.addTextChangedListener(this);

		forgottenAction.setOnClickListener(this);
	}

	private void recoverPassword() {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(emailBox.getWindowToken(), 0);

		email = emailBox.getText().toString().trim();
		if (Checkers.isRightEmail(email)) {
			accessWebService();
		} else {
			Toast.makeText(getApplicationContext(), R.string.forum_error_bad_email, Toast.LENGTH_SHORT).show();
		}
	}

	private void accessWebService() {
		progressbarView.setVisibility(View.VISIBLE);
		ForumLoginRegisterBusiness.retriveForgottenPasswd(email, new ServerListenerInterface<String>() {
			@Override
			public void onServerSuccess(String result) {
				progressbarView.setVisibility(View.GONE);
				Notifications.showToast(ForumForgottenPasswordActivity.this, getString(R.string.forum_forgot_email_success));
				finish();
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
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
		if (id == R.id.forgotten_email) {
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
		if (id == R.id.forgotten_button) {
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
			forgottenAction.setEnabled(true);
			forgottenAction.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			forgottenAction.setEnabled(false);
			forgottenAction.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}
}