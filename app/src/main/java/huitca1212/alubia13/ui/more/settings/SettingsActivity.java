package huitca1212.alubia13.ui.more.settings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.Setting;
import huitca1212.alubia13.ui.forum.ForumActivity;
import huitca1212.alubia13.ui.forum.ForumForgottenPasswordActivity;
import huitca1212.alubia13.ui.more.MoreActivity;
import huitca1212.alubia13.utils.Dialogs;
import huitca1212.alubia13.utils.Notifications;

public class SettingsActivity extends AppCompatActivity implements ListView.OnItemClickListener {

	private enum SettingsItem {
		logOut(0), forgotPasswd(1), deleteAccount(2), privacyPolicy(3), shareApp(4), updateApp(5);

		private int value;

		SettingsItem(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (position == SettingsItem.logOut.getValue()) {
			onLogOut();
		} else if (position == SettingsItem.forgotPasswd.getValue()) {
			onForgotPasswd();
		} else if (position == SettingsItem.deleteAccount.getValue()) {
			onDeleteAccount();
		} else if (position == SettingsItem.privacyPolicy.getValue()) {
			onPrivacyPolicy();
		} else if (position == SettingsItem.shareApp.getValue()) {
			onShareApp();
		} else if (position == SettingsItem.updateApp.getValue()) {
			onUpdateApp();
		}
	}

	private void onLogOut() {
		boolean notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
		if (notregister) {
			Notifications.showToast(settingsActivity, getString(R.string.settings_error_logout));
		} else {
			Dialogs.showSettingsLogoutDialog(settingsActivity);
		}
	}

	private void onForgotPasswd() {
		Intent intent = new Intent(SettingsActivity.this, ForumForgottenPasswordActivity.class);
		startActivity(intent);
	}

	private void onDeleteAccount() {
		boolean notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
		if (notregister) {
			Toast.makeText(getApplicationContext(), R.string.settings_error_logout, Toast.LENGTH_SHORT).show();
		} else {
			Dialogs.showSettingsDeleteAccountDialog(SettingsActivity.this, new AllBusinessListener<String>() {
				@Override
				public void onDatabaseSuccess(String object) {

				}

				@Override
				public void onServerSuccess(String object) {
					Notifications.showToast(SettingsActivity.this, getString(R.string.settings_delete_ok));
					getSharedPreferences("PREFERENCE", MODE_PRIVATE)
							.edit()
							.putString("username", "")
							.commit();
					getSharedPreferences("PREFERENCE", MODE_PRIVATE)
							.edit()
							.putBoolean("notregister", true)
							.commit();
					try {
						MoreActivity.moreActivity.finish();
					} catch (NullPointerException e) {
						//NOOP
					}
					try {
						ForumActivity.forumActivity.finish();
					} catch (NullPointerException e) {
						//NOOP
					}
					finish();
				}

				@Override
				public void onFailure(String result) {
					if (result.equals("-1")) {
						Notifications.showToast(SettingsActivity.this, getString(R.string.common_internet_error));
					} else if (result.equals("-2")) {
						Notifications.showToast(SettingsActivity.this, getString(R.string.settings_error_delete));
					}
				}
			});
		}
	}

	private void onPrivacyPolicy() {
		Uri uri = Uri.parse("http://rjapps.x10host.com/responsabilidad.html");
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}

	private void onShareApp() {
		final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		} else {
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
		}
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.settings_share_title));
		shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.settings_share_content));
		startActivity(Intent.createChooser(shareIntent, "Compartir mediante"));
	}

	private void onUpdateApp() {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
		}
	}

	public void onDestroy() {
		settingsActivity = null;
		super.onDestroy();
	}
}