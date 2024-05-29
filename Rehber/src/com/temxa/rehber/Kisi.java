package com.temxa.rehber;

import java.io.Serializable;

public class Kisi implements Serializable {
	private static final long serialVersionUID = 4916137331470149547L;

	public Kisi(int id, String isim, String tel, String mail, String sirket_, String adres_) {
		_id = id;
		ad_soyad = isim;
		tel_no = tel;
		email = mail;
		sirket = sirket_;
		adres = adres_;
	}

	public int _id;
	public String ad_soyad;
	public String tel_no;
	public String email;
	public String sirket;
	public String adres;
}
