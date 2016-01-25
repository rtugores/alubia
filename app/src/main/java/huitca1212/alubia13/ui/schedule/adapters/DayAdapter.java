package huitca1212.alubia13.ui.schedule.adapters;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Schedule;

public class DayAdapter extends ArrayAdapter<Schedule> {

    private Activity context;
    private Schedule[] datos;

    public DayAdapter(Activity context, Schedule[] datos) {
        super(context, R.layout.layout_day_item, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String hora, titulo;
        LayoutInflater inflater = context.getLayoutInflater();

        View item = inflater.inflate(R.layout.layout_day_item, null);
        TextView lblTitulo = (TextView) item.findViewById(R.id.LblTitulo);

        if (datos[position].getTitulo().contains("-")) {
            if (Character.isDigit(datos[position].getTitulo().charAt(14))) {
                hora = datos[position].getTitulo().substring(0, 25);
                titulo = datos[position].getTitulo().substring(25);
            } else {
                hora = datos[position].getTitulo().substring(0, 11);
                titulo = datos[position].getTitulo().substring(11);
            }
        } else {
            hora = datos[position].getTitulo().substring(0, 5);
            titulo = datos[position].getTitulo().substring(5);
        }

        lblTitulo.setText(Html.fromHtml("<font color='#B40431'>" + hora + "</font>" + titulo));

        TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblSubTitulo);
        lblSubtitulo.setText(datos[position].getSubtitulo());

        return (item);
    }
}
