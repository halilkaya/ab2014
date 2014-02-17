package sayfalar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mersin.ab2014androidb.R;

public class Detaylar extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detaylar);
		
		Intent ben = getIntent();
		Bundle veri = ben.getBundleExtra("bilgi");
		
		ImageView resim = (ImageView)
				findViewById(R.id.resim);
		TextView adsoyad = (TextView)
				findViewById(R.id.adsoyad);
		TextView sabit_tel = (TextView)
				findViewById(R.id.sabit_tel);
		TextView cep_tel = (TextView)
				findViewById(R.id.cep_tel);
		TextView adres = (TextView)
				findViewById(R.id.adres);
		
		resim.setImageResource(
				this.getResources().getIdentifier(
						veri.getString("resim"),
						"drawable",
						"com.mersin.ab2014androidb")
				);
		adsoyad.setText(veri.getString("adsoyad"));
		sabit_tel.setText(veri.getString("sabit_tel"));
		cep_tel.setText(veri.getString("cep_tel"));
		adres.setText(veri.getString("adres"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detaylar, menu);
		return true;
	}
	
	// ActionBar'daki menülere işlevsellik veriyoruz...
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_geri:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
