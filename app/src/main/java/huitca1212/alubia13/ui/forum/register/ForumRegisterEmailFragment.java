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

public class ForumRegisterEmailFragment extends ForumBaseFragment implements View.OnClickListener, TextView.OnEditorActionListener {

	private String email;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.email_edit_text) EditText emailEditText;
	@Bind(R.id.politica2_text) TextView policyText;
	@Bind(R.id.perform_register) Button continueRegisterButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_register_email, container, false);
		ButterKnife.bind(this, view);

		emailEditText.requestFocus();
		InputMethodManager imgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		emailEditText.addTextChangedListener(this);
		emailEditText.setOnEditorActionListener(this);
		continueRegisterButton.setOnClickListener(this);
		policyText.setOnClickListener(this);

		return view;
	}

	private void checkEmail() {
		email = emailEditText.getText().toString().trim();
		if (!Checkers.isRightEmail(email)) {
			emailEditText.setError(getString(R.string.forum_error_bad_email));
		} else {
			accessWebService();
		}
	}

	private void accessWebService() {
		blockScreen(continueRegisterButton, emailEditText, progressbarView);
		ForumLoginRegisterBusiness.checkEmailForum(email, false, new AllBusinessListener<String>() {
			@Override
			public void onServerSuccess(String result) {
				unblockScreen(continueRegisterButton, emailEditText, progressbarView);
				((ForumRegisterActivity)getActivity()).openRegisterPasswordFragment(email);
			}

			@Override
			public void onFailure(String result) {
				unblockScreen(continueRegisterButton, emailEditText, progressbarView);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(getActivity(), getString(R.string.common_internet_error));
						break;
					case "1":
						Notifications.showToast(getActivity(), getString(R.string.forum_error_bad_email_repeat));
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
		if (actionId == EditorInfo.IME_ACTION_NEXT) {
			checkEmail();
			return true;
		}
		return false;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().trim().length() > 0) {
			continueRegisterButton.setEnabled(true);
			continueRegisterButton.setBackgroundResource(R.drawable.d_button_blue);
		} else {
			continueRegisterButton.setEnabled(false);
			continueRegisterButton.setBackgroundResource(R.drawable.d_button_gray);
		}
	}
}