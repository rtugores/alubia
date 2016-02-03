package huitca1212.alubia13.ui.forum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;


public class ForumMenuActivity extends AppCompatActivity implements View.OnClickListener {

	public static Activity forumMenuActivity;

	@Bind(R.id.register) Button register;
	@Bind(R.id.login) Button login;
	@Bind(R.id.invited) Button invited;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_menu);
		forumMenuActivity = this;
		ButterKnife.bind(this);

		register.setOnClickListener(this);
		login.setOnClickListener(this);
		invited.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = null;
		if (id == R.id.register) {
			intent = new Intent(ForumMenuActivity.this, ForumRegisterUserActivity.class);
		} else if (id == R.id.login) {
			intent = new Intent(ForumMenuActivity.this, ForumLoginEmailActivity.class);
		} else if (id == R.id.invited) {
			intent = new Intent(ForumMenuActivity.this, ForumActivity.class);
			intent.putExtra(ForumActivity.INVITED_USER, "OK");
		}
		startActivity(intent);
	}

	public void onDestroy(){
		forumMenuActivity = null;
		super.onDestroy();
	}
}