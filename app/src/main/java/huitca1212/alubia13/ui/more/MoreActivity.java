package huitca1212.alubia13.ui.more;

import android.app.Activity;
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

	public static Activity moreActivity;
	@Bind(R.id.alubia_quiz) Button alubiaQuiz;
	@Bind(R.id.about) Button about;
	@Bind(R.id.settings) Button settings;
	@Bind(R.id.contact) Button contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		ButterKnife.bind(this);

		moreActivity = this;
		alubiaQuiz.setOnClickListener(this);
		about.setOnClickListener(this);
		settings.setOnClickListener(this);
		contact.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = null;
		if (id == R.id.alubia_quiz) {
			intent = new Intent(this, AlubiaQuizMenuActivity.class);
		} else if (id == R.id.about) {
			intent = new Intent(this, FestivityInfoActivity.class);
		} else if (id == R.id.settings) {
			intent = new Intent(this, SettingsActivity.class);
		} else if (id == R.id.contact) {
			intent = new Intent(this, ContactActivity.class);
		}
		startActivity(intent);
	}

	public void onDestroy(){
		moreActivity = null;
		super.onDestroy();
	}
}