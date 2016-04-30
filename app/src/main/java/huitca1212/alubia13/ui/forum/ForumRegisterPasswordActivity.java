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
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.utils.Checkers;

public class ForumRegisterPasswordActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	public static Activity forumRegisterPasswordActivity;
	private String email, user;
	@Bind(R.id.password_edit_text) EditText passwdEditText;
	@Bind(R.id.continue_login_button) Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_password);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_default);
		forumRegisterPasswordActivity = this;

		Bundle extras = getIntent().getExtras();
		user = extras.getString("usuario");
		email = extras.getString("email");

		passwdEditText.setOnEditorActionListener(this);
		passwdEditText.addTextChangedListener(this);
		registerButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.continue_login_button) {
			checkPassword();
		}
	}

	protected void checkPassword() {
		String passwd = passwdEditText.getText().toString().trim();
		if (Checkers.isRightPassword(passwd)) {
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(passwdEditText.getWindowToken(), 0);
			Intent intent = new Intent(ForumRegisterPasswordActivity.this, ForumRegisterCodeActivity.class);
			intent.putExtra("usuario", user)
					.putExtra("email", email)
					.putExtra("contrasenya", passwd);
			startActivity(intent);
		} else {
			passwdEditText.setError(getString(R.string.forum_error_bad_passwd));
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_NEXT) {
			checkPassword();
			return true;
		}
		return false;
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
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	public void onDestroy() {
		forumRegisterPasswordActivity = null;
		super.onDestroy();
	}
}
