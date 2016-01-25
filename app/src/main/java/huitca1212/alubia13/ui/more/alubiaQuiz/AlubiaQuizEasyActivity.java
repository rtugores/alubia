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

public class AlubiaQuizEasyActivity extends AppCompatActivity {

	private int respuesta;
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

		quizWelcome.setText(R.string.alubiaquiz1);
		firstQuestion.setText(R.string.q1_f);
		firstOption.setText(R.string.r11_f);
		secondOption.setText(R.string.r12_f);
		thirdOption.setText(R.string.r13_f);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (secondOption.isChecked()) {
					respuesta += 1;
					Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
				} else {
					crearDialogoHasFallado1(1, 0).show();
				}
				firstOption.setChecked(true);
				firstQuestion.setText(R.string.q2_f);
				firstOption.setText(R.string.r21_f);
				secondOption.setText(R.string.r22_f);
				thirdOption.setText(R.string.r23_f);
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (firstOption.isChecked()) {
							respuesta += 1;
							Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
						} else {
							crearDialogoHasFallado1(2, 0).show();
						}
						firstOption.setChecked(true);
						firstQuestion.setText(R.string.q3_f);
						firstOption.setText(R.string.r31_f);
						secondOption.setText(R.string.r32_f);
						thirdOption.setText(R.string.r33_f);
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (secondOption.isChecked()) {
									respuesta += 1;
									Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
								} else {
									crearDialogoHasFallado1(3, 0).show();
								}
								firstOption.setChecked(true);
								firstQuestion.setText(R.string.q4_f);
								firstOption.setText(R.string.r41_f);
								secondOption.setText(R.string.r42_f);
								thirdOption.setText(R.string.r43_f);
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (secondOption.isChecked()) {
											respuesta += 1;
											Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
										} else {
											crearDialogoHasFallado1(4, 0).show();
										}
										firstOption.setChecked(true);
										firstQuestion.setText(R.string.q5_f);
										firstOption.setText(R.string.r51_f);
										secondOption.setText(R.string.r52_f);
										thirdOption.setText(R.string.r53_f);
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (thirdOption.isChecked()) {
													respuesta += 1;
													Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
												} else {
													crearDialogoHasFallado1(5, 0).show();
												}
												firstOption.setChecked(true);
												firstQuestion.setText(R.string.q6_f);
												firstOption.setText(R.string.r61_f);
												secondOption.setText(R.string.r62_f);
												thirdOption.setText(R.string.r63_f);
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (firstOption.isChecked()) {
															respuesta += 1;
															Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
														} else {
															crearDialogoHasFallado1(6, 0).show();
														}
														firstOption.setChecked(true);
														firstQuestion.setText(R.string.q7_f);
														firstOption.setText(R.string.r71_f);
														secondOption.setText(R.string.r72_f);
														thirdOption.setText(R.string.r73_f);
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (secondOption.isChecked()) {
																	respuesta += 1;
																	Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
																} else {
																	crearDialogoHasFallado1(7, 0).show();
																}
																firstOption.setChecked(true);
																firstQuestion.setText(R.string.q8_f);
																firstOption.setText(R.string.r81_f);
																secondOption.setText(R.string.r82_f);
																thirdOption.setText(R.string.r83_f);
																nextButton.setOnClickListener(new View.OnClickListener() {
																	public void onClick(View v) {
																		if (thirdOption.isChecked()) {
																			respuesta += 1;
																			Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
																		} else {
																			crearDialogoHasFallado1(8, 0).show();
																		}
																		firstOption.setChecked(true);
																		firstQuestion.setText(R.string.q9_f);
																		firstOption.setText(R.string.r91_f);
																		secondOption.setText(R.string.r92_f);
																		thirdOption.setText(R.string.r93_f);
																		nextButton.setOnClickListener(new View.OnClickListener() {
																			public void onClick(View v) {
																				if (thirdOption.isChecked()) {
																					respuesta += 1;
																					Dialogs.alubiaQuizRightAnswerDialog(0, 0, AlubiaQuizEasyActivity.this).show();
																				} else {
																					crearDialogoHasFallado1(9, 0).show();
																				}
																				firstOption.setChecked(true);
																				firstQuestion.setText(R.string.q10_f);
																				firstOption.setText(R.string.r101_f);
																				secondOption.setText(R.string.r102_f);
																				thirdOption.setText(R.string.r103_f);
																				nextButton.setOnClickListener(new View.OnClickListener() {
																					public void onClick(View v) {
																						if (firstOption.isChecked()) {
																							respuesta += 1;
																							Dialogs.alubiaQuizRightAnswerDialog(10, respuesta, AlubiaQuizEasyActivity.this).show();
																						} else {
																							crearDialogoHasFallado1(10, respuesta).show();
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

	private Dialog crearDialogoHasFallado1(int numeroPregunta, final int respuesta) {
		AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuizEasyActivity.this);
		builder.setTitle(R.string.malaSuerte);
		switch (numeroPregunta) {
			case 1:
				builder.setMessage(R.string.s1_f);
				break;
			case 2:
				builder.setMessage(R.string.s2_f);
				break;
			case 3:
				builder.setMessage(R.string.s3_f);
				break;
			case 4:
				builder.setMessage(R.string.s4_f);
				break;
			case 5:
				builder.setMessage(R.string.s5_f);
				break;
			case 6:
				builder.setMessage(R.string.s6_f);
				break;
			case 7:
				builder.setMessage(R.string.s7_f);
				break;
			case 8:
				builder.setMessage(R.string.s8_f);
				break;
			case 9:
				builder.setMessage(R.string.s9_f);
				break;
			case 10:
				builder.setMessage(R.string.s10_f);
				builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
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
			builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		return builder.create();
	}
}
