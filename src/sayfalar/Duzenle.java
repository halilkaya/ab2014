package sayfalar;

import java.util.ArrayList;

import veritabani.VeritabaniIslemleri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView.BufferType;

import com.mersin.ab2014androidb.R;

public class Duzenle extends Activity {
	
	EditText adsoyad, sabitTel, cepTel, adres;
	Spinner resim;
	ArrayList<String> resimler;
	
	VeritabaniIslemleri islem;
	
	Bundle gelenVeri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duzenle);
		
		islem = new VeritabaniIslemleri(Duzenle.this);
		resimler = new ArrayList<String>();
		
		Intent ben = getIntent();
		gelenVeri = ben.getBundleExtra("bilgi");
		
		// EditText ve Spinner nesnelerimizi tanımlıyoruz:
		adsoyad = (EditText) findViewById(R.id.adsoyad);
		sabitTel = (EditText) findViewById(R.id.sabit_tel);
		cepTel = (EditText) findViewById(R.id.cep_tel);
		adres = (EditText) findViewById(R.id.adres);
		resim = (Spinner) findViewById(R.id.resim);
		
		// MainActivity'den gönderdiğimiz verilere EditText'lere ve Spinner'a dolduruyoruz:
		resimleriDoldur();
		verileriDoldur();
		
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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Duzenle.this,
																android.R.layout.simple_spinner_dropdown_item,
																resimler);
		
		// Adapter, spinner nesnesine bağlanıyor:
		resim.setAdapter(adapter);
	}
	
	public void verileriDoldur() {
		
		adsoyad.setText(gelenVeri.getString("adsoyad"));
		sabitTel.setText(gelenVeri.getString("sabit_tel"), BufferType.EDITABLE);
		cepTel.setText(gelenVeri.getString("cep_tel"), BufferType.EDITABLE);
		adres.setText(gelenVeri.getString("adres"));
		if (gelenVeri.getString("resim").equals("anchor")) {
			resim.setSelection(0);
		} else if (gelenVeri.getString("resim").equals("aperture")) {
			resim.setSelection(1);
		} else if (gelenVeri.getString("resim").equals("arrow_down")) {
			resim.setSelection(2);
		} else if (gelenVeri.getString("resim").equals("arrow_up")) {
			resim.setSelection(3);
		} else if (gelenVeri.getString("resim").equals("art")) {
			resim.setSelection(4);
		} else if (gelenVeri.getString("resim").equals("batterylow")) {
			resim.setSelection(5);
		} else if (gelenVeri.getString("resim").equals("bike")) {
			resim.setSelection(6);
		} else if (gelenVeri.getString("resim").equals("biker")) {
			resim.setSelection(7);
		} else if (gelenVeri.getString("resim").equals("bikewheel")) {
			resim.setSelection(8);
		} else if (gelenVeri.getString("resim").equals("blimp")) {
			resim.setSelection(9);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duzenle, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_kaydet:
			
			islem.kayitDuzenle(gelenVeri.getInt("no"),
								adsoyad.getText().toString(),
								sabitTel.getText().toString(),
								cepTel.getText().toString(),
								adres.getText().toString(),
								resim.getSelectedItem().toString());
			
			Toast.makeText(getBaseContext(), "Kayıt düzenlendi!", Toast.LENGTH_SHORT).show();
			finish();
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
