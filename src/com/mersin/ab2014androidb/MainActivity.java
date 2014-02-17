package com.mersin.ab2014androidb;

import sayfalar.Detaylar;
import sayfalar.Duzenle;
import sayfalar.YeniKayit;
import veritabani.VeritabaniIslemleri;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

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
		
		// Listedeki her bir item'a uzun basılınca yapılacak olan action'ı tanımlıyoruz:
		liste.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int aposition, long arg3) {
				
				// İşlem menülerini CharSequence olarak tanımlıyoruz:
				CharSequence menuler[] = {"Düzenle","Sil"};
				
				// Silme işlemi için AlertDialog oluşturuyoruz:
				final AlertDialog.Builder sil = new AlertDialog.Builder(MainActivity.this);
				
				// İşlemleri listeleyeceğimiz AlertDialog nesnesini oluşturuyoruz:
				AlertDialog.Builder islemler = new AlertDialog.Builder(MainActivity.this);
				islemler.setTitle("İşlem");
				islemler.setItems(menuler, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int pos) {
						
						// position'ların her biri bir menüyü simgeliyor.
						// position'a göre action'ları tanımlıyoruz:
						
						if (pos==0) {
							
							// 0. position, Düzenle'ye denk geliyor. Duzenle activity'sine, seçilen kayda
							// ait verileri göndererek activity'yi başlatıyoruz:
							Intent duzenle = new Intent(MainActivity.this, Duzenle.class);
							Bundle veri = new Bundle();
							veri.putInt("no", islem.no.get(aposition));
							veri.putString("adsoyad", islem.adsoyad.get(aposition));
							veri.putString("sabit_tel", islem.sabit_tel.get(aposition));
							veri.putString("cep_tel", islem.cep_tel.get(aposition));
							veri.putString("adres", islem.adres.get(aposition));
							veri.putString("resim", islem.resim.get(aposition));
							duzenle.putExtra("bilgi", veri);
							startActivity(duzenle);
							
						} else if (pos==1) {
							
							// 1. position, Sil'e denk geliyor. Oluşturduğumuz AlertDialog sınıfında
							// kullanıcının kaydı silmesini onaylatıyoruz:
							sil.setTitle("Sil");
							sil.setMessage("Bu kaydı silmek istediğinize emin misiniz?");
							sil.setCancelable(false);
							sil.setPositiveButton("Evet", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									
									// Kaydı silip listeyi yeniliyoruz:
									islem.kayitSil(islem.no.get(aposition));
									liste.setAdapter(islem.kayitGetir(MainActivity.this, R.layout.liste));
									Toast.makeText(getBaseContext(), "Kayıt silindi!", Toast.LENGTH_SHORT).show();
									
								}
							});
							sil.setNegativeButton("Hayır", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int arg1) {
									
									// Dialog penceresini kapatıyoruz:
									dialog.cancel();
									
								}
							});
							sil.create().show();
							
						}
						
					}
				});
				islemler.create().show();
				
				return true;
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
