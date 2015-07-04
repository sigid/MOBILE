package com.menu.digital;

public class EntitasMakanan {
	int idmenu;
	String namamenu = "";
	String hargamenu = "";
	String deskripsimenu = "";
	String picmenu = "";

	public void setIDmenu(int id) {
		this.idmenu = id;
	}

	public int getIDmenu() {
		return idmenu;
	}

	public void setNamaMenu(String n) {
		this.namamenu = n;
	}

	public String getNamaMenu() {
		return namamenu;
	}

	public void setHargaMenu(String h) {
		this.hargamenu = h;
	}

	public String getHargaMenu() {
		return hargamenu;
	}

	public void setDeskripsiMenu(String d) {
		this.deskripsimenu = d;
	}

	public String getDeskripsiMenu() {
		return deskripsimenu;
	}

	public void setPicMenu(String p) {
		this.picmenu = p;
	}

	public String getPicMenu() {
		return picmenu;
	}

}
