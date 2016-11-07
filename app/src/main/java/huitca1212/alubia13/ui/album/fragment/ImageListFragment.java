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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.Constants;
import huitca1212.alubia13.ui.album.SimpleImageActivity;

public class ImageListFragment extends Fragment {

	public ImageListFragment() {
		//NOOP
	}

	protected ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_list, container, false);
		listView = (ListView)rootView.findViewById(android.R.id.list);
		listView.setAdapter(new ImageAdapter(getActivity()));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleImageActivity.startActivity(getActivity(), ImagePagerPenyasFragment.INDEX, position);
			}
		});
		return rootView;
	}

	private static class ImageAdapter extends BaseAdapter {
		private static final String[] IMAGE_URLS = Constants.IMAGES_PENYAS;
		private static final String[] IMAGE_STRINGS = Constants.IMAGE_TITLES;
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
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = inflater.inflate(R.layout.layout_list_image_item, parent, false);
				holder = new ViewHolder();
				holder.text = (TextView)view.findViewById(R.id.text);
				holder.image = (ImageView)view.findViewById(R.id.image);
				view.setTag(holder);
			} else {
				holder = (ViewHolder)view.getTag();
			}

			holder.text.setText(IMAGE_STRINGS[position]);

			Picasso.with(view.getContext())
					.load(IMAGE_URLS[position])
					.placeholder(R.drawable.ic_stub)
					.error(R.drawable.ic_error)
					.into(holder.image);

			return view;
		}
	}

	static class ViewHolder {
		TextView text;
		ImageView image;
	}
}
