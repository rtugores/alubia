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

public class AlubiaQuizDifficultActivity extends AppCompatActivity {

	private int answer;
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

		quizWelcome.setText(R.string.alubiaquiz3);
		firstQuestion.setText(R.string.q1_d);
		firstOption.setText(R.string.r11_d);
		secondOption.setText(R.string.r12_d);
		thirdOption.setText(R.string.r13_d);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (thirdOption.isChecked()) {
					answer += 1;
					Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
				} else {
					crearDialogoHasFallado3(1, 0).show();
				}
				firstOption.setChecked(true);
				firstQuestion.setText(R.string.q2_d);
				firstOption.setText(R.string.r21_d);
				secondOption.setText(R.string.r22_d);
				thirdOption.setText(R.string.r23_d);
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (thirdOption.isChecked()) {
							answer += 1;
							Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
						} else {
							crearDialogoHasFallado3(2, 0).show();
						}
						firstOption.setChecked(true);
						firstQuestion.setText(R.string.q3_d);
						firstOption.setText(R.string.r31_d);
						secondOption.setText(R.string.r32_d);
						thirdOption.setText(R.string.r33_d);
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (secondOption.isChecked()) {
									answer += 1;
									Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
								} else {
									crearDialogoHasFallado3(3, 0).show();
								}
								firstOption.setChecked(true);
								firstQuestion.setText(R.string.q4_d);
								firstOption.setText(R.string.r41_d);
								secondOption.setText(R.string.r42_d);
								thirdOption.setText(R.string.r43_d);
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (thirdOption.isChecked()) {
											answer += 1;
											Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
										} else {
											crearDialogoHasFallado3(4, 0).show();
										}
										firstOption.setChecked(true);
										firstQuestion.setText(R.string.q5_d);
										firstOption.setText(R.string.r51_d);
										secondOption.setText(R.string.r52_d);
										thirdOption.setText(R.string.r53_d);
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (firstOption.isChecked()) {
													answer += 1;
													Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
												} else {
													crearDialogoHasFallado3(5, 0).show();
												}
												firstOption.setChecked(true);
												firstQuestion.setText(R.string.q6_d);
												firstOption.setText(R.string.r61_d);
												secondOption.setText(R.string.r62_d);
												thirdOption.setText(R.string.r63_d);
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (thirdOption.isChecked()) {
															answer += 1;
															Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
														} else {
															crearDialogoHasFallado3(6, 0).show();
														}
														firstOption.setChecked(true);
														firstQuestion.setText(R.string.q7_d);
														firstOption.setText(R.string.r71_d);
														secondOption.setText(R.string.r72_d);
														thirdOption.setText(R.string.r73_d);
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (firstOption.isChecked()) {
																	answer += 1;
																	Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
																} else {
																	crearDialogoHasFallado3(7, 0).show();
																}
																firstOption.setChecked(true);
																firstQuestion.setText(R.string.q8_d);
																firstOption.setText(R.string.r81_d);
																secondOption.setText(R.string.r82_d);
																thirdOption.setText(R.string.r83_d);
																nextButton.setOnClickListener(new View.OnClickListener() {
																	public void onClick(View v) {
																		if (firstOption.isChecked()) {
																			answer += 1;
																			Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
																		} else {
																			crearDialogoHasFallado3(8, 0).show();
																		}
																		firstOption.setChecked(true);
																		firstQuestion.setText(R.string.q9_d);
																		firstOption.setText(R.string.r91_d);
																		secondOption.setText(R.string.r92_d);
																		thirdOption.setText(R.string.r93_d);
																		nextButton.setOnClickListener(new View.OnClickListener() {
																			public void onClick(View v) {
																				if (secondOption.isChecked()) {
																					answer += 1;
																					Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizDifficultActivity.this).show();
																				} else {
																					crearDialogoHasFallado3(9, 0).show();
																				}
																				firstOption.setChecked(true);
																				firstQuestion.setText(R.string.q10_d);
																				firstOption.setText(R.string.r101_d);
																				secondOption.setText(R.string.r102_d);
																				thirdOption.setText(R.string.r103_d);
																				nextButton.setOnClickListener(new View.OnClickListener() {
																					public void onClick(View v) {
																						if (thirdOption.isChecked()) {
																							answer += 1;
																							Dialogs.alubiaQuizRightAnswerDialog(10, answer, AlubiaQuizDifficultActivity.this)
																									.show();
																						} else {
																							crearDialogoHasFallado3(10, answer).show();
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

	private  Dialog crearDialogoHasFallado3(int numeroPregunta, final int respuesta) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizDifficultActivity.this);
		builder.setTitle(R.string.malaSuerte);
		switch (numeroPregunta) {
			case 1:
				builder.setMessage(R.string.s1_d);
				break;
			case 2:
				builder.setMessage(R.string.s2_d);
				break;
			case 3:
				builder.setMessage(R.string.s3_d);
				break;
			case 4:
				builder.setMessage(R.string.s4_d);
				break;
			case 5:
				builder.setMessage(R.string.s5_d);
				break;
			case 6:
				builder.setMessage(R.string.s6_d);
				break;
			case 7:
				builder.setMessage(R.string.s7_d);
				break;
			case 8:
				builder.setMessage(R.string.s8_d);
				break;
			case 9:
				builder.setMessage(R.string.s9_d);
				break;
			case 10:
				builder.setMessage(R.string.s10_d);
				builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(AlubiaQuizDifficultActivity.this, AlubiaQuizSolutionActivity.class);
						String respuesta_s = Integer.toString(respuesta);
						Bundle b = new Bundle();
						b.putString("RESPUESTA", respuesta_s);
						intent.putExtras(b);
						AlubiaQuizDifficultActivity.this.startActivity(intent);
						AlubiaQuizDifficultActivity.this.finish();
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
