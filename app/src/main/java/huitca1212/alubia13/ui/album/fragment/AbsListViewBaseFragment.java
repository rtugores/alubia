/**
 * ****************************************************************************
 * Copyright 2011-2014 Sergey Tarasevich
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
package huitca1212.alubia13.ui.album.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import android.content.Intent;
import android.widget.AbsListView;

import huitca1212.alubia13.ui.album.Constants;
import huitca1212.alubia13.ui.album.SimpleImageActivity;


/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public abstract class AbsListViewBaseFragment extends BaseFragment {

	protected AbsListView listView;

	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;

	@Override
	public void onResume() {
		super.onResume();
		applyScrollListener();
	}

	protected void startImagePagerActivityPenyas(int position) {
		Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerPenyasFragment.INDEX);
		intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	protected void startImagePagerActivityAlubia15(int position) {
		Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerAlubia15Fragment.INDEX);
		intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	private void applyScrollListener() {
		listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
	}
}