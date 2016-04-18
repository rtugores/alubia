package huitca1212.alubia13.utils;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Analytics {

	public static void setAnalytics(Context ctx){
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(ctx);
		analytics.setLocalDispatchPeriod(1800);
		Tracker tracker = analytics.newTracker("UA-42496077-1");
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}
}
