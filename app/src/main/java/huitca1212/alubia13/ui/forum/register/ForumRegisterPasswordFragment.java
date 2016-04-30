package huitca1212.alubia13.ui.forum.register;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import huitca1212.alubia13.utils.Checkers;

public class ForumRegisterPasswordFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	@Bind(R.id.password_edit_text) EditText passwdEditText;
	@Bind(R.id.perform_register) Button registerButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_register_password, container, false);
		ButterKnife.bind(this, view);

		passwdEditText.setOnEditorActionListener(this);
		passwdEditText.addTextChangedListener(this);
		registerButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.perform_register) {
			checkPassword();
		}
	}

	protected void checkPassword() {
		String password = passwdEditText.getText().toString().trim();
		if (Checkers.isRightPassword(password)) {
			InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(passwdEditText.getWindowToken(), 0);
			((ForumRegisterActivity)getActivity()).openRegisterCodeFragment(password);
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
}
