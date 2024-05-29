package com.temxa.rehber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KisiEkle extends Activity {

	EditText isimE, telE, mailE, sirketE, adresE;
	Button kaydet;
	DataCalistirma datab;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ekle_degistir);

		isimE = (EditText) findViewById(R.id.adedit);
		telE = (EditText) findViewById(R.id.teledit);
		mailE = (EditText) findViewById(R.id.mailedit);
		sirketE = (EditText) findViewById(R.id.sirketedit);
		adresE = (EditText) findViewById(R.id.adresedit);
		kaydet = (Button) findViewById(R.id.kaydet);

		kaydet.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				datab = new DataCalistirma(KisiEkle.this);

				if (isimE.getText().length() != 0 && telE.getText().length() != 0) {
					kisEkle();
				} else {
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(getApplicationContext(), "İsim ve telefon numarası bilgisini giriniz",
							duration);
					toast.show();
				}

			}
		});

	}

	public void kisEkle() {

		datab.adres_ekle(isimE.getText().toString(), telE.getText().toString(), adresE.getText().toString(), sirketE
				.getText().toString(), mailE.getText().toString());
		isimE.setText("");
		telE.setText("");
		adresE.setText("");
		mailE.setText("");
		sirketE.setText("");

//		finish demek yeterli
//		Intent i = new Intent(KisiEkle.this, KisiListele.class);
//		startActivity(i);
		finish();

	}

}
