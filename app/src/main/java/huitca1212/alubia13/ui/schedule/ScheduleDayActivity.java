package huitca1212.alubia13.ui.schedule;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.schedule.ScheduleDay;
import huitca1212.alubia13.ui.schedule.adapters.ScheduleDayAdapter;
import huitca1212.alubia13.utils.DialogParams;
import huitca1212.alubia13.utils.Dialogs;

public class ScheduleDayActivity extends AppCompatActivity {

	private static final String BUNDLE_DAY_INFO = "BUNDLE_DAY_INFO";
	private LinearLayoutManager mLayoutManager;
	private ScheduleDayAdapter adapter;
	@Bind(R.id.ad_view) AdView adView;
	@Bind(R.id.schedule_list) RecyclerView recyclerView;

	public static void startActivity(Context ctx, ScheduleDay dayInfo) {
		Intent intent = new Intent(ctx, ScheduleDayActivity.class);
		intent.putExtra(BUNDLE_DAY_INFO, dayInfo);
		ctx.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_day);
		ButterKnife.bind(this);

		setDefaultAdapter();

		final ScheduleDay scheduleDay = (ScheduleDay)getIntent().getSerializableExtra(BUNDLE_DAY_INFO);
		if (scheduleDay != null) {
			adapter.updateList(scheduleDay.getEvents());
			adapter.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int positionSelected = recyclerView.getChildAdapterPosition(v);
					DialogParams params = new DialogParams();
					params.setTitle(scheduleDay.getEvents().get(positionSelected).getScheduleEventDetails().getTitle());
					params.setMessage(scheduleDay.getEvents().get(positionSelected).getScheduleEventDetails().getDescription());
					params.setPositiveButton(getString(R.string.common_accept));
					Dialogs.showGenericDialog(ScheduleDayActivity.this, params, null);
				}
			});
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

	private void setDefaultAdapter() {
		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(mLayoutManager);
		adapter = new ScheduleDayAdapter();
		recyclerView.setAdapter(adapter);
	}
}