package huitca1212.alubia13;

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

public class Lunes extends Activity {

    private ListView lstOpciones;

    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("13:00 Santa misa de peñas", "Iglesia de san Juan Bautista"),
                    new TitularPrograma("15:00 Alubiada popular", "Zona recreativa del polideportivo municipal"),
                    new TitularPrograma("17:00 Juegos infantiles", "Frontón municipal"),
                    new TitularPrograma("19:00 Fiesta de la espuma", "Campo de fútbol"),
                    new TitularPrograma("21:00 Bollo preñao", "Explanada de la casa de la cultura"),
                    new TitularPrograma("22:30 Verbena", "Explanada de la casa de la cultura")};

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
                builder.setTitle("Santa misa de peñas");
                builder.setMessage("Santa misa para las peñas en la iglesia de san Juan Bautista.");
                break;
            case 1:
                builder.setTitle("Alubiada popular");
                builder.setMessage("Alubiada para todos en el merendero.");
                break;
            case 2:
                builder.setTitle("Juegos infantiles");
                builder.setMessage("Juegos para todas los niños y niñas en el frontón municipal. Diversión asegurada.");
                break;
            case 3:
                builder.setTitle("Fiesta de la espuma");
                builder.setMessage("El campo de fútbol se cubre de espuma para los más atrevidos.");
                break;
            case 4:
                builder.setTitle("Bollo preñao");
                builder.setMessage("Bollo preñao para todos.");
                break;
            case 5:
                builder.setTitle("Verbena");
                builder.setMessage("Baile fin de fiesta amenizado por la discoteca móvil \"Christian\". Dará color a la noche " +
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