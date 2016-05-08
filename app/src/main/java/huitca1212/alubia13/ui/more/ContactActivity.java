package huitca1212.alubia13.ui.more;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
	@Bind(R.id.contactar_whatsapp) Button whatsappButton;
	@Bind(R.id.contactar_email) Button emailButton;

	public static void startActivity(Activity activity) {
		Intent intent = new Intent(activity, ContactActivity.class);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		ButterKnife.bind(this);

		whatsappButton.setOnClickListener(this);
		emailButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.contactar_whatsapp) {
			Intent i = new Intent(Intent.ACTION_DIAL);
			i.setData(Uri.parse(getString(R.string.contact_my_phone)));
			startActivity(i);
		} else if (id == R.id.contactar_email) {
			Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(getString(R.string.contact_my_email)));
			startActivity(Intent.createChooser(i, getString(R.string.contact_send_text)));
		}
	}
}