package huitca1212.alubia13.ui.more.alubiaQuiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;


public class AlubiaQuizMenuActivity extends AppCompatActivity implements View.OnClickListener {
	@Bind(R.id.go_easy_button) Button goEasyButton;
	@Bind(R.id.go_medium_button) Button goMediumButton;
	@Bind(R.id.go_hard_button) Button goHardButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alubiaquiz_menu);
		ButterKnife.bind(this);

		goEasyButton.setOnClickListener(this);
		goMediumButton.setOnClickListener(this);
		goHardButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = null;
		if (id == R.id.go_easy_button) {
			intent = new Intent(AlubiaQuizMenuActivity.this, AlubiaQuizEasyActivity.class);
		} else if (id == R.id.go_medium_button) {
			intent = new Intent(AlubiaQuizMenuActivity.this, AlubiaQuizMediumActivity.class);
		} else if (id == R.id.go_hard_button) {
			intent = new Intent(AlubiaQuizMenuActivity.this, AlubiaQuizDifficultActivity.class);
		}
		startActivity(intent);
		finish();
	}
}