package huitca1212.alubia13.ui.more.settings;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DeleteAccountAsyncTask;
import huitca1212.alubia13.model.Setting;
import huitca1212.alubia13.ui.forum.ForumActivity;
import huitca1212.alubia13.ui.forum.ForumForgottenPasswordActivity;
import huitca1212.alubia13.ui.more.MoreActivity;
import huitca1212.alubia13.utils.Dialogs;
import huitca1212.alubia13.utils.Notifications;

public class SettingsActivity extends AppCompatActivity implements ListView.OnItemClickListener {

	private Setting[] data =
			new Setting[]{
					new Setting("Cerrar sesión", "Cierra tu sesión en el foro"),
					new Setting("Olvidé mi contraseña", "Recupera tu contraseña en tu correo"),
					new Setting("Borrar cuenta", "Elimina tu cuenta en el foro"),
					new Setting("Política de privacidad", "Échale un vistazo a la política de privacidad"),
					new Setting("Compartir", "Comparte la aplicación con tus amigos"),
					new Setting("Actualizar", "Obtén la versión más actualizada"),
					new Setting("Versión", "3.5"),
			};
	public static Activity settingsActivity;
	@Bind(R.id.progressbar_view) LinearLayout progressBar;
	@Bind(R.id.list_options) ListView listOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ButterKnife.bind(this);
		settingsActivity = this;

		SettingsAdapter adaptador = new SettingsAdapter(this, data);

		listOptions.setAdapter(adaptador);
		listOptions.setOnItemClickListener(this);
	}


	public Dialog settingsDeleteAccountDialog(Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.settings_delete_title);
		builder.setMessage(R.string.settings_delete_areyousure);
		builder.setNegativeButton(R.string.common_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String usuario = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("username", "");
				try {
					String mURL = "http://rjapps.x10host.com/borrar_cuenta.php?usuario=" + URLEncoder.encode(usuario, "UTF-8");
					DeleteAccountAsyncTask enviar = new DeleteAccountAsyncTask(SettingsActivity.this, progressBar, mURL);
					enviar.execute(mURL);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), R.string.common_internet_error, Toast.LENGTH_LONG).show();
					dialog.cancel();
				}
				try {
					MoreActivity.moreActivity.finish();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				try {
					ForumActivity.forumActivity.finish();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				finish();
			}
		});
		return builder.create();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case 0:
				// Log out
				boolean notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
				if (notregister) {
					Notifications.showToast(settingsActivity, getString(R.string.settings_error_logout));
				} else {
					Dialogs.showSettingsLogoutDialog(settingsActivity);
				}
				break;
			case 1:
				// Forgot password
				Intent intent = new Intent(SettingsActivity.this, ForumForgottenPasswordActivity.class);
				startActivity(intent);
				break;
			case 2:
				// Delete account
				notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
				if (notregister) {
					Toast.makeText(getApplicationContext(), R.string.settings_error_logout, Toast.LENGTH_SHORT).show();
				} else {
					settingsDeleteAccountDialog(SettingsActivity.this).show();
				}
				break;
			case 3:
				// Review the privacy policy
				Uri uri = Uri.parse("http://rjapps.x10host.com/responsabilidad.html");
				startActivity(new Intent(Intent.ACTION_VIEW, uri));
				break;
			case 4:
				// Share app
				final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
					shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				} else{
					shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
				}
				shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.settings_share_title));
				shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.settings_share_content));
				startActivity(Intent.createChooser(shareIntent, "Compartir mediante"));
				break;
			case 5:
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
				}
				break;
		}
	}
}