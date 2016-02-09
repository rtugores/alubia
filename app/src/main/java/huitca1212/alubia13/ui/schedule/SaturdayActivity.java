package huitca1212.alubia13.ui.schedule;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Schedule;
import huitca1212.alubia13.ui.schedule.adapters.DayAdapter;

public class SaturdayActivity extends AppCompatActivity {

	Schedule[] datos;
	String day;
	@Bind(R.id.ad_view) AdView adView;
	@Bind(R.id.list_options) ListView lstOpciones;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);
		ButterKnife.bind(this);

		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				adView.setVisibility(View.VISIBLE);
			}
		});

		day = getIntent().getStringExtra("day");
		if (day.equals("saturday")) {
			datos = new Schedule[]{
					new Schedule(getString(R.string.schedule_saturday_ac1_hour) + " " + getString(R.string.schedule_saturday_ac1_title), getString(R.string.schedule_saturday_ac1_subtitle)),
					new Schedule("12:00 Concentración de peñas", "Plaza de san Juan Bautista"),
					new Schedule("13:00 Santa misa", "Iglesia de san Juan Bautista"),
					new Schedule("13:00-14:00 Exposición de dibujo y pintura", "Casa de la cultura"),
					new Schedule("14:30 Concurso mata de alubia de las variedades blanca y pinta", "Casa de la cultura"),
					new Schedule("17:00 Descenso del Reguero", "Salida: Puente navarro"),
					new Schedule("18:00-21:00 Exposición de dibujo y pintura", "Casa de la cultura"),
					new Schedule("19:30 Desfile de disfraces", "Salida: Colegio público"),
					new Schedule("23:30 Verbena", "Explanada de la casa de la cultura"),
					new Schedule("00:00 Party Dance", "Recorrido por los chiringuitos")};
		}

		DayAdapter adaptadorDias = new DayAdapter(this, datos);
		lstOpciones.setAdapter(adaptadorDias);

		lstOpciones.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				crearDialogoAlerta(position).show();
			}
		});
	}

	public Dialog crearDialogoAlerta(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (day.equals("saturday")) {
			switch (id) {
				case 0:
					builder.setTitle("Partido de fútbol");
					builder.setMessage(getString(R.string.schedule_saturday_ac1_explanation));
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
		}
		builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		return builder.create();
	}
}