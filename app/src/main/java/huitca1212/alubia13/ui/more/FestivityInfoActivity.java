package huitca1212.alubia13.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;

public class FestivityInfoActivity extends AppCompatActivity implements View.OnClickListener {

	@Bind(R.id.button_maps) Button mapButton;
	@Bind(R.id.info_write) TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_festivity_info);
		ButterKnife.bind(this);

		text.setMovementMethod(new ScrollingMovementMethod());
		mapButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.button_maps) {
			startActivity(new Intent(this, MapInfoActivity.class));
		}
	}
}