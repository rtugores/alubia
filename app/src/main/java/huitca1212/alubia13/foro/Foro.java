package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import java.net.URLEncoder;

import huitca1212.alubia13.R;
import huitca1212.alubia13.mas.ajustes.Ajustes;
import huitca1212.alubia13.masClases.SendDenuncia;

public class Foro extends Activity {

    //Declaramos variables
    private String invitado, comentario, jsonResult, mURL;
    private ListView listView;
    private LinearLayout layout;
    private EditText comentario_id;
    boolean error = false;
    public static Activity foro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro);

        foro = this;

        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        listView = (ListView) findViewById(R.id.listView1);
        LinearLayout barra_comentar = (LinearLayout) findViewById(R.id.barra_comentar);
        comentario_id = (EditText) this.findViewById(R.id.comentario);
        Button actualizar_id = (Button) this.findViewById(R.id.actualizar);
        Button enviar_id = (Button) findViewById(R.id.enviar);

        // No se abre el teclado al entrar en el foro
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // ¿Es el usuario invitado?
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            invitado = extras.getString("invitado");
        } else {
            invitado = (String) savedInstanceState.getSerializable("invitado");
        }
        if (invitado != null && invitado.equals("OK")) {
            barra_comentar.setVisibility(View.GONE);
        }

        // Comprobamos conexión a Internet y descargamos lista de comentarios
        accessWebService();

        // Al hacer click al EditText donde pondremos el comentario
        comentario_id.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                listView.setStackFromBottom(true);
            }
        });

        // Al hacer click en el botón de actualizar del "ActionBar"
        actualizar_id.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Comprobamos conexión a Internet y descargamos lista de comentarios
                accessWebService();
            }
        });

        // Al hacer click en el botón de enviar comentario
        enviar_id.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Checkear comentario
                comentario = comentario_id.getText().toString().trim();
                if (!checkComentario(comentario))
                    return;
                // Tomamos el usuario registrado (del SharedPreferences)
                String usuario = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", "");
                try {
                    // Preparamos la URL
                    mURL = "http://rjapps.x10host.com/anhadir_comentario.php?usuario=" + URLEncoder.encode(usuario, "UTF-8") +
                            "&comentario=" + URLEncoder.encode(comentario, "UTF-8");
                    mURL = mURL.replace(" ", "%20");
                    //Enviamos el comentario
                    SendCommentRefresh enviar = new SendCommentRefresh();
                    enviar.execute(mURL);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (invitado != null && invitado.equals("OK")) {
            return false;
        }
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ajustes:
                Intent intent = new Intent(Foro.this, Ajustes.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //====================================================================================================================
    //Comprobar si el comentario es adecuado
    //====================================================================================================================
    public boolean checkComentario(String comentario) {
        // Comentario vacío
        if (comentario.matches("")) {
            comentario_id.setText("");
            return false;
        }
        // Comentario con palabrotas
        String comentario_minus = comentario.toLowerCase();
        if (comentario_minus.contains("joder") || comentario_minus.contains("puta") ||
                comentario_minus.contains("ostia") || comentario_minus.contains("polla") ||
                comentario_minus.contains("imbecil") || comentario_minus.contains("poya") ||
                comentario_minus.contains("subnormal") || comentario_minus.contains("mierda") ||
                comentario_minus.contains("cabron") || comentario_minus.contains("coño") ||
                comentario_minus.contains("maric") || comentario_minus.contains("marik")) {
            Toast.makeText(getApplicationContext(), R.string.noPalabrotas, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //====================================================================================================================
    //Función para poner un menú una vez pulsamos largo un item del listview
    //====================================================================================================================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (invitado != null && invitado.equals("NOK")) {
            if (v.getId() == R.id.listView1) {
                menu.add(Menu.NONE, 0, 0, R.string.denunciarComentario);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        if (menuItemIndex == 0) {
            // Comprobamos conexión a Internet para denunciar comentario
            if (!checkInternet()) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                return false;
            }
            TextView id_den = (TextView) (info.targetView).findViewById(R.id.id_text);
            String id = id_den.getText().toString();
            try {
                // Preparamos la URL
                mURL = "http://rjapps.x10host.com/denunciar_comentario.php?id=" + URLEncoder.encode(id, "UTF-8");
                //Enviamos la deuncia
                SendDenuncia enviar = new SendDenuncia(this, layout, mURL);
                enviar.execute(mURL);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
            }
        }
        return true;
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
    //Tarea para enviar comentario y recargar la lista de comentarios
    //====================================================================================================================
    private class SendCommentRefresh extends AsyncTask<String, Void, String> {
        HttpClient httpclient = new DefaultHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layout.setVisibility(View.VISIBLE);
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
            layout.setVisibility(View.GONE);
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
                comentario_id.setText("");
                layout.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.usuarioBloqueado, Toast.LENGTH_LONG).show();
                return;
            }
            if (error) {
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                layout.setVisibility(View.GONE);
            } else {
                comentario_id.setText("");
                // Actualizamos la lista de comentarios
                accessWebService();
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
                Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
                return;
            }
            // Dibujamos la lista de comentarios
            listDrawer();
        }
    }

    //====================================================================================================================
    // Comprobamos conexión a Internet y descargamos lista de comentarios
    //====================================================================================================================
    public void accessWebService() {
        if (!checkInternet()) {
            Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
            layout.setVisibility(View.GONE);
        } else {
            JsonReadTask task = new JsonReadTask();
            // passes values for the urls string array
            String url = "http://rjapps.x10host.com/descargar_comentarios.php";
            task.execute(url);
        }
    }

    //====================================================================================================================
    // Decodifica la información JSON e imprime los comentarios
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
            JSONArray jsonMainNode = jsonResponse.optJSONArray("foro");

            TitularForo[] datos = new TitularForo[jsonMainNode.length()];

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String usuario = jsonChildNode.optString("usuario");
                String penya = jsonChildNode.optString("penya");
                String comentario = jsonChildNode.optString("comentario");
                String fecha = jsonChildNode.optString("fecha");
                String vip = jsonChildNode.optString("vip");
                String ban = jsonChildNode.optString("ban");
                String id = jsonChildNode.optString("id");
                datos[i] = new TitularForo(usuario, penya, comentario, fecha, vip, ban, id);
            }

            AdaptadorForo simpleAdapter = new AdaptadorForo(this, datos, listView);
            listView.setAdapter(simpleAdapter);
            registerForContextMenu(listView);

        } catch (JSONException e) {
            e.printStackTrace();
            error = true;
        } catch (NullPointerException e) {
            e.printStackTrace();
            error = true;
        }
        if (error) {
            Toast.makeText(getApplicationContext(), R.string.error_internet, Toast.LENGTH_LONG).show();
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