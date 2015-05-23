package huitca1212.alubia13.foro;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
        TextView penya = (TextView) item.findViewById(R.id.penya_text);
        TextView comentario = (TextView) item.findViewById(R.id.comentario_text);
        TextView fecha = (TextView) item.findViewById(R.id.fecha_text);
        TextView id = (TextView) item.findViewById(R.id.id_text);

        id.setText(datos[position].getId());
        usuario.setText(datos[position].getUsuario());
        FrameLayout frame_item_layout = (FrameLayout) item.findViewById(R.id.frame_item_foro);
        LinearLayout item_layout = (LinearLayout) frame_item_layout.findViewById(R.id.layout_item_foro);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) item_layout.getLayoutParams();
        params.setMargins(8, 11, 8, 0);
        item_layout.setLayoutParams(params);
        if (position > 0) {
            if (datos[position - 1].getUsuario().equals(datos[position].getUsuario())) {
                // Usuario del anterior mensaje es el mismo, quito nombre y peña
                usuario.setVisibility(View.GONE);
                penya.setVisibility(View.GONE);
                params.setMargins(8, 0, 8, 0);
                item_layout.setLayoutParams(params);
            }
        }
        if (datos[position].getVip().equals("1")) {
            usuario.setText(datos[position].getUsuario());
            penya.setText(datos[position].getPenya());
            usuario.setTextColor(0xFFDF013A);
            item_layout.setBackgroundResource(R.drawable.d_foro_vip);
        } else if (datos[position].getVip().equals("2")) {
            usuario.setGravity(Gravity.CENTER);
            comentario.setTypeface(null, Typeface.BOLD);
            item_layout.setBackgroundResource(R.drawable.d_foro_admin);
        } else {
            usuario.setText(datos[position].getUsuario());
            penya.setText(datos[position].getPenya());
        }

        if (datos[position].getBan().equals("1")) {
            comentario.setText("El comentario ha sido bloqueado por infringir las normas del foro.");
            comentario.setTextColor(0xFFFF0000);
        } else {
            comentario.setText(datos[position].getComentario());
        }

        fecha.setText(datos[position].getFecha());

        return (item);
    }
}