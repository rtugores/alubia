package huitca1212.alubia13.programa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import huitca1212.alubia13.R;

public class Programa extends Activity {

    private TitularPrograma[] datos =
            new TitularPrograma[]{
                    new TitularPrograma("Saludo del alcalde", ""),
                    new TitularPrograma("Reinas y Damas Alubia 2015", ""),
                    new TitularPrograma("Viernes", "21 de agosto"),
                    new TitularPrograma("Sábado", "22 de agosto"),
                    new TitularPrograma("Domingo", "23 de agosto"),
                    new TitularPrograma("Lunes", "24 de agosto"),
                    new TitularPrograma("XIV Carrera de la Alubia", "")};
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programa);
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);
        tracker = analytics.newTracker("UA-42496077-1"); // Replace with actual tracker/property Id
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        AdaptadorPrograma adaptador = new AdaptadorPrograma(this, datos);
        ListView lstOpciones = (ListView) findViewById(R.id.LstOpciones);
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
                        Intent intent3 = new Intent(Programa.this, Viernes.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(Programa.this, Sabado.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(Programa.this, Domingo.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(Programa.this, Lunes.class);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(Programa.this, Carrera.class);
                        startActivity(intent7);
                        break;
                }
            }
        });
    }
}
