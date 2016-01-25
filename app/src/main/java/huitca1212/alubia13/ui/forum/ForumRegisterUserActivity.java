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
import huitca1212.alubia13.utils.Checkers;

public class ForumRegisterUserActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	boolean error = false;
	private String jsonResult, mURL;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressBar;
	@Bind(R.id.usuario) EditText registerUserEditText;
	@Bind(R.id.register_button) Button registerUserButton;
	@Bind(R.id.politica2_text) TextView politicText;
	public static Activity forumRegisterUserActivity;
	String userString;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_register_user);
		ButterKnife.bind(this);

		forumRegisterUserActivity = this;

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		registerUserEditText.setOnEditorActionListener(this);
		registerUserEditText.addTextChangedListener(this);

		politicText.setText(Html.fromHtml("<a href=\"http://rjapps.x10host.com/responsabilidad.html\">política de privacidad</a>"));
		politicText.setMovementMethod(LinkMovementMethod.getInstance());

		registerUserButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.register_button) {
			action_foro_registro_usuario();
		}
	}

	protected void action_foro_registro_usuario() {

		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(registerUserEditText.getWindowToken(), 0);

		userString = registerUserEditText.getText().toString().trim();
		if (userString.length() < 3) {
			Toast.makeText(getApplicationContext(), R.string.usuarioError, Toast.LENGTH_SHORT).show();
			return;
		}
		if (Checkers.hasStringBadWords(userString)) {
			Toast.makeText(getApplicationContext(), R.string.no_bad_words, Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			mURL = "http://rjapps.x10host.com/comprobar_usuario.php?usuario=" + URLEncoder.encode(userString, "UTF-8");
			mURL = mURL.replace(" ", "%20");
			SendTask enviar = new SendTask();
			enviar.execute(mURL);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.usuario) {
			if (actionId == EditorInfo.IME_ACTION_NEXT) {
				action_foro_registro_usuario();
				return true;
			}
		}
		return false;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().trim().length() > 0) {
			registerUserButton.setEnabled(true);
			registerUserButton.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			registerUserButton.setEnabled(false);
			registerUserButton.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}


	private class SendTask extends AsyncTask<String, Void, String> {
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
			} else if (resultado.equals("-2")) {
				Toast.makeText(getApplicationContext(), R.string.usuarioEnUso, Toast.LENGTH_LONG).show();
				progressBar.setVisibility(View.GONE);
				return;
			}
			if (error) {
				Toast.makeText(getApplicationContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
			} else {

				Intent intent = new Intent(ForumRegisterUserActivity.this, ForumRegisterEmailActivity.class);
				intent.putExtra("usuario", userString);

				startActivity(intent);
			}
			progressBar.setVisibility(View.GONE);
		}
	}
}
