package huitca1212.alubia13.ui.more.alubiaQuiz;

import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.utils.AdsAndAnalytics;
import huitca1212.alubia13.utils.Dialogs;

public class AlubiaQuizDifficultActivity extends AppCompatActivity implements View.OnClickListener {

	@Bind(R.id.next_button) Button nextButton;
	@Bind(R.id.quiz_welcome) TextView quizWelcome;
	@Bind(R.id.first_question) TextView firstQuestion;
	@Bind(R.id.first_option) RadioButton firstOption;
	@Bind(R.id.second_option) RadioButton secondOption;
	@Bind(R.id.third_option) RadioButton thirdOption;
	@Bind(R.id.ad_view) AdView adView;

	private int rightAnswers;
	private int currentStatus;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, AlubiaQuizDifficultActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alubiaquiz);
		ButterKnife.bind(this);

		nextButton.setOnClickListener(this);
		AdsAndAnalytics.loadAds(adView);

		setInitialQuestion();
	}

	private void setInitialQuestion() {
		quizWelcome.setText(R.string.alubiaquiz_hard_level_subtitle);
		firstQuestion.setText(R.string.alubiaquiz_q1_d);
		firstOption.setText(R.string.alubiaquiz_r11_d);
		secondOption.setText(R.string.alubiaquiz_r12_d);
		thirdOption.setText(R.string.alubiaquiz_r13_d);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.next_button) {
			setNextQuestion();
		}
	}

	private void showAlubiaQuizWrongAnswerDialog(int questionNumber) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizDifficultActivity.this);
		builder.setTitle(R.string.alubiaquiz_you_are_failed);
		switch (questionNumber) {
			case 1:
				builder.setMessage(R.string.alubiaquiz_s1_d);
				break;
			case 2:
				builder.setMessage(R.string.alubiaquiz_s2_d);
				break;
			case 3:
				builder.setMessage(R.string.alubiaquiz_s3_d);
				break;
			case 4:
				builder.setMessage(R.string.alubiaquiz_s4_d);
				break;
			case 5:
				builder.setMessage(R.string.alubiaquiz_s5_d);
				break;
			case 6:
				builder.setMessage(R.string.alubiaquiz_s6_d);
				break;
			case 7:
				builder.setMessage(R.string.alubiaquiz_s7_d);
				break;
			case 8:
				builder.setMessage(R.string.alubiaquiz_s8_d);
				break;
			case 9:
				builder.setMessage(R.string.alubiaquiz_s9_d);
				break;
			case 10:
				builder.setMessage(R.string.alubiaquiz_s10_d);
				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(final DialogInterface dialogInterface) {
						openSolutionActivity();
					}
				});
				builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						openSolutionActivity();
					}
				});
				break;
		}
		if (questionNumber != 10) {
			builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		builder.create().show();
	}

	private void updateScreen(int questionIdTextId, int firstOptionTextId, int secondOptionTextId, int thirdOptionTexId, int rightAnswer) {
		currentStatus += 1;
		if (firstOption.isChecked() && rightAnswer == 1 || secondOption.isChecked() && rightAnswer == 2
				|| thirdOption.isChecked() && rightAnswer == 3) {
			rightAnswers += 1;
			Dialogs.showAlubiaQuizRightAnswerDialog(currentStatus, rightAnswers, AlubiaQuizDifficultActivity.this);
		} else {
			showAlubiaQuizWrongAnswerDialog(currentStatus);
		}
		firstOption.setChecked(true);
		firstQuestion.setText(questionIdTextId);
		firstOption.setText(firstOptionTextId);
		secondOption.setText(secondOptionTextId);
		thirdOption.setText(thirdOptionTexId);
	}

	private void setNextQuestion() {
		switch (currentStatus) {
			case 0:
				updateScreen(R.string.alubiaquiz_q2_d, R.string.alubiaquiz_r21_d, R.string.alubiaquiz_r22_d, R.string.alubiaquiz_r23_d, 3);
				break;
			case 1:
				updateScreen(R.string.alubiaquiz_q3_d, R.string.alubiaquiz_r31_d, R.string.alubiaquiz_r32_d, R.string.alubiaquiz_r33_d, 3);
				break;
			case 2:
				updateScreen(R.string.alubiaquiz_q4_d, R.string.alubiaquiz_r41_d, R.string.alubiaquiz_r42_d, R.string.alubiaquiz_r43_d, 2);
				break;
			case 3:
				updateScreen(R.string.alubiaquiz_q5_d, R.string.alubiaquiz_r51_d, R.string.alubiaquiz_r52_d, R.string.alubiaquiz_r53_d, 3);
				break;
			case 4:
				updateScreen(R.string.alubiaquiz_q6_d, R.string.alubiaquiz_r61_d, R.string.alubiaquiz_r62_d, R.string.alubiaquiz_r63_d, 1);
				break;
			case 5:
				updateScreen(R.string.alubiaquiz_q7_d, R.string.alubiaquiz_r71_d, R.string.alubiaquiz_r72_d, R.string.alubiaquiz_r73_d, 3);
				break;
			case 6:
				updateScreen(R.string.alubiaquiz_q8_d, R.string.alubiaquiz_r81_d, R.string.alubiaquiz_r82_d, R.string.alubiaquiz_r83_d, 1);
				break;
			case 7:
				updateScreen(R.string.alubiaquiz_q9_d, R.string.alubiaquiz_r91_d, R.string.alubiaquiz_r92_d, R.string.alubiaquiz_r93_d, 1);
				break;
			case 8:
				updateScreen(R.string.alubiaquiz_q10_d, R.string.alubiaquiz_r101_d, R.string.alubiaquiz_r102_d, R.string.alubiaquiz_r103_d, 2);
				break;
			case 9:
				updateScreen(R.string.alubiaquiz_q10_d, R.string.alubiaquiz_r101_d, R.string.alubiaquiz_r102_d, R.string.alubiaquiz_r103_d, 3);
				break;
		}
	}

	private void openSolutionActivity() {
		AlubiaQuizSolutionActivity.startActivity(AlubiaQuizDifficultActivity.this, Integer.toString(rightAnswers));
		AlubiaQuizDifficultActivity.this.finish();
	}
}
