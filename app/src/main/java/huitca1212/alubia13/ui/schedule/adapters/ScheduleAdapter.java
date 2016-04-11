package huitca1212.alubia13.ui.schedule.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Schedule;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {

	private Activity context;
	private Schedule[] data;

	public ScheduleAdapter(Activity context, Schedule[] data) {
		super(context, R.layout.layout_schedule_item, data);
		this.context = context;
		this.data = data;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item;
		if (convertView == null) {
			item = inflater.inflate(R.layout.layout_schedule_item, parent, false);
		} else {
			item = convertView;
		}

		TextView lblTitle = (TextView)item.findViewById(R.id.LblTituloPrograma);
		TextView lblSubtitle = (TextView)item.findViewById(R.id.LblSubtituloPrograma);

		lblTitle.setText(data[position].getTitle());
		lblSubtitle.setText(data[position].getSubtitle());

		return (item);
	}
}
