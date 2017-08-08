package huitca1212.alubia13.ui.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.AlbumBusiness;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.album.AlbumItem;
import huitca1212.alubia13.model.album.AlbumWrapper;
import huitca1212.alubia13.ui.album.fragment.ImageGridFragment;
import huitca1212.alubia13.ui.album.fragment.ImageListFragment;
import huitca1212.alubia13.utils.AdsAndAnalytics;
import huitca1212.alubia13.utils.Notifications;

public class AlbumActivity extends AppCompatActivity {
	@Bind(R.id.progressbar_view) View progressbarView;
	@Bind(R.id.pager) ViewPager pager;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, AlbumActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		AdsAndAnalytics.setAnalytics(this);

		ButterKnife.bind(this);

		getAlbum();
	}

	private void getAlbum() {
		progressbarView.setVisibility(View.VISIBLE);
		AlbumBusiness.getAlbumContent(this, new AllBusinessListener<AlbumWrapper>() {
			@Override
			public void onDatabaseSuccess(AlbumWrapper scheduleWrapper) {
				progressbarView.setVisibility(View.GONE);
				drawPager(scheduleWrapper.getAlbumItems());
			}

			@Override
			public void onServerSuccess(AlbumWrapper scheduleWrapper) {
				progressbarView.setVisibility(View.GONE);
				drawPager(scheduleWrapper.getAlbumItems());
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showToast(AlbumActivity.this, getString(R.string.common_no_internet));
				}
			}
		});
	}

	private void drawPager(List<AlbumItem> albumItems) {
		pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager(), albumItems));
	}

	private class ImagePagerAdapter extends FragmentPagerAdapter {
		List<Fragment> fragments = new ArrayList<>();
		List<AlbumItem> albumItems;

		public ImagePagerAdapter(FragmentManager fm, List<AlbumItem> albumItems) {
			super(fm);
			this.albumItems = albumItems;
			fragments.add(ImageListFragment.newInstance(albumItems.get(0)));
			for (int i = 1; i < albumItems.size(); i++) {
				fragments.add(ImageGridFragment.newInstance(albumItems.get(i)));
			}
		}

		@Override
		public int getCount() {
			return albumItems.size();
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return albumItems.get(position).getTitle();
		}
	}
}