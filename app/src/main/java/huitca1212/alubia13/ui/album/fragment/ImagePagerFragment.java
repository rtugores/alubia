package huitca1212.alubia13.ui.album.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huitca1212.alubia13.R;
import huitca1212.alubia13.model.album.AlbumItem;
import huitca1212.alubia13.ui.album.ImagePagerAdapter;

public class ImagePagerFragment extends Fragment {

	private static final String ALBUM_ITEM_ARG = "albumItemArg";
	private static final String POSITION_ARG = "positionArg";
	public static final String PENYAS = "penyas";
	public static final String ALUBIA_16 = "alubia16";
	public static final String ALUBIA_15 = "alubia15";

	public static ImagePagerFragment newInstance(AlbumItem albumitem, int position) {
		ImagePagerFragment fragment = new ImagePagerFragment();
		Bundle args = new Bundle();
		args.putSerializable(ALBUM_ITEM_ARG, albumitem);
		args.putInt(POSITION_ARG, position);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_pager, container, false);
		ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
		AlbumItem albumItem = (AlbumItem) getArguments().getSerializable(ALBUM_ITEM_ARG);
		if (albumItem != null) {
			ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity(), albumItem);
			pager.setAdapter(adapter);
			pager.setCurrentItem(getArguments().getInt(POSITION_ARG));
		}
		return rootView;
	}
}