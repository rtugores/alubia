package huitca1212.alubia13.programa;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class ReinasDamas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reinas_damas);
        final TextView texto = (TextView) findViewById(R.id.reinas_damas_contenido);
        texto.setMovementMethod(new ScrollingMovementMethod());
    }

}