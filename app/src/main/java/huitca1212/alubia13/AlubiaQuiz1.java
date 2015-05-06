package huitca1212.alubia13;

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

public class AlubiaQuiz1 extends Activity {

    protected int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alubia_quiz1);

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
                if (opcion2.isChecked()) {
                    respuesta += 1; //respuestas a la primera pregunta
                    crearDialogoHasAcertado(0, 0).show();
                } else crearDialogoHasFallado1(1, 0).show();
                opcion1.setChecked(true);
                pregunta.setText("2. ¿Dónde se celebraron los primeros conciertos del Alubia Rock?");
                opcion1.setText("En la plaza de toros");
                opcion2.setText("En el campo de fútbol");
                opcion3.setText("En la explanada de la Casa de la cultura");
                boton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (opcion1.isChecked()) {
                            respuesta += 1; //respuestas a la segunda pregunta
                            crearDialogoHasAcertado(0, 0).show();
                        } else crearDialogoHasFallado1(2, 0).show();
                        opcion1.setChecked(true);
                        pregunta.setText("3. ¿Qué famoso juego tuvo relevancia hace algunos años en la fiesta de la Alubia?");
                        opcion1.setText("La petanca");
                        opcion2.setText("La maceta");
                        opcion3.setText("Tiro al blanco");
                        boton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (opcion2.isChecked()) {
                                    respuesta += 1; //respuestas a la tercera pregunta
                                    crearDialogoHasAcertado(0, 0).show();
                                } else crearDialogoHasFallado1(3, 0).show();
                                opcion1.setChecked(true);
                                pregunta.setText("4. Las misas de peñas se celebran:");
                                opcion1.setText("Al amanecer");
                                opcion2.setText("Al mediodía");
                                opcion3.setText("Al atardecer");
                                boton1.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        if (opcion2.isChecked()) {
                                            respuesta += 1;
                                            crearDialogoHasAcertado(0, 0).show();
                                        } else crearDialogoHasFallado1(4, 0).show();
                                        opcion1.setChecked(true);
                                        pregunta.setText("5. ¿De qué es la exposición que se muestra cada año en la Casa de la cultura?");
                                        opcion1.setText("De escultura");
                                        opcion2.setText("De arquitectura");
                                        opcion3.setText("De pintura");
                                        boton1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                if (opcion3.isChecked()) {
                                                    respuesta += 1;
                                                    crearDialogoHasAcertado(0, 0).show();
                                                } else crearDialogoHasFallado1(5, 0).show();
                                                opcion1.setChecked(true);
                                                pregunta.setText("6. ¿Qué es la Party Dance?");
                                                opcion1.setText("Un tractor con un remolque donde se sube la gente para bailar");
                                                opcion2.setText("Un local ubicado cerca de las piscinas donde se baila y se sirven copas");
                                                opcion3.setText("Una expresión que se utiliza como sinónimo de desmadre");
                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        if (opcion1.isChecked()) {
                                                            respuesta += 1;
                                                            crearDialogoHasAcertado(0, 0).show();
                                                        } else crearDialogoHasFallado1(6, 0).show();
                                                        opcion1.setChecked(true);
                                                        pregunta.setText("7. ¿Qué día se celebra el desfile de peñas y carrozas?");
                                                        opcion1.setText("Sábado");
                                                        opcion2.setText("Domingo");
                                                        opcion3.setText("Lunes");
                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View v) {
                                                                if (opcion2.isChecked()) {
                                                                    respuesta += 1;
                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                } else
                                                                    crearDialogoHasFallado1(7, 0).show();
                                                                opcion1.setChecked(true);
                                                                pregunta.setText("8. ¿Cuál de estos concursos nunca se ha celebrado en una fiesta de la Alubia?");
                                                                opcion1.setText("Concurso de grafitis");
                                                                opcion2.setText("Concurso de disfraces");
                                                                opcion3.setText("Concurso de ballet");
                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                    public void onClick(View v) {
                                                                        if (opcion3.isChecked()) {
                                                                            respuesta += 1;
                                                                            crearDialogoHasAcertado(0, 0).show();
                                                                        } else
                                                                            crearDialogoHasFallado1(8, 0).show();
                                                                        opcion1.setChecked(true);
                                                                        pregunta.setText("9. La entrada anticipada al Alubia Rock tiene un precio de:");
                                                                        opcion1.setText("Un euro");
                                                                        opcion2.setText("Cinco euros");
                                                                        opcion3.setText("No hay entradas, es gratis");
                                                                        boton1.setOnClickListener(new View.OnClickListener() {
                                                                            public void onClick(View v) {
                                                                                if (opcion3.isChecked()) {
                                                                                    respuesta += 1;
                                                                                    crearDialogoHasAcertado(0, 0).show();
                                                                                } else
                                                                                    crearDialogoHasFallado1(9, 0).show();
                                                                                opcion1.setChecked(true);
                                                                                pregunta.setText("10. ¿Desde dónde sale el desfile infantil de disfraces?");
                                                                                opcion1.setText("Desde el colegio púlico");
                                                                                opcion2.setText("Desde el castillo");
                                                                                opcion3.setText("Desde la plaza de toros");
                                                                                boton1.setOnClickListener(new View.OnClickListener() {
                                                                                    public void onClick(View v) {
                                                                                        if (opcion1.isChecked() == true) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AlubiaQuiz1.this);
        builder.setTitle("¡Enhorabuena!");
        builder.setMessage("Has acertado la pregunta.");
        if (numeroPregunta == 10) {
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
            builder.setPositiveButton("Aceptar", new OnClickListener() {
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
        builder.setTitle("¡Mala suerte!");
        switch (numeroPregunta) {
            case 1:
                builder.setMessage("Has fallado. La carrera de la Alubia consiste en hacer todo el trayecto corriendo.");
                break;
            case 2:
                builder.setMessage("Mal. El Alubia Rock se suele celebrar en la plaza de toros del pueblo.");
                break;
            case 3:
                builder.setMessage("Incorrecto. El juego del que hubo concursos en la fiesta fue la maceta.");
                break;
            case 4:
                builder.setMessage("Te has equivocado. Las misas de peñas se suelen celebrar a las 13h, es decir, al mediodía.");
                break;
            case 5:
                builder.setMessage("No. La exposición que se muestra en la Casa de la cultura es de pintura.");
                break;
            case 6:
                builder.setMessage("¿Qué sería de las noches sin ella? La Party Dance es un tractor con un remolque donde se sube la gente para bailar.");
                break;
            case 7:
                builder.setMessage("Error. El famoso desfile de peñas y carrozas se celebra el domingo por la tarde.");
                break;
            case 8:
                builder.setMessage("No. El cocurso que nunca se celebró fue el de ballet.");
                break;
            case 9:
                builder.setMessage("Respuesta incorrecta. El Alubia Rock es gratis y no hay entradas.");
                break;
            case 10:
                builder.setMessage("Mal. El desfile infantil de disfraces sale desde el colegio público.");
                builder.setPositiveButton("Aceptar", new OnClickListener() {
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
            builder.setPositiveButton("Aceptar", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        return builder.create();
    }
    //===========================================================================================

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
