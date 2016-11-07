package huitca1212.alubia13.ui.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.fragment.ImagePagerAlubia15Fragment;
import huitca1212.alubia13.ui.album.fragment.ImagePagerPenyasFragment;

public class SimpleImageActivity extends FragmentActivity {

	public static void startActivity(Context ctx, int index, int position) {
		Intent intent = new Intent(ctx, SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, index);
		intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
		ctx.startActivity(intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
		Fragment fr;
		String tag;
		int titleRes;
		switch (frIndex) {
			default:
			case ImagePagerPenyasFragment.INDEX:
				tag = ImagePagerPenyasFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImagePagerPenyasFragment();
					fr.setArguments(getIntent().getExtras());
				}
				titleRes = R.string.album_image_pager_1;
				break;
			case ImagePagerAlubia15Fragment.INDEX:
				tag = ImagePagerAlubia15Fragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImagePagerAlubia15Fragment();
					fr.setArguments(getIntent().getExtras());
				}
				titleRes = R.string.album_image_pager_2;
				break;
		}

		setTitle(titleRes);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
	}
}