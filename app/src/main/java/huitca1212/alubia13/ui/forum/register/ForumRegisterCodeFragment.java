package huitca1212.alubia13.ui.forum.register;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumLoginRegisterBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.ui.more.ContactActivity;

public class ForumRegisterCodeFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

	private String user, email, password;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressbarView;
	@Bind(R.id.perform_register) Button performRegisterButton;
	@Bind(R.id.contact_button) Button contactButton;
	@Bind(R.id.code_edit_text) EditText codeEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum_register_code, container, false);
		ButterKnife.bind(this, view);

		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		Bundle args = this.getArguments();
		user = args.getString("user");
		email = args.getString("email");
		password = args.getString("password");
		codeEditText.setOnEditorActionListener(this);
		performRegisterButton.setOnClickListener(this);
		contactButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.perform_register) {
			performRegistation();
		} else if (id == R.id.contact_button) {
			ContactActivity.startActivity(getActivity());
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

		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(codeEditText.getWindowToken(), 0);
		// Put code zero if lenght = 0 in order to understand with the server
		if (code.length() == 0) {
			code = "0";
		}
		try {
			mobileId = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
		} catch (Exception e) {
			mobileId = "0";
		}

		blockScreen();
		ForumLoginRegisterBusiness.performRegistrationForum(user, password, email, code, mobileId, new AllBusinessListener<String>() {
			@Override
			public void onServerSuccess(String result) {
				unblockScreen();
				getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
						.putString("username", user).commit();
				getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
						.putBoolean("notregister", false).commit();
				((ForumRegisterActivity)getActivity()).finishRegister();
			}

			@Override
			public void onFailure(String result) {
				unblockScreen();
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Toast.makeText(getActivity(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
						break;
					case "-2":
						Toast.makeText(getActivity(), R.string.forum_error_end_user_repeated, Toast.LENGTH_LONG).show();
						break;
					case "-3":
						Toast.makeText(getActivity(), R.string.forum_error_end_email_repeated, Toast.LENGTH_LONG).show();
						break;
					case "-4":
						Toast.makeText(getActivity(), R.string.forum_error_different_code, Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void blockScreen() {
		performRegisterButton.setEnabled(false);
		codeEditText.setEnabled(false);
		contactButton.setEnabled(false);
		progressbarView.setVisibility(View.VISIBLE);
	}

	private void unblockScreen() {
		performRegisterButton.setEnabled(true);
		codeEditText.setEnabled(true);
		contactButton.setEnabled(true);
		progressbarView.setVisibility(View.GONE);
	}
}
