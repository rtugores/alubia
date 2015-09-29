package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
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
import java.util.Locale;

import huitca1212.alubia13.R;

public class ForoRegistroUsuario extends Activity {

    // Declaramos variables
    boolean error = false;
    private String jsonResult, mURL;
    private LinearLayout pantalla_cargando;
    public static Activity foro_registro_usuario;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_registro_usuario);

        getWindow().setBackgroundDrawableResource(R.drawable.fondo_nuevo);

        foro_registro_usuario = this;

        // Ocultamos teclado para que se pueda leer la política de privacidad
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        pantalla_cargando = (LinearLayout) findViewById(R.id.progressbar_view_registro);

        final EditText usuario_edit = (EditText) findViewById(R.id.usuario);
        usuario_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    action_foro_registro_usuario();
                    return true;
                }
                return false;
            }
        });

        final String responsabilidad = "http://rjapps.x10host.com/responsabilidad.html";
        final TextView politica_edit = (TextView) findViewById(R.id.politica2_text);
        politica_edit.setText(Html.fromHtml("<a href=" + responsabilidad + ">política de privacidad</a>"));
        politica_edit.setMovementMethod(LinkMovementMethod.getInstance());

        final Button boton = (Button) findViewById(R.id.button);
        // Manejador para cambios en el botón (estética)
        usuario_edit.addTextChangedListener(new TextWatcher() {
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

        // Al hacer click en el botón de continuar con el registro
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action_foro_registro_usuario();
            }
        });
    }

    protected void action_foro_registro_usuario() {
        // Escondemos teclado
        final EditText usuario_edit = (EditText) findViewById(R.id.usuario);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usuario_edit.getWindowToken(), 0);
        // Comprobamos si el usuario está escrito correctamente
        usuario = usuario_edit.getText().toString().trim();
        if (usuario.length() < 3) {
            Toast.makeText(getApplicationContext(), R.string.usuarioError, Toast.LENGTH_SHORT).show();
            return;
        }
        String usuario_minus = usuario.toLowerCase(Locale.getDefault());
        if (usuario_minus.contains("joder") || usuario_minus.contains("puta") ||
                usuario_minus.contains("ostia") || usuario_minus.contains("polla") ||
                usuario_minus.contains("imbecil") || usuario_minus.contains("poya") ||
                usuario_minus.contains("subnormal") || usuario_minus.contains("mierda") ||
                usuario_minus.contains("cabron") || usuario_minus.contains("coño") ||
                usuario_minus.contains("maric") || usuario_minus.contains("marik")) {
            Toast.makeText(getApplicationContext(), R.string.noPalabrotas, Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mURL = "http://rjapps.x10host.com/comprobar_usuario.php?usuario=" + URLEncoder.encode(usuario, "UTF-8");
            mURL = mURL.replace(" ", "%20");
            // Chequear si está la conexión a Internet activa
            if (!checkInternet()) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                return;
            }
            // Comprobamos el usuario
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
    //Tarea para enviar registro
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
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
                error = true;
            }
            if (resultado.equals("-1")) {
                error = true;
            } else if (resultado.equals("-2")) {
                Toast.makeText(getApplicationContext(), R.string.usuarioEnUso, Toast.LENGTH_LONG).show();
                pantalla_cargando.setVisibility(View.GONE);
                return;
            }
            if (error) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
            } else {
                // Enviamos el usuario a la nueva actividad (ForoRegistroEmail)
                Intent intent = new Intent(ForoRegistroUsuario.this, ForoRegistroEmail.class);
                intent.putExtra("usuario", usuario);
                // Abrimos nueva actividad
                startActivity(intent);
            }
            pantalla_cargando.setVisibility(View.GONE);
        }
    }
}
