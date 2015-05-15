package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import huitca1212.alubia13.R;
import huitca1212.alubia13.funcionesWeb.VariasFunciones;

public class ForoRegistro extends Activity {

    // Declaramos variables
    private String jsonResult, mURL;
    private LinearLayout pantalla_cargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_registro);

        pantalla_cargando = (LinearLayout) findViewById(R.id.progressbar_view_registro);
        final EditText username_edit = (EditText) findViewById(R.id.user);
        final EditText password_edit = (EditText) findViewById(R.id.password);
        final EditText email_edit = (EditText) findViewById(R.id.email);
        final EditText codigo_penya_edit = (EditText) findViewById(R.id.codigo_penha);
        final Button boton = (Button) findViewById(R.id.button);

        // Código para la aceptación de la privacidad
        final String responsabilidad = "http://rjapps.x10host.com/responsabilidad.html";
        final TextView terminos = (TextView) findViewById(R.id.terminos);
        terminos.setText(Html.fromHtml("Al hacer click en Terminado, confirmas que has leído y aceptas los " +
                "<a href=" + responsabilidad + ">términos y condiciones de uso</a>."));
        terminos.setMovementMethod(LinkMovementMethod.getInstance());

        // Al hacer click en el botón de enviar registro
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Comprobamos si el nombre de usuario está escrito correctamente
                String username = username_edit.getText().toString();
                if (username.length() < 3) {
                    Toast.makeText(getApplicationContext(), "El nombre ha de tener al menos 3 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Comprobamos si la contraseña está escrita correctamente
                String password = password_edit.getText().toString();
                if (password.length() < 5) {
                    Toast.makeText(getApplicationContext(), "La contraseña ha de tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Comprobamos si el email está escrito correctamente
                String email = email_edit.getText().toString();
                if (password.length() < 1) {
                    Toast.makeText(getApplicationContext(), "La contraseña ha de tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Comprobamos si el código de peña es adecuado
                String codigo_penya = codigo_penya_edit.getText().toString();
                if (codigo_penya.length() == 0) {
                    codigo_penya = "0";
                }
                try {
                    String mobile_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                    mURL = "http://rjapps.x10host.com/anhadir_usuario.php?usuario=" + URLEncoder.encode(username, "UTF-8") +
                            "&contrasenya=" + URLEncoder.encode(password, "UTF-8") + "&email=" + URLEncoder.encode(email, "UTF-8") +
                            "&codigo_penya=" + URLEncoder.encode(codigo_penya, "UTF-8") + "&mobile_id=" + URLEncoder.encode(mobile_id, "UTF-8");
                    mURL = mURL.replace(" ", "%20");
                    // Chequear si está la conexión a Internet activa
                    if (!checkInternet()) {
                        Toast.makeText(getApplicationContext(), "Necesitas conexión a Internet para registrarte.", Toast.LENGTH_LONG).show();
                        return;
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
    //Tarea para enviar registro
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
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                error_envio = true;
            } catch (IOException e) {
                e.printStackTrace();
                error_envio = true;
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
            String resultado;
            pantalla_cargando.setVisibility(View.GONE);
            try {
                JSONObject jsonResponse = new JSONObject(jsonResult);
                resultado = jsonResponse.optString("resultado");
                pantalla_cargando.setVisibility(View.GONE);
            } catch (JSONException e) {

                pantalla_cargando.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error en la recepción de los datos.", Toast.LENGTH_SHORT).show();
                return;
            } catch (NullPointerException e) {
                pantalla_cargando.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error en la recepción de los datos.", Toast.LENGTH_SHORT).show();
                return;
            }
            int resultado_int = Integer.parseInt(resultado);
            switch (resultado_int) {
                case -1:
                    Toast.makeText(getApplicationContext(), "Error al conectar a la base de datos.", Toast.LENGTH_LONG).show();
                    return;
                case -2:
                    Toast.makeText(getApplicationContext(), "Ya existe un usuario con este nombre. Intenta otro.", Toast.LENGTH_LONG).show();
                    return;
                case -3:
                    Toast.makeText(getApplicationContext(), "Ya existe un usuario con este email. Intenta otro.", Toast.LENGTH_LONG).show();
                    return;
                case -4:
                    Toast.makeText(getApplicationContext(), "El código de peña no existe. Déjalo en blanco si no tienes.", Toast.LENGTH_LONG).show();
                    return;
            }
            if (error_envio) {
                Toast.makeText(getApplicationContext(), "El registro no se ha completado correctamente. Revisa tu conexión a Internet.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "El registro se ha completado con éxito.", Toast.LENGTH_SHORT).show();
                final EditText username_edit = (EditText) findViewById(R.id.user);
                String username = username_edit.getText().toString();
                // Almacenamos el nombre de usuario en el teléfono
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("username", username)
                        .commit();
                Intent intent = new Intent(ForoRegistro.this, Foro.class);
                // Almacenamos que el usuario ya se ha registrado en el teléfono
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("notregister", false)
                        .commit();
                // Abrimos nueva actividad y cerramos ya esta
                startActivity(intent);
                finish();
            }
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
