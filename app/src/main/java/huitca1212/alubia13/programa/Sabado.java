package huitca1212.alubia13.programa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import huitca1212.alubia13.R;

public class Sabado extends Activity {

    private ListView lstOpciones;

    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("11:30 Partido de fútbol", "Campo de fútbol municipal"),
                    new TitularPrograma("12:00 Concentración de peñas", "Plaza de san Juan Bautista"),
                    new TitularPrograma("13:00 Santa misa", "Iglesia de san Juan Bautista"),
                    new TitularPrograma("13:00-14:00 Exposición de dibujo y pintura", "Casa de la cultura"),
                    new TitularPrograma("14:30 Concurso mata de alubia de las variedades blanca y pinta", "Casa de la cultura"),
                    new TitularPrograma("17:00 Descenso del Reguero", "Salida: Puente navarro"),
                    new TitularPrograma("18:00-21:00 Exposición de dibujo y pintura", "Casa de la cultura"),
                    new TitularPrograma("19:30 Desfile de disfraces", "Salida: Colegio público"),
                    new TitularPrograma("23:30 Verbena", "Explanada de la casa de la cultura"),
                    new TitularPrograma("00:00 Party Dance", "Recorrido por los chiringuitos")};

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
                builder.setTitle("Partido de fútbol");
                builder.setMessage("Enfrentamiento del Juristas de León contra el Candas en el campo de fútbol municipal.");
                break;
            case 1:
                builder.setTitle("Concentración de peñas");
                builder.setMessage("Las peñas de la fiesta se reunen para recoger a Reinas y Damas. Primer " +
                        "control de peñas.");
                break;
            case 2:
                builder.setTitle("Santa misa");
                builder.setMessage("Santa misa en la iglesia de san Juan Bautista.");
                break;
            case 3:
                builder.setTitle("Exposición de dibujo y pintura");
                builder.setMessage("Muestra de cuadros en la casa de la cultura.");
                break;
            case 4:
                builder.setTitle("Concurso mata de alubia");
                builder.setMessage("Concurso de matas de alubias, variedades blancas y pintas.");
                break;
            case 5:
                builder.setTitle("Descenso del Reguero");
                builder.setMessage("Laguneses y forasteros se echan a las aguas del Reguero con sus barcas de " +
                        "fabricación casera.");
                break;
            case 6:
                builder.setTitle("Exposición de dibujo y pintura");
                builder.setMessage("Muestra de cuadros en la casa de la cultura.");
                break;
            case 7:
                builder.setTitle("Desfile de disfraces");
                builder.setMessage("Paseo de los disfraces más ingeniosos. Al finalizar habrá " +
                        "pinchos para todos los participantes. Segundo control de peñas.");
                break;
            case 8:
                builder.setTitle("Verbena");
                builder.setMessage("La orquesta \"Ipanema\" se encarga de dar color a la noche " +
                        "con la música de fiestas más actual.");
                break;
            case 9:
                builder.setTitle("Party Dance");
                builder.setMessage("Recorrido por las calles del pueblo y paradas en los chiringuitos " +
                        "con la \"Supermartxé\".");
                break;
        }
        builder.setPositiveButton("Aceptar", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}