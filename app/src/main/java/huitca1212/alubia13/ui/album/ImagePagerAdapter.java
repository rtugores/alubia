package huitca1212.alubia13.ui.album;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.fragment.ImagePagerFragment;

public class ImagePagerAdapter extends PagerAdapter {

	private String[] imageUrls;
	private String[] titles;
	private LayoutInflater inflater;

	public ImagePagerAdapter(Context context, String type) {
		inflater = LayoutInflater.from(context);
		switch (type) {
			case ImagePagerFragment.PENYAS:
				imageUrls = Constants.IMAGES_PENYAS;
				titles = Constants.TITLES_PENYAS;
				break;
			case ImagePagerFragment.ALUBIA_16:
				imageUrls = Constants.IMAGES_ALUBIA16;
				break;
			case ImagePagerFragment.ALUBIA_15:
				imageUrls = Constants.IMAGES_ALUBIA15;
				break;
		}
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return imageUrls.length;
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = inflater.inflate(R.layout.layout_pager_image_item, view, false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		TextView title = (TextView) imageLayout.findViewById(R.id.title);
		final View loader = imageLayout.findViewById(R.id.loader);

		loader.setVisibility(View.VISIBLE);
		if (titles != null) {
			title.setVisibility(View.VISIBLE);
			title.setText(titles[position]);
		} else {
			title.setVisibility(View.GONE);
		}
		Picasso.with(view.getContext())
				.load(imageUrls[position])
				.error(R.drawable.ic_stub)
				.into(imageView, new com.squareup.picasso.Callback() {
					@Override
					public void onSuccess() {
						loader.setVisibility(View.GONE);
					}

					@Override
					public void onError() {
						loader.setVisibility(View.GONE);

					}
				});

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