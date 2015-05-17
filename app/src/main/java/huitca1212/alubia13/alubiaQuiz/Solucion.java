package huitca1212.alubia13.alubiaQuiz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;
import huitca1212.alubia13.masClases.VariasFunciones;


public class Solucion extends Activity {

    protected int resultado_compartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solucion);
        TextView txtTamanho = (TextView) findViewById(R.id.Respuestas_texto);
        TextView txtSolucion = (TextView) findViewById(R.id.Solucion_texto);
        Bundle bundle = this.getIntent().getExtras();
        String resultado_s = bundle.getString("RESPUESTA");
        Integer resultado = Integer.parseInt(resultado_s);
        resultado_compartir = resultado;
        txtTamanho.setText("¡Has acertado " + Integer.toString(resultado) + " de 10!");
        MediaPlayer mpRes = new MediaPlayer();

        if (resultado <= 4) {
            txtSolucion.setText("No has acertado muchas respuestas, pero no te preocupes.\n\n¡Inténtalo con otro nivel!");
            mpRes = MediaPlayer.create(getApplicationContext(), R.raw.abucheo);
            mpRes.start();
        } else {
            txtSolucion.setText("¡Enhorabuena!\n\n¿Sabes ya si has acertado más que tus amigos?");
            mpRes = MediaPlayer.create(getApplicationContext(), R.raw.aplausos);
            mpRes.start();
        }

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.button_ini); //PRIMER BOTON
        boton1.setOnClickListener(new View.OnClickListener() {
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