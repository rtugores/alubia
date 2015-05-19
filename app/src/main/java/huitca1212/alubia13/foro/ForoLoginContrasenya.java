package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ForoLoginContrasenya extends Activity {

    // Declaramos variables
    private String jsonResult, mURL;
    private LinearLayout pantalla_cargando;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_login_contrasenya);

        getWindow().setBackgroundDrawableResource(R.drawable.fondo_nuevo);

        // Escondemos teclado
        final EditText password_edit = (EditText) findViewById(R.id.password);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        pantalla_cargando = (LinearLayout) findViewById(R.id.progressbar_view_registro);
        password_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    action_foro_login_contrasenya();
                    return true;
                }
                return false;
            }
        });

        final Button boton = (Button) findViewById(R.id.button);
        // Manejador para cambios en el botón (estética)
        password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable str) {
                if (str.toString().trim().length() > 0) {
                    boton.setEnabled(true);
                    boton.setBackgroundResource(R.drawable.d_normal_button_blue);
                } else {
                    boton.setEnabled(false);
                    boton.setBackgroundResource(R.drawable.d_normal_button_gray);
                }
            }
        });

        final Button boton2 = (Button) findViewById(R.id.olvide_contrasenya); //OLVIDE CONTRASEÑA
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ForoLoginContrasenya.this, ForoOlvide.class);
                startActivity(intent);
            }
        });

        // Tomamos el email de la pantalla anterior
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            email = extras.getString("email");
        } else {
            email = (String) savedInstanceState.getSerializable("email");
        }

        // Al hacer click en el botón de enviar login
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action_foro_login_contrasenya();
            }
        });
    }

    protected void action_foro_login_contrasenya() {
        // Escondemos teclado
        final EditText password_edit = (EditText) findViewById(R.id.password);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(password_edit.getWindowToken(), 0);
        // Comprobamos si el email está escrito correctamente
        password = password_edit.getText().toString().trim();
        if (password.length() < 5) {
            Toast.makeText(getApplicationContext(), "La contraseña ha de tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mURL = "http://rjapps.x10host.com/comprobar_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8") +
                    "&contrasenya=" + URLEncoder.encode(password, "UTF-8");
            mURL = mURL.replace(" ", "%20");
            // Chequear si está la conexión a Internet activa
            if (!checkInternet()) {
                Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo! [1]", Toast.LENGTH_LONG).show();
                return;
            }
            // Enviamos el login
            SendTask enviar = new SendTask();
            enviar.execute(mURL);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo! [2]", Toast.LENGTH_LONG).show();
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
        boolean error = false;
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
            if (resultado.equals("-1")) {
                error = true;
            } else if (resultado.equals("-2")) {
                Toast.makeText(getApplicationContext(), "La contraseña que introdujiste no coincide con la de la cuenta", Toast.LENGTH_LONG).show();
                pantalla_cargando.setVisibility(View.GONE);
                return;
            }
            if (error) {
                Toast.makeText(getApplicationContext(), "Algo fue mal! Comprueba tu conexión a Internet e inténtalo de nuevo!", Toast.LENGTH_LONG).show();
                pantalla_cargando.setVisibility(View.GONE);
            } else {
                // Almacenamos el nombre de usuario en el teléfono
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("username", resultado)
                        .commit();
                // Almacenamos que el usuario ya se ha registrado en el teléfono
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("notregister", false)
                        .commit();
                // Abrimos nueva actividad y cerramos la anterior y esta
                Intent intent = new Intent(ForoLoginContrasenya.this, Foro.class);
                startActivity(intent);
                try {
                    ForoInicial.foro_inicial.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    ForoLoginEmail.foro_login_email.finish();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }
    }
}
