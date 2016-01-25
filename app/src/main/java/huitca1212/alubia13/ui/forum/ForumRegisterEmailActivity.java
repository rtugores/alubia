package huitca1212.alubia13.ui.forum;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import huitca1212.alubia13.R;

public class ForumRegisterEmailActivity extends AppCompatActivity {

	private boolean error = false;
	private String jsonResult, mURL;
	private LinearLayout progressBar;
	public static Activity forumRegisterEmailActivity;
	private String usuario;
	private String email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_email);

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);

		forumRegisterEmailActivity = this;

		progressBar = (LinearLayout)findViewById(R.id.progressbar_view_registro);

		final EditText email_edit = (EditText)findViewById(R.id.email);
		email_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_NEXT) {
					action_foro_registro_email();
					return true;
				}
				return false;
			}
		});

		final String responsabilidad = "http://rjapps.x10host.com/responsabilidad.html";
		final TextView politica_edit = (TextView)findViewById(R.id.politica2_text);
		politica_edit.setText(Html.fromHtml("<a href=" + responsabilidad + ">política de privacidad</a>"));
		politica_edit.setMovementMethod(LinkMovementMethod.getInstance());

		final Button boton = (Button)findViewById(R.id.register_button);
		// Manejador para cambios en el botón (estética)
		email_edit.addTextChangedListener(new TextWatcher() {
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

		// Tomamos el nombre de usuario de la pantalla anterior
		if (savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			usuario = extras.getString("usuario");
		} else {
			usuario = (String)savedInstanceState.getSerializable("usuario");
		}

		// Al hacer click en el botón de enviar el email
		boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				action_foro_registro_email();
			}
		});
	}

	private void action_foro_registro_email() {
		// Escondemos teclado
		final EditText email_edit = (EditText)findViewById(R.id.email);
		InputMethodManager imm = (InputMethodManager)getSystemService(
				Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(email_edit.getWindowToken(), 0);
		// Comprobamos si el email está escrito correctamente
		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		email = email_edit.getText().toString().trim();
		if (!email.matches(emailPattern)) {
			Toast.makeText(getApplicationContext(), R.string.emailError, Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			mURL = "http://rjapps.x10host.com/comprobar_email.php?email=" + URLEncoder.encode(email, "UTF-8");
			mURL = mURL.replace(" ", "%20");
			// Send email
			SendEmailTask enviar = new SendEmailTask();
			enviar.execute(mURL);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
		}
	}

	//====================================================================================================================
	//Tarea para enviar email
	//====================================================================================================================
	private class SendEmailTask extends AsyncTask<String, Void, String> {
		HttpClient httpclient = new DefaultHttpClient();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
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
			progressBar.setVisibility(View.GONE);
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
			} else if (resultado.equals("1")) { // [IMPORTANTE] SIGNIFICA ERROR POR EL SCRIPT PHP QUE REUTILIZAMOS DEL LOGIN  (comprobar_email.php)
				Toast.makeText(getApplicationContext(), R.string.emailEnUso, Toast.LENGTH_LONG).show();
				progressBar.setVisibility(View.GONE);
				return;
			}
			if (error) {
				Toast.makeText(getApplicationContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
			} else {
				// Enviamos el email a la nueva actividad (ForoRegistroContrasenya)
				Intent intent = new Intent(ForumRegisterEmailActivity.this, ForumRegisterPasswordActivity.class);
				intent.putExtra("usuario", usuario);
				intent.putExtra("email", email);
				// Abrimos nueva actividad
				startActivity(intent);
			}
			progressBar.setVisibility(View.GONE);
		}
	}
}
