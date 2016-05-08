package huitca1212.alubia13.ui.more.alubiaQuiz;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
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

public class AlubiaQuizDifficultActivity extends AppCompatActivity {

	private int answer;
	@Bind(R.id.next_button) Button nextButton;
	@Bind(R.id.quiz_welcome) TextView quizWelcome;
	@Bind(R.id.first_question) TextView firstQuestion;
	@Bind(R.id.first_option) RadioButton firstOption;
	@Bind(R.id.second_option) RadioButton secondOption;
	@Bind(R.id.third_option) RadioButton thirdOption;
	@Bind(R.id.ad_view) AdView adView;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, AlubiaQuizDifficultActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alubiaquiz);
		ButterKnife.bind(this);

		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				adView.setVisibility(View.VISIBLE);
			}
		});

		quizWelcome.setText(R.string.alubiaquiz_hard_level_subtitle);
		firstQuestion.setText(R.string.alubiaquiz_q1_d);
		firstOption.setText(R.string.alubiaquiz_r11_d);
		secondOption.setText(R.string.alubiaquiz_r12_d);
		thirdOption.setText(R.string.alubiaquiz_r13_d);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (thirdOption.isChecked()) {
					answer += 1;
					Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
				} else {
					showAlubiaQuizWrongAnswerDialog(1, 0);
				}
				firstOption.setChecked(true);
				firstQuestion.setText(R.string.alubiaquiz_q2_d);
				firstOption.setText(R.string.alubiaquiz_r21_d);
				secondOption.setText(R.string.alubiaquiz_r22_d);
				thirdOption.setText(R.string.alubiaquiz_r23_d);
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (thirdOption.isChecked()) {
							answer += 1;
							Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
						} else {
							showAlubiaQuizWrongAnswerDialog(2, 0);
						}
						firstOption.setChecked(true);
						firstQuestion.setText(R.string.alubiaquiz_q3_d);
						firstOption.setText(R.string.alubiaquiz_r31_d);
						secondOption.setText(R.string.alubiaquiz_r32_d);
						thirdOption.setText(R.string.alubiaquiz_r33_d);
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (secondOption.isChecked()) {
									answer += 1;
									Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
								} else {
									showAlubiaQuizWrongAnswerDialog(3, 0);
								}
								firstOption.setChecked(true);
								firstQuestion.setText(R.string.alubiaquiz_q4_d);
								firstOption.setText(R.string.alubiaquiz_r41_d);
								secondOption.setText(R.string.alubiaquiz_r42_d);
								thirdOption.setText(R.string.alubiaquiz_r43_d);
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (thirdOption.isChecked()) {
											answer += 1;
											Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
										} else {
											showAlubiaQuizWrongAnswerDialog(4, 0);
										}
										firstOption.setChecked(true);
										firstQuestion.setText(R.string.alubiaquiz_q5_d);
										firstOption.setText(R.string.alubiaquiz_r51_d);
										secondOption.setText(R.string.alubiaquiz_r52_d);
										thirdOption.setText(R.string.alubiaquiz_r53_d);
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (firstOption.isChecked()) {
													answer += 1;
													Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
												} else {
													showAlubiaQuizWrongAnswerDialog(5, 0);
												}
												firstOption.setChecked(true);
												firstQuestion.setText(R.string.alubiaquiz_q6_d);
												firstOption.setText(R.string.alubiaquiz_r61_d);
												secondOption.setText(R.string.alubiaquiz_r62_d);
												thirdOption.setText(R.string.alubiaquiz_r63_d);
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (thirdOption.isChecked()) {
															answer += 1;
															Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
														} else {
															showAlubiaQuizWrongAnswerDialog(6, 0);
														}
														firstOption.setChecked(true);
														firstQuestion.setText(R.string.alubiaquiz_q7_d);
														firstOption.setText(R.string.alubiaquiz_r71_d);
														secondOption.setText(R.string.alubiaquiz_r72_d);
														thirdOption.setText(R.string.alubiaquiz_r73_d);
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (firstOption.isChecked()) {
																	answer += 1;
																	Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
																} else {
																	showAlubiaQuizWrongAnswerDialog(7, 0);
																}
																firstOption.setChecked(true);
																firstQuestion.setText(R.string.alubiaquiz_q8_d);
																firstOption.setText(R.string.alubiaquiz_r81_d);
																secondOption.setText(R.string.alubiaquiz_r82_d);
																thirdOption.setText(R.string.alubiaquiz_r83_d);
																nextButton.setOnClickListener(new View.OnClickListener() {
																	public void onClick(View v) {
																		if (firstOption.isChecked()) {
																			answer += 1;
																			Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
																		} else {
																			showAlubiaQuizWrongAnswerDialog(8, 0);
																		}
																		firstOption.setChecked(true);
																		firstQuestion.setText(R.string.alubiaquiz_q9_d);
																		firstOption.setText(R.string.alubiaquiz_r91_d);
																		secondOption.setText(R.string.alubiaquiz_r92_d);
																		thirdOption.setText(R.string.alubiaquiz_r93_d);
																		nextButton.setOnClickListener(new View.OnClickListener() {
																			public void onClick(View v) {
																				if (secondOption.isChecked()) {
																					answer += 1;
																					Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this);
																				} else {
																					showAlubiaQuizWrongAnswerDialog(9, 0);
																				}
																				firstOption.setChecked(true);
																				firstQuestion.setText(R.string.alubiaquiz_q10_d);
																				firstOption.setText(R.string.alubiaquiz_r101_d);
																				secondOption.setText(R.string.alubiaquiz_r102_d);
																				thirdOption.setText(R.string.alubiaquiz_r103_d);
																				nextButton.setOnClickListener(new View.OnClickListener() {
																					public void onClick(View v) {
																						if (thirdOption.isChecked()) {
																							answer += 1;
																							Dialogs.showAlubiaQuizRightAnswerDialog(10, answer, AlubiaQuizDifficultActivity.this);
																						} else {
																							showAlubiaQuizWrongAnswerDialog(10, answer);
																						}
																					}
																				});
																			}
																		});
																	}
																});
															}
														});
													}
												});
											}
										});
									}
								});
							}
						});
					}
				});
			}
		});
		AdsAndAnalytics.loadAds(adView);
	}

	private void showAlubiaQuizWrongAnswerDialog(int numeroPregunta, final int respuesta) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizDifficultActivity.this);
		builder.setTitle(R.string.alubiaquiz_you_are_failed);
		switch (numeroPregunta) {
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
				builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						AlubiaQuizSolutionActivity.startActivity(AlubiaQuizDifficultActivity.this, Integer.toString(respuesta));
						AlubiaQuizDifficultActivity.this.finish();
					}
				});
				break;
		}
		if (numeroPregunta != 10) {
			builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		builder.create().show();
	}
}
