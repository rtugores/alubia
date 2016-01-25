package huitca1212.alubia13.ui.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class GreetingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_greeting);
		final TextView texto = (TextView)findViewById(R.id.saludo_contenido);
		texto.setMovementMethod(new ScrollingMovementMethod());
	}
}