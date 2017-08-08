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
	private static final String STATE_POSITION = "STATE_POSITION";
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

		getAlbum(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private void getAlbum(final Bundle savedInstanceState) {
		progressbarView.setVisibility(View.VISIBLE);
		AlbumBusiness.getAlbumContent(this, new AllBusinessListener<AlbumWrapper>() {
			@Override
			public void onDatabaseSuccess(AlbumWrapper scheduleWrapper) {
				progressbarView.setVisibility(View.GONE);
				drawPager(scheduleWrapper.getAlbumItems(), savedInstanceState);
			}

			@Override
			public void onServerSuccess(AlbumWrapper scheduleWrapper) {
				progressbarView.setVisibility(View.GONE);
				drawPager(scheduleWrapper.getAlbumItems(), savedInstanceState);
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

	private void drawPager(List<AlbumItem> albumItems, Bundle savedInstanceState) {
		int pagerPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(STATE_POSITION);
		pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager(), albumItems));
		pager.setCurrentItem(pagerPosition);
	}

	private class ImagePagerAdapter extends FragmentPagerAdapter {
		List<AlbumItem> albumItems;
		Fragment penyasFragment;
		Fragment alubia16Fragment;
		Fragment alubia15Fragment;

		public ImagePagerAdapter(FragmentManager fm, List<AlbumItem> albumItems) {
			super(fm);
			this.albumItems = albumItems;
			penyasFragment = ImageListFragment.newInstance(albumItems.get(0));
			alubia16Fragment = ImageGridFragment.newInstance(albumItems.get(1));
			alubia15Fragment = ImageGridFragment.newInstance(albumItems.get(2));
		}

		@Override
		public int getCount() {
			return albumItems.size();
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					return penyasFragment;
				case 1:
					return alubia16Fragment;
				case 2:
					return alubia15Fragment;
				default:
					return null;
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return albumItems.get(position).getTitle();
		}
	}
}