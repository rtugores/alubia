package huitca1212.alubia13.ui.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.album.fragment.ImagePagerFragment;

public class SimpleImageActivity extends FragmentActivity {

	public static final String FRAGMENT_TAG_ARG = "fragmentTagArg";
	public static final String IMAGE_POSITION_ARG = "imagePosition";

	public static void startActivity(Context ctx, String tag, int position) {
		Intent intent = new Intent(ctx, SimpleImageActivity.class);
		intent.putExtra(FRAGMENT_TAG_ARG, tag);
		intent.putExtra(IMAGE_POSITION_ARG, position);
		ctx.startActivity(intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String tag = getIntent().getStringExtra(FRAGMENT_TAG_ARG);
		int position = getIntent().getIntExtra(IMAGE_POSITION_ARG, 0);
		Fragment fr = ImagePagerFragment.newInstance(tag, position);
		setTitle(tag.equals(ImagePagerFragment.PENYAS_TAG) ? R.string.album_image_pager_title_penyas : R.string.album_image_pager_title_alubia15);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
	}
}