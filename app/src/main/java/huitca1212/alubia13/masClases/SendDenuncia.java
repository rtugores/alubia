package huitca1212.alubia13.masClases;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

//====================================================================================================================
//Tarea para denunciar comentario
//====================================================================================================================
public class SendDenuncia extends AsyncTask<String, Void, String> {
    protected boolean error_denuncia = false;
    protected HttpClient httpclient = new DefaultHttpClient();
    protected Context contexto;
    protected LinearLayout layout;
    protected String mURL;

    public SendDenuncia(Context contexto, LinearLayout layout, String mURL){
        this.contexto = contexto;
        this.layout = layout;
        this.mURL = mURL;
    }

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
            error_denuncia = true;
        } catch (IOException e) {
            e.printStackTrace();
            error_denuncia = true;
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
        layout.setVisibility(View.GONE);
        if (error_denuncia) {
            Toast.makeText(contexto, "El comentario no se pudo denunciar. Revisa tu conexión a Internet.", Toast.LENGTH_LONG).show();
        }
    }
}