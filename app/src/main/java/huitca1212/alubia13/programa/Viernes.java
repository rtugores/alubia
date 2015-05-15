package huitca1212.alubia13.programa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.funcionesWeb.VariasFunciones;

public class Viernes extends Activity {

    private ListView lstOpciones;

    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("18:30 Fútbol sala infantil", "Frontón municipal"),
                    new TitularPrograma("23:00 Pregón de fiestas", "Plaza del castillo"),
                    new TitularPrograma("23:30 Coronación de Reinas y Damas", "Plaza del castillo"),
                    new TitularPrograma("00:00 V Alubia Rock", "Calle el Jardín"),
                    new TitularPrograma("00:00 Party Dance", "Salida: Plaza del castillo"),
                    new TitularPrograma("00:30 Verbena", "Explanada de la casa de la cultura")};

    //================================================================
    //==============CODIGO PARA LISTVIEW==============================
    //================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dia);
        //====================
        AdaptadorDias adaptadorDias = new AdaptadorDias(this, datos);
        lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptadorDias);
        //====================
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                crearDialogoAlerta(position).show();
            }
        });
    }

    public Dialog crearDialogoAlerta(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case 0:
                builder.setTitle("Fútbol sala infantil");
                builder.setMessage("Dosis de buen fútbol infantil para que puedan disfrutar " +
                        "tanto los pequeños como los más mayores.");
                break;
            case 1:
                builder.setTitle("Pregon de fiestas");
                builder.setMessage("Anunciamiento a cargo de don José María González Rebollo, médico cardiólogo " +
                        "en el hospital de León.");
                break;
            case 2:
                builder.setTitle("Coronación de Reinas y Damas");
                builder.setMessage("Gran evento donde las Reinas y Damas de la Alubia 2014 " +
                        "toman sus bandas y son coronadas en la plaza del castillo.");
                break;
            case 3:
                builder.setTitle("V Alubia Rock");
                builder.setMessage("Participación de los grupos \"Me la sopla\", " +
                        "\"Alpaka HxC\", \"Seiskafés\" y \"Vagos Permanentes\" en directo. Entrada gratuita.");
                break;
            case 4:
                builder.setTitle("Party Dance");
                builder.setMessage("Recorrido por las calles del pueblo y paradas en los chiringuitos " +
                        "con la \"Supermartxé\".");
                break;
            case 5:
                builder.setTitle("Verbena");
                builder.setMessage("La discoteca móvil \"Toño\" se encarga de dar color a la noche " +
                        "con la música de fiestas más actual.");
                break;
        }
        builder.setPositiveButton("Aceptar", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    //================================================================
    //==============CODIGO PARA ACTION BAR============================
    //================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        VariasFunciones opcion = new VariasFunciones();
        switch (item.getItemId()) {
            case R.id.menu_share:
                opcion.compartir(this);
                return true;
            case R.id.menu_info:
                opcion.crearDialogoAlerta(this).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}