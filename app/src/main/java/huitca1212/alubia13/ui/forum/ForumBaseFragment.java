package huitca1212.alubia13.ui.forum;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public abstract class ForumBaseFragment extends Fragment implements TextWatcher{

	protected void blockScreen(Button continueButton, EditText boxEditText, LinearLayout progressbarView) {
		continueButton.setEnabled(false);
		boxEditText.setEnabled(false);
		progressbarView.setVisibility(View.VISIBLE);
	}

	protected void unblockScreen(Button continueButton, EditText boxEditText, LinearLayout progressbarView) {
		continueButton.setEnabled(true);
		boxEditText.setEnabled(true);
		progressbarView.setVisibility(View.GONE);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}
}
