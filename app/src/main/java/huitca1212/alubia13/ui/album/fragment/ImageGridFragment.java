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

	private static final int NUM_COLUMNS = 3;
	private static final int SEPARATION_DP = 9;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_grid, container, false);
		RecyclerView imageGrid = (RecyclerView) rootView.findViewById(R.id.recycler_view);

		ImageAdapter adapter = new ImageAdapter(getActivity(), this, Constants.IMAGES_ALUBIA15, R.layout.layout_grid_image_item);
		imageGrid.addItemDecoration(new GridSpacingItemDecoration(NUM_COLUMNS, ScreenUtils.dpToPx(rootView.getContext(), SEPARATION_DP), true));
		imageGrid.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMNS));
		imageGrid.setAdapter(adapter);

		return rootView;
	}

	@Override
	public void onItemClick(int position) {
		SimpleImageActivity.startActivity(getActivity(), ImagePagerFragment.ALUBIA_15_TAG, position);
	}
}