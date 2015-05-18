package huitca1212.alubia13.programa;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class Saludo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saludo);
        final TextView texto = (TextView) findViewById(R.id.saludo_contenido); //PRIMER BOTON
        texto.setMovementMethod(new ScrollingMovementMethod());
    }
}