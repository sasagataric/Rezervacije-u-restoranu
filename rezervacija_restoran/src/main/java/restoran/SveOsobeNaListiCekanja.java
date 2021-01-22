package restoran;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import mejl.MailSender;

public class SveOsobeNaListiCekanja {

	private final static String RAZMAK1 = "**********************************************";
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
//	    System.out.println("Osoba uspešno saèuvane.");
	    output.close();
	    fo.close();
	}
	
	public boolean isteklaRezervacijaUListiCekanja(OsobaNaListiCekanja o ) {
		Calendar danas = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String todayString = sdf.format(danas.getTime());
        
		String stringDatumRezervacije =o.getDatum();
		try {
			Date datumRezervacije=new SimpleDateFormat("dd.MM.yyyy").parse(stringDatumRezervacije);
			Date danasnjiDatum=new SimpleDateFormat("dd.MM.yyyy").parse(todayString);
			if(datumRezervacije.before(danasnjiDatum)) {
				return true;
			}
		} catch (ParseException e) {
		}
		return false;
	}
	
	public void loadListaCekanja() throws IOException, ClassNotFoundException {
        File file = new File("Lista cekanja.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        ArrayList<OsobaNaListiCekanja> nizOsoba = new ArrayList<OsobaNaListiCekanja>();
        try {
            while (true) {
            	OsobaNaListiCekanja os = (OsobaNaListiCekanja) input1.readObject();
            	if(!isteklaRezervacijaUListiCekanja(os)) {
            		nizOsoba.add(os);
            	}
            	
            }
        } catch (EOFException ex) {
        	setListaCekanja(nizOsoba);
            System.out.println("Lista èekanja uspešno uèitana.");
        }

    }
	
	public void pokreni() {
    	boolean x = true;
        Scanner input = new Scanner(System.in);
        int odabir=0;
        while (x) {
            boolean y = true;
            try {
            	 System.out.println("");
                 prikazOpcijaMenija();
                 odabir = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
            }
            
            System.out.println(RAZMAK2);
            switch (odabir) {
                case (1):
                    do {
                    	prikaziListuCekanja();
                        y = false;
                    } while (y);
                    break;
                case (2):
                    do {
                    	obrisiSaListeCekanja(1);
                        y = false;
                    } while (y);
                    break;

                case (3):
                    do {
                    	obrisiSaListeCekanja(2);
                        y = false;
                    } while (y);
                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Odaberite validnu opciju");
            }
        }
    }
	
public void obrisiSaListeCekanja(int telOrDate) {
	    
	    Scanner input = new Scanner(System.in);
	    String broj;
	    boolean x = true;
	    while (x) {
	    	ArrayList<OsobaNaListiCekanja> pomListaRez = new ArrayList<OsobaNaListiCekanja>();
	        if (telOrDate == 1) {
	            System.out.println("##### Obriši sa liste èekanja ######");
	            System.out.println("Uneti broj telefona: (Uneti -1 za korak unazad)");
	            broj = input.nextLine();
	            if (broj.equals("-1")) {
	                return;
	            }
	            
	            for (int i = 0; i < listaCekanja.size(); i++) {
	            	OsobaNaListiCekanja temp = listaCekanja.get(i);
	                if (temp.getBrojTelefona().equals(broj)) {
	                	pomListaRez.add(temp);
	                }
	            }
	        } else if (telOrDate == 0) {
	            System.out.println("Uneti datum formatu dd.mm.gggg: (Uneti -1 za korak unazad)");
	            String inputDatum;
	            do {
	            	inputDatum = input.next();
                    if(inputDatum.length()==10) {
                        break;
                    }
                    if (inputDatum.equals("-1")) {
                        return;
                    }
                    
                    System.out.println("Nepravilno unet datum. \nDodati nulu ako je dan ili mesec jednocifren u formatu dd.mm.gggg."); 
	            }while(true);
	            

	
	            for (int i = 0; i < listaCekanja.size(); i++) {
	            	OsobaNaListiCekanja temp = listaCekanja.get(i);
	                if (temp.getDatum().equals(inputDatum)) {
	                	pomListaRez.add(temp);
	                }
	            }
	        }
	        if (pomListaRez.isEmpty()) {
	            System.out.println("Nema osobe sa unetim brojem/datumom.");
	            return;
	        } else {
	            System.out.println("Lista èelanja za datim podacima: ");
	            for (int i = 0; i < pomListaRez.size(); i++) {
	                System.out.print(i + 1 + ". ");
	                pomListaRez.get(i).prikazOsobe();
	                System.out.println("");
	            }
	            int br = 0;
	            
	            boolean z = true;
	            do {
	                do {
	                    try {
	                        System.out.println("Odabrati rezervaciju za brisanje: (Uneti -1 za korak unazad)");
	                        br = input.nextInt();
	                        System.out.println(RAZMAK2);
	                        break;
	                    } catch (InputMismatchException e) {
	                        System.out.println("Neispravan unos");
	                    }
	                } while (true);
	                if (br == -1) {
	                    return;
	                }
	                try {
	                	OsobaNaListiCekanja zabrisanje = pomListaRez.get(br - 1);
	                	obrisiSaListeCekanja(zabrisanje);
	                    saveOsobuUListuCekanja();
	                    
	                    x = false;
	                    break; 
	                } catch (ArrayIndexOutOfBoundsException e) {
	                    System.out.println("Neispravan unos");
	                } catch (IOException ex) {
	                } catch (ClassNotFoundException ex) {
	                }
	
	            } while (z);
	        }
	    }
	}

		
		
		public void prikazOpcijaMenija() {		
		    System.out.println(RAZMAK1);
		    System.out.println("*            OPCIJE LISTE ÈEKANJA              *");
		    System.out.println("*  Odabratio opciju: (Uneti -1 za korak nazad) *");
		    System.out.println("*  1. Prikaz liste èekanja                     *");
		    System.out.println("*  2. Obriši iz liste (po broju telefona)      *");
		    System.out.println("*  3. Obriši iz liste (po datumu)              *");
		    System.out.println(RAZMAK1);
		}
}
