package com.temxa.rehber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RehberData extends SQLiteOpenHelper{
	
	private static final String DATABASE_CREATE ="create table " 
      + SabitDegerler.TABLO_REHBER
      + "(" 
      + SabitDegerler.KEY_ID + " integer primary key autoincrement, " 
      + SabitDegerler.AD_SOYAD + " text not null, " 
      + SabitDegerler.TEL_NO + " text not null," 
      + SabitDegerler.EMAIL + " text not null,"
      + SabitDegerler.SIRKET + " text not null,"
      + SabitDegerler.ADRES + " text not null"
      + ");";
	
	public RehberData(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(RehberData.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + SabitDegerler.TABLO_REHBER);
	    onCreate(database);
	  }
}
