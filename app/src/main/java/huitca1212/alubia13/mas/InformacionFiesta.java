package huitca1212.alubia13.mas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class InformacionFiesta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_fiesta);
        final TextView texto = (TextView) findViewById(R.id.info_write); //PRIMER BOTON
        texto.setMovementMethod(new ScrollingMovementMethod());

        final Button mapa = (Button) findViewById(R.id.button_maps);
        mapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mapa_url = "https://www.google.es/maps/place/24234+Laguna+de+Negrillos,+Le%C3%B3n/@42.2397558,-5.6599392,11z/data=!4m2!3m1!1s0xd38321134a1221b:0x551ce1e99dc7011d";
                Uri uri = Uri.parse(mapa_url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}