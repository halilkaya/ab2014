package veritabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabani extends SQLiteOpenHelper {

	private static final String VERITABANI_ADI = "adres_defteri";
	private static final int VERSION = 1;
	
	public Veritabani(Context context) {
		super(context, VERITABANI_ADI, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE kayitlar (" +
				"no integer primary key autoincrement," +
				"adsoyad string," +
				"sabit_tel string," +
				"cep_tel string," +
				"adres text," +
				"resim string);"
				);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,
							int eskiVersion,
							int yeniVersion) {
		db.execSQL(
				"DROP TABLE IF EXISTS kayitlar;"
				);
		onCreate(db);
	}
	
}
