package com.temxa.rehber;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataCalistirma {

	private SQLiteDatabase db;
	private final Context context;
	private final RehberData dbhelper;

	public DataCalistirma(Context c) {
		context = c;
		dbhelper = new RehberData(context, SabitDegerler.TABLO_REHBER, null, SabitDegerler.DATABASE_VERSION);
	}

	// public void ac() throws SQLException {
	// bunu burada açma
	// db=dbhelper.getWritableDatabase();
	// }

	// public void kapat(){
	// dbhelper.close();
	// }

	public void adres_ekle(String ad_soyad, String telefon, String adres, String sirket, String email)
			throws SQLException {

		ContentValues icerik = new ContentValues();

		icerik.put(SabitDegerler.AD_SOYAD, ad_soyad);
		icerik.put(SabitDegerler.TEL_NO, telefon);
		icerik.put(SabitDegerler.ADRES, adres);
		icerik.put(SabitDegerler.SIRKET, sirket);
		icerik.put(SabitDegerler.EMAIL, email);

		db = dbhelper.getWritableDatabase();
		db.insert(SabitDegerler.TABLO_REHBER, null, icerik);
		dbhelper.close();
	}

	public void degistir(int id, String ad_soyad, String telefon, String adres, String sirket, String email)
			throws SQLException {

		ContentValues yeniicerik = new ContentValues();
		String[] idArray = { String.valueOf(id) };

		yeniicerik.put(SabitDegerler.AD_SOYAD, ad_soyad);
		yeniicerik.put(SabitDegerler.TEL_NO, telefon);
		yeniicerik.put(SabitDegerler.ADRES, adres);
		yeniicerik.put(SabitDegerler.SIRKET, sirket);
		yeniicerik.put(SabitDegerler.EMAIL, email);

		db = dbhelper.getWritableDatabase();
		db.update(SabitDegerler.TABLO_REHBER, yeniicerik, SabitDegerler.KEY_ID + "=?", idArray);
		dbhelper.close();
	}

	public void sil(int id) throws SQLException {
		db = dbhelper.getWritableDatabase();
		db.delete(SabitDegerler.TABLO_REHBER, SabitDegerler.KEY_ID + "=" + id, null);
		dbhelper.close();
	}

	public Cursor ad_cagir() {
		Cursor adlar = db.query(SabitDegerler.TABLO_REHBER, null, null, null, null, null, SabitDegerler.AD_SOYAD);
		return adlar;
	}

	public ArrayList<Kisi> listele() {
		db = dbhelper.getReadableDatabase();

		ArrayList<Kisi> kisiler = new ArrayList<Kisi>();
		Cursor kisi = ad_cagir();

		if (kisi.moveToFirst()) {
			do {
				int id1 = kisi.getInt(kisi.getColumnIndex(SabitDegerler.KEY_ID));
				String ad_soyad = kisi.getString(kisi.getColumnIndex(SabitDegerler.AD_SOYAD));
				String tel = kisi.getString(kisi.getColumnIndex(SabitDegerler.TEL_NO));
				String mail = kisi.getString(kisi.getColumnIndex(SabitDegerler.EMAIL));
				String sirket = kisi.getString(kisi.getColumnIndex(SabitDegerler.SIRKET));
				String adres = kisi.getString(kisi.getColumnIndex(SabitDegerler.ADRES));

				Kisi temp = new Kisi(id1, ad_soyad, tel, mail, sirket, adres);
				kisiler.add(temp);

			} while (kisi.moveToNext());

		}
		dbhelper.close();
		return kisiler;

	}
}
