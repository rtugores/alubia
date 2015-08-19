package huitca1212.alubia13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import huitca1212.alubia13.foro.Foro;
import huitca1212.alubia13.foro.ForoInicial;
import huitca1212.alubia13.mas.Mas;
import huitca1212.alubia13.novedades.Novedades;
import huitca1212.alubia13.penyas.Penyas;
import huitca1212.alubia13.programa.Programa;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.button1); //PRIMER BOTON
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Programa.class);
                startActivity(intent);
            }
        });
        final Button boton2 = (Button) findViewById(R.id.button2); //SEGUNDO BOTON
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Penyas.class);
                startActivity(intent);
            }
        });

        final Button boton3 = (Button) findViewById(R.id.button3); //TERCER BOTON
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
                if (notregister) {
                    Intent intent = new Intent(MainActivity.this, ForoInicial.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, Foro.class);
                    intent.putExtra("invitado", "NOK");
                    startActivity(intent);
                }
            }
        });

        final Button boton4 = (Button) findViewById(R.id.button4);  //CUARTO BOTON
        boton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Novedades.class);
                startActivity(intent);
            }
        });
        final Button boton5 = (Button) findViewById(R.id.button5);  //QUINTO BOTON
        boton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mas.class);
                startActivity(intent);
            }
        });
    }
}