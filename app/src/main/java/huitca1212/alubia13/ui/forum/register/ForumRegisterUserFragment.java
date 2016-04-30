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
import huitca1212.alubia13.ui.forum.ForumPrivacyActivity;
import huitca1212.alubia13.utils.Checkers;
import huitca1212.alubia13.utils.Notifications;

public class ForumRegisterUserFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String user;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.user_edit_text) EditText userEditText;
	@Bind(R.id.perform_register) Button continueRegisterButton;
	@Bind(R.id.politica2_text) TextView politicText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_register_user, container, false);
		ButterKnife.bind(this, view);

		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		userEditText.setOnEditorActionListener(this);
		userEditText.addTextChangedListener(this);
		politicText.setOnClickListener(this);
		continueRegisterButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.perform_register) {
			checkUser();
		} else if (id == R.id.politica2_text) {
			ForumPrivacyActivity.startActivity(getActivity());
		}
	}

	private void checkUser() {
		user = userEditText.getText().toString().trim();
		if (user.length() < 3) {
			userEditText.setError(getString(R.string.forum_error_bad_user));
		} else if (Checkers.hasStringBadWords(user)) {
			userEditText.setError(getString(R.string.forum_error_bad_words));
		} else {
			InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(userEditText.getWindowToken(), 0);
			accessWebService();
		}
	}

	private void accessWebService() {
		blockScreen();
		ForumLoginRegisterBusiness.checkUserRegisterForum(user, new AllBusinessListener<String>() {
			@Override
			public void onServerSuccess(String result) {
				unblockScreen();
				((ForumRegisterActivity)getActivity()).openRegisterEmailFragment(user);
			}

			@Override
			public void onFailure(String result) {
				unblockScreen();
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Notifications.showToast(getActivity(), getString(R.string.common_internet_error));
						break;
					case "-2":
						Notifications.showToast(getActivity(), getString(R.string.forum_error_user_repeated));
						break;
				}
			}
		});
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.user_edit_text) {
			if (actionId == EditorInfo.IME_ACTION_NEXT) {
				checkUser();
				return true;
			}
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
			continueRegisterButton.setEnabled(true);
			continueRegisterButton.setBackgroundResource(R.drawable.d_button_blue);
		} else {
			continueRegisterButton.setEnabled(false);
			continueRegisterButton.setBackgroundResource(R.drawable.d_button_gray);
		}
	}

	private void blockScreen() {
		continueRegisterButton.setEnabled(false);
		userEditText.setEnabled(false);
		progressbarView.setVisibility(View.VISIBLE);
	}

	private void unblockScreen() {
		continueRegisterButton.setEnabled(true);
		userEditText.setEnabled(true);
		progressbarView.setVisibility(View.GONE);
	}
}
