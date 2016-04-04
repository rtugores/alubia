package huitca1212.alubia13.ui.schedule;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Schedule;
import huitca1212.alubia13.ui.schedule.adapters.ScheduleAdapter;

public class ScheduleActivity extends AppCompatActivity implements ListView.OnItemClickListener {

	public static GoogleAnalytics analytics;
	public static Tracker tracker;
	@Bind(R.id.schedule_list) ViewGroup scheduleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		ButterKnife.bind(this);

		setAnalytics();
		setList();
	}

	private void setAnalytics() {
		analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);
		tracker = analytics.newTracker("UA-42496077-1");
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}

	private void setList() {
		ScheduleAdapter adaptador = new ScheduleAdapter(this, new Schedule[]{
				new Schedule(getString(R.string.schedule_greeting)),
				new Schedule(getString(R.string.schedule_queens_ladies)),
				new Schedule(getString(R.string.schedule_friday), getString(R.string.schedule_friday_calendar)),
				new Schedule(getString(R.string.schedule_saturday), getString(R.string.schedule_saturday_calendar)),
				new Schedule(getString(R.string.schedule_sunday), getString(R.string.schedule_sunday_calendar)),
				new Schedule(getString(R.string.schedule_monday), getString(R.string.schedule_monday_calendar)),
				new Schedule(getString(R.string.schedule_race)),
		});
		((ListView)scheduleList).setAdapter(adaptador);
		((ListView)scheduleList).setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case 0:
				Intent intent0 = new Intent(ScheduleActivity.this, GreetingActivity.class);
				startActivity(intent0);
				break;
			case 1:
				Intent intent1 = new Intent(ScheduleActivity.this, QueensLadiesActivity.class);
				startActivity(intent1);
				break;
			case 2:
				Intent intent3 = new Intent(ScheduleActivity.this, FridayActivity.class);
				startActivity(intent3);
				break;
			case 3:
				Intent intent4 = new Intent(ScheduleActivity.this, SaturdayActivity.class);
				intent4.putExtra("day", "saturday");
				startActivity(intent4);
				break;
			case 4:
				Intent intent5 = new Intent(ScheduleActivity.this, SundayActivity.class);
				startActivity(intent5);
				break;
			case 5:
				Intent intent6 = new Intent(ScheduleActivity.this, MondayActivity.class);
				startActivity(intent6);
				break;
			case 6:
				Intent intent7 = new Intent(ScheduleActivity.this, RaceActivity.class);
				startActivity(intent7);
				break;
		}
	}
}
