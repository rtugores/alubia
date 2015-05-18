package huitca1212.alubia13.alubiaQuiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import huitca1212.alubia13.R;


public class AlubiaQuizMenu extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alubia_quiz_menu);

        //================================================================
        //==============CODIGO PARA BOTONES===============================
        //================================================================

        final Button boton1 = (Button) findViewById(R.id.button1); //PRIMER BOTON
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlubiaQuizMenu.this, AlubiaQuiz1.class);
                startActivity(intent);
                finish();
            }
        });
        final Button boton2 = (Button) findViewById(R.id.button2); //SEGUNDO BOTON
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlubiaQuizMenu.this, AlubiaQuiz2.class);
                startActivity(intent);
                finish();
            }
        });
        final Button boton3 = (Button) findViewById(R.id.button3); //TERCER BOTON
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlubiaQuizMenu.this, AlubiaQuiz3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}