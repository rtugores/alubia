package huitca1212.alubia13.penyas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;

import huitca1212.alubia13.R;

public class Penyas extends Activity {

    private ListView lstOpciones;
    private TitularPenyas[] datos =
            new TitularPenyas[]{
                    new TitularPenyas("B12"), new TitularPenyas("BBdoble"), new TitularPenyas("Birlybirloke"),
                    new TitularPenyas("Boogie"), new TitularPenyas("B&N (Blanco y negro)"),
                    new TitularPenyas("Costa azul"), new TitularPenyas("Desfase"), new TitularPenyas("Descoloke"), new TitularPenyas("Dislokey"),
                    new TitularPenyas("El Cachi"), new TitularPenyas("El Ginkgo"), new TitularPenyas("Embarazo no deseado"),
                    new TitularPenyas("EUKZ (El Último Ke Zierre)"), new TitularPenyas("FBI (Federación de Borrachos Inocentes)"),
                    new TitularPenyas("Imperfectos"), new TitularPenyas("Indis (Indiscretos)"), new TitularPenyas("Jaia"),
                    new TitularPenyas("Jarra y pedal"), new TitularPenyas("Kachi-chirín"), new TitularPenyas("Kalankoe"), new TitularPenyas("Kalyke"),
                    new TitularPenyas("Kamensoky"), new TitularPenyas("Kamikaze"), new TitularPenyas("Kelnozz & Ceda el vaso"), new TitularPenyas("KQK"),
                    new TitularPenyas("La coral"), new TitularPenyas("La DGT (Dirección General de Tragos)"),
                    new TitularPenyas("Los colgaos"), new TitularPenyas("Los tocapelotas"), new TitularPenyas("Motokaskaos"),
                    new TitularPenyas("Nosting"), new TitularPenyas("Pa' brujas nosotras"), new TitularPenyas("Pk2 (Pecados)"),
                    new TitularPenyas("Pocos pero locos"), new TitularPenyas("Psicosys"),
                    new TitularPenyas("¡¡Qué apostamos!!"), new TitularPenyas("Rambosteroides"), new TitularPenyas("Rockambole"),
                    new TitularPenyas("Sin papeles"), new TitularPenyas("Taboo"), new TitularPenyas("The madness"), new TitularPenyas("Tibuky"),
                    new TitularPenyas("Trapicheos"), new TitularPenyas("Vaganzia pura"), new TitularPenyas("Vankenoven"),
                    new TitularPenyas("Ya estamos todos"), new TitularPenyas("Yokers"), new TitularPenyas("Zumbagaritos"),
                    new TitularPenyas("Zumbalitros"), new TitularPenyas("Zumbapajascervecistas")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penyas);

        //================================================================
        //==============CODIGO PARA LISTVIEW==============================
        //================================================================
        AdaptadorPenyas adaptador = new AdaptadorPenyas(this, datos);
        lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ImageView im = new ImageView(Penyas.this);
                Dialog d = new Dialog(Penyas.this);
                switch (position) {
                    case 0:
                        d.setTitle("Peña B12");
                        im.setImageResource(R.drawable.b12_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 1:
                        d.setTitle("Peña BBdoble");
                        im.setImageResource(R.drawable.bbdoble_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 2:
                        d.setTitle("Peña Birlybirloke");
                        im.setImageResource(R.drawable.birlybirloke_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 4:
                        d.setTitle("Peña B&N");
                        im.setImageResource(R.drawable.blanconegro_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 6:
                        d.setTitle("Peña Desfase");
                        im.setImageResource(R.drawable.desfase_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 7:
                        d.setTitle("Peña Descoloke");
                        im.setImageResource(R.drawable.descoloke_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 8:
                        d.setTitle("Peña Dislokey");
                        im.setImageResource(R.drawable.dislokey_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 11:
                        d.setTitle("Peña Embarazo");
                        im.setImageResource(R.drawable.embarazo_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 13:
                        d.setTitle("Peña FBI");
                        im.setImageResource(R.drawable.fbi_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 14:
                        d.setTitle("Peña Imperfectos");
                        im.setImageResource(R.drawable.imperfectos_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 15:
                        d.setTitle("Peña Indis");
                        im.setImageResource(R.drawable.indis_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 16:
                        d.setTitle("Peña Jaia");
                        im.setImageResource(R.drawable.jaia_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 18:
                        d.setTitle("Peña Kachi-chirín");
                        im.setImageResource(R.drawable.kachi_chirin_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 21:
                        d.setTitle("Peña Kamensoky");
                        im.setImageResource(R.drawable.kamensoky_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 23:
                        d.setTitle("Peña Kelnozz & Ceda el vaso");
                        im.setImageResource(R.drawable.kelnozz_ceda_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 25:
                        d.setTitle("Peña La coral");
                        im.setImageResource(R.drawable.coral_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 27:
                        d.setTitle("Peña Los colgaos");
                        im.setImageResource(R.drawable.loscolgaos_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 28:
                        d.setTitle("Peña Los tocapelotas");
                        im.setImageResource(R.drawable.tocapelotas_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 29:
                        d.setTitle("Peña Motokaskaos");
                        im.setImageResource(R.drawable.motokaskaos_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 30:
                        d.setTitle("Peña Nosting");
                        im.setImageResource(R.drawable.nosting_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 31:
                        d.setTitle("Peña Pa' brujas nosotras");
                        im.setImageResource(R.drawable.brujas_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 32:
                        d.setTitle("Peña Pk2");
                        im.setImageResource(R.drawable.pk2_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 33:
                        d.setTitle("Peña Pocos pero locos");
                        im.setImageResource(R.drawable.pocos_pero_locos_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 34:
                        d.setTitle("Peña Psicosys");
                        im.setImageResource(R.drawable.psicosys_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 36:
                        d.setTitle("Peña Rambosteroides");
                        im.setImageResource(R.drawable.rambosteroides_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 37:
                        d.setTitle("Peña Rockambole");
                        im.setImageResource(R.drawable.rockambole_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 39:
                        d.setTitle("Peña Taboo");
                        im.setImageResource(R.drawable.taboo_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 41:
                        d.setTitle("Peña Tibuky");
                        im.setImageResource(R.drawable.tibuky_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 43:
                        d.setTitle("Peña Vaganzia pura");
                        im.setImageResource(R.drawable.vaganzia_pura_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 44:
                        d.setTitle("Peña Vankenoven");
                        im.setImageResource(R.drawable.vankenoven_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 45:
                        d.setTitle("Peña Ya estamos todos");
                        im.setImageResource(R.drawable.yaestamostodos_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 46:
                        d.setTitle("Peña Yokers");
                        im.setImageResource(R.drawable.yokers_g);
                        d.setContentView(im);
                        d.show();
                        break;
                    case 47:
                        d.setTitle("Peña Zumbagaritos");
                        im.setImageResource(R.drawable.zumbagaritos_g);
                        d.setContentView(im);
                        d.show();
                        break;
                }
            }
        });
        //====================
        final Button boton1 = (Button) findViewById(R.id.button_contact); //BOTON ENVIAR FOTO
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                crearDialogoContact(Penyas.this).show();
            }
        });
    }

    public Dialog crearDialogoContact(Context eso) {
        AlertDialog.Builder builder = new AlertDialog.Builder(eso);
        builder.setTitle("Foto de tu peña");
        builder.setMessage("¡Envía una foto de tu peña para que aparezca en la aplicación!");
        builder.setNegativeButton("WhatsApp", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:664732632"));
                startActivity(callIntent);
            }
        });
        builder.setNeutralButton("Email", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"huitca1212@gmail.com"});
                startActivity(Intent.createChooser(i, "Enviar mediante"));
            }
        });
        builder.setPositiveButton("Salir", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    //================================================================
    //==============CODIGO PARA ESTADISTICAS==========================
    //================================================================
    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this); // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this); // Add this method.
    }
}