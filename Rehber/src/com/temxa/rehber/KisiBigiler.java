package com.temxa.rehber;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KisiBigiler extends BaseAdapter {

	private LayoutInflater mInflater;
	ArrayList<Kisi> kisiler;
	DataCalistirma datab;

	public KisiBigiler(Context context, ArrayList<Kisi> kisiler) {

		mInflater = LayoutInflater.from(context);

		datab = new DataCalistirma(context);

		this.kisiler = kisiler;
	}

	public int getCount() {
		return kisiler.size();
	}

	public long getItemId(int position) {
		return position;
	}

	public Kisi getItem(int i) {
		return kisiler.get(i);
	}

//	public ArrayList<Kisi> kisiListesi() {
//		return kisiler;
//	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.isim_satiri, null);

			holder = new ViewHolder();
			holder.isim = (TextView) convertView.findViewById(R.id.isimview);
		} else {

			holder = (ViewHolder) convertView.getTag();

		}

		holder.isim.setText(kisiler.get(position).ad_soyad);

		convertView.setTag(holder);

		return convertView;

	}

	public class ViewHolder {

		TextView isim;
	}
}
