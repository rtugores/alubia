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

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;

public class AlubiaQuiz2 extends Activity {

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
        bienvenida_quiz.setText(R.string.alubiaquiz2);
        pregunta.setText(R.string.q1_m);
        opcion1.setText(R.string.r11_m);
        opcion2.setText(R.string.r12_m);
        opcion3.setText(R.string.r13_m);

        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //PROCEDO CON LAS SIGUIENTES PREGUNTAS
                if (opcion3.isChecked()) {
                    respuesta += 1; //respuestas a la primera pregunta
                    crearDialogoHasAcertado(0, 0).show();
                } else crearDialogoHasFallado2(1, 0).show();
                opcion1.setChecked(true);
                pregunta.setText(R.string.q2_m);
                opcion1.setText(R.string.r21_m);
                opcion2.setText(R.string.r22_m);
                opcion3.setText(R.string.r23_m);
                boton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (opcion1.isChecked()) {
                            respuesta += 1; //respuestas a la segunda pregunta
                            crearDialogoHasAcertado(0, 0).show();
                        } else crearDialogoHasFallado2(2, 0).show();
                        opcion1.setChecked(true);
                        pregunta.setText(R.string.q3_m);
                        opcion1.setText(R.string.r31_m);
                        opcion2.setText(R.string.r32_m);
                        opcion3.setText(R.string.r33_m);
                        boton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (opcion1.isChecked()) {
                                    respuesta += 1; //respuestas a la tercera pregunta
                                    crearDialogoHasAcertado(0, 0).show();
                                } else crearDialogoHasFallado2(3, 0).show();
                                opcion1.setChecked(true);
                                pregunta.setText(R.string.q4_m);
                                opcion1.setText(R.string.r41_m);
                                opcion2.setText(R.string.r42_m);
                                opcion3.setText(R.string.r43_m);
                                boton1.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        if (opcion2.isChecked()) {
                                            respuesta += 1;
                                            crearDialogoHasAcertado(0, 0).show();
                                        } else crearDialogoHasFallado2(4, 0).show();
                                        opcion1.setChecked(true);
                                        pregunta.setText(R.string.q5_m);
                                        opcion1.setText(R.string.r51_m);
                                        opcion2.setText(R.string.r52_m);
                                        opcion3.setText(R.string.r53_m);
                                        boton1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                if (opcion1.isChecked()) {
                                                    respuesta += 1;
                                                    crearDialogoHasAcertado(0, 0).show();
                                                } else crearDialogoHasFallado2(5, 0).show();
                                                opcion1.setChecked(true);
                                                pregunta.setText(R.string.q6_m);
                                                opcion1.setText(R.string.r61_m);
                                                opcion2.setText(R.string.r62_m);
                                                opcion3.setText(R.string.r63_m);
                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        if (opcion2.isChecked()) {
                                                            respuesta += 1;
                                                            crearDialogoHasAcertado(0, 0).show();
                                                        } else crearDialogoHasFallado2(6, 0).show();
                                                        opcion1.setChecked(true);
                                                        pregunta.setText(R.string.q7_m);
                                                        opcion1.setText(R.string.r71_m);
                                                        opcion2.setText(R.string.r72_m);
                                                        opcion3.setText(R.string.r73_m);
                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View v) {
                                                                if (opcion3.isChecked()) {
                                                                    respuesta += 1;
                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                } else
                                                                    crearDialogoHasFallado2(7, 0).show();
                                                                opcion1.setChecked(true);
                                                                pregunta.setText(R.string.q8_m);
                                                                opcion1.setText(R.string.r81_m);
                                                                opcion2.setText(R.string.r82_m);
                                                                opcion3.setText(R.string.r83_m);
                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                    public void onClick(View v) {
                                                                        if (opcion3.isChecked()) {
                                                                            respuesta += 1;
                                                                            crearDialogoHasAcertado(0, 0).show();
                                                                        } else
                                                                            crearDialogoHasFallado2(8, 0).show();
                                                                        opcion1.setChecked(true);
                                                                        pregunta.setText(R.string.q9_m);
                                                                        opcion1.setText(R.string.r91_m);
                                                                        opcion2.setText(R.string.r92_m);
                                                                        opcion3.setText(R.string.r93_m);
                                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                                            public void onClick(View v) {
                                                                                if (opcion3.isChecked()) {
                                                                                    respuesta += 1;
                                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                                } else
                                                                                    crearDialogoHasFallado2(9, 0).show();
                                                                                opcion1.setChecked(true);
                                                                                pregunta.setText(R.string.q10_m);
                                                                                opcion1.setText(R.string.r101_m);
                                                                                opcion2.setText(R.string.r102_m);
                                                                                opcion3.setText(R.string.r103_m);
                                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                                    public void onClick(View v) {
                                                                                        if (opcion1.isChecked()) {
                                                                                            respuesta += 1;
                                                                                            crearDialogoHasAcertado(10, respuesta).show();
                                                                                        } else
                                                                                            crearDialogoHasFallado2(10, respuesta).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz2.this);
        builder.setTitle(R.string.enhorabuena);
        builder.setMessage(R.string.hasAcertado);
        if (numeroPregunta == 10) {
            builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AlubiaQuiz2.this, Solucion.class);
                    String respuesta_s = Integer.toString(respuesta);
                    Bundle b = new Bundle();
                    b.putString("RESPUESTA", respuesta_s);
                    intent.putExtras(b);
                    AlubiaQuiz2.this.startActivity(intent);
                    AlubiaQuiz2.this.finish();
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

    //================================================================
    //DIALOGO PARA EL QUIZ FALLA PREGUNTA (2)
    public Dialog crearDialogoHasFallado2(int numeroPregunta, final int respuesta) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz2.this);
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
                        Intent intent = new Intent(AlubiaQuiz2.this, Solucion.class);
                        String respuesta_s = Integer.toString(respuesta);
                        Bundle b = new Bundle();
                        b.putString("RESPUESTA", respuesta_s);
                        intent.putExtras(b);
                        AlubiaQuiz2.this.startActivity(intent);
                        AlubiaQuiz2.this.finish();
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

    //==============CODIGO PARA ESTADISTICAS==========================
    //================================================================
    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this); // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this); // Add this method.
    }
}
