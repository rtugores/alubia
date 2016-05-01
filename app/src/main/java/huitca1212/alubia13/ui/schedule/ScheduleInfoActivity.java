package huitca1212.alubia13.ui.schedule;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;

public class ScheduleInfoActivity extends AppCompatActivity {

	private static final String BUNDLE_DAY_INFO = "BUNDLE_DAY_INFO";
	@Bind(R.id.ad_view) AdView adView;
	@Bind(R.id.info_content) TextView infoContent;

	public static void startActivity(Context ctx, String dayInfo) {
		Intent intent = new Intent(ctx, ScheduleInfoActivity.class);
		intent.putExtra(BUNDLE_DAY_INFO, dayInfo);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_info);
		ButterKnife.bind(this);

		String dayInfo = getIntent().getExtras().getString(BUNDLE_DAY_INFO);
		if (dayInfo != null) {
			infoContent.setText(Html.fromHtml(dayInfo));
		}

		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				adView.setVisibility(View.VISIBLE);
			}
		});
	}
}