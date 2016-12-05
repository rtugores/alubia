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

public class AlubiaQuizEasyActivity extends AppCompatActivity implements View.OnClickListener {

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
		Intent intent = new Intent(ctx, AlubiaQuizEasyActivity.class);
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
		quizWelcome.setText(R.string.alubiaquiz_easy_level_subtitle);
		firstQuestion.setText(R.string.alubiaquiz_q1_f);
		firstOption.setText(R.string.alubiaquiz_r11_f);
		secondOption.setText(R.string.alubiaquiz_r12_f);
		thirdOption.setText(R.string.alubiaquiz_r13_f);
	}

	private void setNextQuestion() {
		switch (currentStatus) {
			case 0:
				updateScreen(R.string.alubiaquiz_q2_f, R.string.alubiaquiz_r21_f, R.string.alubiaquiz_r22_f, R.string.alubiaquiz_r23_f, 2);
				break;
			case 1:
				updateScreen(R.string.alubiaquiz_q3_f, R.string.alubiaquiz_r31_f, R.string.alubiaquiz_r32_f, R.string.alubiaquiz_r33_f, 1);
				break;
			case 2:
				updateScreen(R.string.alubiaquiz_q4_f, R.string.alubiaquiz_r41_f, R.string.alubiaquiz_r42_f, R.string.alubiaquiz_r43_f, 2);
				break;
			case 3:
				updateScreen(R.string.alubiaquiz_q5_f, R.string.alubiaquiz_r51_f, R.string.alubiaquiz_r52_f, R.string.alubiaquiz_r53_f, 2);
				break;
			case 4:
				updateScreen(R.string.alubiaquiz_q6_f, R.string.alubiaquiz_r61_f, R.string.alubiaquiz_r62_f, R.string.alubiaquiz_r63_f, 3);
				break;
			case 5:
				updateScreen(R.string.alubiaquiz_q7_f, R.string.alubiaquiz_r71_f, R.string.alubiaquiz_r72_f, R.string.alubiaquiz_r73_f, 1);
				break;
			case 6:
				updateScreen(R.string.alubiaquiz_q8_f, R.string.alubiaquiz_r81_f, R.string.alubiaquiz_r82_f, R.string.alubiaquiz_r83_f, 2);
				break;
			case 7:
				updateScreen(R.string.alubiaquiz_q9_f, R.string.alubiaquiz_r91_f, R.string.alubiaquiz_r92_f, R.string.alubiaquiz_r93_f, 3);
				break;
			case 8:
				updateScreen(R.string.alubiaquiz_q10_f, R.string.alubiaquiz_r101_f, R.string.alubiaquiz_r102_f, R.string.alubiaquiz_r103_f, 3);
				break;
			case 9:
				updateScreen(R.string.alubiaquiz_q10_f, R.string.alubiaquiz_r101_f, R.string.alubiaquiz_r102_f, R.string.alubiaquiz_r103_f, 1);
				break;
		}
	}

	private void updateScreen(int questionIdTextId, int firstOptionTextId, int secondOptionTextId, int thirdOptionTexId, int rightAnswer) {
		currentStatus += 1;
		if (firstOption.isChecked() && rightAnswer == 1 || secondOption.isChecked() && rightAnswer == 2
				|| thirdOption.isChecked() && rightAnswer == 3) {
			rightAnswers += 1;
			Dialogs.showAlubiaQuizRightAnswerDialog(currentStatus, rightAnswers, AlubiaQuizEasyActivity.this);
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
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizEasyActivity.this);
		builder.setTitle(R.string.alubiaquiz_you_are_failed);
		switch (questionNumber) {
			case 1:
				builder.setMessage(R.string.alubiaquiz_s1_f);
				break;
			case 2:
				builder.setMessage(R.string.alubiaquiz_s2_f);
				break;
			case 3:
				builder.setMessage(R.string.alubiaquiz_s3_f);
				break;
			case 4:
				builder.setMessage(R.string.alubiaquiz_s4_f);
				break;
			case 5:
				builder.setMessage(R.string.alubiaquiz_s5_f);
				break;
			case 6:
				builder.setMessage(R.string.alubiaquiz_s6_f);
				break;
			case 7:
				builder.setMessage(R.string.alubiaquiz_s7_f);
				break;
			case 8:
				builder.setMessage(R.string.alubiaquiz_s8_f);
				break;
			case 9:
				builder.setMessage(R.string.alubiaquiz_s9_f);
				break;
			case 10:
				builder.setMessage(R.string.alubiaquiz_s10_f);
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
		AlubiaQuizSolutionActivity.startActivity(AlubiaQuizEasyActivity.this, Integer.toString(rightAnswers));
		AlubiaQuizEasyActivity.this.finish();
	}
}
