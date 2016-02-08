package huitca1212.alubia13.ui.forum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
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
import huitca1212.alubia13.business.ServerListenerInterface;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumRegisterEmailActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	public static Activity forumRegisterEmailActivity;
	private String user, email;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.email) EditText emailEditText;
	@Bind(R.id.politica2_text) TextView policyText;
	@Bind(R.id.register_button) Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_email);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		forumRegisterEmailActivity = this;

		policyText.setText(Html.fromHtml("<a href=http://rjapps.x10host.com/responsabilidad.html>política de privacidad</a>"));
		policyText.setMovementMethod(LinkMovementMethod.getInstance());

		Bundle extras = getIntent().getExtras();
		user = extras.getString("usuario");

		emailEditText.addTextChangedListener(this);
		emailEditText.setOnEditorActionListener(this);
		registerButton.setOnClickListener(this);
	}

	private void checkEmail() {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(emailEditText.getWindowToken(), 0);

		email = emailEditText.getText().toString().trim();
		if (!Checkers.isRightEmail(email)) {
			Notifications.showToast(ForumRegisterEmailActivity.this, getString(R.string.forum_error_bad_email));
		} else {
			accessWebService();
		}
	}

	private void accessWebService() {
		progressbarView.setVisibility(View.VISIBLE);
		ForumLoginRegisterBusiness.checkEmailForum(email, false, new ServerListenerInterface<String>() {
			@Override
			public void onServerSuccess(String result) {
				progressbarView.setVisibility(View.GONE);
				Intent intent = new Intent(ForumRegisterEmailActivity.this, ForumRegisterPasswordActivity.class)
						.putExtra("usuario", user)
						.putExtra("email", email);
				startActivity(intent);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(ForumRegisterEmailActivity.this, getString(R.string.common_internet_error));
						break;
					case "1":
						Notifications.showToast(ForumRegisterEmailActivity.this, getString(R.string.forum_error_bad_email_repeat));
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
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_NEXT) {
			checkEmail();
			return true;
		}
		return false;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().trim().length() > 0) {
			registerButton.setEnabled(true);
			registerButton.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			registerButton.setEnabled(false);
			registerButton.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	public void onDestroy() {
		forumRegisterEmailActivity = null;
		super.onDestroy();
	}
}