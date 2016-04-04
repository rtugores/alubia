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
import huitca1212.alubia13.BuildConfig;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.ForumBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.Setting;
import huitca1212.alubia13.ui.forum.ForumActivity;
import huitca1212.alubia13.ui.forum.ForumForgottenPasswordActivity;
import huitca1212.alubia13.ui.forum.ForumPrivacyActivity;
import huitca1212.alubia13.ui.more.MoreActivity;
import huitca1212.alubia13.utils.DialogParams;
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

	public static Activity settingsActivity;
	@Bind(R.id.schedule_list) ListView listOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ButterKnife.bind(this);
		settingsActivity = this;

		SettingsAdapter adaptador = new SettingsAdapter(this, new Setting[]{
				new Setting(getString(R.string.settings_logout), getString(R.string.settings_logout_sub)),
				new Setting(getString(R.string.settings_forgot_passwd), getString(R.string.settings_forgot_passwd_sub)),
				new Setting(getString(R.string.settings_delete_title), getString(R.string.settings_delete_title_sub)),
				new Setting(getString(R.string.settings_privacy_policy), getString(R.string.settings_privacy_policy_sub)),
				new Setting(getString(R.string.settings_share), getString(R.string.settings_share_sub)),
				new Setting(getString(R.string.settings_update), getString(R.string.settings_update_sub)),
				new Setting(getString(R.string.settings_version), BuildConfig.VERSION_NAME),
		});

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
			DialogParams params = new DialogParams();
			params.setTitle(getString(R.string.settings_logout));
			params.setMessage(getString(R.string.settings_logout_areyousure));
			params.setPositiveButton(getString(R.string.common_accept));
			params.setNegativeButton(getString(R.string.common_cancel));
			Dialogs.showGenericDialog(this, params, new Dialogs.DialogListener() {
				@Override
				public void onPositive() {
					getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE).edit().putString("username", "").commit();
					getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE).edit().putBoolean("notregister", true).commit();
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
					try {
						SettingsActivity.settingsActivity.finish();
					} catch (NullPointerException e) {
						//NOOP
					}
				}

				@Override
				public void onNegative() {
				}
			});
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
			DialogParams params = new DialogParams();
			params.setTitle(getString(R.string.settings_delete_title));
			params.setMessage(getString(R.string.settings_delete_areyousure));
			params.setPositiveButton(getString(R.string.common_accept));
			params.setNegativeButton(getString(R.string.common_cancel));
			Dialogs.showGenericDialog(this, params, new Dialogs.DialogListener() {
				@Override
				public void onPositive() {
					String user = getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE).getString("username", "");
					ForumBusiness.deleteForumAccount(user, new AllBusinessListener<String>() {
						@Override
						public void onDatabaseSuccess(String object) {

						}

						@Override
						public void onServerSuccess(String object) {
							Notifications.showToast(SettingsActivity.this, getString(R.string.settings_delete_ok));
							getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("username", "").commit();
							getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("notregister", true).commit();
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

				@Override
				public void onNegative() {
				}
			});
		}
	}

	private void onPrivacyPolicy() {
		Intent intent = new Intent(SettingsActivity.this, ForumPrivacyActivity.class);
		startActivity(intent);
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
		startActivity(Intent.createChooser(shareIntent, getString(R.string.settings_share_chooser)));
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