package huitca1212.alubia13.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import huitca1212.alubia13.R;

public class ForoRegistroContrasenya extends Activity {

    // Declaramos variables
    public static Activity foro_registro_contrasenya;
    String email;
    String usuario;
    String contrasenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_registro_contrasenya);

        getWindow().setBackgroundDrawableResource(R.drawable.fondo_nuevo);

        foro_registro_contrasenya = this;

        final EditText contrasenya_edit = (EditText) findViewById(R.id.password);
        contrasenya_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    action_foro_registro_contrasenya();
                    return true;
                }
                return false;
            }
        });

        // Tomamos el nombre de usuario y el email de la pantalla anterior
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            usuario = extras.getString("usuario");
            email = extras.getString("email");
        } else {
            usuario = (String) savedInstanceState.getSerializable("usuario");
            email = (String) savedInstanceState.getSerializable("email");
        }

        final Button boton = (Button) findViewById(R.id.button);
        // Manejador para cambios en el botón (estética)
        contrasenya_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable str) {
                if (str.toString().trim().length() > 0) {
                    boton.setEnabled(true);
                    boton.setBackgroundResource(R.drawable.d_normal_button_blue);
                } else {
                    boton.setEnabled(false);
                    boton.setBackgroundResource(R.drawable.d_normal_button_gray);
                }
            }
        });

        // Al hacer click en el botón de enviar la contraseña a la siguiente actividad
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action_foro_registro_contrasenya();
            }
        });
    }

    protected void action_foro_registro_contrasenya() {
        // Escondemos teclado
        final EditText contrasenya_edit = (EditText) findViewById(R.id.password);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(contrasenya_edit.getWindowToken(), 0);
        // Comprobamos si la contraseña está escrita correctamente
        contrasenya = contrasenya_edit.getText().toString().trim();
        if (contrasenya.length() < 5) {
            Toast.makeText(getApplicationContext(), "La contraseña ha de tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
        }
        // Enviamos los datos recogidos a la nueva actividad (ForoRegistroCodigo)
        Intent intent = new Intent(ForoRegistroContrasenya.this, ForoRegistroCodigo.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("email", email);
        intent.putExtra("contrasenya", contrasenya);
        // Abrimos nueva actividad
        startActivity(intent);
    }
}
