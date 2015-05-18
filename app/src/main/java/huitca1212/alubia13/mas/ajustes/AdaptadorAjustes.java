package huitca1212.alubia13.mas.ajustes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class AdaptadorAjustes extends ArrayAdapter<TitularAjustes> {

    Activity context;
    TitularAjustes[] datos;

    AdaptadorAjustes(Activity context, TitularAjustes[] datos) {
        super(context, R.layout.listitem_ajustes, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_ajustes, null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.LblTituloAjustes);
        lblTitulo.setText(datos[position].getTitulo());

        TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblSubtituloAjustes);
        lblSubtitulo.setText(datos[position].getSubtitulo());

        return (item);
    }
}
