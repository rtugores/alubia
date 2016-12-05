package huitca1212.alubia13.ui.more.alubiaQuiz;

import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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

public class AlubiaQuizMediumActivity extends AppCompatActivity implements View.OnClickListener {

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
		Intent intent = new Intent(ctx, AlubiaQuizMediumActivity.class);
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


	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.next_button) {
			setNextQuestion();
		}
	}

	private void setInitialQuestion() {
		quizWelcome.setText(R.string.alubiaquiz_medium_level_subtitle);
		firstQuestion.setText(R.string.alubiaquiz_q1_m);
		firstOption.setText(R.string.alubiaquiz_r11_m);
		secondOption.setText(R.string.alubiaquiz_r12_m);
		thirdOption.setText(R.string.alubiaquiz_r13_m);
	}

	private void setNextQuestion() {
		switch (currentStatus) {
			case 0:
				updateScreen(R.string.alubiaquiz_q2_m, R.string.alubiaquiz_r21_m, R.string.alubiaquiz_r22_m, R.string.alubiaquiz_r23_m, 3);
				break;
			case 1:
				updateScreen(R.string.alubiaquiz_q3_m, R.string.alubiaquiz_r31_m, R.string.alubiaquiz_r32_m, R.string.alubiaquiz_r33_m, 1);
				break;
			case 2:
				updateScreen(R.string.alubiaquiz_q4_m, R.string.alubiaquiz_r41_m, R.string.alubiaquiz_r42_m, R.string.alubiaquiz_r43_m, 1);
				break;
			case 3:
				updateScreen(R.string.alubiaquiz_q5_m, R.string.alubiaquiz_r51_m, R.string.alubiaquiz_r52_m, R.string.alubiaquiz_r53_m, 2);
				break;
			case 4:
				updateScreen(R.string.alubiaquiz_q6_m, R.string.alubiaquiz_r61_m, R.string.alubiaquiz_r62_m, R.string.alubiaquiz_r63_m, 1);
				break;
			case 5:
				updateScreen(R.string.alubiaquiz_q7_m, R.string.alubiaquiz_r71_m, R.string.alubiaquiz_r72_m, R.string.alubiaquiz_r73_m, 2);
				break;
			case 6:
				updateScreen(R.string.alubiaquiz_q8_m, R.string.alubiaquiz_r81_m, R.string.alubiaquiz_r82_m, R.string.alubiaquiz_r83_m, 3);
				break;
			case 7:
				updateScreen(R.string.alubiaquiz_q9_m, R.string.alubiaquiz_r91_m, R.string.alubiaquiz_r92_m, R.string.alubiaquiz_r93_m, 3);
				break;
			case 8:
				updateScreen(R.string.alubiaquiz_q10_m, R.string.alubiaquiz_r101_m, R.string.alubiaquiz_r102_m, R.string.alubiaquiz_r103_m, 3);
				break;
			case 9:
				updateScreen(R.string.alubiaquiz_q10_m, R.string.alubiaquiz_r101_m, R.string.alubiaquiz_r102_m, R.string.alubiaquiz_r103_m, 1);
				break;
		}
	}

	private void updateScreen(int questionIdTextId, int firstOptionTextId, int secondOptionTextId, int thirdOptionTexId,
			int rightAnswer) {
		currentStatus += 1;
		if (firstOption.isChecked() && rightAnswer == 1 || secondOption.isChecked() && rightAnswer == 2
				|| thirdOption.isChecked() && rightAnswer == 3) {
			rightAnswers += 1;
			Dialogs.showAlubiaQuizRightAnswerDialog(currentStatus, rightAnswers, AlubiaQuizMediumActivity.this);
		} else {
			showAlubiaQuizWrongAnswerDialog(currentStatus);
		}
		firstOption.setChecked(true);
		firstQuestion.setText(questionIdTextId);
		firstOption.setText(firstOptionTextId);
		secondOption.setText(secondOptionTextId);
		thirdOption.setText(thirdOptionTexId);
	}

	private void showAlubiaQuizWrongAnswerDialog(int questionNumber) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizMediumActivity.this);
		builder.setTitle(R.string.alubiaquiz_you_are_failed);
		switch (questionNumber) {
			case 1:
				builder.setMessage(R.string.alubiaquiz_s1_m);
				break;
			case 2:
				builder.setMessage(R.string.alubiaquiz_s2_m);
				break;
			case 3:
				builder.setMessage(R.string.alubiaquiz_s3_m);
				break;
			case 4:
				builder.setMessage(R.string.alubiaquiz_s4_m);
				break;
			case 5:
				builder.setMessage(R.string.alubiaquiz_s5_m);
				break;
			case 6:
				builder.setMessage(R.string.alubiaquiz_s6_m);
				break;
			case 7:
				builder.setMessage(R.string.alubiaquiz_s7_m);
				break;
			case 8:
				builder.setMessage(R.string.alubiaquiz_s8_m);
				break;
			case 9:
				builder.setMessage(R.string.alubiaquiz_s9_m);
				break;
			case 10:
				builder.setMessage(R.string.alubiaquiz_s10_m);
				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(final DialogInterface dialogInterface) {
						openSolutionActivity();
					}
				});
				builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						openSolutionActivity();
					}
				});
				break;
		}
		if (questionNumber != 10) {
			builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		builder.create().show();
	}

	private void openSolutionActivity() {
		AlubiaQuizSolutionActivity.startActivity(AlubiaQuizMediumActivity.this, Integer.toString(rightAnswers));
		AlubiaQuizMediumActivity.this.finish();
	}
}