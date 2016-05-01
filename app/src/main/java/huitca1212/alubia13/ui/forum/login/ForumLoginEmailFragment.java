package huitca1212.alubia13.ui.forum.login;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import huitca1212.alubia13.ui.forum.ForumBaseFragment;
import huitca1212.alubia13.ui.forum.ForumPrivacyActivity;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumLoginEmailFragment extends ForumBaseFragment implements View.OnClickListener, EditText.OnEditorActionListener {

	private String email;
	@Bind(R.id.progressbar_view_login) LinearLayout progressbarView;
	@Bind(R.id.email_edit_text) EditText emailEditText;
	@Bind(R.id.perform_register) Button continueLoginButton;
	@Bind(R.id.politica2_text) TextView politicText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_login_email, container, false);
		ButterKnife.bind(this, view);

		politicText.setText(R.string.forum_privacy_2);
		emailEditText.addTextChangedListener(this);
		emailEditText.setOnEditorActionListener(this);
		politicText.setOnClickListener(this);
		continueLoginButton.setOnClickListener(this);

		return view;
	}

	private void checkEmail() {
		email = emailEditText.getText().toString().trim();
		if (Checkers.isRightEmail(email)) {
			accessWebService();
		} else {
			emailEditText.setError(getString(R.string.forum_error_bad_email));
		}
	}

	private void accessWebService() {
		blockScreen(continueLoginButton, emailEditText, progressbarView);
		ForumLoginRegisterBusiness.checkEmailForum(email, true, new AllBusinessListener<String>() {
			@Override
			public void onServerSuccess(String result) {
				unblockScreen(continueLoginButton, emailEditText, progressbarView);
				// Continue login
				((ForumLoginActivity)getActivity()).openLoginPasswordFragment(email);
			}

			@Override
			public void onFailure(String result) {
				unblockScreen(continueLoginButton, emailEditText, progressbarView);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(getActivity(), getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(getActivity(), getString(R.string.forum_error_different_email));
						break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.perform_register) {
			checkEmail();
		} else if (id == R.id.politica2_text) {
			ForumPrivacyActivity.startActivity(getActivity());
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.email_edit_text) {
			if (actionId == EditorInfo.IME_ACTION_NEXT) {
				checkEmail();
				return true;
			}
		}
		return false;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().trim().length() > 0) {
			continueLoginButton.setEnabled(true);
			continueLoginButton.setBackgroundResource(R.drawable.d_button_blue);
		} else {
			continueLoginButton.setEnabled(false);
			continueLoginButton.setBackgroundResource(R.drawable.d_button_gray);
		}
	}
}