package huitca1212.alubia13.novedades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;
import huitca1212.alubia13.masClases.VariasFunciones;
import huitca1212.alubia13.programa.TitularPrograma;

public class Novedades extends Activity {
    private String jsonResult;
    private ListView listView;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novedades);
        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        listView = (ListView) findViewById(R.id.listView1);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // Comprobamos conexión a Internet y descargamos lista de comentarios
        accessWebService(); //ACTUALIZACION DE LA LISTA DE COMENTARIOS

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
        if (i == null)
            return false;
        return true;
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
            listView.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
                Toast.makeText(getApplicationContext(),
                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onCancelled() {
            httpclient.getConnectionManager().shutdown();
            layout.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            layout.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            ListDrawer();
        }
    }

    //====================================================================================================================
    // Comprobamos conexión a Internet y descargamos lista de novedades
    //====================================================================================================================
    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        if (checkInternet() == false) {
            Toast.makeText(getApplicationContext(), "Necesitas conexión a Internet para ver las novedades.", Toast.LENGTH_LONG).show();
            task.cancel(true);
            finish();
        }
        // passes values for the urls string array
        String url = "http://rjapps.x10host.com/descargar_novedades.php";
        task.execute(url);
    }

    //====================================================================================================================
    // Decodifica la información JSON e imprime las novedades
    //====================================================================================================================
    public void ListDrawer() {

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
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


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error en la recepción de los datos.", Toast.LENGTH_SHORT).show();
        }

    }

    //====================================================================================================================
    // Código para el ActionBar
    //====================================================================================================================
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

    //====================================================================================================================
    // Código para las estadísticas
    //====================================================================================================================
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