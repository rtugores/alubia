package huitca1212.alubia13.ui.schedule.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.schedule.ScheduleEvent;

public class ScheduleDayAdapter extends RecyclerView.Adapter<ScheduleDayAdapter.DayViewHolder> implements View.OnClickListener {

	public ArrayList<ScheduleEvent> events = new ArrayList<>();
	private View.OnClickListener listener;

	@Override
	public void onClick(View v) {
		if (listener != null) {
			listener.onClick(v);
		}
	}

	public void setOnClickListener(View.OnClickListener listener) {
		this.listener = listener;
	}

	public static class DayViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.day_title) TextView event;
		@Bind(R.id.day_subtitle) TextView place;

		public DayViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	@Override
	public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.layout_day_item, parent, false);
		itemView.setOnClickListener(this);
		return new DayViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(DayViewHolder dayViewHolder, int position) {
		dayViewHolder.event.setText(Html.fromHtml("<font color='#B40431'>" + events.get(position).getHour() + "</font> " + events.get(position).getName()));
		dayViewHolder.place.setText(events.get(position).getPlace());
	}

	@Override
	public int getItemCount() {
		return events.size();
	}

	public void updateList(ArrayList<ScheduleEvent> events) {
		this.events = events;
		notifyDataSetChanged();
	}
}