package huitca1212.alubia13.ui.album.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.Constants;
import huitca1212.alubia13.ui.album.ImageAdapter;
import huitca1212.alubia13.ui.album.SimpleImageActivity;

public class ImageListFragment extends Fragment implements ImageAdapter.OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album_image_list, container, false);
		RecyclerView imageList = (RecyclerView) rootView.findViewById(R.id.recycler_view);

		ImageAdapter adapter = new ImageAdapter(getActivity(), this, Constants.IMAGES_PENYAS, R.layout.layout_list_image_item);
		imageList.setLayoutManager(new LinearLayoutManager(getActivity()));
		imageList.setAdapter(adapter);

		return rootView;
	}

	@Override
	public void onItemClick(int position) {
		SimpleImageActivity.startActivity(getActivity(), ImagePagerFragment.PENYAS, position);
	}
}
