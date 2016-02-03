package huitca1212.alubia13.ui.forum;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.more.ContactActivity;

public class ForumRegisterCodeActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

	private boolean error = false;
	private String jsonResult, mURL;
	private String user;
	private String email;
	private String password;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressBar;
	@Bind(R.id.register_button) Button registerButton;
	@Bind(R.id.contact_button) Button contactButton;
	@Bind(R.id.code_edit_text) EditText codeEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_code);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		codeEditText.setOnEditorActionListener(this);

		// Take username and other data from the previous activity
		if (savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			user = extras.getString("usuario");
			email = extras.getString("email");
			password = extras.getString("contrasenya");
		} else {
			user = (String)savedInstanceState.getSerializable("usuario");
			email = (String)savedInstanceState.getSerializable("email");
			password = (String)savedInstanceState.getSerializable("contrasenya");
		}
		registerButton.setOnClickListener(this);
		contactButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.register_button) {
			performRegisterCodeAction();
		} else if (id == R.id.contact_button) {
			Intent intent = new Intent(ForumRegisterCodeActivity.this, ContactActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.code_edit_text) {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				performRegisterCodeAction();
				return true;
			}
		}
		return false;
	}

	private void performRegisterCodeAction() {
		String mobile_id = "";

		// Hide keyboard
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(codeEditText.getWindowToken(), 0);

		String code = codeEditText.getText().toString().trim();
		if (code.length() != 10 && code.length() != 0) {
			Toast.makeText(getApplicationContext(), R.string.forum_error_bad_code, Toast.LENGTH_SHORT).show();
			return;
		}
		// Convertimos para que nos entienda el servidor
		if (code.length() == 0) {
			code = "0";
		}
		try {
			mobile_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			mURL = "http://rjapps.x10host.com/comprobar_codigo.php?usuario=" + URLEncoder.encode(user, "UTF-8") +
					"&contrasenya=" + URLEncoder.encode(password, "UTF-8") + "&email=" + URLEncoder.encode(email, "UTF-8") +
					"&codigo_penya=" + URLEncoder.encode(code, "UTF-8") + "&mobile_id=" + URLEncoder.encode(mobile_id, "UTF-8");
			mURL = mURL.replace(" ", "%20");
			// Enviamos el login
			SendLoginAsyncTask enviar = new SendLoginAsyncTask();
			enviar.execute(mURL);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
		}
	}


	private class SendLoginAsyncTask extends AsyncTask<String, Void, String> {
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
			int resultado_int = Integer.parseInt(resultado);
			switch (resultado_int) {
				case -1:
					Toast.makeText(getApplicationContext(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
					progressBar.setVisibility(View.GONE);
					return;
				case -2:
					Toast.makeText(getApplicationContext(), R.string.forum_error_end_user_repeated, Toast.LENGTH_LONG).show();
					progressBar.setVisibility(View.GONE);
					return;
				case -3:
					Toast.makeText(getApplicationContext(), R.string.forum_error_end_email_repeated, Toast.LENGTH_LONG).show();
					progressBar.setVisibility(View.GONE);
					return;
				case -4:
					Toast.makeText(getApplicationContext(), R.string.forum_error_different_code, Toast.LENGTH_LONG).show();
					progressBar.setVisibility(View.GONE);
					return;
			}
			if (error) {
				Toast.makeText(getApplicationContext(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
				progressBar.setVisibility(View.GONE);
			} else {
				// Almacenamos el nombre de usuario en el teléfono
				getSharedPreferences("PREFERENCE", MODE_PRIVATE)
						.edit()
						.putString("username", user)
						.commit();
				// Almacenamos que el usuario ya se ha registrado en el teléfono
				getSharedPreferences("PREFERENCE", MODE_PRIVATE)
						.edit()
						.putBoolean("notregister", false)
						.commit();
				// Abrimos nueva actividad y cerramos las anteriores y esta
				Intent intent = new Intent(ForumRegisterCodeActivity.this, ForumActivity.class);
				intent.putExtra(ForumActivity.INVITED_USER, "NOK");
				startActivity(intent);
				try {
					ForumMenuActivity.forumMenuActivity.finish();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				try {
					ForumRegisterUserActivity.forumRegisterUserActivity.finish();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				try {
					ForumRegisterEmailActivity.forumRegisterEmailActivity.finish();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				try {
					ForumRegisterPasswordActivity.forumRegisterPasswordActivity.finish();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				finish();
			}
		}
	}
}
