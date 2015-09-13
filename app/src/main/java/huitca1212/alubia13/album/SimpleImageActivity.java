/**
 * ****************************************************************************
 * Copyright 2014 Sergey Tarasevich
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
package huitca1212.alubia13.album;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import huitca1212.alubia13.Constants;
import huitca1212.alubia13.R;
import huitca1212.alubia13.album.fragment.ImageGridFragment;
import huitca1212.alubia13.album.fragment.ImageListFragment;
import huitca1212.alubia13.album.fragment.ImagePagerFragmentAlubia15;
import huitca1212.alubia13.album.fragment.ImagePagerFragmentPenyas;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class SimpleImageActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
        Fragment fr;
        String tag;
        int titleRes;
        switch (frIndex) {
            default:
            case ImagePagerFragmentPenyas.INDEX:
                tag = ImagePagerFragmentPenyas.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if (fr == null) {
                    fr = new ImagePagerFragmentPenyas();
                    fr.setArguments(getIntent().getExtras());
                }
                titleRes = R.string.ac_name_image_pager_penyas;
                break;
            case ImagePagerFragmentAlubia15.INDEX:
                tag = ImagePagerFragmentAlubia15.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if (fr == null) {
                    fr = new ImagePagerFragmentAlubia15();
                    fr.setArguments(getIntent().getExtras());
                }
                titleRes = R.string.ac_name_image_pager_alubia15;
                break;
        }

        setTitle(titleRes);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
    }
}