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

import huitca1212.alubia13.R;
import huitca1212.alubia13.model.Schedule;
import huitca1212.alubia13.ui.schedule.adapters.DayAdapter;

public class FridayActivity extends AppCompatActivity {

	private Schedule[] datos =
			new Schedule[]{
					new Schedule("19:30 Fútbol sala infantil", "Frontón municipal"),
					new Schedule("23:00 Pregón de fiestas", "Plaza del castillo"),
					new Schedule("23:30 Coronación de Reinas y Damas", "Plaza del castillo"),
					new Schedule("00:00 Party Dance", "Salida: Plaza del castillo"),
					new Schedule("00:00 VI Alubia Rock", "Plaza de toros"),
					new Schedule("00:30 Verbena", "Explanada de la casa de la cultura")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);

		DayAdapter adaptadorDias = new DayAdapter(this, datos);
		ListView lstOpciones = (ListView)findViewById(R.id.list_options);
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
				builder.setTitle("Fútbol sala infantil");
				builder.setMessage("Dosis de buen fútbol infantil para que puedan disfrutar " +
						"tanto los pequeños como los más mayores.");
				break;
			case 1:
				builder.setTitle("Pregon de fiestas");
				builder.setMessage("Anunciamiento a cargo de don Alberto Rodríguez Santos, sacerdote de la parroquia " +
						"San Juan Bautista de Laguna de Negrillos.");
				break;
			case 2:
				builder.setTitle("Coronación de Reinas y Damas");
				builder.setMessage("Gran evento donde las Reinas y Damas de la Alubia 2015 " +
						"toman sus bandas y son coronadas en la plaza del castillo.");
				break;
			case 3:
				builder.setTitle("Party Dance");
				builder.setMessage("Recorrido por las calles del pueblo y paradas en los chiringuitos " +
						"con la \"Supermartxé\".");
				break;
			case 4:
				builder.setTitle("V Alubia Rock");
				builder.setMessage("Participación de los grupos \"Envidia Kotxina\", " +
						"\"Limando\", \"Debakle\" y \"T.A.K.E.\" (Tributo a Kaos Etíliko) en directo. Entrada gratuita.");
				break;
			case 5:
				builder.setTitle("Verbena");
				builder.setMessage("La discoteca móvil \"Christian\" se encarga de dar color a la noche " +
						"con la música de fiestas más actual.");
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