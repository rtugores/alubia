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

public class Sabado16 extends Activity {

    private ListView lstOpciones;

    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("11:00-14:00 y 16:00-20:00 Concurso de dibujo y pintura", "Plaza de san Juan Bautista"),
            };

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
                builder.setTitle("Concurso de dibujo y pintura");
                builder.setMessage("Concurso para toda la gente que tenga buena mano y creatividad en el lienzo.");
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