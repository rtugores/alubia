package huitca1212.alubia13.ui.album.fragment;


import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.Constants;
import huitca1212.alubia13.ui.album.SimpleImageActivity;


public class ImageGridFragment extends Fragment {

	public ImageGridFragment() {
		//NOOP
	}

	protected GridView gridView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_grid, container, false);
		gridView = (GridView) rootView.findViewById(R.id.grid);
		gridView.setAdapter(new ImageAdapter(getActivity()));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleImageActivity.startActivity(getActivity(), ImagePagerAlubia15Fragment.INDEX, position);
			}
		});
		return rootView;
	}

	private static class ImageAdapter extends BaseAdapter {
		private static final String[] IMAGE_URLS = Constants.IMAGES_ALUBIA15;
		private LayoutInflater inflater;

		ImageAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return IMAGE_URLS.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.layout.layout_grid_image_item, parent, false);
				holder = new ViewHolder();
				assert view != null;
				holder.imageView = (ImageView) view.findViewById(R.id.image);
				holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			Picasso.with(view.getContext())
					.load(IMAGE_URLS[position])
					.placeholder(R.drawable.ic_stub)
					.error(R.drawable.ic_error)
					.into(holder.imageView);

			return view;
		}
	}

	static class ViewHolder {
		ImageView imageView;
		ProgressBar progressBar;
	}
}