package huitca1212.alubia13.novedades;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import huitca1212.alubia13.R;
import huitca1212.alubia13.programa.TitularPrograma;

public class Novedades extends Activity {
    boolean error = false;
    private String jsonResult;
    private LinearLayout layout;
    private ListView listView;
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novedades);
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);
        tracker = analytics.newTracker("UA-42496077-1"); // Replace with actual tracker/property Id
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        listView = (ListView) findViewById(R.id.listView1);

        final Button boton1 = (Button) findViewById(R.id.button_novedades); //BOTON ENVIAR FOTO
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                crearDialogoContact(Novedades.this).show();
            }
        });

        // Comprobamos conexión a Internet y descargamos lista de novedades
        accessWebService();
    }

    //====================================================================================================================
    //Función que comprueba si está Internet habilitado
    //====================================================================================================================
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

    //====================================================================================================================
    //Tarea asíncrona para acceder a la Web
    //====================================================================================================================
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        HttpClient httpclient = new DefaultHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layout.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
            } catch (IOException e) {
                e.printStackTrace();
                error = true;
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine;
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
                error = true;
            }
            return answer;
        }

        @Override
        protected void onCancelled() {
            httpclient.getConnectionManager().shutdown();
            layout.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            layout.setVisibility(View.GONE);
            if (error) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                return;
            }
            // Dibujamos la lista de novedades
            listDrawer();
        }
    }

    //====================================================================================================================
    // Comprobamos conexión a Internet y descargamos lista de novedades
    //====================================================================================================================
    public void accessWebService() {
        if (!checkInternet()) {
            Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
            layout.setVisibility(View.GONE);
        } else {
            JsonReadTask task = new JsonReadTask();
            // passes values for the urls string array
            String url = "http://rjapps.x10host.com/descargar_novedades.php";
            task.execute(url);
        }
    }

    //====================================================================================================================
    // Decodifica la información JSON e imprime las novedades
    //====================================================================================================================
    public void listDrawer() {
        String resultado;
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            resultado = jsonResponse.optString("resultado");
            if (resultado.equals("-1")) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                return;
            }
            JSONArray jsonMainNode = jsonResponse.optJSONArray("novedades");

            TitularPrograma[] datos = new TitularPrograma[jsonMainNode.length()];

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String fecha = jsonChildNode.optString("fecha");
                String comentario = jsonChildNode.optString("comentario");
                datos[i] = new TitularPrograma(fecha, comentario);
            }//end for

            AdaptadorNovedades simpleAdapter = new AdaptadorNovedades(this, datos);
            listView.setAdapter(simpleAdapter);


        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
            error = true;
        }
        if (error) {
            Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
        }
    }

    public Dialog crearDialogoContact(Context eso) {
        AlertDialog.Builder builder = new AlertDialog.Builder(eso);
        builder.setTitle(R.string.evento);
        builder.setMessage(R.string.eventoTexto);
        builder.setNegativeButton(R.string.whatsApp, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:664732632"));
                startActivity(callIntent);
            }
        });
        builder.setNeutralButton(R.string.email, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822")
                        .putExtra(Intent.EXTRA_EMAIL, "huitca1212@gmail.com");
                startActivity(Intent.createChooser(i, "Enviar mediante"));
            }
        });
        builder.setPositiveButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}