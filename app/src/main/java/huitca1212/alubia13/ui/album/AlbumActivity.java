package huitca1212.alubia13.ui.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.fragment.ImageGridFragment;
import huitca1212.alubia13.ui.album.fragment.ImageListFragment;
import huitca1212.alubia13.utils.AdsAndAnalytics;

public class AlbumActivity extends AppCompatActivity {
	private static final String STATE_POSITION = "STATE_POSITION";
	private ViewPager pager;

	public static void startActivity(Context ctx) {
		Intent intent = new Intent(ctx, AlbumActivity.class);
		ctx.startActivity(intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_complex);
		AdsAndAnalytics.setAnalytics(this);

		int pagerPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(STATE_POSITION);

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager()));
		pager.setCurrentItem(pagerPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends FragmentPagerAdapter {
		Fragment penyasFragment;
		Fragment alubia16Fragment;
		Fragment alubia15Fragment;

		public ImagePagerAdapter(FragmentManager fm) {
			super(fm);
			penyasFragment = new ImageListFragment();
			alubia16Fragment = ImageGridFragment.newInstance(ImageGridFragment.ALUBIA16);
			alubia15Fragment = ImageGridFragment.newInstance(ImageGridFragment.ALUBIA15);
		}

		@Override
		public int getCount() {
			return 3;
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
			switch (position) {
				case 0:
					return getString(R.string.home_groups);
				case 1:
					return getString(R.string.album_image_pager_title_alubia16);
				case 2:
					return getString(R.string.album_image_pager_title_alubia15);
				default:
					return null;
			}
		}
	}
}