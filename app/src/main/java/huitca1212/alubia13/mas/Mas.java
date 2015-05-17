package huitca1212.alubia13.mas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import huitca1212.alubia13.R;
import huitca1212.alubia13.masClases.VariasFunciones;
import huitca1212.alubia13.mas.ajustes.Ajustes;
import huitca1212.alubia13.mas.novedades.Novedades;


public class Mas extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mas);

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.button1); //PRIMER BOTON
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Mas.this, Novedades.class);
                startActivity(intent);
            }
        });
        final Button boton2 = (Button) findViewById(R.id.button2); //SEGUNDO BOTON
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Mas.this, InformacionFiesta.class);
                startActivity(intent);
            }
        });

        final Button boton3 = (Button) findViewById(R.id.button3); //TERCER BOTON
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(Mas.this, Ajustes.class);
                    startActivity(intent);
            }
        });

        final Button boton4 = (Button) findViewById(R.id.button4);  //CUARTO BOTON
        boton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Mas.this, Contactar.class);
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