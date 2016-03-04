package huitca1212.alubia13.ui.home;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import huitca1212.alubia13.utils.Notifications;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Bind(R.id.schedule_button) Button scheduleButton;
	@Bind(R.id.complex_image_button) Button complexImageButton;
	@Bind(R.id.forum_button) Button forumButton;
	@Bind(R.id.news_button) Button newsButton;
	@Bind(R.id.more_button) Button moreButton;

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
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.schedule_button) {
			Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
			startActivity(intent);
		} else if (id == R.id.complex_image_button) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				Intent intent = new Intent(MainActivity.this, ComplexImageActivity.class);
				startActivity(intent);
			} else {
				Notifications.showToast(this, getString(R.string.album_api_error));
			}
		} else if (id == R.id.forum_button) {
			boolean notregister = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notregister", true);
			if (notregister) {
				Intent intent = new Intent(MainActivity.this, ForumMenuActivity.class);
				intent.putExtra(ForumActivity.INVITED_USER, "OK");
				startActivity(intent);
			} else {
				Intent intent = new Intent(MainActivity.this, ForumActivity.class);
				intent.putExtra(ForumActivity.INVITED_USER, "NOK");
				startActivity(intent);
			}
		} else if (id == R.id.news_button) {
			Intent intent = new Intent(MainActivity.this, NewsActivity.class);
			startActivity(intent);
		} else if (id == R.id.more_button) {
			Intent intent = new Intent(MainActivity.this, MoreActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		ImageLoader.getInstance().stop();
		super.onBackPressed();
	}
}