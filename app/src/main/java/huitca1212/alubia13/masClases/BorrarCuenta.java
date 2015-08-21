package huitca1212.alubia13.masClases;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
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

import huitca1212.alubia13.R;

//====================================================================================================================
//Tarea para Borrar Cuenta
//====================================================================================================================
public class BorrarCuenta extends AsyncTask<String, Void, String> {
    protected boolean error = false;
    protected HttpClient httpclient = new DefaultHttpClient();
    protected Context contexto;
    protected LinearLayout layout;
    protected String mURL;
    private String jsonResult;

    public BorrarCuenta(Context contexto, LinearLayout layout, String mURL) {
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
        if (error) {
            Toast.makeText(contexto, R.string.error_internet, Toast.LENGTH_LONG).show();
            return;
        }
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
            Toast.makeText(contexto, R.string.cuentaYaEliminada, Toast.LENGTH_LONG).show();
            layout.setVisibility(View.GONE);
            return;
        }
        if (error) {
            Toast.makeText(contexto, R.string.error_internet, Toast.LENGTH_LONG).show();
            layout.setVisibility(View.GONE);
        } else {
            Toast.makeText(contexto, R.string.cuentaEliminada, Toast.LENGTH_SHORT).show();
            contexto.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                    .edit()
                    .putString("username", "")
                    .commit();
            contexto.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean("notregister", true)
                    .commit();
        }
    }
}