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
import huitca1212.alubia13.model.album.AlbumItem;
import huitca1212.alubia13.model.album.AlbumLink;

public class ImagePagerAdapter extends PagerAdapter {

	private LayoutInflater inflater;
	private AlbumItem albumItem;

	public ImagePagerAdapter(Context context, AlbumItem albumItem) {
		inflater = LayoutInflater.from(context);
		this.albumItem = albumItem;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return albumItem.getLinks().size();
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = inflater.inflate(R.layout.layout_pager_image_item, view, false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		TextView title = (TextView) imageLayout.findViewById(R.id.title);
		final View loader = imageLayout.findViewById(R.id.loader);

		AlbumLink albumLink = albumItem.getLinks().get(position);

		loader.setVisibility(View.VISIBLE);
		if (albumLink.getTitle() != null) {
			title.setVisibility(View.VISIBLE);
			title.setText(albumLink.getTitle());
		} else {
			title.setVisibility(View.GONE);
		}
		Picasso.with(view.getContext())
				.load(albumLink.getUrl())
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