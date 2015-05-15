package huitca1212.alubia13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import huitca1212.alubia13.alubiaQuiz.AlubiaQuizMenu;
import huitca1212.alubia13.foro.ForoRegistro;
import huitca1212.alubia13.masClases.VariasFunciones;
import huitca1212.alubia13.novedades.Novedades;


public class MasInformacion extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mas_informacion);

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.button1); //PRIMER BOTON
        boton1.setBackgroundResource(R.drawable.alubia_button);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MasInformacion.this, Novedades.class);
                startActivity(intent);
            }
        });
        final Button boton2 = (Button) findViewById(R.id.button2); //SEGUNDO BOTON
        boton2.setBackgroundResource(R.drawable.alubia_button);
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MasInformacion.this, InformacionFiesta.class);
                startActivity(intent);
            }
        });

        final Button boton3 = (Button) findViewById(R.id.button3); //TERCER BOTON
        boton3.setBackgroundResource(R.drawable.alubia_button);
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(MasInformacion.this, ForoRegistro.class);
                    startActivity(intent);
            }
        });

        final Button boton4 = (Button) findViewById(R.id.button4);  //CUARTO BOTON
        boton4.setBackgroundResource(R.drawable.alubia_button);
        boton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MasInformacion.this, AlubiaQuizMenu.class);
                startActivity(intent);
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
}