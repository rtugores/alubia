package huitca1212.alubia13.ui.forum;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
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

public class ForumForgottenPasswordActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {

	private String jsonResult;
	@Bind(R.id.progressbar_view) LinearLayout progressBar;
	@Bind(R.id.forgotten_email) EditText forgottenEmail;
	@Bind(R.id.forgotten_button) Button forgottenAction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_forgotten_password);
		ButterKnife.bind(this);

		getWindow().setBackgroundDrawableResource(R.drawable.background_image);

		forgottenEmail.setOnEditorActionListener(this);
		forgottenEmail.addTextChangedListener(this);

		forgottenAction.setOnClickListener(this);
	}

	private void recoverPassword() {

		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(forgottenEmail.getWindowToken(), 0);

		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		final String email = forgottenEmail.getText().toString().trim();
		if (!email.matches(emailPattern)) {
			Toast.makeText(this, R.string.emailError, Toast.LENGTH_SHORT).show();
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
				String url = "http://rjapps.x10host.com/olvide_contrasenya.php?email=" + URLEncoder.encode(email, "UTF-8");

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
						Toast.makeText(getApplicationContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
						break;
					case "-2":
						Toast.makeText(getApplicationContext(), R.string.different_email, Toast.LENGTH_LONG).show();
						break;
					default:
						finish();
						Toast.makeText(getApplicationContext(), R.string.forgotten_ok, Toast.LENGTH_LONG).show();
						break;
				}
			}
		}).execute();
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.forgotten_email) {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				recoverPassword();
				return true;
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.forgotten_button) {
			recoverPassword();
		}
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
			forgottenAction.setEnabled(true);
			forgottenAction.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			forgottenAction.setEnabled(false);
			forgottenAction.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}
}
