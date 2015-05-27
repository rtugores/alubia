package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import huitca1212.alubia13.mas.Contactar;

public class ForoRegistroCodigo extends Activity {

    // Declaramos variables
    boolean error = false;
    private String jsonResult, mURL;
    private LinearLayout pantalla_cargando;
    String usuario;
    String email;
    String contrasenya;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_registro_codigo);

        getWindow().setBackgroundDrawableResource(R.drawable.fondo_nuevo);

        // Escondemos teclado
        final EditText codigo_edit = (EditText) findViewById(R.id.codigo);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        pantalla_cargando = (LinearLayout) findViewById(R.id.progressbar_view_registro);

        codigo_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    action_foro_registro_codigo();
                    return true;
                }
                return false;
            }
        });

        // Tomamos el nombre de usuario y los demás datos de la pantalla anterior
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            usuario = extras.getString("usuario");
            email = extras.getString("email");
            contrasenya = extras.getString("contrasenya");
        } else {
            usuario = (String) savedInstanceState.getSerializable("usuario");
            email = (String) savedInstanceState.getSerializable("email");
            contrasenya = (String) savedInstanceState.getSerializable("contrasenya");
        }

        final Button boton = (Button) findViewById(R.id.button);
        // Al hacer click en el botón de enviar el registro
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action_foro_registro_codigo();
            }
        });

        final Button boton2 = (Button) findViewById(R.id.button_codigo); //OLVIDE CONTRASEÑA
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ForoRegistroCodigo.this, Contactar.class);
                startActivity(intent);
            }
        });
    }

    protected void action_foro_registro_codigo() {
        String mobile_id = "";
        // Escondemos teclado
        final EditText codigo_edit = (EditText) findViewById(R.id.codigo);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(codigo_edit.getWindowToken(), 0);
        // Comprobamos si el código está escrito correctamente
        codigo = codigo_edit.getText().toString().trim();
        if (codigo.length() != 10 && codigo.length() != 0) {
            Toast.makeText(getApplicationContext(), "El código tiene que tener 10 caracteres o debe permanecer vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        // Convertimos para que nos entienda el servidor
        if (codigo.length() == 0) {
            codigo = "0";
        }
        try {
            mobile_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mURL = "http://rjapps.x10host.com/comprobar_codigo.php?usuario=" + URLEncoder.encode(usuario, "UTF-8") +
                    "&contrasenya=" + URLEncoder.encode(contrasenya, "UTF-8") + "&email=" + URLEncoder.encode(email, "UTF-8") +
                    "&codigo_penya=" + URLEncoder.encode(codigo, "UTF-8") + "&mobile_id=" + URLEncoder.encode(mobile_id, "UTF-8");
            mURL = mURL.replace(" ", "%20");
            // Chequear si está la conexión a Internet activa
            if (!checkInternet()) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                return;
            }
            // Enviamos el login
            SendTask enviar = new SendTask();
            enviar.execute(mURL);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
        }
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
    //Tarea para enviar login
    //====================================================================================================================
    private class SendTask extends AsyncTask<String, Void, String> {
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
            pantalla_cargando.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(String result) {
            String resultado = "-1";
            try {
                JSONObject jsonResponse = new JSONObject(jsonResult);
                resultado = jsonResponse.optString("resultado");
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
            } catch (NullPointerException e) {
                e.printStackTrace();
                error = true;
            }
            int resultado_int = Integer.parseInt(resultado);
            switch (resultado_int) {
                case -1:
                    Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo!", Toast.LENGTH_LONG).show();
                    pantalla_cargando.setVisibility(View.GONE);
                    return;
                case -2:
                    Toast.makeText(getApplicationContext(), "Se acaba de registrar un usuario con ese mismo nombre. Vuelve a empezar el registro", Toast.LENGTH_LONG).show();
                    pantalla_cargando.setVisibility(View.GONE);
                    return;
                case -3:
                    Toast.makeText(getApplicationContext(), "Se acaba de registrar un usuario con ese mismo email. Vuelve a empezar el registro", Toast.LENGTH_LONG).show();
                    pantalla_cargando.setVisibility(View.GONE);
                    return;
                case -4:
                    Toast.makeText(getApplicationContext(), "El código de peña introducido no existe. No escribas ninguno si no tienes peña", Toast.LENGTH_LONG).show();
                    pantalla_cargando.setVisibility(View.GONE);
                    return;
            }
            if (error) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                pantalla_cargando.setVisibility(View.GONE);
            } else {
                // Almacenamos el nombre de usuario en el teléfono
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("username", usuario)
                        .commit();
                // Almacenamos que el usuario ya se ha registrado en el teléfono
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("notregister", false)
                        .commit();
                // Abrimos nueva actividad y cerramos las anteriores y esta
                Intent intent = new Intent(ForoRegistroCodigo.this, Foro.class);
                startActivity(intent);
                try {
                    ForoInicial.foro_inicial.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    ForoRegistroUsuario.foro_registro_usuario.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    ForoRegistroEmail.foro_registro_email.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    ForoRegistroContrasenya.foro_registro_contrasenya.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }
    }
}
