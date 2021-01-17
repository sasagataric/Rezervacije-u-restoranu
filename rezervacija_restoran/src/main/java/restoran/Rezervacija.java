package restoran;

import java.io.Serializable;

public class Rezervacija implements Serializable{

	private int brojStola;
	private int brojOsoba;
	private String brojTelefona;
	private String imeOsobe;
	private String datumRezervacije;
	private String vremeDolaska;
	private String smena;

	/**
	 * @param brojStola Broj stola koji je dodeljen rezervaciji
	 * @param brojOsoba Broj osoba za koje se kreira rezervacija
	 * @param brojTelefona Broj telefona korisnika
	 * @param imeKorisnika Ime korisnika
	 * @param danRezervacije Dan rezervacije
	 * @param vremeDolaska Vreme rezervacije
	 * @param smena Prepodnevna ili poslepodnevna smena
	 */
	public Rezervacija(int brojStola, int brojOsoba, String brojTelefona, String imeOsobe, String datumRezervacije, String vremeDolaska, String smena) {
		this.brojStola = brojStola;
		this.brojOsoba = brojOsoba;
		this.brojTelefona = brojTelefona;
		this.imeOsobe = imeOsobe;
		this.datumRezervacije = datumRezervacije;
		this.vremeDolaska = vremeDolaska;
		this.smena = smena;
	}
	
	public int getBrojStola() {
		return brojStola;
	}
	public void setBrojStola(int brojStola) {
		this.brojStola = brojStola;
	}
	public int getBrojOsoba() {
		return brojOsoba;
	}
	public void setBrojOsoba(int brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getImeOsobe() {
		return imeOsobe;
	}
	public void setImeOsobe(String imeOsobe) {
		this.imeOsobe = imeOsobe;
	}
	public String getDatumRezervacije() {
		return datumRezervacije;
	}
	public void setDatumRezervacije(String datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
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
	
	 public void prikazRezervacije() {
	        System.out.println( "******* Rezervacija za: " + imeOsobe+ " *******");
	        System.out.println("Datum rezervacije: " + datumRezervacije);
	        System.out.println("Vreme dolaska: " + vremeDolaska);
	        System.out.println("Sto: " + brojStola);
	        System.out.println("Broj osoba: " + brojOsoba);
	        System.out.println("Broj telefona: " + brojTelefona);
	    }
}
