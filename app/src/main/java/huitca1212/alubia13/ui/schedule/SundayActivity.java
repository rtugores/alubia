package huitca1212.alubia13.ui.schedule;

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

public class SundayActivity extends AppCompatActivity {

	@Bind(R.id.list_options) ListView lstOpciones;
	private Schedule[] data = new Schedule[]{
			new Schedule("12:00 Concentración de peñas", "Plaza de san Juan Bautista"),
			new Schedule("13:00 Santa misa", "Iglesia de san Juan Bautista"),
			new Schedule("13:00-14.00 Exposición de dibujo y pintura", "Casa de la cultura"),
			new Schedule("14:30 Colocación de placa identificativa", "Polideportivo municipal"),
			new Schedule("18:00-21.00 Exposición de dibujo y pintura", "Casa de la cultura"),
			new Schedule("19:00 Desfile Mayor Alubia 2015", "Salida: Colegio público"),
			new Schedule("23:30 Verbena", "Explanada de la casa de la cultura"),
			new Schedule("00:00 Party Dance", "Recorrido por los chiringuitos")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);
		ButterKnife.bind(this);

		DayAdapter adaptadorDias = new DayAdapter(this, data);
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
		switch (id) {
			case 0:
				builder.setTitle("Concentración de peñas");
				builder.setMessage("Reunión de las peñas de la fiesta para recoger a las Reinas y Damas de la Alubia 2015. Tercer control de peñas.");
				break;
			case 1:
				builder.setTitle("Santa misa");
				builder.setMessage("Santa misa en la iglesia de san Juan Bautista.");
				break;
			case 2:
				builder.setTitle("Exposición de dibujo y pintura");
				builder.setMessage("Muestra de cuadros en la Casa de la Cultura.");
				break;
			case 3:
				builder.setTitle("Colocación placa identificativa");
				builder.setMessage("Colocación de la placa identificativa de la nueva denominación del Polideportivo Municipal Fidel Rodríguez Rodríguez.");
				break;
			case 4:
				builder.setTitle("Exposición de dibujo y pintura");
				builder.setMessage("Muestra de cuadros en la Casa de la Cultura.");
				break;
			case 5:
				builder.setTitle("Desfile Mayor Alubia 2015");
				builder.setMessage("Desfile de carrozas por las calles del pueblo. Cuarto control de peñas.");
				break;
			case 6:
				builder.setTitle("Verbena");
				builder.setMessage("El grupo \"Radar\" se encarga de dar color a la noche " +
						"con la música de fiestas más actual.");
				break;
			case 7:
				builder.setTitle("Party Dance");
				builder.setMessage("Recorrido por las calles del pueblo y paradas en los chiringuitos " +
						"con la \"Supermartxé\".");
				break;
		}
		builder.setPositiveButton(R.string.common_accept, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		return builder.create();
	}
}