package huitca1212.alubia13.ui.more.alubiaQuiz;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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

public class AlubiaQuizEasyActivity extends AppCompatActivity {

	private int answer;
	@Bind(R.id.next_button) Button nextButton;
	@Bind(R.id.quiz_welcome) TextView quizWelcome;
	@Bind(R.id.first_question) TextView firstQuestion;
	@Bind(R.id.first_option) RadioButton firstOption;
	@Bind(R.id.second_option) RadioButton secondOption;
	@Bind(R.id.third_option) RadioButton thirdOption;
	@Bind(R.id.ad_view) AdView adView;

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

		quizWelcome.setText(R.string.alubiaquiz_easy_level_subtitle);
		firstQuestion.setText(R.string.alubiaquiz_q1_f);
		firstOption.setText(R.string.alubiaquiz_r11_f);
		secondOption.setText(R.string.alubiaquiz_r12_f);
		thirdOption.setText(R.string.alubiaquiz_r13_f);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (secondOption.isChecked()) {
					answer += 1;
					Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
				} else {
					showAlubiaQuizWrongAnswerDialog(1, 0);
				}
				firstOption.setChecked(true);
				firstQuestion.setText(R.string.alubiaquiz_q2_f);
				firstOption.setText(R.string.alubiaquiz_r21_f);
				secondOption.setText(R.string.alubiaquiz_r22_f);
				thirdOption.setText(R.string.alubiaquiz_r23_f);
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (firstOption.isChecked()) {
							answer += 1;
							Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
						} else {
							showAlubiaQuizWrongAnswerDialog(2, 0);
						}
						firstOption.setChecked(true);
						firstQuestion.setText(R.string.alubiaquiz_q3_f);
						firstOption.setText(R.string.alubiaquiz_r31_f);
						secondOption.setText(R.string.alubiaquiz_r32_f);
						thirdOption.setText(R.string.alubiaquiz_r33_f);
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (secondOption.isChecked()) {
									answer += 1;
									Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
								} else {
									showAlubiaQuizWrongAnswerDialog(3, 0);
								}
								firstOption.setChecked(true);
								firstQuestion.setText(R.string.alubiaquiz_q4_f);
								firstOption.setText(R.string.alubiaquiz_r41_f);
								secondOption.setText(R.string.alubiaquiz_r42_f);
								thirdOption.setText(R.string.alubiaquiz_r43_f);
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (secondOption.isChecked()) {
											answer += 1;
											Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
										} else {
											showAlubiaQuizWrongAnswerDialog(4, 0);
										}
										firstOption.setChecked(true);
										firstQuestion.setText(R.string.alubiaquiz_q5_f);
										firstOption.setText(R.string.alubiaquiz_r51_f);
										secondOption.setText(R.string.alubiaquiz_r52_f);
										thirdOption.setText(R.string.alubiaquiz_r53_f);
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (thirdOption.isChecked()) {
													answer += 1;
													Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
												} else {
													showAlubiaQuizWrongAnswerDialog(5, 0);
												}
												firstOption.setChecked(true);
												firstQuestion.setText(R.string.alubiaquiz_q6_f);
												firstOption.setText(R.string.alubiaquiz_r61_f);
												secondOption.setText(R.string.alubiaquiz_r62_f);
												thirdOption.setText(R.string.alubiaquiz_r63_f);
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (firstOption.isChecked()) {
															answer += 1;
															Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
														} else {
															showAlubiaQuizWrongAnswerDialog(6, 0);
														}
														firstOption.setChecked(true);
														firstQuestion.setText(R.string.alubiaquiz_q7_f);
														firstOption.setText(R.string.alubiaquiz_r71_f);
														secondOption.setText(R.string.alubiaquiz_r72_f);
														thirdOption.setText(R.string.alubiaquiz_r73_f);
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (secondOption.isChecked()) {
																	answer += 1;
																	Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
																} else {
																	showAlubiaQuizWrongAnswerDialog(7, 0);
																}
																firstOption.setChecked(true);
																firstQuestion.setText(R.string.alubiaquiz_q8_f);
																firstOption.setText(R.string.alubiaquiz_r81_f);
																secondOption.setText(R.string.alubiaquiz_r82_f);
																thirdOption.setText(R.string.alubiaquiz_r83_f);
																nextButton.setOnClickListener(new View.OnClickListener() {
																	public void onClick(View v) {
																		if (thirdOption.isChecked()) {
																			answer += 1;
																			Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
																		} else {
																			showAlubiaQuizWrongAnswerDialog(8, 0);
																		}
																		firstOption.setChecked(true);
																		firstQuestion.setText(R.string.alubiaquiz_q9_f);
																		firstOption.setText(R.string.alubiaquiz_r91_f);
																		secondOption.setText(R.string.alubiaquiz_r92_f);
																		thirdOption.setText(R.string.alubiaquiz_r93_f);
																		nextButton.setOnClickListener(new View.OnClickListener() {
																			public void onClick(View v) {
																				if (thirdOption.isChecked()) {
																					answer += 1;
																					Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this);
																				} else {
																					showAlubiaQuizWrongAnswerDialog(9, 0);
																				}
																				firstOption.setChecked(true);
																				firstQuestion.setText(R.string.alubiaquiz_q10_f);
																				firstOption.setText(R.string.alubiaquiz_r101_f);
																				secondOption.setText(R.string.alubiaquiz_r102_f);
																				thirdOption.setText(R.string.alubiaquiz_r103_f);
																				nextButton.setOnClickListener(new View.OnClickListener() {
																					public void onClick(View v) {
																						if (firstOption.isChecked()) {
																							answer += 1;
																							Dialogs.showAlubiaQuizRightAnswerDialog(10, answer, AlubiaQuizEasyActivity.this);
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
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizEasyActivity.this);
		builder.setTitle(R.string.alubiaquiz_you_are_failed);
		switch (numeroPregunta) {
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
				builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(AlubiaQuizEasyActivity.this, AlubiaQuizSolutionActivity.class);
						String respuesta_s = Integer.toString(respuesta);
						Bundle b = new Bundle();
						b.putString("RESPUESTA", respuesta_s);
						intent.putExtras(b);
						AlubiaQuizEasyActivity.this.startActivity(intent);
						AlubiaQuizEasyActivity.this.finish();
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
