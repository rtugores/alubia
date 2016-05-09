package huitca1212.alubia13.ui.more.settings;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Setting;

public class SettingsAdapter extends ArrayAdapter<Setting> {

    Activity context;
    Setting[] datos;

    SettingsAdapter(Activity context, Setting[] datos) {
        super(context, R.layout.layout_settings_item, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_settings_item, null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.setting_title);
        lblTitulo.setText(datos[position].getTitulo());

        TextView lblSubtitulo = (TextView) item.findViewById(R.id.setting_subtitle);
        lblSubtitulo.setText(datos[position].getSubtitulo());

        return (item);
    }
}
