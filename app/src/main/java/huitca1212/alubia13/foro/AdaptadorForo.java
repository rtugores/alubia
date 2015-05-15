package huitca1212.alubia13.foro;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import huitca1212.alubia13.R;

class AdaptadorForo extends ArrayAdapter<TitularForo> {

    Activity context;
    TitularForo[] datos;
    ListView listView;

    public AdaptadorForo(Activity context, TitularForo[] datos, ListView listView) {
        super(context, R.layout.listitem_foro, datos);
        this.context = context;
        this.datos = datos;
        this.listView = listView;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_foro, null);

        TextView usuario = (TextView) item.findViewById(R.id.usuario_text);
        TextView comentario = (TextView) item.findViewById(R.id.comentario_text);
        TextView fecha = (TextView) item.findViewById(R.id.fecha_text);
        TextView id = (TextView) item.findViewById(R.id.id_text);
        View separador = item.findViewById(R.id.separador);

        id.setText(datos[position].getId());
        usuario.setText(datos[position].getUsuario());
        if (position > 0) {
            if (datos[position - 1].getUsuario().equals(datos[position].getUsuario())) {
                // Usuario del anterior mensaje es el mismo, quito separador y nombre
                separador.setVisibility(View.GONE);
                usuario.setVisibility(View.GONE);
            }
        }
        if (datos[position].getVip().equals("1")) {
            usuario.setText(datos[position].getUsuario() + " - VIP");
            usuario.setTextColor(0xFFDF013A);
            item.setBackgroundColor(0xFFEFE4B0);
        } else if (datos[position].getVip().equals("2")) {
            usuario.setGravity(Gravity.CENTER);
            comentario.setTypeface(null, Typeface.BOLD);
            item.setBackgroundColor(0xFFCCFFFF);
        } else {
            usuario.setText(datos[position].getUsuario());
        }

        if (datos[position].getBan().equals("1")) {
            comentario.setText("El comentario ha sido baneado por infringir las normas del foro.");
            comentario.setTextColor(0xFFFF0000);
        } else {
            comentario.setText(datos[position].getComentario());
        }

        fecha.setText(datos[position].getFecha());

        return (item);
    }
}