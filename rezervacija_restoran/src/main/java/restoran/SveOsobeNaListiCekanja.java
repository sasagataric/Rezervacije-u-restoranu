package restoran;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SveOsobeNaListiCekanja {

	 private final static String RAZMAK2 = "##############################################";
	 
	private ArrayList<OsobaNaListiCekanja> listaCekanja;
	
	public ArrayList<OsobaNaListiCekanja> getListaCekanja() {
		return listaCekanja;
	}

	public void setListaCekanja(ArrayList<OsobaNaListiCekanja> listaCekanja) {
		this.listaCekanja = listaCekanja;
	}

	public SveOsobeNaListiCekanja() {
		listaCekanja=new ArrayList<OsobaNaListiCekanja>();
	}
	
	public void dodajNaListuCejanja(String ime, String brojTelefona, String mejl, String datum, String vremeDolaska, String smena, int brojOsoba) {
		OsobaNaListiCekanja osoba = new OsobaNaListiCekanja(ime, brojTelefona, mejl, datum, vremeDolaska, smena, brojOsoba);
		listaCekanja.add(osoba);
		osoba.prikazOsobe();
	}
	
	public void obrisiSaListeCekanja(OsobaNaListiCekanja osoba) {
		int msg = 0;
        for (int i = 0; i < listaCekanja.size(); i++) {
            if (listaCekanja.get(i) == osoba) {
            	listaCekanja.remove(i);
                msg = 1;
            }
        }
        if (msg == 1) {
            System.out.println("Osoba je obrisana sa liste èekanja");
        } else {
            System.out.println("Greška, ništa nije promenjeno");
        }
	}
	
	 public void prikaziListuCekanja() {
		 if(listaCekanja.isEmpty()) {
			 System.out.println("");
			 System.out.println("Lista èekanja je prazna");
			 System.out.println("");
		 }else {
			 System.out.println("*******  Lista èekanja  *******");
			 for (int i = 0; i < listaCekanja.size(); i++) {
		            System.out.println("");
		            System.out.print(i + 1 + ". ");
		            listaCekanja.get(i).prikazOsobe();
		        }
			 System.out.println("");
             System.out.println(RAZMAK2);
		 }
	    }
	 
	 public ArrayList<OsobaNaListiCekanja> getListuCekanjaZaDatum(String datum, String smena) {
	        ArrayList<OsobaNaListiCekanja> pomlist = new ArrayList<OsobaNaListiCekanja>();
	        for (int i = 0; i < listaCekanja.size(); i++) {
	        	OsobaNaListiCekanja temp = listaCekanja.get(i);
	            if (temp.getDatum().equals(datum) && (temp.getSmena().equals(smena))) {
	            	pomlist.add(temp);
	            }
	        }
	        return pomlist;
	    }
	 
	public void saveOsobuUListuCekanja() throws FileNotFoundException, IOException, ClassNotFoundException {
	    File file = new File("Lista cekanja.txt");
	    ArrayList<OsobaNaListiCekanja> nizOsoba = listaCekanja;
	    FileOutputStream fo = new FileOutputStream(file);
	    ObjectOutputStream output = new ObjectOutputStream(fo);
	    for (int i = 0; i < nizOsoba.size(); i++) {
	        output.writeObject(nizOsoba.get(i));
	    }
	    System.out.println("Osoba uspešno saèuvane.");
	    output.close();
	    fo.close();
	}
	
	public void loadListaCekanja() throws IOException, ClassNotFoundException {
        File file = new File("Lista cekanja.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        ArrayList<OsobaNaListiCekanja> nizOsoba = new ArrayList<OsobaNaListiCekanja>();
        try {
            while (true) {
            	OsobaNaListiCekanja os = (OsobaNaListiCekanja) input1.readObject();
            	nizOsoba.add(os);
            }
        } catch (EOFException ex) {
        	setListaCekanja(nizOsoba);
            System.out.println("Lista èekanja uspešno uèitana.");
        }

    }
}
