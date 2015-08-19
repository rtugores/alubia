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
        pregunta.setText("1. ¿Cuál de estas peñas no existía cuando nació la fiesta de la Alubia?");
        opcion1.setText("Sonic 2000");
        opcion2.setText("Un millón de amigos");
        opcion3.setText("Rockambole");

        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //PROCEDO CON LAS SIGUIENTES PREGUNTAS
                if (opcion3.isChecked()) {
                    respuesta += 1; //respuestas a la primera pregunta
                    crearDialogoHasAcertado(0, 0).show();
                } else crearDialogoHasFallado2(1, 0).show();
                opcion1.setChecked(true);
                pregunta.setText("2. ¿Cuál fue el último año en el que hubo suelta de vaquillas?");
                opcion1.setText("2009");
                opcion2.setText("2011");
                opcion3.setText("2013");
                boton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (opcion1.isChecked()) {
                            respuesta += 1; //respuestas a la segunda pregunta
                            crearDialogoHasAcertado(0, 0).show();
                        } else crearDialogoHasFallado2(2, 0).show();
                        opcion1.setChecked(true);
                        pregunta.setText("3. ¿Quién fue la reina infantil de la fiesta de la Alubia en el año 2013?");
                        opcion1.setText("Amaia Rodríguez Cubero");
                        opcion2.setText("Irene Gorgojo Sordo");
                        opcion3.setText("Alba Ugidos Matilla");
                        boton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (opcion1.isChecked()) {
                                    respuesta += 1; //respuestas a la tercera pregunta
                                    crearDialogoHasAcertado(0, 0).show();
                                } else crearDialogoHasFallado2(3, 0).show();
                                opcion1.setChecked(true);
                                pregunta.setText("4. ¿Qué típica degustación se hace el lunes de cada fiesta al mediodía?");
                                opcion1.setText("Bollo Preñao");
                                opcion2.setText("Alubiada");
                                opcion3.setText("Paella");
                                boton1.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        if (opcion2.isChecked()) {
                                            respuesta += 1;
                                            crearDialogoHasAcertado(0, 0).show();
                                        } else crearDialogoHasFallado2(4, 0).show();
                                        opcion1.setChecked(true);
                                        pregunta.setText("5. ¿Qué dos peñas se fusionaron en la fiesta de la Alubia 2013?");
                                        opcion1.setText("Kelnozz & Ceda el vaso");
                                        opcion2.setText("Taboo & Despista2");
                                        opcion3.setText("Kachi-chirín & La coral");
                                        boton1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                if (opcion1.isChecked()) {
                                                    respuesta += 1;
                                                    crearDialogoHasAcertado(0, 0).show();
                                                } else crearDialogoHasFallado2(5, 0).show();
                                                opcion1.setChecked(true);
                                                pregunta.setText("6. ¿En qué año nació la fiesta de la Alubia?");
                                                opcion1.setText("1969");
                                                opcion2.setText("1977");
                                                opcion3.setText("1985");
                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        if (opcion2.isChecked()) {
                                                            respuesta += 1;
                                                            crearDialogoHasAcertado(0, 0).show();
                                                        } else crearDialogoHasFallado2(6, 0).show();
                                                        opcion1.setChecked(true);
                                                        pregunta.setText("7. En la fiesta de la Alubia nunca ha habido un torneo de:");
                                                        opcion1.setText("Pelota vasca");
                                                        opcion2.setText("Balonmano");
                                                        opcion3.setText("Hockey");
                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View v) {
                                                                if (opcion3.isChecked()) {
                                                                    respuesta += 1;
                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                } else
                                                                    crearDialogoHasFallado2(7, 0).show();
                                                                opcion1.setChecked(true);
                                                                pregunta.setText("8. La peña Indis (Indiscretos) está formada por:");
                                                                opcion1.setText("Sólo chicos");
                                                                opcion2.setText("Sólo chicas");
                                                                opcion3.setText("Es mixta");
                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                    public void onClick(View v) {
                                                                        if (opcion3.isChecked()) {
                                                                            respuesta += 1;
                                                                            crearDialogoHasAcertado(0, 0).show();
                                                                        } else
                                                                            crearDialogoHasFallado2(8, 0).show();
                                                                        opcion1.setChecked(true);
                                                                        pregunta.setText("9. ¿Qué conocida cantante de Operación Triunfo asistió a una fiesta de la Alubia?");
                                                                        opcion1.setText("Edurne");
                                                                        opcion2.setText("Chenoa");
                                                                        opcion3.setText("Natalia");
                                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                                            public void onClick(View v) {
                                                                                if (opcion3.isChecked()) {
                                                                                    respuesta += 1;
                                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                                } else
                                                                                    crearDialogoHasFallado2(9, 0).show();
                                                                                opcion1.setChecked(true);
                                                                                pregunta.setText("10. ¿Cuál de estas peñas tiene menos de 7 años de existencia?");
                                                                                opcion1.setText("Taboo");
                                                                                opcion2.setText("Kachi-chirín");
                                                                                opcion3.setText("Ginkgo");
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
        builder.setTitle("¡Enhorabuena!");
        builder.setMessage("Has acertado la pregunta.");
        if (numeroPregunta == 10) {
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
        builder.setTitle("¡Mala suerte!");
        switch (numeroPregunta) {
            case 1:
                builder.setMessage("Has fallado. La peña que no existía cuando nació la fiesta es la peña Rockambole.");
                break;
            case 2:
                builder.setMessage("Te has equivocado. El último año que hubo suelta de vaquillas fue el 2009.");
                break;
            case 3:
                builder.setMessage("Incorrecto. La reina infantil de la fiesta de la Alubia 2013 fue Amaia Rodríguez Cubero.");
                break;
            case 4:
                builder.setMessage("Falso. Los lunes al mediodía se sirve Alubiada para todos.");
                break;
            case 5:
                builder.setMessage("Ellos sí que la acertaron. Las peña fusionada se llaman \"Kelnozz & Ceda el vaso\"");
                break;
            case 6:
                builder.setMessage("Esta había que acertarla. La fiesta de la Alubia nació en 1977.");
                break;
            case 7:
                builder.setMessage("Te has equivocado. El torneo que nunca hubo en la Alubia fue de Hockey.");
                break;
            case 8:
                builder.setMessage("Mal. La peña Indis (Indiscretos) está formada por chicos y chicas.");
                break;
            case 9:
                builder.setMessage("Has fallado. La artista que cantó en Laguna de Negrillos en el año 2003 fue Natalia.");
                break;
            case 10:
                builder.setMessage("Error. La peña con menos de 6 años es Taboo, con 5 años de vida.");
                builder.setPositiveButton("Aceptar", new OnClickListener() {
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
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
