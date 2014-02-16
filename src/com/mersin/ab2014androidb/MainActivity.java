package com.mersin.ab2014androidb;

import sayfalar.Detaylar;
import sayfalar.YeniKayit;
import veritabani.VeritabaniIslemleri;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private VeritabaniIslemleri islem;
	private ListView liste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		islem = new VeritabaniIslemleri(MainActivity.this);
		
		liste = (ListView) findViewById(R.id.liste);
		liste.setAdapter(islem.kayitGetir(MainActivity.this, R.layout.liste));
		
		liste.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int position,
					long arg3) {
				
				Intent detay = new Intent(
									MainActivity.this,
									Detaylar.class);
				
				Bundle veri = new Bundle();
				veri.putInt("no", islem.no.get(position));
				veri.putString("adsoyad",
						islem.adsoyad.get(position));
				veri.putString("sabit_tel", 
						islem.sabit_tel.get(position));
				veri.putString("cep_tel", 
						islem.cep_tel.get(position));
				veri.putString("adres", 
						islem.adres.get(position));
				veri.putString("resim", 
						islem.resim.get(position));
				
				detay.putExtra("bilgi", veri);
				
				startActivity(detay);
				
			}
		});
		
	}
	
	// Yeni kayıt eklendikten ve bir kayıt düzenlendikten sonra bu ekrana dönüldüğünde
	// listenin yenilenmesi gerekiyor. Bu yüzden onResume olayına kayıtları tekrar listeliyoruz:
	@Override
	public void onResume() {
		liste.setAdapter(islem.kayitGetir(MainActivity.this, R.layout.liste));
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// ActionBar'daki menülere işlevsellik veriyoruz...
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_yeni_kayit:
			Intent yeniKayit = new Intent(MainActivity.this, YeniKayit.class);
			startActivity(yeniKayit);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
