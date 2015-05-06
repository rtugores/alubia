package huitca1212.alubia13;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorNovedades extends ArrayAdapter<TitularPrograma> {

    Activity context;
    TitularPrograma[] datos;

    AdaptadorNovedades(Activity context, TitularPrograma[] datos) {
        super(context, R.layout.listitem_foro, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_foro, null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.usuario_text);
        lblTitulo.setText(datos[position].getTitulo());

        TextView lblSubtitulo2 = (TextView) item.findViewById(R.id.comentario_text);
        lblSubtitulo2.setText(datos[position].getSubtitulo());

        return (item);
    }
}
