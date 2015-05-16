package huitca1212.alubia13.foro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

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
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;
import huitca1212.alubia13.masClases.VariasFunciones;
import huitca1212.alubia13.masClases.*;

public class Foro extends Activity {

    //Declaramos variables
    private String jsonResult;
    private String mURL;
    private ListView listView;
    private LinearLayout layout;
    private String comentario;
    private EditText comentario_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro);

        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        listView = (ListView) findViewById(R.id.listView1);
        comentario_id = (EditText) this.findViewById(R.id.comentario);
        Button actualizar_id = (Button) this.findViewById(R.id.actualizar);
        Button enviar_id = (Button) findViewById(R.id.enviar);

        // No se abre el teclado al entrar en el foro
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
                    Toast.makeText(getApplicationContext(), "Error con la recuperacion de información, verifica tu conexión a Internet.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //====================================================================================================================
    //Comprobar si el comentario es adecuado
    //====================================================================================================================
    public boolean checkComentario(String comentario){
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
            Toast.makeText(getApplicationContext(), "No está permitido escribir palabrotas", Toast.LENGTH_SHORT).show();
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
        if (v.getId()==R.id.listView1) {
            menu.add(Menu.NONE, 0, 0, "Denunciar comentario");
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        if (menuItemIndex == 0) {
            // Comprobamos conexión a Internet para denunciar comentario
            if (!checkInternet()) {
                Toast.makeText(getApplicationContext(), "Necesitas conexión a Internet para denunciar el comentario.", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Error al denunciar el comentario.", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Comentario denunciado correctamente. Será revisado por el admin.", Toast.LENGTH_SHORT).show();
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
        boolean error_comentario = false;
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
                httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                error_comentario = true;
            } catch (IOException e) {
                e.printStackTrace();
                error_comentario = true;
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            httpclient.getConnectionManager().shutdown();
            layout.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(String result) {
            if (error_comentario) {
                Toast.makeText(getApplicationContext(), "El comentario no se pudo enviar. Revisa tu conexión a Internet.", Toast.LENGTH_LONG).show();
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
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            layout.setVisibility(View.GONE);
            // Dibujamos la lista de comentarios
            ListDrawer();
        }
    }

    //====================================================================================================================
    // Comprobamos conexión a Internet y descargamos lista de comentarios
    //====================================================================================================================
    public void accessWebService() {
        if (!checkInternet()) {
            Toast.makeText(getApplicationContext(), "Necesitas conexión a Internet para ver el foro.", Toast.LENGTH_LONG).show();
            layout.setVisibility(View.GONE);
        }
        else {
            JsonReadTask task = new JsonReadTask();
            // passes values for the urls string array
            String url = "http://rjapps.x10host.com/descargar_comentarios.php";
            task.execute(url);
        }
    }

    //====================================================================================================================
    // Decodifica la información JSON e imprime los comentarios
    //====================================================================================================================
    public void ListDrawer() {

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("foro");

            TitularForo[] datos = new TitularForo[jsonMainNode.length()];

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String usuario_penya;
                String usuario = jsonChildNode.optString("usuario");
                String penya = jsonChildNode.optString("penya");
                String comentario = jsonChildNode.optString("comentario");
                String fecha = jsonChildNode.optString("fecha");
                String vip = jsonChildNode.optString("vip");
                String ban = jsonChildNode.optString("ban");
                String id = jsonChildNode.optString("id");
                if (penya.length()>1) {
                    usuario_penya = usuario + " (" + penya + ")";
                }
                else{
                    usuario_penya = usuario;
                }
                datos[i] = new TitularForo(usuario_penya, comentario, fecha, vip, ban, id);
            }

            AdaptadorForo simpleAdapter = new AdaptadorForo(this, datos, listView);
            listView.setAdapter(simpleAdapter);
            registerForContextMenu(listView);

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