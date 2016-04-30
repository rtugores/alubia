package huitca1212.alubia13.ui.forum.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import huitca1212.alubia13.ui.forum.ForumForgottenPasswordActivity;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumLoginPasswordFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String email, password;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.password_edit_text) EditText passwordEditText;
	@Bind(R.id.perform_register) Button sendLoginButton;
	@Bind(R.id.forget_password_button) Button forgottenPassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_login_password, container, false);
		ButterKnife.bind(this, view);

		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		passwordEditText.setOnEditorActionListener(this);
		passwordEditText.addTextChangedListener(this);
		forgottenPassword.setOnClickListener(this);
		sendLoginButton.setOnClickListener(this);
		email = ((ForumLoginActivity)getActivity()).getEmail();

		return view;
	}

	private void checkPassword() {
		password = passwordEditText.getText().toString().trim();
		if (Checkers.isRightPassword(password)) {
			InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
			accessWebService();
		} else {
			passwordEditText.setError(getString(R.string.forum_error_bad_passwd));
		}
	}

	private void accessWebService() {
		blockScreen();
		ForumLoginRegisterBusiness.checkPasswordLoginForum(email, password, new AllBusinessListener<String>() {
			@Override
			public void onServerSuccess(String result) {
				unblockScreen();
				// Save login
				getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
						.putString("username", result).commit();
				getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
						.putBoolean("notregister", false).commit();
				((ForumLoginActivity)getActivity()).finishLogin();
			}

			@Override
			public void onFailure(String result) {
				unblockScreen();
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(getActivity(), getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(getActivity(), getString(R.string.forum_error_different_passwd));
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
			sendLoginButton.setEnabled(true);
			sendLoginButton.setBackgroundResource(R.drawable.d_button_blue);
		} else {
			sendLoginButton.setEnabled(false);
			sendLoginButton.setBackgroundResource(R.drawable.d_button_gray);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.forget_password_button) {
			Intent intent = new Intent(getActivity(), ForumForgottenPasswordActivity.class);
			startActivity(intent);
		} else if (id == R.id.perform_register) {
			checkPassword();
		}
	}

	private void blockScreen() {
		sendLoginButton.setEnabled(false);
		passwordEditText.setEnabled(false);
		forgottenPassword.setEnabled(false);
		progressbarView.setVisibility(View.VISIBLE);
	}

	private void unblockScreen() {
		sendLoginButton.setEnabled(true);
		passwordEditText.setEnabled(true);
		forgottenPassword.setEnabled(true);
		progressbarView.setVisibility(View.GONE);
	}
}
