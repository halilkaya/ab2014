package sayfalar;

import java.util.ArrayList;

import veritabani.VeritabaniIslemleri;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mersin.ab2014androidb.R;

public class YeniKayit extends Activity {

	// Nesneler global değişken olarak tanımlanıyor:
	EditText adsoyad, sabitTel, cepTel, adres;
	Spinner resim;
	ArrayList<String> resimler;
	VeritabaniIslemleri islem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yeni_kayit);
		
		// Form elemanları tanımlanıyor:
		adsoyad = (EditText) findViewById(R.id.adsoyad);
		sabitTel = (EditText) findViewById(R.id.sabit_tel);
		cepTel = (EditText) findViewById(R.id.cep_tel);
		adres = (EditText) findViewById(R.id.adres);
		resim = (Spinner) findViewById(R.id.resim);
		
		// VeritabaniIslemleri sınıfı açılıyor:
		islem = new VeritabaniIslemleri(YeniKayit.this);
		
		// ArrayList nesnesi açılıyor:
		resimler = new ArrayList<String>();
		
		// Spinner'a resimler dolduruluyor:
		resimleriDoldur();
		
	}
	
	public void resimleriDoldur() {
		// ArrayList'e drawable'daki resimler ekleniyor:
		resimler.add("anchor");
		resimler.add("aperture");
		resimler.add("arrow_down");
		resimler.add("arrow_up");
		resimler.add("art");
		resimler.add("batterylow");
		resimler.add("bike");
		resimler.add("biker");
		resimler.add("bikewheel");
		resimler.add("blimp");
		
		// Adapter tanımlanıyor:
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(YeniKayit.this,
																android.R.layout.simple_spinner_dropdown_item,
																resimler);
		
		// Adapter, spinner nesnesine bağlanıyor:
		resim.setAdapter(adapter);
	}

	// Gerekli kontrollerin yapılması için bir method oluşturuyoruz:
	public void kaydet() {
		if ( adsoyad.getText().toString().equals("") ) {
			Toast.makeText(getBaseContext(), "Ad soyad giriniz!", Toast.LENGTH_SHORT).show();
		} else if ( sabitTel.getText().toString().equals("") ) {
			Toast.makeText(getBaseContext(), "Sabit telefon giriniz!", Toast.LENGTH_SHORT).show();
		} else if ( cepTel.getText().toString().equals("") ) {
			Toast.makeText(getBaseContext(), "Cep telefonu giriniz!", Toast.LENGTH_SHORT).show();
		} else if ( adres.getText().toString().equals("") ) {
			Toast.makeText(getBaseContext(), "Adres giriniz!", Toast.LENGTH_SHORT).show();
		} else {
			islem.kayitEkle(adsoyad.getText().toString(),
							sabitTel.getText().toString(),
							cepTel.getText().toString(),
							adres.getText().toString(),
							resim.getSelectedItem().toString());
			Toast.makeText(getBaseContext(), "Kayıt eklendi!", Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.yeni_kayit, menu);
		return true;
	}
	
	// ActionBar'daki menülere işlevsellik veriyoruz...
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_kaydet:
			kaydet();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
