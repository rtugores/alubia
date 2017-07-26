package huitca1212.alubia13.ui.album;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.fragment.ImagePagerFragment;
import huitca1212.alubia13.utils.ImageUtils;
import huitca1212.alubia13.utils.ScreenUtils;

public class ImagePagerAdapter extends PagerAdapter {

	private String[] imageUrls;
	private LayoutInflater inflater;

	public ImagePagerAdapter(Context context, String type) {
		inflater = LayoutInflater.from(context);
		imageUrls = type.equals(ImagePagerFragment.PENYAS_TAG) ? Constants.IMAGES_PENYAS : Constants.IMAGES_ALUBIA15;
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
		assert imageLayout != null;
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		final View loader = imageLayout.findViewById(R.id.loader);

		loader.setVisibility(View.VISIBLE);
		Picasso.with(view.getContext())
				.load(ImageUtils.generateImageResizeUrl(imageUrls[position], ScreenUtils.getScreenWidth()))
				.placeholder(R.drawable.ic_stub)
				.error(R.drawable.ic_error)
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