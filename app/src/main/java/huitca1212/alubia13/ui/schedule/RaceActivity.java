package huitca1212.alubia13.ui.schedule;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;

public class RaceActivity extends AppCompatActivity {

	@Bind(R.id.ad_view) AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_race);
		ButterKnife.bind(this);

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