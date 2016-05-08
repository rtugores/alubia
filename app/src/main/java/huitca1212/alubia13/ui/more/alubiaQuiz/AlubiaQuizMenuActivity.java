package huitca1212.alubia13.ui.more.alubiaQuiz;

import android.content.Context;
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

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, AlubiaQuizMenuActivity.class);
		ctx.startActivity(intent);
	}

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
		if (id == R.id.go_easy_button) {
			AlubiaQuizEasyActivity.startActivity(AlubiaQuizMenuActivity.this);
		} else if (id == R.id.go_medium_button) {
			AlubiaQuizMediumActivity.startActivity((AlubiaQuizMenuActivity.this));
		} else if (id == R.id.go_hard_button) {
			AlubiaQuizDifficultActivity.startActivity(AlubiaQuizMenuActivity.this);
		}
		finish();
	}
}