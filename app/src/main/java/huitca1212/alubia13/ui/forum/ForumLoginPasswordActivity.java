package huitca1212.alubia13.ui.forum;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.AsyncTaskListenerInterface;
import huitca1212.alubia13.business.DefaultAsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForumLoginPasswordActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String jsonResult, email, password;
	@Bind(R.id.progressbar_view_registro) LinearLayout progressBar;
	@Bind(R.id.password_edit_text) EditText passwordEditText;
	@Bind(R.id.register_button) Button registerButton;
	@Bind(R.id.olvide_contrasenya) Button forgottenPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_login_password);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		passwordEditText.setOnEditorActionListener(this);
		passwordEditText.addTextChangedListener(this);

		forgottenPassword.setOnClickListener(this);
		registerButton.setOnClickListener(this);

		// Get the email from the last screen
		if (savedInstanceState == null) {
			email = getIntent().getExtras().getString("email");
		} else {
			email = (String)savedInstanceState.getSerializable("email");
		}
	}

	private void actionForumLoginPassword() {
		// Hide keyboard
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);

		password = passwordEditText.getText().toString().trim();
		if (password.length() < 5) {
			Toast.makeText(getApplicationContext(), R.string.forum_error_bad_passwd, Toast.LENGTH_SHORT).show();
			return;
		}
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			@Override
			public void onStart() {
				progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public String onBackground() throws IOException {
				OkHttpClient client = new OkHttpClient();
				String url = "http://rjapps.x10host.com/comprobar_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8") +
						"&contrasenya=" + URLEncoder.encode(password, "UTF-8").replace(" ", "%20");
				Request request = new Request.Builder()
						.url(url)
						.build();
				try {
					Response response = client.newCall(request).execute();
					jsonResult = response.body().string();
					JSONObject jsonResponse = new JSONObject(jsonResult);
					return jsonResponse.optString("resultado");
				} catch (IOException | JSONException | NullPointerException e) {
					return DefaultAsyncTask.ASYNC_TASK_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				progressBar.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Toast.makeText(getApplicationContext(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
						break;
					case "-2":
						Toast.makeText(getApplicationContext(), R.string.forum_error_different_passwd, Toast.LENGTH_LONG).show();
						break;
					default:
						getSharedPreferences("PREFERENCE", MODE_PRIVATE)
								.edit().putString("username", result).commit();
						getSharedPreferences("PREFERENCE", MODE_PRIVATE)
								.edit().putBoolean("notregister", false).commit();

						// Abrimos nueva actividad y cerramos la anterior y esta
						Intent intent = new Intent(ForumLoginPasswordActivity.this, ForumActivity.class);
						intent.putExtra(ForumActivity.INVITED_USER, "NOK");
						startActivity(intent);
						try {
							ForumMenuActivity.foro_inicial.finish();
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						try {
							ForumLoginEmailActivity.foroLoginEmailActivity.finish();
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						finish();
						break;
				}
			}
		}).execute();
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			actionForumLoginPassword();
			return true;
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
			registerButton.setEnabled(true);
			registerButton.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			registerButton.setEnabled(false);
			registerButton.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.olvide_contrasenya) {
			Intent intent = new Intent(ForumLoginPasswordActivity.this, ForumForgottenPasswordActivity.class);
			startActivity(intent);
		} else if (id == R.id.register_button) {
			actionForumLoginPassword();
		}
	}

}
