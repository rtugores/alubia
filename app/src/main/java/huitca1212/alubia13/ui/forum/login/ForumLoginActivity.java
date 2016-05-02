package huitca1212.alubia13.ui.forum.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import huitca1212.alubia13.R;

public class ForumLoginActivity extends AppCompatActivity {

	public static final int FORUM_LOGIN_ACTIVITY_REQUEST_CODE = 111;
	private String email;

	public static void startActivityForResult(Activity activity) {
		Intent intent = new Intent(activity, ForumLoginActivity.class);
		activity.startActivityForResult(intent, FORUM_LOGIN_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_login_register);
		getWindow().setBackgroundDrawableResource(R.drawable.background_default);

		if(savedInstanceState == null) {
			ForumLoginEmailFragment forumLoginEmailFragment = new ForumLoginEmailFragment();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.forum_login_fragment_container, forumLoginEmailFragment).commit();
		}
	}

	public void openLoginPasswordFragment(String email) {
		this.email = email;
		ForumLoginPasswordFragment forumloginPasswordFragment = new ForumLoginPasswordFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.forum_login_fragment_container, forumloginPasswordFragment)
				.addToBackStack(null)
				.commit();
	}

	public String getEmail() {
		return email;
	}

	public void finishLogin() {
		setResult(RESULT_OK);
		finish();
	}
}