package huitca1212.alubia13.ui.schedule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ScheduleBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.ui.schedule.adapters.ScheduleAdapter;
import huitca1212.alubia13.utils.Analytics;

public class ScheduleActivity extends AppCompatActivity implements ListView.OnItemClickListener {

	protected ScheduleWrapper scheduleWrapper;
	private LinearLayoutManager mLayoutManager;
	private ScheduleAdapter adapter;
	@Bind(R.id.schedule_list) RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		ButterKnife.bind(this);

		Analytics.setAnalytics(this);
		setDefaultAdapter();
		getSchedule();
	}

	private void setDefaultAdapter() {
		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);
		adapter = new ScheduleAdapter();
		recyclerView.setAdapter(adapter);
	}

	private void getSchedule() {
		ScheduleBusiness.getBackendScheduleContent(new AllBusinessListener<ScheduleWrapper>() {
			@Override
			public void onServerSuccess(ScheduleWrapper scheduleWrapper) {
				ScheduleActivity.this.scheduleWrapper = scheduleWrapper;
				adapter.updateList(scheduleWrapper.getScheduleDays());
			}

			@Override
			public void onFailure(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Toast.makeText(ScheduleActivity.this, "Error", Toast.LENGTH_SHORT).show();
				}
			}
		});
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
