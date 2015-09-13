package huitca1212.alubia13.mas.alubiaQuiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class AlubiaQuiz1 extends Activity {

    protected int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alubia_quiz);

        final Button boton1 = (Button) findViewById(R.id.button_sig);
        final TextView bienvenida_quiz = (TextView) findViewById(R.id.bienvenida_quiz);
        final TextView pregunta = (TextView) findViewById(R.id.primera_ask);
        final RadioButton opcion1 = (RadioButton) findViewById(R.id.radio0);
        final RadioButton opcion2 = (RadioButton) findViewById(R.id.radio1);
        final RadioButton opcion3 = (RadioButton) findViewById(R.id.radio2);
        bienvenida_quiz.setText(R.string.alubiaquiz1);
        pregunta.setText(R.string.q1_f);
        opcion1.setText(R.string.r11_f);
        opcion2.setText(R.string.r12_f);
        opcion3.setText(R.string.r13_f);

        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //PROCEDO CON LAS SIGUINTS PREGUNTAS
                if (opcion2.isChecked()) {
                    respuesta += 1; //respuestas a la primera pregunta
                    crearDialogoHasAcertado(0, 0).show();
                } else crearDialogoHasFallado1(1, 0).show();
                opcion1.setChecked(true);
                pregunta.setText(R.string.q2_f);
                opcion1.setText(R.string.r21_f);
                opcion2.setText(R.string.r22_f);
                opcion3.setText(R.string.r23_f);
                boton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (opcion1.isChecked()) {
                            respuesta += 1; //respuestas a la segunda pregunta
                            crearDialogoHasAcertado(0, 0).show();
                        } else crearDialogoHasFallado1(2, 0).show();
                        opcion1.setChecked(true);
                        pregunta.setText(R.string.q3_f);
                        opcion1.setText(R.string.r31_f);
                        opcion2.setText(R.string.r32_f);
                        opcion3.setText(R.string.r33_f);
                        boton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (opcion2.isChecked()) {
                                    respuesta += 1; //respuestas a la tercera pregunta
                                    crearDialogoHasAcertado(0, 0).show();
                                } else crearDialogoHasFallado1(3, 0).show();
                                opcion1.setChecked(true);
                                pregunta.setText(R.string.q4_f);
                                opcion1.setText(R.string.r41_f);
                                opcion2.setText(R.string.r42_f);
                                opcion3.setText(R.string.r43_f);
                                boton1.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        if (opcion2.isChecked()) {
                                            respuesta += 1;
                                            crearDialogoHasAcertado(0, 0).show();
                                        } else crearDialogoHasFallado1(4, 0).show();
                                        opcion1.setChecked(true);
                                        pregunta.setText(R.string.q5_f);
                                        opcion1.setText(R.string.r51_f);
                                        opcion2.setText(R.string.r52_f);
                                        opcion3.setText(R.string.r53_f);
                                        boton1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                if (opcion3.isChecked()) {
                                                    respuesta += 1;
                                                    crearDialogoHasAcertado(0, 0).show();
                                                } else crearDialogoHasFallado1(5, 0).show();
                                                opcion1.setChecked(true);
                                                pregunta.setText(R.string.q6_f);
                                                opcion1.setText(R.string.r61_f);
                                                opcion2.setText(R.string.r62_f);
                                                opcion3.setText(R.string.r63_f);
                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        if (opcion1.isChecked()) {
                                                            respuesta += 1;
                                                            crearDialogoHasAcertado(0, 0).show();
                                                        } else crearDialogoHasFallado1(6, 0).show();
                                                        opcion1.setChecked(true);
                                                        pregunta.setText(R.string.q7_f);
                                                        opcion1.setText(R.string.r71_f);
                                                        opcion2.setText(R.string.r72_f);
                                                        opcion3.setText(R.string.r73_f);
                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View v) {
                                                                if (opcion2.isChecked()) {
                                                                    respuesta += 1;
                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                } else
                                                                    crearDialogoHasFallado1(7, 0).show();
                                                                opcion1.setChecked(true);
                                                                pregunta.setText(R.string.q8_f);
                                                                opcion1.setText(R.string.r81_f);
                                                                opcion2.setText(R.string.r82_f);
                                                                opcion3.setText(R.string.r83_f);
                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                    public void onClick(View v) {
                                                                        if (opcion3.isChecked()) {
                                                                            respuesta += 1;
                                                                            crearDialogoHasAcertado(0, 0).show();
                                                                        } else
                                                                            crearDialogoHasFallado1(8, 0).show();
                                                                        opcion1.setChecked(true);
                                                                        pregunta.setText(R.string.q9_f);
                                                                        opcion1.setText(R.string.r91_f);
                                                                        opcion2.setText(R.string.r92_f);
                                                                        opcion3.setText(R.string.r93_f);
                                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                                            public void onClick(View v) {
                                                                                if (opcion3.isChecked()) {
                                                                                    respuesta += 1;
                                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                                } else
                                                                                    crearDialogoHasFallado1(9, 0).show();
                                                                                opcion1.setChecked(true);
                                                                                pregunta.setText(R.string.q10_f);
                                                                                opcion1.setText(R.string.r101_f);
                                                                                opcion2.setText(R.string.r102_f);
                                                                                opcion3.setText(R.string.r103_f);
                                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                                    public void onClick(View v) {
                                                                                        if (opcion1.isChecked()) {
                                                                                            respuesta += 1;
                                                                                            crearDialogoHasAcertado(10, respuesta).show();
                                                                                        } else
                                                                                            crearDialogoHasFallado1(10, respuesta).show();
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

    //===========================================================================================
    //DIALOGO PARA EL QUIZ ACIERTA PREGUNTA
    public Dialog crearDialogoHasAcertado(int numeroPregunta, final int respuesta) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz1.this);
        builder.setTitle(R.string.enhorabuena);
        builder.setMessage(R.string.hasAcertado);
        if (numeroPregunta == 10) {
            builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AlubiaQuiz1.this, Solucion.class);
                    String respuesta_s = Integer.toString(respuesta);
                    Bundle b = new Bundle();
                    b.putString("RESPUESTA", respuesta_s);
                    intent.putExtras(b);
                    AlubiaQuiz1.this.startActivity(intent);
                    AlubiaQuiz1.this.finish();
                }
            });
        } else {
            builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        return builder.create();
    }

    //DIALOGO PARA EL QUIZ FALLA PREGUNTA (1)
    public Dialog crearDialogoHasFallado1(int numeroPregunta, final int respuesta) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz1.this);
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
                        Intent intent = new Intent(AlubiaQuiz1.this, Solucion.class);
                        String respuesta_s = Integer.toString(respuesta);
                        Bundle b = new Bundle();
                        b.putString("RESPUESTA", respuesta_s);
                        intent.putExtras(b);
                        AlubiaQuiz1.this.startActivity(intent);
                        AlubiaQuiz1.this.finish();
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
