package huitca1212.alubia13.ui.schedule.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.schedule.ScheduleDay;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

	ArrayList<ScheduleDay> days = new ArrayList<>();

	public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.schedule_title) TextView title;
		@Bind(R.id.schedule_day) TextView day;

		public ScheduleViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	@Override
	public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.layout_schedule_item, parent, false);
		return new ScheduleViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ScheduleViewHolder scheduleViewHolder, int position) {
		scheduleViewHolder.title.setText(days.get(position).getTitle());
		scheduleViewHolder.day.setText(days.get(position).getDay());
	}

	@Override
	public int getItemCount() {
		return days.size();
	}

	public void updateList(ArrayList<ScheduleDay> days) {
		this.days = days;
		notifyDataSetChanged();
	}
}