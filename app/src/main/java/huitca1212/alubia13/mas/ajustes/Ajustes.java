package huitca1212.alubia13.mas.ajustes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import java.net.URLEncoder;

import huitca1212.alubia13.R;
import huitca1212.alubia13.foro.Foro;
import huitca1212.alubia13.foro.ForoOlvide;
import huitca1212.alubia13.mas.Mas;
import huitca1212.alubia13.masClases.BorrarCuenta;

public class Ajustes extends Activity {

    private TitularAjustes[] datos =
            new TitularAjustes[]{
                    new TitularAjustes("Cerrar sesión", "Cierra tu sesión en el foro"),
                    new TitularAjustes("Olvidé mi contraseña", "Recupera tu contraseña en tu correo"),
                    new TitularAjustes("Borrar cuenta", "Elimina tu cuenta en el foro"),
                    new TitularAjustes("Política de privacidad", "Échale un vistazo a la política de privacidad"),
                    new TitularAjustes("Compartir", "Comparte la aplicación con tus amigos"),
                    new TitularAjustes("Actualizar", "Obtén la versión más actualizada"),
                    new TitularAjustes("Versión", "3.0"),
            };
    private boolean notregister;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);
        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        //================================================================
        //==============CODIGO PARA LISTVIEW==============================
        //================================================================
        AdaptadorAjustes adaptador = new AdaptadorAjustes(this, datos);
        ListView lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch (position) {
                    case 0:
                        // Cerrar sesión
                        notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
                        if (notregister) {
                            Toast.makeText(getApplicationContext(), "Ya has cerrado la sesión", Toast.LENGTH_SHORT).show();
                        } else {
                            crearDialogoCerrar(Ajustes.this).show();
                        }
                        break;
                    case 1:
                        // Olvidé contraseña
                        Intent intent = new Intent(Ajustes.this, ForoOlvide.class);
                        startActivity(intent);
                        break;
                    case 2:
                        // Borrar cuenta
                        notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
                        if (notregister) {
                            Toast.makeText(getApplicationContext(), "No has iniciado ninguna sesión", Toast.LENGTH_SHORT).show();
                        } else {
                            crearDialogoBorrar(Ajustes.this).show();
                        }
                        break;
                    case 3:
                        // Revisar política de privacidad
                        String mapa_url = "http://rjapps.x10host.com/responsabilidad.html";
                        Uri uri = Uri.parse(mapa_url);
                        Intent intent_politica = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent_politica);
                        break;
                    case 4:
                        // Compartir aplicación
                        final Intent intent_share = new Intent(android.content.Intent.ACTION_SEND);
                        intent_share.setType("text/plain");
                        intent_share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        intent_share.putExtra(Intent.EXTRA_SUBJECT, "¡Descarga Alubia '15!");
                        intent_share.putExtra(Intent.EXTRA_TEXT, "La aplicación de la fiesta de la Alubia en Laguna de Negrillos. Disponible YA en Google Play: https://play.google.com/store/apps/details?id=huitca1212.alubia13");
                        startActivity(Intent.createChooser(intent_share, "Compartir mediante"));
                        break;
                    case 5:
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                        break;
                }
            }
        });
    }

    public Dialog crearDialogoCerrar(Context eso) {
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
                try {
                    Mas.mas.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    Foro.foro.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        return builder.create();
    }


    public Dialog crearDialogoBorrar(Context eso) {
        AlertDialog.Builder builder = new AlertDialog.Builder(eso);
        builder.setTitle("Borrar cuenta");
        builder.setMessage("¿Estás seguro de que quieres borrar tu cuenta del foro?");
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!checkInternet()) {
                    Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
                // Tomamos el usuario registrado (del SharedPreferences)
                String usuario = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", "");
                try {
                    // Preparamos la URL
                    String mURL = "http://rjapps.x10host.com/borrar_cuenta.php?usuario=" + URLEncoder.encode(usuario, "UTF-8");
                    // Enviamos la petición
                    BorrarCuenta enviar = new BorrarCuenta(Ajustes.this, layout, mURL);
                    enviar.execute(mURL);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
                try {
                    Mas.mas.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    Foro.foro.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        return builder.create();
    }

    protected boolean checkInternet() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i != null) {
            if (!i.isConnected())
                return false;
            if (!i.isAvailable())
                return false;
        }
        return i != null;
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
