package huitca1212.alubia13.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;


public class ForumMenuActivity extends AppCompatActivity implements View.OnClickListener {

	@Bind(R.id.register) Button register;
	@Bind(R.id.login) Button login;
	@Bind(R.id.invited) Button invited;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, ForumMenuActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_menu);
		ButterKnife.bind(this);

		register.setOnClickListener(this);
		login.setOnClickListener(this);
		invited.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.register) {
			ForumRegisterUserActivity.startActivity(ForumMenuActivity.this);
		} else if (id == R.id.login) {
			ForumLoginEmailActivity.startActivityForResult(ForumMenuActivity.this);
		} else if (id == R.id.invited) {
			ForumActivity.startActivity(ForumMenuActivity.this, "OK");
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (ForumLoginEmailActivity.FORUM_LOGIN_EMAIL_ACTIVITY_REQUEST_CODE == requestCode) {
			if (RESULT_OK == resultCode) {
				if (data != null) {
					String email = (String)data.getSerializableExtra("email");
					if (email != null) {
						ForumLoginPasswordActivity.startActivityForResult(ForumMenuActivity.this, email);
					}
				}
			}
		} else if (ForumLoginPasswordActivity.FORUM_LOGIN_PASSWORD_ACTIVITY_REQUEST_CODE == requestCode) {
			if (RESULT_OK == resultCode) {
				ForumActivity.startActivity(ForumMenuActivity.this);
				finish();
			} else if (RESULT_CANCELED == resultCode) {
				ForumLoginEmailActivity.startActivityForResult(ForumMenuActivity.this);
			}
		}
	}
}