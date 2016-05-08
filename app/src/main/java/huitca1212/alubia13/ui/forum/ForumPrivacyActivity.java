package huitca1212.alubia13.ui.forum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import huitca1212.alubia13.R;

public class ForumPrivacyActivity extends Activity {
	private final String PRIVACY_URL = "http://rjapps.x10host.com/responsabilidad.html";

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, ForumPrivacyActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forum_privacy);

		WebView webView = (WebView)this.findViewById(R.id.webView);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl(PRIVACY_URL);
	}
}