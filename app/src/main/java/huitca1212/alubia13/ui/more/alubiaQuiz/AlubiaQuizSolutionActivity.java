package huitca1212.alubia13.ui.more.alubiaQuiz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;


public class AlubiaQuizSolutionActivity extends AppCompatActivity implements View.OnClickListener {

	private final static int NUM_QUESTIONS_FOR_ACCEPTANCE = 4;
	@Bind(R.id.text_answers) TextView answersTextView;
	@Bind(R.id.text_solution) TextView solutionTextView;
	@Bind(R.id.button_finish) Button buttonFinish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alubiaquiz_solution);
		ButterKnife.bind(this);

		buttonFinish.setOnClickListener(this);

		Bundle bundle = this.getIntent().getExtras();
		String resultString = bundle.getString("RESPUESTA");
		int resultInteger = 0;
		if (resultString != null) {
			resultInteger = Integer.parseInt(resultString);
		}
		answersTextView.setText("¡Has acertado " + Integer.toString(resultInteger) + " de 10!");
		MediaPlayer mpRes;

		if (resultInteger <= NUM_QUESTIONS_FOR_ACCEPTANCE) {
			solutionTextView.setText(R.string.pocasRespuestas);
			mpRes = MediaPlayer.create(getApplicationContext(), R.raw.abucheo);
			mpRes.start();
		} else {
			solutionTextView.setText(R.string.muchasRespuestas);
			mpRes = MediaPlayer.create(getApplicationContext(), R.raw.aplausos);
			mpRes.start();
		}

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.button_finish) {
			finish();
		}
	}
}