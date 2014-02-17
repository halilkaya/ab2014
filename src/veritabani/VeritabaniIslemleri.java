package veritabani;

import java.util.ArrayList;



import adapter.ListeAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class VeritabaniIslemleri {
	
	private Veritabani db;
	private SQLiteDatabase yazilabilir;
	private SQLiteDatabase okunabilir;
	public ArrayList<Integer> no;
	public ArrayList<String> adsoyad;
	public ArrayList<String> sabit_tel;
	public ArrayList<String> cep_tel;
	public ArrayList<String> adres;
	public ArrayList<String> resim;
	
	public VeritabaniIslemleri(Context context) {
		db = new Veritabani(context);
		yazilabilir = db.getWritableDatabase();
		okunabilir = db.getReadableDatabase();
	}

	public void kayitEkle(
					String adsoyad,
					String sabit_tel,
					String cep_tel,
					String adres,
					String resim) {
		
		ContentValues bilgiler = new ContentValues();
		bilgiler.put("adsoyad", adsoyad);
		bilgiler.put("sabit_tel", sabit_tel);
		bilgiler.put("cep_tel", cep_tel);
		bilgiler.put("adres", adres);
		bilgiler.put("resim", resim);
		
		yazilabilir.insertOrThrow("kayitlar", null, bilgiler);
		
	}
	
	public ListeAdapter kayitGetir(Context context,
							int liste) {
		
		no = new ArrayList<Integer>();
		adsoyad = new ArrayList<String>();
		sabit_tel = new ArrayList<String>();
		cep_tel = new ArrayList<String>();
		adres = new ArrayList<String>();
		resim = new ArrayList<String>();
		ListeAdapter adapter;
		
		Cursor satir = okunabilir.rawQuery(
				"SELECT * FROM kayitlar",
				null);
		satir.moveToFirst();
		
		if (satir.getCount()>0) {
			
			for (int i=0; i<satir.getCount(); i++) {
				no.add(satir.getInt(0));
				adsoyad.add(satir.getString(1));
				sabit_tel.add(satir.getString(2));
				cep_tel.add(satir.getString(3));
				adres.add(satir.getString(4));
				resim.add(satir.getString(5));
				satir.moveToNext();
			}
			
			adapter = new ListeAdapter(
					context,
					liste,
					resim,
					adsoyad,
					cep_tel);
			
		} else {
			Toast.makeText(context, "KAYIT BULUNAMADI!",
					Toast.LENGTH_LONG).show();
			adapter = null;
		}
	
		return adapter;
		
	}
	

	public void kayitSil(int no) {
		
		yazilabilir.execSQL("DELETE FROM kayitlar WHERE no=" + no);
		
	}
	
	public void kayitDuzenle(
			int no,
			String adsoyad,
			String sabit_tel,
			String cep_tel,
			String adres,
			String resim) {

		yazilabilir.execSQL("UPDATE kayitlar SET adsoyad='" + adsoyad + "', " +
												"sabit_tel='" + sabit_tel + "', " +
												"cep_tel='" + cep_tel + "', " +
												"adres='" + adres + "', " +
												"resim='" + resim+ "' WHERE no=" + no);
	
	}
	
}
