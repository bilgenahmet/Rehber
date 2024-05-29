package com.temxa.rehber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Degistir extends Activity {

	private EditText isimE, telE, mailE, sirketE, adresE;
	private Button kaydet;
	private DataCalistirma datab;
	private int id;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ekle_degistir);

		id = Integer.parseInt(getIntent().getExtras().get("ID").toString());

		isimE = (EditText) findViewById(R.id.adedit);
		telE = (EditText) findViewById(R.id.teledit);
		mailE = (EditText) findViewById(R.id.mailedit);
		sirketE = (EditText) findViewById(R.id.sirketedit);
		adresE = (EditText) findViewById(R.id.adresedit);
		kaydet = (Button) findViewById(R.id.kaydet);

		isimE.setText(getIntent().getExtras().get("ISIM").toString());
		telE.setText(getIntent().getExtras().get("TEL").toString());
		mailE.setText(getIntent().getExtras().get("MAIL").toString());
		sirketE.setText(getIntent().getExtras().get("SIRKET").toString());
		adresE.setText(getIntent().getExtras().get("ADRES").toString());

		kaydet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				datab = new DataCalistirma(Degistir.this);
				duzenle();
			}
		});

	}

	public void duzenle() {
		datab.degistir(id, isimE.getText().toString(), telE.getText().toString(), mailE.getText().toString(), sirketE
				.getText().toString(), adresE.getText().toString());
//		bunlar gerekli değil
//		Intent i = new Intent(Degistir.this, KisiListele.class);
//		startActivity(i);
		finish();

	}

}
