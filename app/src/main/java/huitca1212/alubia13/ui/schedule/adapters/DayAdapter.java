package huitca1212.alubia13.ui.schedule.adapters;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.model.schedule.Schedule;

public class DayAdapter extends ArrayAdapter<Schedule> {

	private Activity context;
	private Schedule[] data;

	public DayAdapter(Activity context, Schedule[] data) {
		super(context, R.layout.layout_day_item, data);
		this.context = context;
		this.data = data;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item;
		if (convertView == null) {
			item = inflater.inflate(R.layout.layout_day_item, parent, false);
		} else {
			item = convertView;
		}
		TextView lblTitle = (TextView)item.findViewById(R.id.LblTitulo);
		TextView lblSubtitle = (TextView)item.findViewById(R.id.LblSubTitulo);

		String time;
		String title;
		if (data[position].getTitle().contains("-")) {
			if (Character.isDigit(data[position].getTitle().charAt(14))) {
				time = data[position].getTitle().substring(0, 25);
				title = data[position].getTitle().substring(25);
			} else {
				time = data[position].getTitle().substring(0, 11);
				title = data[position].getTitle().substring(11);
			}
		} else {
			time = data[position].getTitle().substring(0, 5);
			title = data[position].getTitle().substring(5);
		}

		lblTitle.setText(Html.fromHtml("<font color='#B40431'>" + time + "</font>" + title));
		lblSubtitle.setText(data[position].getSubtitle());

		return item;
	}
}
