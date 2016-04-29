package huitca1212.alubia13.ui.home;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.ComplexImageActivity;
import huitca1212.alubia13.ui.forum.ForumActivity;
import huitca1212.alubia13.ui.forum.ForumMenuActivity;
import huitca1212.alubia13.ui.more.MoreActivity;
import huitca1212.alubia13.ui.news.NewsActivity;
import huitca1212.alubia13.ui.schedule.ScheduleActivity;
import huitca1212.alubia13.utils.DialogParams;
import huitca1212.alubia13.utils.Dialogs;
import huitca1212.alubia13.utils.Notifications;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

	@Bind(R.id.schedule_button) Button scheduleButton;
	@Bind(R.id.complex_image_button) Button complexImageButton;
	@Bind(R.id.forum_button) Button forumButton;
	@Bind(R.id.news_button) Button newsButton;
	@Bind(R.id.more_button) Button moreButton;
	private static final int PERMISSION_REQUEST_WRITE_ACCESS = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		scheduleButton.setOnClickListener(this);
		complexImageButton.setOnClickListener(this);
		forumButton.setOnClickListener(this);
		newsButton.setOnClickListener(this);
		moreButton.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		ImageLoader.getInstance().stop();
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.schedule_button) {
			Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
			startActivity(intent);
		} else if (id == R.id.complex_image_button) {
			onAlbumClicked();
		} else if (id == R.id.forum_button) {
			onForumClicked();
		} else if (id == R.id.news_button) {
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			startActivity(intent);
		} else if (id == R.id.more_button) {
			Intent intent = new Intent(MainActivity.this, MoreActivity.class);
			startActivity(intent);
		}
	}

	private void loadAlbumActivity() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			Intent intent = new Intent(MainActivity.this, ComplexImageActivity.class);
			startActivity(intent);
		} else {
			Notifications.showToast(this, getString(R.string.album_api_error));
		}
	}

	private void onAlbumClicked() {
		if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
			// Access granted, load activity
			loadAlbumActivity();
		} else {
			requestWriteAccessPermission();
		}
	}

	private void onForumClicked() {
		boolean isNotLoggedIn = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
		if (isNotLoggedIn) {
			ForumMenuActivity.startActivity(MainActivity.this);
		} else {
			ForumActivity.startActivity(MainActivity.this, "NOK");
		}
	}

	private void requestWriteAccessPermission() {
		// Should we show an explanation?
		if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
			DialogParams params = new DialogParams();
			params.setTitle(getString(R.string.album_write_access_permissions_title));
			params.setMessage(getString(R.string.album_write_access_permissions_description));
			params.setPositiveButton(getString(R.string.common_accept));
			Dialogs.showGenericDialog(this, params, new Dialogs.DialogListener() {
				@Override
				public void onPositive() {
					makeRequestWriteAccessPermission();
				}

				@Override
				public void onNegative() {
				}
			});
		} else {
			makeRequestWriteAccessPermission();
		}
	}

	public void makeRequestWriteAccessPermission() {
		ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE_ACCESS);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == PERMISSION_REQUEST_WRITE_ACCESS) {
			// Request for write access permissions
			if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// Access granted, load activity
				loadAlbumActivity();
			}
			// Else -> Permission request was denied.
		}
	}
}