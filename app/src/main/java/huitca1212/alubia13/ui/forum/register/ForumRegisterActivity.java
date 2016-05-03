package huitca1212.alubia13.ui.forum.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import huitca1212.alubia13.R;

public class ForumRegisterActivity extends AppCompatActivity {

	public static final int FORUM_REGISTER_USER_ACTIVITY_REQUEST_CODE = 222;
	private String user, email;

	public static void startActivityForResult(Activity activity) {
		Intent intent = new Intent(activity, ForumRegisterActivity.class);
		activity.startActivityForResult(intent, FORUM_REGISTER_USER_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_login_register);
		getWindow().setBackgroundDrawableResource(R.drawable.background_default);

		if(savedInstanceState == null) {
			ForumRegisterUserFragment forumRegisterUserFragment = new ForumRegisterUserFragment();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.forum_login_fragment_container, forumRegisterUserFragment).commit();
		}
	}

	public void openRegisterEmailFragment(String user) {
		this.user = user;
		ForumRegisterEmailFragment forumRegisterEmailFragment = new ForumRegisterEmailFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.forum_login_fragment_container, forumRegisterEmailFragment)
				.addToBackStack(null)
				.commit();
	}

	public void openRegisterPasswordFragment(String email) {
		this.email = email;
		ForumRegisterPasswordFragment forumRegisterPasswordFragment = new ForumRegisterPasswordFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.forum_login_fragment_container, forumRegisterPasswordFragment)
				.addToBackStack(null)
				.commit();
	}

	public void openRegisterCodeFragment(String password) {
		Bundle args = new Bundle();
		args.putString("user", user);
		args.putString("email", email);
		args.putString("password", password);
		ForumRegisterCodeFragment forumRegisterCodeFragment = new ForumRegisterCodeFragment();
		forumRegisterCodeFragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.forum_login_fragment_container, forumRegisterCodeFragment)
				.addToBackStack(null)
				.commit();
	}

	public void finishRegister() {
		setResult(RESULT_OK);
		finish();
	}
}