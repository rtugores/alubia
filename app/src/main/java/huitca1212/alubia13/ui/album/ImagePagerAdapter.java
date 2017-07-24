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

public class ImagePagerAdapter extends PagerAdapter {

	private String[] imageUrls;
	private LayoutInflater inflater;

	public ImagePagerAdapter(Context context, String type) {
		inflater = LayoutInflater.from(context);
		if (type.equals(ImagePagerFragment.PENYAS_TAG)) {
			imageUrls = Constants.IMAGES_PENYAS;
		} else {
			imageUrls = Constants.IMAGES_ALUBIA15;
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
		assert imageLayout != null;
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

		Picasso.with(view.getContext())
				.load(imageUrls[position])
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