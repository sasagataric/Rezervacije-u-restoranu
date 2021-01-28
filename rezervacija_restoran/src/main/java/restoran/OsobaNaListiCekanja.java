package restoran;

import java.io.Serializable;

public class OsobaNaListiCekanja extends Rezervacija implements Serializable{
	
	private String mejl;

	public OsobaNaListiCekanja(String ime, String brojTelefona, String mejl, String datum, String vremeDolaska, String smena, int brojOsoba) {
		super(-1, brojOsoba, brojTelefona, ime, datum, vremeDolaska, smena);
		this.mejl = mejl;
	}
	public String getMejl() {
		return mejl;
	}

	public void setMejl(String mejl) {
		this.mejl = mejl;
	}
	
	public void prikazOsobe() {
		super.prikazRezervacije();
		System.out.println("Mejl adresa: " + mejl);
	}
	
}
