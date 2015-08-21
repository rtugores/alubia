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

public class Lunes extends Activity {

    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("13:00 Santa misa de peñas", "Iglesia de san Juan Bautista"),
                    new TitularPrograma("15:00 Alubiada popular", "Merendero del polideportivo municipal"),
                    new TitularPrograma("18:00-20:00 Bumperbal y actividades infantiles", "Campo de fútbol municipal"),
                    new TitularPrograma("19:00 Fiesta de la espuma", "Campo de fútbol"),
                    new TitularPrograma("20:30 Entrega de premios del concurso de pintura", "Casa de la cultura"),
                    new TitularPrograma("21:00 Bollo preñao", "Explanada de la casa de la cultura"),
                    new TitularPrograma("22:30 Verbena", "Explanada de la casa de la cultura")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dia);
        //====================
        AdaptadorDias adaptadorDias = new AdaptadorDias(this, datos);
        ListView lstOpciones = (ListView) findViewById(R.id.LstOpciones);
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
                builder.setTitle("Santa misa de peñas");
                builder.setMessage("Santa misa para las peñas en la iglesia de san Juan Bautista.");
                break;
            case 1:
                builder.setTitle("Alubiada popular");
                builder.setMessage("Alubiada para todos en el merendero.");
                break;
            case 2:
                builder.setTitle("Juegos infantiles");
                builder.setMessage("Bumperbal para peñas a partir de 12 años de edad y actividades infantiles. Diversión asegurada.");
                break;
            case 3:
                builder.setTitle("Fiesta de la espuma");
                builder.setMessage("El campo de fútbol se cubre de espuma para los más atrevidos.");
                break;
            case 4:
                builder.setTitle("Entrega de premios");
                builder.setMessage("Entrega de premios para los ganadores del concurso de pintura.");
                break;
            case 5:
                builder.setTitle("Bollo preñao");
                builder.setMessage("Bollo preñao para todos.");
                break;
            case 6:
                builder.setTitle("Verbena");
                builder.setMessage("Baile fin de fiesta amenizado por la discoteca móvil \"Christian\". Dará color a la noche " +
                        "con la música de fiestas más actual.");
                break;
        }
        builder.setPositiveButton(R.string.aceptar, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}