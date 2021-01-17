package restoran;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Klasa SviStolovi sadrži sve stolove
 * 
 * @author Saša Gatariæ
 *
 */
public class SviStolovi {

	private ArrayList<Sto> sviStolovi;
	private static int brDesetMesta;
	private static int brOsamMesta;
	private static int brCetiriMesta;
	private static int brDvaMesta;
	private int brojStolova;
	
	private ArrayList<Rezervacija> rezervacijaDanasPrepodne;
	private ArrayList<Rezervacija> rezervacijaDanasPoslepodne;
	
	public SviStolovi(int a, int b, int c, int d) {
		sviStolovi= new ArrayList<Sto>();
		brDesetMesta=a;
		brOsamMesta=b;
		brCetiriMesta=c;
		brDvaMesta=d;
		postaviStolove();
		
		brojStolova= a+b+c+d;
		rezervacijaDanasPrepodne = new ArrayList<Rezervacija>();
		rezervacijaDanasPoslepodne = new ArrayList<Rezervacija>();
	}
	
	public ArrayList<Sto> getSviStolovi() {
		return sviStolovi;
	}

	public void setSviStolovi(ArrayList<Sto> sviStolovi) {
		this.sviStolovi = sviStolovi;
	}

	public static int getBrDesetMesta() {
		return brDesetMesta;
	}

	public static void setBrDesetMesta(int brDesetMesta) {
		SviStolovi.brDesetMesta = brDesetMesta;
	}

	public static int getBrOsamMesta() {
		return brOsamMesta;
	}

	public static void setBrOsamMesta(int brOsamMesta) {
		SviStolovi.brOsamMesta = brOsamMesta;
	}

	public static int getBrCetiriMesta() {
		return brCetiriMesta;
	}

	public static void setBrCetiriMesta(int brCetiriMesta) {
		SviStolovi.brCetiriMesta = brCetiriMesta;
	}

	public static int getBrDvaMesta() {
		return brDvaMesta;
	}

	public static void setBrDvaMesta(int brDvaMesta) {
		SviStolovi.brDvaMesta = brDvaMesta;
	}

	public int getBrojStolova() {
		return brojStolova;
	}


	public ArrayList<Rezervacija> getRezervacijaDanasPrepodne() {
		return rezervacijaDanasPrepodne;
	}

	public ArrayList<Rezervacija> getRezervacijaDanasPoslepodne() {
		return rezervacijaDanasPoslepodne;
	}
	
	public void DodajSto(Sto sto) {
        sviStolovi.add(sto);
    }
	
	 /**
	 * postaviStolove upiseje stolove u ArrayList sviStolovi
	 */
	public void postaviStolove() {	
	        int br = 1;
	        for (int i = 0; i < brDvaMesta; i++) {
	        	Sto sto = new Sto(br, 2, false, false);
	        	DodajSto(sto);
	        	br++;
	        }
	        for (int i = 0; i < brCetiriMesta;i++) {
	        	Sto sto = new Sto(br, 4, false, false);
	        	DodajSto(sto);
	        	br++;
	        }
	        for (int i = 0; i < brOsamMesta; i++) {
	        	Sto sto = new Sto(br, 8, false, false);
	        	DodajSto(sto);
	        	br++;
	        }
	        for (int i = 0; i < brDesetMesta; i++) {
	        	Sto sto = new Sto(br, 10, false, false);
	        	DodajSto(sto);
	        	br++;
	        }
	    }
	 
	 /**
	 * prikazSlobodnihStolova prikazuje sve slobodne stolove
	 */
	public void prikazSlobodnihStolova() {
	        for (int i = 0; i < sviStolovi.size(); i++) {
	            if (!(sviStolovi.get(i).rezervisanPoslepodne() && sviStolovi.get(i).rezervisanPrepodne())) {
	            	sviStolovi.get(i).prikazStola();
	            }
	        }
	    }

	 /**
	 * prikazStatusaStola prikazuje status svih stolova
	 */
	public void prikazStatusaStola() {
	        for (int i = 0; i < sviStolovi.size(); i++) {
	        	sviStolovi.get(i).prikazStola();
	        }
	    }
	 
	 public void sinhronizacijaStolovaSaRezervacijama(String danasnjiDatum, SveRezervacije sr) {
	        ArrayList<Integer> rezervisaniStoloviPrepodne = sr.getStoloviRezervisaniDatuma(danasnjiDatum, "Prepodne");
	        ArrayList<Integer> rezervisaniStoloviPoslepodne = sr.getStoloviRezervisaniDatuma(danasnjiDatum, "Poslepodne");
	        
	        for (int i = 0; i < brojStolova; i++) {
	            if (rezervisaniStoloviPrepodne.contains(sviStolovi.get(i).getBrojStola())) {
	                Sto pom = sviStolovi.get(i);
	                pom.setRezervisanPrepodne(true);
	            }
	            if (rezervisaniStoloviPoslepodne.contains(sviStolovi.get(i).getBrojStola())) {
	            	Sto pom = sviStolovi.get(i);
	            	pom.setRezervisanPoslepodne(true);
	            }
	        }
	    }
	 
	 /**
	  * getDanasRezervisaniStolovi metoda vraæa sve stolove koji su rezervisani na današnji dan
	  * 
	 * @return stolovi koji su danas rezervisani
	 */
	public ArrayList<Sto> getDanasRezervisaniStolovi() {
	        ArrayList<Sto> danasRezervisaniStolovi = new ArrayList<Sto>();
	        Sto pom = null;
	        for (int i = 0; i < brojStolova; i++) {
	            if (sviStolovi.get(i).rezervisanPoslepodne() || sviStolovi.get(i).rezervisanPrepodne()) {
	                pom = sviStolovi.get(i);
	                danasRezervisaniStolovi.add(pom);
	            }
	        }
	        return danasRezervisaniStolovi;
	    }
	 
	 public ArrayList<Sto> getSlobodniStolovi() {
	        ArrayList<Sto> slobodniStolovi = new ArrayList<Sto>();
	        Sto sto = null;
	        Date trenutnoVreme = new GregorianCalendar().getTime();
	        String Slot = "";
	        try {
				Slot = UpravljanjeRezervacijama.proveraSmene(trenutnoVreme);
				if(Slot.equals("Prepodne")){
					for(int i=0;i<brojStolova;i++){
						if (!( sviStolovi.get(i).rezervisanPrepodne())) {
							sto = sviStolovi.get(i);
							slobodniStolovi.add(sto);
		                }
					}
				}
				else if(Slot.equals("Poslepodne")){
					for(int i=0;i<brojStolova;i++){
						if (!(sviStolovi.get(i).rezervisanPoslepodne())) {
							sto = sviStolovi.get(i);
							slobodniStolovi.add(sto);
		                }
					}
				}
				else if(Slot.equals("Zatvoren")){
					System.out.println("");
					System.out.println("Restoran je zatvoren");
					System.out.println("");
				}

			} catch (ParseException e) {
				System.out.println("Trenutno nije moguca provera slobodnih stolova. ");
			}
	        return slobodniStolovi;
	    }
	 
	 /**
	  * getSto metoda vraæa odrdjen sto iz liste stolova
	  * 
	 * @param brojStola Broj stola
	 * @return Sto
	 */
	public Sto getSto(int brojStola) {
		 	Sto sto = sviStolovi.get(brojStola - 1);
	        return sto;
	    }
	
	
}
