package huitca1212.alubia13.ui.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ScheduleBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.ui.schedule.adapters.ScheduleAdapter;
import huitca1212.alubia13.utils.Analytics;

public class ScheduleActivity extends AppCompatActivity {

	protected ScheduleWrapper scheduleWrapper;
	private LinearLayoutManager mLayoutManager;
	private ScheduleAdapter adapter;
	@Bind(R.id.schedule_list) RecyclerView recyclerView;
	@Bind(R.id.schedule_title) TextView scheduleTitle;

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
		recyclerView.setLayoutManager(mLayoutManager);
		adapter = new ScheduleAdapter();
		recyclerView.setAdapter(adapter);
	}

	private void getSchedule() {
		ScheduleBusiness.getScheduleContent(new AllBusinessListener<ScheduleWrapper>() {
			@Override
			public void onDatabaseSuccess(final ScheduleWrapper scheduleWrapper) {
				if (scheduleWrapper != null) {
					ScheduleActivity.this.scheduleWrapper = scheduleWrapper;
					scheduleTitle.setText(scheduleWrapper.getTitle());
					adapter.updateList(scheduleWrapper.getScheduleDays());
					adapter.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							int positionSelected = recyclerView.getChildAdapterPosition(v);
							if (scheduleWrapper.getScheduleDays().get(positionSelected).getDay() == null) {
								ScheduleInfoActivity.startActivity(ScheduleActivity.this,
										scheduleWrapper.getScheduleDays().get(positionSelected).getDescription());
							} else {
								ScheduleDayActivity.startActivity(ScheduleActivity.this,
										scheduleWrapper.getScheduleDays().get(positionSelected));
							}
						}
					});
				}
			}

			@Override
			public void onServerSuccess(final ScheduleWrapper scheduleWrapper) {
				ScheduleActivity.this.scheduleWrapper = scheduleWrapper;
				scheduleTitle.setText(scheduleWrapper.getTitle());
				adapter.updateList(scheduleWrapper.getScheduleDays());
				adapter.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int positionSelected = recyclerView.getChildAdapterPosition(v);
						if (scheduleWrapper.getScheduleDays().get(positionSelected).getDay() == null) {
							ScheduleInfoActivity.startActivity(ScheduleActivity.this,
									scheduleWrapper.getScheduleDays().get(positionSelected).getDescription());
						} else {
							ScheduleDayActivity.startActivity(ScheduleActivity.this,
									scheduleWrapper.getScheduleDays().get(positionSelected));
						}
					}
				});
				DatabaseFunctions.setDatabaseScheduleWrapper(scheduleWrapper);
			}

			@Override
			public void onFailure(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Toast.makeText(ScheduleActivity.this, "Error", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
