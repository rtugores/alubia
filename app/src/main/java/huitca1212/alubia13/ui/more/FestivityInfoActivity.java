package huitca1212.alubia13.ui.more;

import android.content.Intent;
import android.net.Uri;
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
			Uri uri = Uri.parse(
					"https://www.google.es/maps/place/24234+Laguna+de+Negrillos,+Le%C3%B3n/@42.2397558,-5.6599392,11z/data=!4m2!3m1!1s0xd38321134a1221b:0x551ce1e99dc7011d");
			startActivity(new Intent(Intent.ACTION_VIEW, uri));
		}
	}
}