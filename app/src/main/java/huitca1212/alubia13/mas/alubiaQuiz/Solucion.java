package huitca1212.alubia13.mas.alubiaQuiz;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;


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
        MediaPlayer mpRes;

        if (resultado <= 4) {
            txtSolucion.setText(R.string.pocasRespuestas);
            mpRes = MediaPlayer.create(getApplicationContext(), R.raw.abucheo);
            mpRes.start();
        } else {
            txtSolucion.setText(R.string.muchasRespuestas);
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