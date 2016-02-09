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
import huitca1212.alubia13.utils.Dialogs;

public class AlubiaQuizMediumActivity extends AppCompatActivity {

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

		quizWelcome.setText(R.string.alubiaquiz_medium_level_subtitle);
		firstQuestion.setText(R.string.alubiaquiz_q1_m);
		firstOption.setText(R.string.alubiaquiz_r11_m);
		secondOption.setText(R.string.alubiaquiz_r12_m);
		thirdOption.setText(R.string.alubiaquiz_r13_m);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (thirdOption.isChecked()) {
					answer += 1;
					Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
				} else {
					showAlubiaQuizWrongAnswerDialog(1, 0);
				}
				firstOption.setChecked(true);
				firstQuestion.setText(R.string.alubiaquiz_q2_m);
				firstOption.setText(R.string.alubiaquiz_r21_m);
				secondOption.setText(R.string.alubiaquiz_r22_m);
				thirdOption.setText(R.string.alubiaquiz_r23_m);
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (firstOption.isChecked()) {
							answer += 1;
							Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
						} else {
							showAlubiaQuizWrongAnswerDialog(2, 0);
						}
						firstOption.setChecked(true);
						firstQuestion.setText(R.string.alubiaquiz_q3_m);
						firstOption.setText(R.string.alubiaquiz_r31_m);
						secondOption.setText(R.string.alubiaquiz_r32_m);
						thirdOption.setText(R.string.alubiaquiz_r33_m);
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (firstOption.isChecked()) {
									answer += 1;
									Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
								} else {
									showAlubiaQuizWrongAnswerDialog(3, 0);
								}
								firstOption.setChecked(true);
								firstQuestion.setText(R.string.alubiaquiz_q4_m);
								firstOption.setText(R.string.alubiaquiz_r41_m);
								secondOption.setText(R.string.alubiaquiz_r42_m);
								thirdOption.setText(R.string.alubiaquiz_r43_m);
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (secondOption.isChecked()) {
											answer += 1;
											Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
										} else {
											showAlubiaQuizWrongAnswerDialog(4, 0);
										}
										firstOption.setChecked(true);
										firstQuestion.setText(R.string.alubiaquiz_q5_m);
										firstOption.setText(R.string.alubiaquiz_r51_m);
										secondOption.setText(R.string.alubiaquiz_r52_m);
										thirdOption.setText(R.string.alubiaquiz_r53_m);
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (firstOption.isChecked()) {
													answer += 1;
													Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
												} else {
													showAlubiaQuizWrongAnswerDialog(5, 0);
												}
												firstOption.setChecked(true);
												firstQuestion.setText(R.string.alubiaquiz_q6_m);
												firstOption.setText(R.string.alubiaquiz_r61_m);
												secondOption.setText(R.string.alubiaquiz_r62_m);
												thirdOption.setText(R.string.alubiaquiz_r63_m);
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (secondOption.isChecked()) {
															answer += 1;
															Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
														} else {
															showAlubiaQuizWrongAnswerDialog(6, 0);
														}
														firstOption.setChecked(true);
														firstQuestion.setText(R.string.alubiaquiz_q7_m);
														firstOption.setText(R.string.alubiaquiz_r71_m);
														secondOption.setText(R.string.alubiaquiz_r72_m);
														thirdOption.setText(R.string.alubiaquiz_r73_m);
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (thirdOption.isChecked()) {
																	answer += 1;
																	Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
																} else {
																	showAlubiaQuizWrongAnswerDialog(7, 0);
																}
																firstOption.setChecked(true);
																firstQuestion.setText(R.string.alubiaquiz_q8_m);
																firstOption.setText(R.string.alubiaquiz_r81_m);
																secondOption.setText(R.string.alubiaquiz_r82_m);
																thirdOption.setText(R.string.alubiaquiz_r83_m);
																nextButton.setOnClickListener(new View.OnClickListener() {
																	public void onClick(View v) {
																		if (thirdOption.isChecked()) {
																			answer += 1;
																			Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
																		} else {
																			showAlubiaQuizWrongAnswerDialog(8, 0);
																		}
																		firstOption.setChecked(true);
																		firstQuestion.setText(R.string.alubiaquiz_q9_m);
																		firstOption.setText(R.string.alubiaquiz_r91_m);
																		secondOption.setText(R.string.alubiaquiz_r92_m);
																		thirdOption.setText(R.string.alubiaquiz_r93_m);
																		nextButton.setOnClickListener(new View.OnClickListener() {
																			public void onClick(View v) {
																				if (thirdOption.isChecked()) {
																					answer += 1;
																					Dialogs.showAlubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this);
																				} else {
																					showAlubiaQuizWrongAnswerDialog(9, 0);
																				}
																				firstOption.setChecked(true);
																				firstQuestion.setText(R.string.alubiaquiz_q10_m);
																				firstOption.setText(R.string.alubiaquiz_r101_m);
																				secondOption.setText(R.string.alubiaquiz_r102_m);
																				thirdOption.setText(R.string.alubiaquiz_r103_m);
																				nextButton.setOnClickListener(new View.OnClickListener() {
																					public void onClick(View v) {
																						if (firstOption.isChecked()) {
																							answer += 1;
																							Dialogs.showAlubiaQuizRightAnswerDialog(10, answer, AlubiaQuizMediumActivity.this);
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
	}

	private void showAlubiaQuizWrongAnswerDialog(int numeroPregunta, final int respuesta) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizMediumActivity.this);
		builder.setTitle(R.string.alubiaquiz_you_are_failed);
		switch (numeroPregunta) {
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
				builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(AlubiaQuizMediumActivity.this, AlubiaQuizSolutionActivity.class);
						String respuesta_s = Integer.toString(respuesta);
						Bundle b = new Bundle();
						b.putString("RESPUESTA", respuesta_s);
						intent.putExtras(b);
						AlubiaQuizMediumActivity.this.startActivity(intent);
						AlubiaQuizMediumActivity.this.finish();
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
