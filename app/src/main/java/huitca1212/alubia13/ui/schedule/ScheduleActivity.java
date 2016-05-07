package huitca1212.alubia13.ui.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ScheduleBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.schedule.ScheduleWrapper;
import huitca1212.alubia13.ui.schedule.adapters.ScheduleAdapter;
import huitca1212.alubia13.utils.AdsAndAnalytics;
import huitca1212.alubia13.utils.Notifications;

public class ScheduleActivity extends AppCompatActivity {

	protected ScheduleWrapper scheduleWrapper;
	private LinearLayoutManager mLayoutManager;
	private ScheduleAdapter adapter;
	@Bind(R.id.progressbar_view) ViewGroup progressbarView;
	@Bind(R.id.schedule_list) RecyclerView recyclerView;
	@Bind(R.id.schedule_title) TextView scheduleTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		ButterKnife.bind(this);

		AdsAndAnalytics.setAnalytics(this);
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
		progressbarView.setVisibility(View.VISIBLE);
		ScheduleBusiness.getScheduleContent(this, new AllBusinessListener<ScheduleWrapper>() {
			@Override
			public void onDatabaseSuccess(ScheduleWrapper scheduleWrapper) {
				progressbarView.setVisibility(View.GONE);
				drawList(scheduleWrapper);
			}

			@Override
			public void onServerSuccess(ScheduleWrapper scheduleWrapper) {
				progressbarView.setVisibility(View.GONE);
				drawList(scheduleWrapper);
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showToast(ScheduleActivity.this, getString(R.string.common_no_internet));
				}
			}
		});
	}

	private void drawList(final ScheduleWrapper scheduleWrapper) {
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
