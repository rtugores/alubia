package huitca1212.alubia13.ui.forum;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import huitca1212.alubia13.R;

public class ForumPrivacyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forum_privacy);

		WebView webView = (WebView)this.findViewById(R.id.webView);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("http://rjapps.x10host.com/responsabilidad.html");
	}
}