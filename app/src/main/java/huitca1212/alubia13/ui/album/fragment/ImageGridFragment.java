package huitca1212.alubia13.ui.album.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.Constants;
import huitca1212.alubia13.ui.album.GridSpacingItemDecoration;
import huitca1212.alubia13.ui.album.ImageAdapter;
import huitca1212.alubia13.ui.album.SimpleImageActivity;
import huitca1212.alubia13.utils.ScreenUtils;

public class ImageGridFragment extends Fragment implements ImageAdapter.OnItemClickListener {

	private static final String TYPE_ARG = "typeArg";
	public static final String ALUBIA16 = "alubia16";
	public static final String ALUBIA15 = "alubia15";
	private static final int NUM_COLUMNS = 3;
	private static final int SEPARATION_DP = 9;

	private String type;

	public static Fragment newInstance(String type) {
		Fragment fragment = new ImageGridFragment();
		Bundle args = new Bundle();
		args.putString(TYPE_ARG, type);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_grid, container, false);
		RecyclerView imageGrid = (RecyclerView) rootView.findViewById(R.id.recycler_view);

		type = getArguments().getString(TYPE_ARG);
		if (type != null) {
			ImageAdapter adapter = new ImageAdapter(getActivity(), this,
					type.equals(ALUBIA16) ? Constants.IMAGES_ALUBIA16 : Constants.IMAGES_ALUBIA15, R.layout.layout_grid_image_item);
			imageGrid.addItemDecoration(new GridSpacingItemDecoration(NUM_COLUMNS, ScreenUtils.dpToPx(rootView.getContext(), SEPARATION_DP), true));
			imageGrid.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMNS));
			imageGrid.setAdapter(adapter);
		}

		return rootView;
	}

	@Override
	public void onItemClick(int position) {
		SimpleImageActivity.startActivity(getActivity(), type.equals(ALUBIA16) ? ImagePagerFragment.ALUBIA_16 : ImagePagerFragment.ALUBIA_15, position);
	}
}