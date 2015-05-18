package huitca1212.alubia13.penyas;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class AdaptadorPenyas extends ArrayAdapter<TitularPenyas> {

    Activity context;
    TitularPenyas[] datos;

    AdaptadorPenyas(Activity context, TitularPenyas[] datos) {
        super(context, R.layout.listitem_penyas, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_penyas, null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.LblTitulo);
        lblTitulo.setText(datos[position].getTitulo());
        Drawable img;

        switch (position) {
            case 0:
                img = lblTitulo.getResources().getDrawable(R.drawable.b12_p);
                break;
            case 1:
                img = lblTitulo.getResources().getDrawable(R.drawable.bbdoble_p);
                break;
            case 2:
                img = lblTitulo.getResources().getDrawable(R.drawable.birlybirloke_p);
                break;
            case 4:
                img = lblTitulo.getResources().getDrawable(R.drawable.blanconegro_p);
                break;
            case 6:
                img = lblTitulo.getResources().getDrawable(R.drawable.desfase_p);
                break;
            case 7:
                img = lblTitulo.getResources().getDrawable(R.drawable.descoloke_p);
                break;
            case 8:
                img = lblTitulo.getResources().getDrawable(R.drawable.dislokey_p);
                break;
            case 11:
                img = lblTitulo.getResources().getDrawable(R.drawable.embarazo_p);
                break;
            case 13:
                img = lblTitulo.getResources().getDrawable(R.drawable.fbi_p);
                break;
            case 14:
                img = lblTitulo.getResources().getDrawable(R.drawable.imperfectos_p);
                break;
            case 15:
                img = lblTitulo.getResources().getDrawable(R.drawable.indis_p);
                break;
            case 16:
                img = lblTitulo.getResources().getDrawable(R.drawable.jaia_p);
                break;
            case 18:
                img = lblTitulo.getResources().getDrawable(R.drawable.kachi_chirin_p);
                break;
            case 21:
                img = lblTitulo.getResources().getDrawable(R.drawable.kamensoky_p);
                break;
            case 23:
                img = lblTitulo.getResources().getDrawable(R.drawable.kelnozz_ceda_p);
                break;
            case 25:
                img = lblTitulo.getResources().getDrawable(R.drawable.coral_p);
                break;
            case 27:
                img = lblTitulo.getResources().getDrawable(R.drawable.loscolgaos_p);
                break;
            case 28:
                img = lblTitulo.getResources().getDrawable(R.drawable.tocapelotas_p);
                break;
            case 29:
                img = lblTitulo.getResources().getDrawable(R.drawable.motokaskaos_p);
                break;
            case 30:
                img = lblTitulo.getResources().getDrawable(R.drawable.nosting_p);
                break;
            case 31:
                img = lblTitulo.getResources().getDrawable(R.drawable.brujas_p);
                break;
            case 32:
                img = lblTitulo.getResources().getDrawable(R.drawable.pk2_p);
                break;
            case 33:
                img = lblTitulo.getResources().getDrawable(R.drawable.pocos_pero_locos_p);
                break;
            case 34:
                img = lblTitulo.getResources().getDrawable(R.drawable.psicosys_p);
                break;
            case 36:
                img = lblTitulo.getResources().getDrawable(R.drawable.rambosteroides_p);
                break;
            case 37:
                img = lblTitulo.getResources().getDrawable(R.drawable.rockambole_p);
                break;
            case 39:
                img = lblTitulo.getResources().getDrawable(R.drawable.taboo_p);
                break;
            case 41:
                img = lblTitulo.getResources().getDrawable(R.drawable.tibuky_p);
                break;
            case 43:
                img = lblTitulo.getResources().getDrawable(R.drawable.vaganzia_pura_p);
                break;
            case 44:
                img = lblTitulo.getResources().getDrawable(R.drawable.vankenoven_p);
                break;
            case 45:
                img = lblTitulo.getResources().getDrawable(R.drawable.yaestamostodos_p);
                break;
            case 46:
                img = lblTitulo.getResources().getDrawable(R.drawable.yokers_p);
                break;
            case 47:
                img = lblTitulo.getResources().getDrawable(R.drawable.zumbagaritos_p);
                break;
            default:
                img = lblTitulo.getResources().getDrawable(R.drawable.ic_launcher2);
                break;
        }
        lblTitulo.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        return (item);
    }
}
