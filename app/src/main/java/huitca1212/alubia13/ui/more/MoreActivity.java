package huitca1212.alubia13.ui.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.more.alubiaQuiz.AlubiaQuizMenuActivity;
import huitca1212.alubia13.ui.more.settings.SettingsActivity;


public class MoreActivity extends AppCompatActivity implements View.OnClickListener {

	@Bind(R.id.alubia_quiz) Button alubiaQuiz;
	@Bind(R.id.about) Button about;
	@Bind(R.id.settings) Button settings;
	@Bind(R.id.contact) Button contact;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, MoreActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		ButterKnife.bind(this);

		alubiaQuiz.setOnClickListener(this);
		about.setOnClickListener(this);
		settings.setOnClickListener(this);
		contact.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.alubia_quiz) {
			AlubiaQuizMenuActivity.startActivity(MoreActivity.this);
		} else if (id == R.id.about) {
			FestivityInfoActivity.startActivity(MoreActivity.this);
		} else if (id == R.id.settings) {
			SettingsActivity.startActivityForResult(MoreActivity.this);
		} else if (id == R.id.contact) {
			ContactActivity.startActivity(MoreActivity.this);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (SettingsActivity.SETTINGS_ACTIVITY_REQUEST_CODE == requestCode) {
			if (RESULT_OK == resultCode) {
				finish();
			}
		}
	}
}