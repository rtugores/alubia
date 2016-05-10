/**
 * ****************************************************************************
 * Copyright 2014 Sergey Tarasevich
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
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

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
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

		pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager()));
		pager.setCurrentItem(pagerPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends FragmentPagerAdapter {
		Fragment listFragment;
		Fragment gridFragment;

		ImagePagerAdapter(FragmentManager fm) {
			super(fm);
			listFragment = new ImageListFragment();
			gridFragment = new ImageGridFragment();
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					return listFragment;
				case 1:
					return gridFragment;
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
					return getString(R.string.album_image_pager_2);
				default:
					return null;
			}
		}
	}
}