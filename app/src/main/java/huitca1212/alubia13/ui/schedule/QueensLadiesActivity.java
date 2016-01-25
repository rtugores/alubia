package huitca1212.alubia13.ui.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;

public class QueensLadiesActivity extends AppCompatActivity {

	@Bind(R.id.reinas_damas_contenido) TextView texto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queens_ladies);
		ButterKnife.bind(this);

		texto.setMovementMethod(new ScrollingMovementMethod());
	}

}