package huitca1212.alubia13.utils;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.content.Context;
import android.os.Handler;
import android.view.View;

public class AdsAndAnalytics {

	public static void setAnalytics(Context ctx) {
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(ctx);
		analytics.setLocalDispatchPeriod(1800);
		Tracker tracker = analytics.newTracker("UA-42496077-1");
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}

	public static void loadAds(final AdView adView) {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				AdRequest adRequest = new AdRequest.Builder().build();
				adView.loadAd(adRequest);
				adView.setAdListener(new AdListener() {
					@Override
					public void onAdLoaded() {
						adView.setVisibility(View.VISIBLE);
					}
				});
			}
		}, 200);
	}
}
