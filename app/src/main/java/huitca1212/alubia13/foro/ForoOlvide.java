package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import huitca1212.alubia13.R;
import huitca1212.alubia13.funcionesWeb.VariasFunciones;

public class ForoOlvide extends Activity {

    // Declaramos variables
    private String jsonResult, mURL;
    private LinearLayout pantalla_cargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_olvide);

        pantalla_cargando = (LinearLayout) findViewById(R.id.progressbar_view_registro);
        final EditText email = (EditText) findViewById(R.id._email);
        final Button boton = (Button) findViewById(R.id._emailbutton);

        // Al hacer click en el botón de recuperar contrasenya
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Comprobamos si el nombre de usuario está escrito correctamente
                String s_email = email.getText().toString();
                if (s_email.length() < 5) {
                    Toast.makeText(getApplicationContext(), "El email no puede tener menos de 5 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mURL = "http://rjapps.x10host.com/olvide_contrasenya.php?email=" + URLEncoder.encode(s_email, "UTF-8");
                    mURL = mURL.replace(" ", "%20");
                    Toast.makeText(getApplicationContext(), mURL, Toast.LENGTH_LONG).show();
                    // Chequear si está la conexión a Internet activa
                    if (!checkInternet()) {
                        Toast.makeText(getApplicationContext(), "Necesitas conexión a Internet para solicitar tu contraseña.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    // Enviamos el registro
                    SendTask enviar = new SendTask();
                    enviar.execute(mURL);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error con la recuperacion de información, verifica tu conexión a Internet.", Toast.LENGTH_LONG).show();
                }
            }
        });
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
    //Tarea para enviar consulta
    //====================================================================================================================
    private class SendTask extends AsyncTask<String, Void, String> {
        boolean error_envio = false;
        HttpClient httpclient = new DefaultHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pantalla_cargando.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpPost httppost = new HttpPost(mURL);
            try {
                httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                error_envio = true;
            } catch (IOException e) {
                e.printStackTrace();
                error_envio = true;
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            httpclient.getConnectionManager().shutdown();
            pantalla_cargando.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(String result) {
            if (error_envio) {
                pantalla_cargando.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "La consulta no se ha completado correctamente. Revisa tu conexión a Internet.", Toast.LENGTH_LONG).show();
            } else {
                pantalla_cargando.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "En unos segundos recibirás un email a tu correo con la contraseña.", Toast.LENGTH_LONG).show();
                // Cerramos actividad
                finish();
            }
        }
    }

    //====================================================================================================================
    //Tarea asíncrona para acceder a la Web
    //====================================================================================================================
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        HttpClient httpclient = new DefaultHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pantalla_cargando.setVisibility(View.VISIBLE);
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
            pantalla_cargando.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pantalla_cargando.setVisibility(View.GONE);
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