package huitca1212.alubia13.ui.album.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.ImagePagerAdapter;

public class ImagePagerFragment extends Fragment {

	private static final String TYPE_ARG = "typeArg";
	private static final String POSITION_ARG = "positionArg";
	public static final String PENYAS_TAG = "penyasTag";
	public static final String ALUBIA_15_TAG = "alubia15Tag";

	public static ImagePagerFragment newInstance(String type, int position) {
		ImagePagerFragment fragment = new ImagePagerFragment();
		Bundle args = new Bundle();
		args.putString(TYPE_ARG, type);
		args.putInt(POSITION_ARG, position);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_pager, container, false);
		ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
		String type = getArguments().getString(TYPE_ARG);
		if (type != null) {
			ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity(), type);
			pager.setAdapter(adapter);
			pager.setCurrentItem(getArguments().getInt(POSITION_ARG));
		}
		return rootView;
	}
}