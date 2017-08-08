package huitca1212.alubia13.ui.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import huitca1212.alubia13.model.album.AlbumItem;
import huitca1212.alubia13.ui.album.fragment.ImagePagerFragment;

public class SimpleImageActivity extends FragmentActivity {

	public static final String ALBUM_ITEM_ARG = "albumItem";
	public static final String IMAGE_POSITION_ARG = "imagePosition";

	public static void startActivity(Context ctx, AlbumItem item, int position) {
		Intent intent = new Intent(ctx, SimpleImageActivity.class);
		intent.putExtra(ALBUM_ITEM_ARG, item);
		intent.putExtra(IMAGE_POSITION_ARG, position);
		ctx.startActivity(intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlbumItem albumItem = (AlbumItem) getIntent().getSerializableExtra(ALBUM_ITEM_ARG);
		int position = getIntent().getIntExtra(IMAGE_POSITION_ARG, 0);
		Fragment fr = ImagePagerFragment.newInstance(albumItem, position);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, albumItem.getTitle()).commit();
	}
}