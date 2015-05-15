package huitca1212.alubia13.masClases;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class VariasFunciones extends Activity {

    public void compartir(Context eso) {
        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_SUBJECT, "¡Descarga Alubia '14!");
        intent.putExtra(Intent.EXTRA_TEXT, "La aplicación de la fiesta de la Alubia en Laguna de Negrillos. Disponible YA en Google Play: https://play.google.com/store/apps/details?id=huitca1212.alubia13");
        eso.startActivity(Intent.createChooser(intent, "Compartir mediante"));
    }

    public Dialog crearDialogoAlerta(final Context eso) {
        AlertDialog.Builder builder = new AlertDialog.Builder(eso);
        builder.setTitle("Información");
        builder.setMessage("Aplicación desarrollada por Rodrigo J. Tugores Blanco. " +
                "Para cualquier sugerencia, no dudes en contactar.");
        builder.setNegativeButton("Contactar", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, new String[]{"huitca1212@gmail.com"});
                eso.startActivity(Intent.createChooser(i, "Enviar mediante"));
            }
        });
        builder.setPositiveButton("Aceptar", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}