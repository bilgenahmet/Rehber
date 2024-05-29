package com.temxa.rehber;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class KisiListele extends Activity {

	private ArrayList<Kisi> kisiler;
	DataCalistirma datab;
	private KisiBigiler bilgi;
	ListView liste;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listele);

		Button islemler = (Button) findViewById(R.id.islemler);
		liste = (ListView) findViewById(R.id.liste);

		datab = new DataCalistirma(this);
		kisiler = datab.listele();
		bilgi = new KisiBigiler(this, kisiler);
		liste.setAdapter(bilgi);

		islemler.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				kisiekle();
			}
		});

		tiklamaSec();
	}

	@Override
	protected void onResume() {
		// bu metod activity her görünür olduğunda çalıştırılır.
		super.onResume();

		listeyenile();
	}

	public void tiklamaSec() {

		liste.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position, long id) {

				StringBuilder sb = new StringBuilder();
				sb.append(kisiler.get(position).ad_soyad.toString()).append("\n");
				sb.append(kisiler.get(position).tel_no.toString()).append("\n");
				sb.append(kisiler.get(position).email.toString()).append("\n");
				sb.append(kisiler.get(position).sirket.toString()).append("\n");
				sb.append(kisiler.get(position).adres.toString()).append("\n");

				SonucDialog sd = new SonucDialog(KisiListele.this);
				sd.setSonuc(sb.toString());
				sd.show();
			}
		});

		liste.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
				final int ps = pos;

				final CharSequence[] items = { " Kişi Düzenle", "Kişi Sil" };

				AlertDialog.Builder builder = new AlertDialog.Builder(KisiListele.this);
				builder.setTitle(kisiler.get(ps).ad_soyad.toString());
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch (item) {
						case 0:
							kisiDuzenle(ps);
							break;
						case 1:
							kisiSil(ps);
							break;
						default:
							break;
						}
					}
				});
				AlertDialog alert = builder.create();
				alert.show();

				return false;

			}
		});

	}

	public void kisiekle() {

		Intent i = new Intent(KisiListele.this, KisiEkle.class);
		startActivity(i);
		// finish();
	}

	public void kisiDuzenle(int ps) {

		Intent i = new Intent(KisiListele.this, Degistir.class);
		i.putExtra("ID", kisiler.get(ps)._id);
		i.putExtra("TEL", kisiler.get(ps).tel_no);
		i.putExtra("ISIM", kisiler.get(ps).ad_soyad);
		i.putExtra("MAIL", kisiler.get(ps).email);
		i.putExtra("SIRKET", kisiler.get(ps).sirket);
		i.putExtra("ADRES", kisiler.get(ps).adres);

		startActivity(i);
		finish();
	}

	public void kisiSil(int ps) {

		datab.sil(kisiler.get(ps)._id);
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(getApplicationContext(), kisiler.get(ps).ad_soyad.toString() + "  silinmiştir !",
				duration);
		toast.show();
		listeyenile();

	}

	public void listeyenile() {
		bilgi.kisiler = datab.listele();
		bilgi.notifyDataSetChanged();
	}

}
