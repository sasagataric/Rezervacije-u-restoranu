package restoran;

import java.io.Serializable;

/**
 * Klasa Sto sadrži sve podatke koji su vezani za specifièan sto
 * 
 * @author Saša Gatariæ
 *
 */
public class Sto implements Serializable{

	private int brojStola;
	private int kapacitetStola;
	private boolean rezervisanPrepodne;
	private boolean rezervisanPoslepodne;
	private boolean rezervisan;
	
	/**
	 * @param brojStola Predstavlja broj dodeljen stolu
	 * @param kapacitetStola Predstavlja broj mesta za stolom
	 * @param zauzetPrepodne Da li je sto rezervisan prepodne
	 * @param zauzetPoslepodne Da li je sto rezervisan poslepodne
	 */
	public Sto(int brojStola, int kapacitetStola, boolean rezervisanPrepodne, boolean rezervisanPoslepodne) {
		this.brojStola = brojStola;
		this.kapacitetStola = kapacitetStola;
		this.rezervisanPrepodne = rezervisanPrepodne;
		this.rezervisanPoslepodne = rezervisanPoslepodne;
	}

	public int getBrojStola() {
		return brojStola;
	}
	public void setBrojStola(int brojStola) {
		this.brojStola = brojStola;
	}
	public int getKapacitetStola() {
		return kapacitetStola;
	}
	public void setKapacitetStola(int kapacitetStola) {
		this.kapacitetStola = kapacitetStola;
	}
	public boolean rezervisanPrepodne() {
		return rezervisanPrepodne;
	}
	public void setRezervisanPrepodne(boolean rezervisanPrepodne) {
		this.rezervisanPrepodne = rezervisanPrepodne;
	}
	public boolean rezervisanPoslepodne() {
		return rezervisanPoslepodne;
	}
	public void setRezervisanPoslepodne(boolean rezervisanPoslepodne) {
		this.rezervisanPoslepodne = rezervisanPoslepodne;
	}
	
	public void prikazStola() {
		Boolean rezPre=false,rezPolse=false;
		System.out.println("");
		System.out.println("Broj stola: " + getBrojStola());
		System.out.println("Kapacitet stola: " + getKapacitetStola());
		if(!(rezervisanPoslepodne || rezervisanPrepodne)) {
			System.out.println("Slobodan");
		}else {
			if(rezervisanPrepodne) {
				System.out.println("Rezervisan prepodne");
				rezPre=true;
			}
			if(rezervisanPoslepodne) {
				System.out.println("Rezervisan poslepodne");
				rezPolse=true;
			}
			if(rezPre && !rezPolse)System.out.println("Slobodan poslepodne");
			if(!rezPre && rezPolse)System.out.println("Slobodan prepodne");
		}
	}
}
