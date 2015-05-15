package huitca1212.alubia13;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.funcionesWeb.VariasFunciones;

public class InformacionFiesta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_fiesta);
        final TextView texto = (TextView) findViewById(R.id.info_write); //PRIMER BOTON
        texto.setMovementMethod(new ScrollingMovementMethod());

        //====================
        TextView tvEnlace_2;
        String url = "http://www.lagunadenegrillos.com/index.do?metodo=visualizarSeccion&idSeccion=27";
        String url2 = "https://www.google.es/maps/place/24234+Laguna+de+Negrillos,+Le%C3%B3n/@42.2397558,-5.6599392,11z/data=!4m2!3m1!1s0xd38321134a1221b:0x551ce1e99dc7011d";

        tvEnlace_2 = (TextView) findViewById(R.id.tvEnlace_2);
        tvEnlace_2.setText(Html.fromHtml("Mucha más información " +
                "<a href=" + url + ">aquí</a>. También puedes consultar el " +
                "<a href=" + url2 + ">mapa</a>."));
        tvEnlace_2.setMovementMethod(LinkMovementMethod.getInstance());
        //====================
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