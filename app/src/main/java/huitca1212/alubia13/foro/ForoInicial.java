package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import huitca1212.alubia13.R;


public class ForoInicial extends Activity {

    public static Activity foro_inicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_inicial);
        foro_inicial = this;

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.registro); //REGISTRO
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ForoInicial.this, ForoRegistroUsuario.class);
                startActivity(intent);
            }
        });
        final Button boton2 = (Button) findViewById(R.id.login); //LOGIN
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ForoInicial.this, ForoLoginEmail.class);
                startActivity(intent);
            }
        });
    }
}