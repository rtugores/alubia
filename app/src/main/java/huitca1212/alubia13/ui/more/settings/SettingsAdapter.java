package huitca1212.alubia13.ui.more.settings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Setting;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ScheduleViewHolder> implements View.OnClickListener {
	public ArrayList<Setting> items = new ArrayList<>();
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

	public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.setting_title) TextView title;
		@Bind(R.id.setting_subtitle) TextView subtitle;

		public ScheduleViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	@Override
	public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.layout_settings_item, parent, false);
		itemView.setOnClickListener(this);
		return new ScheduleViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ScheduleViewHolder scheduleViewHolder, int position) {
		scheduleViewHolder.title.setText(items.get(position).getTitle());
		scheduleViewHolder.subtitle.setText(items.get(position).getSubtitle());
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public void updateList(ArrayList<Setting> items) {
		this.items = items;
		notifyDataSetChanged();
	}
}