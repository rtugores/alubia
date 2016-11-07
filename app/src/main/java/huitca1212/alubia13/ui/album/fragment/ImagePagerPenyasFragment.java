package huitca1212.alubia13.ui.album.fragment;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.Constants;

public class ImagePagerPenyasFragment extends Fragment {

	public static final int INDEX = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_pager, container, false);
		ViewPager pager = (ViewPager)rootView.findViewById(R.id.pager);
		pager.setAdapter(new ImageAdapter(getActivity()));
		pager.setCurrentItem(getArguments().getInt(Constants.Extra.IMAGE_POSITION, 0));
		return rootView;
	}

	private static class ImageAdapter extends PagerAdapter {

		private static final String[] IMAGE_URLS = Constants.IMAGES_PENYAS;

		private LayoutInflater inflater;

		ImageAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}

		@Override
		public int getCount() {
			return IMAGE_URLS.length;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = inflater.inflate(R.layout.layout_pager_image_item, view, false);
			assert imageLayout != null;
			final ImageView imageView = (ImageView)imageLayout.findViewById(R.id.image);
			final ProgressBar spinner = (ProgressBar)imageLayout.findViewById(R.id.loading);

			Picasso.with(view.getContext())
					.load(IMAGE_URLS[position])
					.placeholder(R.drawable.ic_stub)
					.error(R.drawable.ic_error)
					.into(imageView);

			view.addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}
}