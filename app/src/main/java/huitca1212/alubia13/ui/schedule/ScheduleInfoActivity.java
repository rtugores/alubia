package huitca1212.alubia13.ui.schedule;

import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.utils.AdsAndAnalytics;

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
		AdsAndAnalytics.loadAds(adView);
	}
}