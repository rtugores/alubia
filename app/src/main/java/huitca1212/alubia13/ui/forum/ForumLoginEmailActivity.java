package huitca1212.alubia13.ui.forum;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class ForumLoginEmailActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener, TextWatcher {

	private String jsonResult, email;
	public static Activity foroLoginEmailActivity;
	@Bind(R.id.progressbar_view_login) LinearLayout progressbarView;
	@Bind(R.id.email) EditText emailBox;
	@Bind(R.id.register_button) Button sendLogin;
	@Bind(R.id.politica2_text) TextView politicText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_login_email);
		ButterKnife.bind(this);

		foroLoginEmailActivity = this;
		getWindow().setBackgroundDrawableResource(R.drawable.background_image);
		emailBox.addTextChangedListener(this);
		emailBox.setOnEditorActionListener(this);

		politicText.setText(Html.fromHtml("<a href=http://rjapps.x10host.com/responsabilidad.html>política de privacidad</a>"));
		politicText.setMovementMethod(LinkMovementMethod.getInstance());

		sendLogin.setOnClickListener(this);
	}

	private void action_foro_login_email() {

		final EditText email_edit = (EditText)findViewById(R.id.email);
		InputMethodManager imm = (InputMethodManager)getSystemService(
				Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(email_edit.getWindowToken(), 0);

		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		email = email_edit.getText().toString().trim();
		if (!email.matches(emailPattern)) {
			Toast.makeText(getApplicationContext(), R.string.emailError, Toast.LENGTH_SHORT).show();
		} else {
			accessWebService();
		}
	}

	private void accessWebService() {
		new DefaultAsyncTask(new AsyncTaskListenerInterface() {
			@Override
			public void onStart() {
				progressbarView.setVisibility(View.VISIBLE);
			}

			@Override
			public String onBackground() throws IOException {
				OkHttpClient client = new OkHttpClient();
				String url = "http://rjapps.x10host.com/comprobar_email.php?email=" + URLEncoder.encode(email, "UTF-8");
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
				progressbarView.setVisibility(View.GONE);
				switch (result) {
					case DefaultAsyncTask.ASYNC_TASK_ERROR:
						Toast.makeText(getApplicationContext(), R.string.internet_error, Toast.LENGTH_LONG).show();
						break;
					case "-2":
						Toast.makeText(getApplicationContext(), R.string.different_email, Toast.LENGTH_LONG).show();
						return;
					default:
						Intent intent = new Intent(ForumLoginEmailActivity.this, ForumLoginPasswordActivity.class);
						intent.putExtra("email", email);
						startActivity(intent);
						break;
				}
			}
		}).execute();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.register_button) {
			action_foro_login_email();
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int id = v.getId();
		if (id == R.id.email) {
			if (actionId == EditorInfo.IME_ACTION_NEXT) {
				action_foro_login_email();
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
			sendLogin.setEnabled(true);
			sendLogin.setBackgroundResource(R.drawable.d_normal_button_blue);
		} else {
			sendLogin.setEnabled(false);
			sendLogin.setBackgroundResource(R.drawable.d_normal_button_gray);
		}
	}
}