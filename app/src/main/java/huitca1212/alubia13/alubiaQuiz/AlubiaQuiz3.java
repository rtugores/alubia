package huitca1212.alubia13.alubiaQuiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;
import huitca1212.alubia13.funcionesWeb.VariasFunciones;

public class AlubiaQuiz3 extends Activity {

    protected int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alubia_quiz3);

        //================================================================
        //==============SIGUIENTE===============================
        //================================================================
        final Button boton1 = (Button) findViewById(R.id.button_sig);
        final TextView pregunta = (TextView) findViewById(R.id.primera_ask);
        final RadioButton opcion1 = (RadioButton) findViewById(R.id.radio0);
        final RadioButton opcion2 = (RadioButton) findViewById(R.id.radio1);
        final RadioButton opcion3 = (RadioButton) findViewById(R.id.radio2);

        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //PROCEDO CON LAS PREGUNTAS
                if (opcion3.isChecked()) {
                    respuesta += 1; //respuestas a la primera pregunta
                    crearDialogoHasAcertado(0, 0).show();
                } else crearDialogoHasFallado3(1, 0).show();
                opcion1.setChecked(true);
                pregunta.setText("2. ¿Cuántos años de existencia tiene la peña Kamensoky?");
                opcion1.setText("6 años");
                opcion2.setText("9 años");
                opcion3.setText("13 años");
                boton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (opcion3.isChecked()) {
                            respuesta += 1; //respuestas a la segunda pregunta
                            crearDialogoHasAcertado(0, 0).show();
                        } else crearDialogoHasFallado3(2, 0).show();
                        opcion1.setChecked(true);
                        pregunta.setText("3. ¿En qué calle se encuentra la peña Desfase?");
                        opcion1.setText("Calle Onésimo Redondo");
                        opcion2.setText("Calle la Paz");
                        opcion3.setText("Calle Santa Cruz");
                        boton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (opcion2.isChecked()) {
                                    respuesta += 1; //respuestas a la tercera pregunta
                                    crearDialogoHasAcertado(0, 0).show();
                                } else crearDialogoHasFallado3(3, 0).show();
                                opcion1.setChecked(true);
                                pregunta.setText("4. ¿En qué año se celebró por primera vez el descenso del reguero?");
                                opcion1.setText("1985");
                                opcion2.setText("1997");
                                opcion3.setText("2001");
                                boton1.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        if (opcion3.isChecked()) {
                                            respuesta += 1;
                                            crearDialogoHasAcertado(0, 0).show();
                                        } else crearDialogoHasFallado3(4, 0).show();
                                        opcion1.setChecked(true);
                                        pregunta.setText("5. ¿Cuál de estos grupos no participó en el Alubia Rock 2013?");
                                        opcion1.setText("Dementes");
                                        opcion2.setText("Rayadura");
                                        opcion3.setText("Def con Dos");
                                        boton1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                if (opcion1.isChecked()) {
                                                    respuesta += 1;
                                                    crearDialogoHasAcertado(0, 0).show();
                                                } else crearDialogoHasFallado3(5, 0).show();
                                                opcion1.setChecked(true);
                                                pregunta.setText("6. ¿Qué peña colabora en el reparto del bollo preñao de la fiesta de la Alubia en el 2013?");
                                                opcion1.setText("La coral");
                                                opcion2.setText("Kalankoe");
                                                opcion3.setText("Jarra y pedal");
                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        if (opcion3.isChecked()) {
                                                            respuesta += 1;
                                                            crearDialogoHasAcertado(0, 0).show();
                                                        } else crearDialogoHasFallado3(6, 0).show();
                                                        opcion1.setChecked(true);
                                                        pregunta.setText("7. ¿Qué pelotaris se enfrentaron en el partido de pelota a mano del 2005?");
                                                        opcion1.setText("Bengoetxea-Eraso VS Garciandia-Apecetxea");
                                                        opcion2.setText("Beroiz-TintínIII VS Cecilio-Ongay");
                                                        opcion3.setText("Apraiz-Begino VS Arruti-BerasaluzeVIII");
                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View v) {
                                                                if (opcion1.isChecked()) {
                                                                    respuesta += 1;
                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                } else
                                                                    crearDialogoHasFallado3(7, 0).show();
                                                                opcion1.setChecked(true);
                                                                pregunta.setText("8. ¿De qué color tiene la camiseta la peña Los colgaos?");
                                                                opcion1.setText("Amarilla");
                                                                opcion2.setText("Roja");
                                                                opcion3.setText("Verde");
                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                    public void onClick(View v) {
                                                                        if (opcion1.isChecked()) {
                                                                            respuesta += 1;
                                                                            crearDialogoHasAcertado(0, 0).show();
                                                                        } else
                                                                            crearDialogoHasFallado3(8, 0).show();
                                                                        opcion1.setChecked(true);
                                                                        pregunta.setText("9. ¿Quién fue la primera dama juvenil de la fiesta de la Alubia 2012?");
                                                                        opcion1.setText("Sara Sutil Gómez");
                                                                        opcion2.setText("Jennifer Ceñera Fernández");
                                                                        opcion3.setText("Ana Montero González");
                                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                                            public void onClick(View v) {
                                                                                if (opcion2.isChecked()) {
                                                                                    respuesta += 1;
                                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                                } else
                                                                                    crearDialogoHasFallado3(9, 0).show();
                                                                                opcion1.setChecked(true);
                                                                                pregunta.setText("10. ¿Qué otro nombre recibe la peña \"Ya estamos todos\"?");
                                                                                opcion1.setText("Jaia");
                                                                                opcion2.setText("PocaPena");
                                                                                opcion3.setText("Ketache");
                                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                                    public void onClick(View v) {
                                                                                        if (opcion3.isChecked() == true) {
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

        //================================================================
        //==============INICIO===============================
        //================================================================

        final Button boton2 = (Button) findViewById(R.id.button_ini); //INICIO
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    //================================================================
    //==============CODIGO PARA ACTION BAR============================
    //================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        VariasFunciones opcion = new VariasFunciones();
        switch (item.getItemId()) {
            case R.id.menu_share:
                opcion.compartir(this);
                return true;
            case R.id.menu_info:
                opcion.crearDialogoAlerta(this).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //===========================================================================================
    //DIALOGO PARA EL QUIZ ACIERTA PREGUNTA
    public Dialog crearDialogoHasAcertado(int numeroPregunta, final int respuesta) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz3.this);
        builder.setTitle("¡Enhorabuena!");
        builder.setMessage("Has acertado la pregunta.");
        if (numeroPregunta == 10) {
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
        builder.setTitle("¡Mala suerte!");
        switch (numeroPregunta) {
            case 1:
                builder.setMessage("Era algo difícil. La reina juvenil de la fiesta de la Alubia 2006 fue Raquel González Vivas.");
                break;
            case 2:
                builder.setMessage("Incorrecto. La peña Kamensoky fue fundada en 2002, por lo que tiene 13 años.");
                break;
            case 3:
                builder.setMessage("¿Andas algo perdido? La peña Desfase se encuentra en la calle la Paz.");
                break;
            case 4:
                builder.setMessage("Error. El descenso del reguero se celebró por primera vez en el año 2001.");
                break;
            case 5:
                builder.setMessage("Mal. El grupo que no participó en el Alubia Rock 2013 fue el conjunto paramés Dementes.");
                break;
            case 6:
                builder.setMessage("Has fallado. La peña que colaboró en el reparto fue Jarra y pedal.");
                break;
            case 7:
                builder.setMessage("Pregunta difícil. Los pelotaris que se enfrentaron fueron Bengoetxea-Eraso y Garciandia-Apecetxea.");
                break;
            case 8:
                builder.setMessage("Fíjate bien en ellos para la próxima. La peña Los colgaos visten camiseta amarilla.");
                break;
            case 9:
                builder.setMessage("Te has equivocado. La primera dama juvenil del 2012 fue Jennifer Ceñera Fernández.");
                break;
            case 10:
                builder.setMessage("Mal. El otro nombre que recibe la peña \"Ya estamos todos\" es \"Ketache\".");
                builder.setPositiveButton("Aceptar", new OnClickListener() {
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
            builder.setPositiveButton("Aceptar", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        return builder.create();
    }

    //================================================================
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
