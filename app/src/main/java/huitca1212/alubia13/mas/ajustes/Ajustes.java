package huitca1212.alubia13.mas.ajustes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;

public class Ajustes extends Activity {

    private ListView lstOpciones;
    private TitularAjustes[] datos =
            new TitularAjustes[]{
                    new TitularAjustes("Cerrar sesión", "Cierra tu sesión en el foro"),
                    new TitularAjustes("Borrar cuenta", "Estamos trabajando para que funcione"),
                    new TitularAjustes("Política de privacidad", "Échale un vistazo a la política de privacidad"),
                    new TitularAjustes("Compartir", "Comparte la aplicación con tus amigos"),
                    new TitularAjustes("Versión", "3.0"),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);

        //================================================================
        //==============CODIGO PARA LISTVIEW==============================
        //================================================================
        AdaptadorAjustes adaptador = new AdaptadorAjustes(this, datos);
        lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch (position) {
                    case 0:
                        // Cerrar sesión
                        boolean notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
                        if (notregister) {
                            Toast.makeText(getApplicationContext(), "Ya has cerrado la sesión", Toast.LENGTH_LONG).show();
                        } else {
                            crearDialogoContact(Ajustes.this).show();
                        }
                        break;
                    case 1:
                        // Borrar cuenta
                        break;
                    case 2:
                        // Revisar política de privacidad
                        String mapa_url = "http://rjapps.x10host.com/responsabilidad.html";
                        Uri uri = Uri.parse(mapa_url);
                        Intent intent_politica = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent_politica);
                        break;
                    case 3:
                        // Compartir aplicación
                        final Intent intent_share = new Intent(android.content.Intent.ACTION_SEND);
                        intent_share.setType("text/plain");
                        intent_share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        intent_share.putExtra(Intent.EXTRA_SUBJECT, "¡Descarga Alubia '15!");
                        intent_share.putExtra(Intent.EXTRA_TEXT, "La aplicación de la fiesta de la Alubia en Laguna de Negrillos. Disponible YA en Google Play: https://play.google.com/store/apps/details?id=huitca1212.alubia13");
                        startActivity(Intent.createChooser(intent_share, "Compartir mediante"));
                        break;
                }
            }
        });
    }

    public Dialog crearDialogoContact(Context eso) {
        AlertDialog.Builder builder = new AlertDialog.Builder(eso);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro de que quieres cerrar la sesión en el foro?");
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("username", "")
                        .commit();
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("notregister", true)
                        .commit();
            }
        });
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
