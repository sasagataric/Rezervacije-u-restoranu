package restoran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class OsobaNaListiCekanja implements Serializable{

	private String ime;
	private String brojTelefona;
	private String mejl;
	private String datum;
	private String vremeDolaska;
	private String smena;
	private int brojOsoba;
	
	public OsobaNaListiCekanja(String ime, String brojTelefona, String mejl, String datum, String vremeDolaska, String smena, int brojOsoba) {
		this.ime = ime;
		this.brojTelefona = brojTelefona;
		this.mejl = mejl;
		this.datum = datum;
		this.vremeDolaska = vremeDolaska;
		this.smena = smena;
		this.brojOsoba = brojOsoba;
	}
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getMejl() {
		return mejl;
	}

	public void setMejl(String mejl) {
		this.mejl = mejl;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVremeDolaska() {
		return vremeDolaska;
	}

	public void setVremeDolaska(String vremeDolaska) {
		this.vremeDolaska = vremeDolaska;
	}

	public String getSmena() {
		return smena;
	}

	public void setSmena(String smena) {
		this.smena = smena;
	}

	public int getBrojLOsoba() {
		return brojOsoba;
	}

	public void setBrojOsoba(int brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	
	public void prikazOsobe() {
		System.out.println("   Ime: " + ime);
        System.out.println("   Datum: " + datum);
        System.out.println("   Vreme dolaska: " + vremeDolaska);
        System.out.println("   Smena: " + smena);
        System.out.println("   Broj osoba: " + brojOsoba);
        System.out.println("   Broj telefona: " + brojTelefona);
		System.out.println("   Mejl adresa: " + mejl);
	}
	
}
