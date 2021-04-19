package restoran;

import java.util.ArrayList;

public class SveRezervacije {

	private final static String RAZMAK2 = "##############################################";
	
	private ArrayList<Rezervacija> listaRezervacija;
	
	public SveRezervacije() {
		listaRezervacija= new ArrayList<Rezervacija>();
	}
	
	public ArrayList<Rezervacija> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<Rezervacija> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	public void kreirajRezervaciju(int brojStola, int brojOsoba, String brojTelefona, String imeOsobe, String datumRezervacije, String vremeDolaska, String smena) {
        Rezervacija novaRez = new Rezervacija(brojStola, brojOsoba, brojTelefona, imeOsobe, datumRezervacije, vremeDolaska, smena);
        listaRezervacija.add(novaRez);
        System.out.println("Rezervacija kreirana");
        novaRez.prikazRezervacije();
        System.out.println("");
    }
	
	public void obrisiRezervaciju(Rezervacija rezervacija) {
        int msg = 0;
        for (int i = 0; i < listaRezervacija.size(); i++) {
            if (listaRezervacija.get(i) == rezervacija) {
            	listaRezervacija.remove(i);
                msg = 1;
            }
        }
        if (msg == 1) {
            System.out.println("Rezervacija je obrisana");
        } else {
            System.out.println("Greška, ništa nije promenjeno");
        }

    }
	
	 public void prikaziRezervacijePoDatumu(String datum) {
	        ArrayList<Rezervacija> pomList = new ArrayList<Rezervacija>();
	        for (int i = 0; i < listaRezervacija.size(); i++) {
	        	Rezervacija pom = listaRezervacija.get(i);
	            if (pom.getDatumRezervacije().equals(datum)) {
	            	pomList.add(pom); 
	            }
	        }
	        if (pomList.isEmpty()) {
	            System.out.println("Nema rezervacija za odabran datum: " + datum);
	        } else {
	            System.out.println("Rezervacije za: " + datum);
	            for (int i = 0; i < pomList.size(); i++) {
	                System.out.print("\n"+i + 1 + ". ");
	                pomList.get(i).prikazRezervacije();
	            }
	        }

	    }

	 public ArrayList<Integer> getStoloviRezervisaniDatuma(String datum, String smena) {
	        ArrayList<Integer> rezervisaniStolovi = new ArrayList<Integer>();
	        ArrayList<Rezervacija> pomList = getRezervacijeNapravljeneDatuma(datum, smena);
	        if (!(pomList.isEmpty())) {
	            for (int i = 0; i < pomList.size(); i++) {
	                int x = pomList.get(i).getBrojStola();
	                rezervisaniStolovi.add(x);
	            }
	        }
	        return rezervisaniStolovi;
	    }

	  
	 public ArrayList<Rezervacija> getRezervacijeNapravljeneDatuma(String datum, String smena) {
	        ArrayList<Rezervacija> pomlist = new ArrayList<Rezervacija>();
	        for (int i = 0; i < listaRezervacija.size(); i++) {
	        	Rezervacija temp = listaRezervacija.get(i);
	            if (temp.getDatumRezervacije().equals(datum) && (temp.getSmena().equals(smena))) {
	            	pomlist.add(temp);
	            }
	        }
	        return pomlist;
	    }

	   
	 public void prikaziSveRezervacije() {
		 if(listaRezervacija.isEmpty()) {
			 System.out.println("");
			 System.out.println("Nema ni jedne rezervacije.");
			 System.out.println("");
		 }else {
			 for (int i = 0; i < listaRezervacija.size(); i++) {
		            System.out.println("");
		            listaRezervacija.get(i).prikazRezervacije();
		        }
			 System.out.println("");
             System.out.println(RAZMAK2);
		 }
	       
	    }
	 
	 
	
}
