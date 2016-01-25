package huitca1212.alubia13.ui.more.alubiaQuiz;

import android.app.Dialog;
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

	private  int answer;
	@Bind(R.id.next_button) Button nextButton;
	@Bind(R.id.quiz_welcome) TextView quizWelcome;
	@Bind(R.id.first_question) TextView firstQuestion;
	@Bind(R.id.first_option) RadioButton firstOption;
	@Bind(R.id.second_option) RadioButton secondOption;
	@Bind(R.id.third_option) RadioButton thirdOption;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alubiaquiz);
		ButterKnife.bind(this);

		quizWelcome.setText(R.string.alubiaquiz2);
		firstQuestion.setText(R.string.q1_m);
		firstOption.setText(R.string.r11_m);
		secondOption.setText(R.string.r12_m);
		thirdOption.setText(R.string.r13_m);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (thirdOption.isChecked()) {
					answer += 1;
					Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
				} else {
					crearDialogoHasFallado2(1, 0).show();
				}
				firstOption.setChecked(true);
				firstQuestion.setText(R.string.q2_m);
				firstOption.setText(R.string.r21_m);
				secondOption.setText(R.string.r22_m);
				thirdOption.setText(R.string.r23_m);
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (firstOption.isChecked()) {
							answer += 1;
							Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
						} else {
							crearDialogoHasFallado2(2, 0).show();
						}
						firstOption.setChecked(true);
						firstQuestion.setText(R.string.q3_m);
						firstOption.setText(R.string.r31_m);
						secondOption.setText(R.string.r32_m);
						thirdOption.setText(R.string.r33_m);
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (firstOption.isChecked()) {
									answer += 1;
									Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
								} else {
									crearDialogoHasFallado2(3, 0).show();
								}
								firstOption.setChecked(true);
								firstQuestion.setText(R.string.q4_m);
								firstOption.setText(R.string.r41_m);
								secondOption.setText(R.string.r42_m);
								thirdOption.setText(R.string.r43_m);
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (secondOption.isChecked()) {
											answer += 1;
											Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
										} else {
											crearDialogoHasFallado2(4, 0).show();
										}
										firstOption.setChecked(true);
										firstQuestion.setText(R.string.q5_m);
										firstOption.setText(R.string.r51_m);
										secondOption.setText(R.string.r52_m);
										thirdOption.setText(R.string.r53_m);
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (firstOption.isChecked()) {
													answer += 1;
													Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
												} else {
													crearDialogoHasFallado2(5, 0).show();
												}
												firstOption.setChecked(true);
												firstQuestion.setText(R.string.q6_m);
												firstOption.setText(R.string.r61_m);
												secondOption.setText(R.string.r62_m);
												thirdOption.setText(R.string.r63_m);
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (secondOption.isChecked()) {
															answer += 1;
															Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
														} else {
															crearDialogoHasFallado2(6, 0).show();
														}
														firstOption.setChecked(true);
														firstQuestion.setText(R.string.q7_m);
														firstOption.setText(R.string.r71_m);
														secondOption.setText(R.string.r72_m);
														thirdOption.setText(R.string.r73_m);
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (thirdOption.isChecked()) {
																	answer += 1;
																	Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
																} else {
																	crearDialogoHasFallado2(7, 0).show();
																}
																firstOption.setChecked(true);
																firstQuestion.setText(R.string.q8_m);
																firstOption.setText(R.string.r81_m);
																secondOption.setText(R.string.r82_m);
																thirdOption.setText(R.string.r83_m);
																nextButton.setOnClickListener(new View.OnClickListener() {
																	public void onClick(View v) {
																		if (thirdOption.isChecked()) {
																			answer += 1;
																			Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
																		} else {
																			crearDialogoHasFallado2(8, 0).show();
																		}
																		firstOption.setChecked(true);
																		firstQuestion.setText(R.string.q9_m);
																		firstOption.setText(R.string.r91_m);
																		secondOption.setText(R.string.r92_m);
																		thirdOption.setText(R.string.r93_m);
																		nextButton.setOnClickListener(new View.OnClickListener() {
																			public void onClick(View v) {
																				if (thirdOption.isChecked()) {
																					answer += 1;
																					Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizMediumActivity.this).show();
																				} else {
																					crearDialogoHasFallado2(9, 0).show();
																				}
																				firstOption.setChecked(true);
																				firstQuestion.setText(R.string.q10_m);
																				firstOption.setText(R.string.r101_m);
																				secondOption.setText(R.string.r102_m);
																				thirdOption.setText(R.string.r103_m);
																				nextButton.setOnClickListener(new View.OnClickListener() {
																					public void onClick(View v) {
																						if (firstOption.isChecked()) {
																							answer += 1;
																							Dialogs.alubiaQuizRightAnswerDialog(10, answer, AlubiaQuizMediumActivity.this)
																									.show();
																						} else {
																							crearDialogoHasFallado2(10, answer).show();
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

	private Dialog crearDialogoHasFallado2(int numeroPregunta, final int respuesta) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizMediumActivity.this);
		builder.setTitle(R.string.malaSuerte);
		switch (numeroPregunta) {
			case 1:
				builder.setMessage(R.string.s1_m);
				break;
			case 2:
				builder.setMessage(R.string.s2_m);
				break;
			case 3:
				builder.setMessage(R.string.s3_m);
				break;
			case 4:
				builder.setMessage(R.string.s4_m);
				break;
			case 5:
				builder.setMessage(R.string.s5_m);
				break;
			case 6:
				builder.setMessage(R.string.s6_m);
				break;
			case 7:
				builder.setMessage(R.string.s7_m);
				break;
			case 8:
				builder.setMessage(R.string.s8_m);
				break;
			case 9:
				builder.setMessage(R.string.s9_m);
				break;
			case 10:
				builder.setMessage(R.string.s10_m);
				builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
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
			builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		return builder.create();
	}
}
