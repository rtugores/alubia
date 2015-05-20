package huitca1212.alubia13.mas.novedades;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novedades);
        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        listView = (ListView) findViewById(R.id.listView1);

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
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                error = true;
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
                Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo!", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo!", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo!", Toast.LENGTH_LONG).show();
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


        } catch (JSONException e) {
            e.printStackTrace();
            error = true;
        } catch (NullPointerException e) {
            e.printStackTrace();
            error = true;
        }
        if (error) {
            Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo!", Toast.LENGTH_LONG).show();
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