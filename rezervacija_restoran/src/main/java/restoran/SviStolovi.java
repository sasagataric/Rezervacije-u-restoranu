package restoran;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class SviStolovi implements Serializable{

	private ArrayList<Sto> sviStolovi;
	private int brDesetMesta;
	private int brOsamMesta;
	private int brCetiriMesta;
	private int brDvaMesta;
	private int brojStolova;
	
	private ArrayList<Rezervacija> rezervacijaDanasPrepodne;
	private ArrayList<Rezervacija> rezervacijaDanasPoslepodne;
	
	public SviStolovi(int br10, int br8, int br4, int br2) {
		sviStolovi= new ArrayList<Sto>();
		brDesetMesta=br10;
		brOsamMesta=br8;
		brCetiriMesta=br4;
		brDvaMesta=br2;
		postaviStolove();
		
		brojStolova= br10+br8+br4+br2;
		rezervacijaDanasPrepodne = new ArrayList<Rezervacija>();
		rezervacijaDanasPoslepodne = new ArrayList<Rezervacija>();
	}
	
	public ArrayList<Sto> getSviStolovi() {
		return sviStolovi;
	}

	public void setSviStolovi(ArrayList<Sto> sviStolovi) {
		this.sviStolovi = sviStolovi;
	}

	public  int getBrDesetMesta() {
		return brDesetMesta;
	}

	public  void setBrDesetMesta(int brDesetMesta) {
		this.brDesetMesta = brDesetMesta;
	}

	public  int getBrOsamMesta() {
		return brOsamMesta;
	}

	public  void setBrOsamMesta(int brOsamMesta) {
		this.brOsamMesta = brOsamMesta;
	}

	public  int getBrCetiriMesta() {
		return brCetiriMesta;
	}

	public  void setBrCetiriMesta(int brCetiriMesta) {
		this.brCetiriMesta = brCetiriMesta;
	}

	public  int getBrDvaMesta() {
		return brDvaMesta;
	}

	public  void setBrDvaMesta(int brDvaMesta) {
		this.brDvaMesta = brDvaMesta;
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
	
	public static void saveStolovi(int deset,int osam,int cetiri,int dva) throws FileNotFoundException, IOException, ClassNotFoundException {
	    File file = new File("Stolovi.txt");
	    SviStolovi stolovi = new SviStolovi(deset,osam,cetiri,dva);
	    FileOutputStream fo = new FileOutputStream(file);
	    ObjectOutputStream output = new ObjectOutputStream(fo);
	    output.writeObject(stolovi);
	    output.close();
	    fo.close();
	}
	
	public static SviStolovi loadStolovi() throws IOException, ClassNotFoundException {
        File file = new File("Stolovi.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        SviStolovi stolovi = null;
        try {
            while (true) {
            	stolovi = (SviStolovi) input1.readObject(); 
            }
        } catch (EOFException ex) {
        	return stolovi;
        }
    }
	
	
	
}
