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

public class AlubiaQuiz3 extends Activity {

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
        bienvenida_quiz.setText(R.string.alubiaquiz3);
        pregunta.setText(R.string.q1_d);
        opcion1.setText(R.string.r11_d);
        opcion2.setText(R.string.r12_d);
        opcion3.setText(R.string.r13_d);

        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //PROCEDO CON LAS SIGUIENTES PREGUNTAS
                if (opcion3.isChecked()) {
                    respuesta += 1; //respuestas a la primera pregunta
                    crearDialogoHasAcertado(0, 0).show();
                } else crearDialogoHasFallado3(1, 0).show();
                opcion1.setChecked(true);
                pregunta.setText(R.string.q2_d);
                opcion1.setText(R.string.r21_d);
                opcion2.setText(R.string.r22_d);
                opcion3.setText(R.string.r23_d);
                boton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (opcion3.isChecked()) {
                            respuesta += 1; //respuestas a la segunda pregunta
                            crearDialogoHasAcertado(0, 0).show();
                        } else crearDialogoHasFallado3(2, 0).show();
                        opcion1.setChecked(true);
                        pregunta.setText(R.string.q3_d);
                        opcion1.setText(R.string.r31_d);
                        opcion2.setText(R.string.r32_d);
                        opcion3.setText(R.string.r33_d);
                        boton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (opcion2.isChecked()) {
                                    respuesta += 1; //respuestas a la tercera pregunta
                                    crearDialogoHasAcertado(0, 0).show();
                                } else crearDialogoHasFallado3(3, 0).show();
                                opcion1.setChecked(true);
                                pregunta.setText(R.string.q4_d);
                                opcion1.setText(R.string.r41_d);
                                opcion2.setText(R.string.r42_d);
                                opcion3.setText(R.string.r43_d);
                                boton1.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        if (opcion3.isChecked()) {
                                            respuesta += 1;
                                            crearDialogoHasAcertado(0, 0).show();
                                        } else crearDialogoHasFallado3(4, 0).show();
                                        opcion1.setChecked(true);
                                        pregunta.setText(R.string.q5_d);
                                        opcion1.setText(R.string.r51_d);
                                        opcion2.setText(R.string.r52_d);
                                        opcion3.setText(R.string.r53_d);
                                        boton1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                if (opcion1.isChecked()) {
                                                    respuesta += 1;
                                                    crearDialogoHasAcertado(0, 0).show();
                                                } else crearDialogoHasFallado3(5, 0).show();
                                                opcion1.setChecked(true);
                                                pregunta.setText(R.string.q6_d);
                                                opcion1.setText(R.string.r61_d);
                                                opcion2.setText(R.string.r62_d);
                                                opcion3.setText(R.string.r63_d);
                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        if (opcion3.isChecked()) {
                                                            respuesta += 1;
                                                            crearDialogoHasAcertado(0, 0).show();
                                                        } else
                                                            crearDialogoHasFallado3(6, 0).show();
                                                        opcion1.setChecked(true);
                                                        pregunta.setText(R.string.q7_d);
                                                        opcion1.setText(R.string.r71_d);
                                                        opcion2.setText(R.string.r72_d);
                                                        opcion3.setText(R.string.r73_d);
                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View v) {
                                                                if (opcion1.isChecked()) {
                                                                    respuesta += 1;
                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                } else
                                                                    crearDialogoHasFallado3(7, 0).show();
                                                                opcion1.setChecked(true);
                                                                pregunta.setText(R.string.q8_d);
                                                                opcion1.setText(R.string.r81_d);
                                                                opcion2.setText(R.string.r82_d);
                                                                opcion3.setText(R.string.r83_d);
                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                    public void onClick(View v) {
                                                                        if (opcion1.isChecked()) {
                                                                            respuesta += 1;
                                                                            crearDialogoHasAcertado(0, 0).show();
                                                                        } else
                                                                            crearDialogoHasFallado3(8, 0).show();
                                                                        opcion1.setChecked(true);
                                                                        pregunta.setText(R.string.q9_d);
                                                                        opcion1.setText(R.string.r91_d);
                                                                        opcion2.setText(R.string.r92_d);
                                                                        opcion3.setText(R.string.r93_d);
                                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                                            public void onClick(View v) {
                                                                                if (opcion2.isChecked()) {
                                                                                    respuesta += 1;
                                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                                } else
                                                                                    crearDialogoHasFallado3(9, 0).show();
                                                                                opcion1.setChecked(true);
                                                                                pregunta.setText(R.string.q10_d);
                                                                                opcion1.setText(R.string.r101_d);
                                                                                opcion2.setText(R.string.r102_d);
                                                                                opcion3.setText(R.string.r103_d);
                                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                                    public void onClick(View v) {
                                                                                        if (opcion3.isChecked()) {
                                                                                            respuesta += 1;
                                                                                            crearDialogoHasAcertado(10, respuesta).show();
                                                                                        } else
                                                                                            crearDialogoHasFallado3(10, respuesta).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz3.this);
        builder.setTitle(R.string.enhorabuena);
        builder.setMessage(R.string.hasAcertado);
        if (numeroPregunta == 10) {
            builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AlubiaQuiz3.this, Solucion.class);
                    String respuesta_s = Integer.toString(respuesta);
                    Bundle b = new Bundle();
                    b.putString("RESPUESTA", respuesta_s);
                    intent.putExtras(b);
                    AlubiaQuiz3.this.startActivity(intent);
                    AlubiaQuiz3.this.finish();
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
    //DIALOGO PARA EL QUIZ FALLA PREGUNTA (3)
    public Dialog crearDialogoHasFallado3(int numeroPregunta, final int respuesta) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz3.this);
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
                        Intent intent = new Intent(AlubiaQuiz3.this, Solucion.class);
                        String respuesta_s = Integer.toString(respuesta);
                        Bundle b = new Bundle();
                        b.putString("RESPUESTA", respuesta_s);
                        intent.putExtras(b);
                        AlubiaQuiz3.this.startActivity(intent);
                        AlubiaQuiz3.this.finish();
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
