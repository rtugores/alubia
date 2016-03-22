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

	Activity context;
	Schedule[] datos;

	public ScheduleAdapter(Activity context, Schedule[] datos) {
		super(context, R.layout.layout_schedule_item, datos);
		this.context = context;
		this.datos = datos;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.layout_schedule_item, null);

		TextView lblTitulo = (TextView)item.findViewById(R.id.LblTituloPrograma);
		lblTitulo.setText(datos[position].getTitle());

		TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubtituloPrograma);
		lblSubtitulo.setText(datos[position].getSubtitle());

		return (item);
	}
}
