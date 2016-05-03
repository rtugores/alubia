package huitca1212.alubia13.ui.forum.register;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.forum.ForumBaseFragment;
import huitca1212.alubia13.utils.Checkers;

public class ForumRegisterPasswordFragment extends ForumBaseFragment implements View.OnClickListener, TextView.OnEditorActionListener {

	@Bind(R.id.password_edit_text) EditText passwordEditText;
	@Bind(R.id.password_confirm_edit_text) EditText passwordConfirmEditText;
	@Bind(R.id.perform_register) Button registerButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_register_password, container, false);
		ButterKnife.bind(this, view);

		passwordEditText.requestFocus();
		InputMethodManager imgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		passwordConfirmEditText.setOnEditorActionListener(this);
		passwordEditText.addTextChangedListener(this);
		registerButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.perform_register) {
			checkPassword();
		}
	}

	protected void checkPassword() {
		String password = passwordEditText.getText().toString().trim();
		String passwordConfirm = passwordConfirmEditText.getText().toString().trim();
		if (Checkers.isRightPassword(password)) {
			if (password.equals(passwordConfirm)) {
				InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
				((ForumRegisterActivity)getActivity()).openRegisterCodeFragment(password);
			} else {
				passwordConfirmEditText.setError(getString(R.string.forum_error_bad_confirm_passwd));
			}
		} else {
			passwordEditText.setError(getString(R.string.forum_error_bad_passwd));
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_SEND) {
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
}
