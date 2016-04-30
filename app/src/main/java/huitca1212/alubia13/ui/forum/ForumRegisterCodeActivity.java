package huitca1212.alubia13.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.ui.more.ContactActivity;

public class ForumRegisterCodeActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

	private String user, email, password;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.continue_login_button) Button registerButton;
	@Bind(R.id.contact_button) Button contactButton;
	@Bind(R.id.code_edit_text) EditText codeEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_code);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_default);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		Bundle extras = getIntent().getExtras();
		user = extras.getString("usuario");
		email = extras.getString("email");
		password = extras.getString("contrasenya");

		codeEditText.setOnEditorActionListener(this);
		registerButton.setOnClickListener(this);
		contactButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.continue_login_button) {
			performRegistation();
		} else if (id == R.id.contact_button) {
			Intent intent = new Intent(ForumRegisterCodeActivity.this, ContactActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.code_edit_text) {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				performRegistation();
				return true;
			}
		}
		return false;
	}

	private void performRegistation() {
		String mobileId;

		String code = codeEditText.getText().toString().trim();
		if (code.length() != 10 && code.length() != 0) {
			codeEditText.setError(getString(R.string.forum_error_bad_code));
			return;
		}

		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(codeEditText.getWindowToken(), 0);
		// Put code zero if lenght = 0 in order to understand with the server
		if (code.length() == 0) {
			code = "0";
		}
		try {
			mobileId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
		} catch (Exception e) {
			mobileId = "0";
		}

		progressbarView.setVisibility(View.VISIBLE);
		ForumLoginRegisterBusiness.performRegistrationForum(user, password, email, code, mobileId, new AllBusinessListener<String>() {
			@Override
			public void onDatabaseSuccess(String object) {

			}

			@Override
			public void onServerSuccess(String result) {
				progressbarView.setVisibility(View.GONE);
				getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("username", user).commit();
				getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("notregister", false).commit();
				try {
					ForumRegisterUserActivity.forumRegisterUserActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				try {
					ForumRegisterEmailActivity.forumRegisterEmailActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				try {
					ForumRegisterPasswordActivity.forumRegisterPasswordActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				Intent intent = new Intent(ForumRegisterCodeActivity.this, ForumActivity.class);
				intent.putExtra(ForumActivity.INVITED_USER, "NOK");
				startActivity(intent);
				finish();
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Toast.makeText(getApplicationContext(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
						break;
					case "-2":
						Toast.makeText(getApplicationContext(), R.string.forum_error_end_user_repeated, Toast.LENGTH_LONG).show();
						break;
					case "-3":
						Toast.makeText(getApplicationContext(), R.string.forum_error_end_email_repeated, Toast.LENGTH_LONG).show();
						break;
					case "-4":
						Toast.makeText(getApplicationContext(), R.string.forum_error_different_code, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
