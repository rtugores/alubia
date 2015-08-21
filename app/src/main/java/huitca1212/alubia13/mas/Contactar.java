package huitca1212.alubia13.mas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import huitca1212.alubia13.R;

public class Contactar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactar);

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.contactar_whatsapp); //REGISTRO
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:664732632"));
                startActivity(callIntent);
            }
        });
        final Button boton2 = (Button) findViewById(R.id.contactar_email); //LOGIN
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822")
                        .putExtra(Intent.EXTRA_EMAIL, "huitca1212@gmail.com");
                startActivity(Intent.createChooser(i, "Enviar mediante"));
            }
        });
    }
}