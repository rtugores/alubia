package huitca1212.alubia13.ui.more;

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
			Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:664732632"));
			startActivity(intent);
		} else if (id == R.id.contactar_email) {
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("message/rfc822")
					.putExtra(Intent.EXTRA_EMAIL, "huitca1212@gmail.com");
			startActivity(Intent.createChooser(intent, "Enviar mediante"));
		}
	}
}