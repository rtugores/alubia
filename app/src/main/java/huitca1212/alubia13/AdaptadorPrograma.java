package huitca1212.alubia13;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorPrograma extends ArrayAdapter<TitularPrograma> {

    Activity context;
    TitularPrograma[] datos;

    AdaptadorPrograma(Activity context, TitularPrograma[] datos) {
        super(context, R.layout.listitem_programa, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_programa, null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.LblTituloPrograma);
        lblTitulo.setText(datos[position].getTitulo());

        TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblSubtituloPrograma);
        lblSubtitulo.setText(datos[position].getSubtitulo());

        return (item);
    }
}
