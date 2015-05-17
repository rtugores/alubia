package huitca1212.alubia13.programa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;

public class Programa extends Activity {

    private ListView lstOpciones;
    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("Saludo del alcalde", ""),
                    new TitularPrograma("Reinas y Damas Alubia 2014", ""),
                    new TitularPrograma("Sábado", "16 de agosto"),
                    new TitularPrograma("Viernes", "22 de agosto"),
                    new TitularPrograma("Sábado", "23 de agosto"),
                    new TitularPrograma("Domingo", "24 de agosto"),
                    new TitularPrograma("Lunes", "25 de agosto"),
                    new TitularPrograma("XIII Carrera de la Alubia", "")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programa);

        //================================================================
        //==============CODIGO PARA LISTVIEW==============================
        //================================================================
        AdaptadorPrograma adaptador = new AdaptadorPrograma(this, datos);
        lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(Programa.this, Saludo.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Programa.this, ReinasDamas.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Programa.this, Sabado16.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Programa.this, Viernes.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Programa.this, Sabado.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Programa.this, Domingo.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Programa.this, Lunes.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(Programa.this, Carrera.class);
                        startActivity(intent7);
                        break;
                }
            }
        });
    }

    //================================================================
    //==============CODIGO PARA ESTADISTICAS==========================
    //================================================================
    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this); // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this); // Add this method.
    }
}
