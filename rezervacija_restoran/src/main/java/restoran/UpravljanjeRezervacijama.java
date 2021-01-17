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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.EmailValidator;

import mejl.MailSender;



public class UpravljanjeRezervacijama {

	public static SveRezervacije listaRezervacije;
	private SviStolovi listaStolova;
	private SveOsobeNaListiCekanja listaCekanja;
	
	public void setListaCekanja(SveOsobeNaListiCekanja listaCekanja) {
		this.listaCekanja = listaCekanja;
	}

	public void setListaStolova(SviStolovi listaStolova) {
		this.listaStolova = listaStolova;
	}
	
	private final static String RAZMAK1 = "**********************************************";
    private final static String RAZMAK2 = "##############################################";

    public UpravljanjeRezervacijama() {
    	listaRezervacije = new SveRezervacije();
    }
    
    public void pokreni() throws FileNotFoundException, ClassNotFoundException, IOException {
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
                    	kreirajRezervaciju();
                        y = false;
                    } while (y);
                    break;
                case (2):
                    do {
                        proveriRezervaciju();
                        y = false;
                    } while (y);
                    break;

                case (3):
                    do {
                    	obrisiRezervaciju(1);
                        y = false;
                    } while (y);
                    break;
                case (4):
                    do {
                    	obrisiRezervaciju(0);
                        y = false;
                    } while (y);
                    break;
                case (5):
                    do {
                    	listaRezervacije.prikaziSveRezervacije();;
                        y = false;
                    } while (y);
                    break;
                case (6):
                    do {
                    	listaCekanja.prikaziListuCekanja();;
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
    
public void kreirajRezervaciju() throws FileNotFoundException, ClassNotFoundException, IOException{
        
        Scanner input = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        String inputDatum;
        String inputVreme;
        Calendar danas = new GregorianCalendar();	
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String todayString = sdf.format(danas.getTime());
        boolean x = true;
        while (x) {
            int pom = 0;
            String smena = "";
            do {
            	System.out.println("\n###### Kreiraj Rezervaciju ######");
            	System.out.println("Uneti datum rezervacije u formatu dd-mm-gggg: (Uneti -1 za korak unazad)");
            	do {
                     inputDatum = input.next();
                     if(inputDatum.length()==10) {
                         break;
                     }
                     if (inputDatum.equals("-1")) {
                         return;
                     }
                     
                     System.out.println("Nepravilno unet datum \nDodati nulu ako je dan ili mesec jednocifren u formatu dd-mm-gggg"); 
            	}while(true);
                
                System.out.println("Uneti vreme dolaska u formatu SSmm: (Uneti -1 za korak unazad)");
                	 	
                
                
                do {
                	inputVreme = input.next();
                    if(inputVreme.length()==4) {
                        break;
                    }
                    if (inputVreme.equals("-1")) {
                        return;
                    }
                    System.out.println("Neodgovarajuæi broj cifara \nUneti vreme dolaska u formatu SSmm: (Uneti -1 za korak unazad)"); 
           	}while(true);
                try {
                	smena = proveraSmene(inputVreme);
                    if (smena.equals("")) {
                        System.out.println("Restoran je zatvoren");
                        break;
                    }
                    
                    
                    Calendar datumKorisnika=Calendar.getInstance();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HHmm");
                    Date reservationDate = sdf1.parse(inputDatum + " " + inputDatum);
                    datumKorisnika.setTime(reservationDate);
                    
                    Calendar mesecDanaOdDanas = new GregorianCalendar();
                    mesecDanaOdDanas.add(Calendar.DAY_OF_MONTH, 30);
                    if (datumKorisnika.compareTo(danas) < 0 || datumKorisnika.compareTo(mesecDanaOdDanas) == 1 ) {
                        System.out.println("Nije moguæa rezervacija za unet datum");
                    } else {
                    	System.out.println("Smena: " + smena);
                        pom = 1;
                        x = false;
                    }
                } catch (ParseException e) {
                    System.out.println("Uneti datum u formatu dd-mm-gggg i vreme u formatu SSmm");
                    input.nextLine();
                }
                
            } while (x);
            
            if (pom == 1) { 
                ArrayList<Integer> brojRezervisanogStolaUSmeni = listaRezervacije.getStoloviRezervisaniDatuma(inputDatum, smena);
                ArrayList<Sto> neRezervisaniStolovi = new ArrayList<Sto>(); 
                ArrayList<Sto> sviStolovi = listaStolova.getSviStolovi();

                for (int i = 0; i < listaStolova.getBrojStolova(); i++) {
                    if (!(brojRezervisanogStolaUSmeni.contains(sviStolovi.get(i).getBrojStola()))) {
                        Sto sto = sviStolovi.get(i);
                        neRezervisaniStolovi.add(sto);
                    }
                }
                System.out.println("Uneti broj telefona: (Uneti -1 za korak unazad)");
                String brojTelefona = inputString.nextLine();
                if (brojTelefona.equals("-1") || brojTelefona.equals("")){
                    break;
                }
                System.out.println("Uneti ime: (Uneti -1 za korak unazad)");
                
                input.nextLine();
                String ime = inputString.nextLine();
                if (ime.equals("-1") || ime.equals("")) {
                    break;
                }
                
                System.out.println("Uneti broj ljudi: (Uneti -1 za korak unazad)");
                int brOsoba = 0;
                do {
                    try {
                    	brOsoba = input.nextInt();
                        System.out.println(RAZMAK2);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Uneti broj");
                        input.nextLine();
                    }
                } while (true);
                if (brOsoba == -1) {
                    break;
                }
                System.out.println("Ime:" + ime + ", broj telefona: " + brojTelefona + ", broj ljudi: " + brOsoba);

                for (int i = 0; i < neRezervisaniStolovi.size(); i++) {

                    if ((neRezervisaniStolovi.get(i).getKapacitetStola() >= brOsoba) && (neRezervisaniStolovi.get(i).getKapacitetStola() <= brOsoba + 3)) {
                        Sto sto = neRezervisaniStolovi.get(i);
                        System.out.println("Sto: " + sto.getBrojStola() + ", kapacitet stola: " + sto.getKapacitetStola());
                        listaRezervacije.kreirajRezervaciju(sto.getBrojStola(), brOsoba, brojTelefona, ime, inputDatum, inputVreme, smena);
                        listaStolova.sinhronizacijaStolovaSaRezervacijama(todayString, listaRezervacije);
                        saveReservations();
  
                        return;
                    }
                }
                System.out.println("Nema stola koji odgovara za unetu rezervaciju");
                System.out.println("Da li zelite da vas stavimo na listu èekanja (d/n)");
                char odluka;
                String mejl;
                do {
                	odluka=input.next().charAt(0);
                    if(odluka=='d' || odluka=='n') {
                        break;
                    }
                    System.out.println("Uneti d za DA ili n za NE"); 
                }while(true);
                switch(odluka) {
    			case 'd':
    				System.out.println("Unesite mejl adresu:");
    				do {
    					mejl=inputString.nextLine();
                        if(EmailValidator.getInstance().isValid(mejl)) {
                            break;
                        }
                        System.out.println("Nepravilno uneta mejl adresa. \nUnesite mejl adresu:"); 
               	}while(true);
    				listaCekanja.dodajNaListuCejanja(ime, brojTelefona, mejl, inputDatum, inputVreme, smena, brOsoba);
    				listaCekanja.saveOsobuUListuCekanja();
    				break;
    			case 'n':
    				System.out.println("Hvala na razumevanju.");
    				break;
				}
                
                
            }
        }
        
    }

	public void obrisiRezervaciju(int telOrDate) {
	    
	    Scanner input = new Scanner(System.in);
	    String broj;
	    ArrayList<Rezervacija> sveRezervacije = listaRezervacije.getListaRezervacija();
	    boolean x = true;
	    while (x) {
	        ArrayList<Rezervacija> pomListaRez = new ArrayList<Rezervacija>();
	        if (telOrDate == 1) {
	            System.out.println("##### Obriši rezervaciju ######");
	            System.out.println("Uneti broj telefona: (Uneti -1 za korak unazad)");
	            broj = input.nextLine();
	            if (broj.equals("-1")) {
	                return;
	            }
	            System.out.println("Broj telefona: " + broj);
	            for (int i = 0; i < sveRezervacije.size(); i++) {
	                Rezervacija temp = sveRezervacije.get(i);
	                if (temp.getBrojTelefona().equals(broj)) {
	                	pomListaRez.add(temp);
	                }
	            }
	        } else if (telOrDate == 0) {
	            System.out.println("Uneti datum formatu dd-mm-gggg: (Uneti -1 za korak unazad)");
	            String inputDatum;
	            do {
	            	inputDatum = input.next();
                    if(inputDatum.length()==10) {
                        break;
                    }
                    if (inputDatum.equals("-1")) {
                        return;
                    }
                    
                    System.out.println("Nepravilno unet datum. \nDodati nulu ako je dan ili mesec jednocifren u formatu dd-mm-gggg."); 
	            }while(true);
	            
	            System.out.println("Datum: " + inputDatum);
	
	            for (int i = 0; i < sveRezervacije.size(); i++) {
	            	Rezervacija temp = sveRezervacije.get(i);
	                if (temp.getDatumRezervacije().equals(inputDatum)) {
	                	pomListaRez.add(temp);
	                }
	            }
	        }
	        if (pomListaRez.isEmpty()) {
	            System.out.println("Nema rezervacija sa unetim brojem/datumom.");
	            return;
	        } else {
	            System.out.println("Rezervacije sa datim podacima: ");
	            for (int i = 0; i < pomListaRez.size(); i++) {
	                System.out.print(i + 1 + ". ");
	                pomListaRez.get(i).prikazRezervacije();
	                System.out.println("");
	            }
	            int choice = 0;
	            
	            boolean z = true;
	            do {
	                do {
	                    try {
	                        System.out.println("Odabrati rezervaciju za brisanje: (Uneti -1 za korak unazad)");
	                        choice = input.nextInt();
	                        System.out.println(RAZMAK2);
	                        break;
	                    } catch (InputMismatchException e) {
	                        System.out.println("Neispravan unos");
	                    }
	                } while (true);
	                if (choice == -1) {
	                    return;
	                }
	                try {
	                    Rezervacija todelete = pomListaRez.get(choice - 1);
	                    listaRezervacije.obrisiRezervaciju(todelete);
	                    saveReservations();
	                    
//	                    Proveravanje da li postoji korisnik sa liste cekanja koji bi moga da popuni obrisano mesto
	                    
	                    ArrayList<OsobaNaListiCekanja> lCekanja = listaCekanja.getListuCekanjaZaDatum(todelete.getDatumRezervacije(), todelete.getSmena()); 
	                    ArrayList<Integer> brojRezervisanogStolaUSmeni = listaRezervacije.getStoloviRezervisaniDatuma(todelete.getDatumRezervacije(), todelete.getSmena());
	                    ArrayList<Sto> neRezervisaniStolovi = new ArrayList<Sto>(); 
	                    ArrayList<Sto> sviStolovi = listaStolova.getSviStolovi();

	                    for (int i = 0; i < listaStolova.getBrojStolova(); i++) {
	                        if (!(brojRezervisanogStolaUSmeni.contains(sviStolovi.get(i).getBrojStola()))) {
	                            Sto sto = sviStolovi.get(i);
	                            neRezervisaniStolovi.add(sto);
	                        }
	                    }
	                    
	                    for (int i = 0; i < neRezervisaniStolovi.size(); i++) {
	                    	 for (int j = 0; j < lCekanja.size(); j++) {
	                    		 if ((neRezervisaniStolovi.get(i).getKapacitetStola() >= lCekanja.get(j).getBrojLOsoba()) && (neRezervisaniStolovi.get(i).getKapacitetStola() <= lCekanja.get(j).getBrojLOsoba() + 3)) {
	 	                            Sto sto = neRezervisaniStolovi.get(i);
	 	                            System.out.println("Sto: " + sto.getBrojStola() + ", kapacitet stola: " + sto.getKapacitetStola());
	 	                            listaRezervacije.kreirajRezervaciju(sto.getBrojStola(), lCekanja.get(j).getBrojLOsoba(), lCekanja.get(j).getBrojTelefona(), lCekanja.get(j).getIme(), lCekanja.get(j).getDatum(), lCekanja.get(j).getVremeDolaska(), lCekanja.get(j).getSmena());
	 	                            listaStolova.sinhronizacijaStolovaSaRezervacijama(lCekanja.get(j).getDatum(), listaRezervacije);
	 	                            saveReservations();
	 	                            listaCekanja.obrisiSaListeCekanja(lCekanja.get(j));
	 	                            listaCekanja.saveOsobuUListuCekanja();
	 	                           MailSender mailSender=new MailSender(lCekanja.get(j).getMejl(),"Obaveštenje o rezervaciji ", MailSender.textPoruke(lCekanja.get(j).getDatum(), lCekanja.get(j).getVremeDolaska(), lCekanja.get(j).getBrojLOsoba()));
	 	                            return;
	 	                        }
	                    	 }
	                    }
	                    
	                    z = false;
	                    x = false;
	                } catch (ArrayIndexOutOfBoundsException e) {
	                    System.out.println("Neispravan unos");
	                } catch (IOException ex) {
	                } catch (ClassNotFoundException ex) {
	                }
	
	            } while (z);
	        }
	    }
	}

	public static String proveraSmene(String slot) throws ParseException {	
	    String smena = "";
	    Date smenaD = new SimpleDateFormat("HHmm").parse(slot);
	    String amStart = "0000";
	    String amEnd = "1200";
	    String pmStart = "1201";
	    String pmEnd = "2359";
//	    String amStart = "0800";
//	    String amEnd = "1500";
//	    String pmStart = "1501";
//	    String pmEnd = "2300";
	    Date amStartD = new SimpleDateFormat("HHmm").parse(amStart);
	    Date amEndD = new SimpleDateFormat("HHmm").parse(amEnd);
	    Date pmStartD = new SimpleDateFormat("HHmm").parse(pmStart);
	    Date pmEndD = new SimpleDateFormat("HHmm").parse(pmEnd);
	    if (amStartD.before(smenaD) && amEndD.after(smenaD)) {
	    	smena = "Prepodne";
	    } else if (pmStartD.before(smenaD) && pmEndD.after(smenaD)) {
	    	smena = "Poslepodne";
	    }
	    return smena;
	}
	
	
	public static String proveraSmene(Date now) throws ParseException {	
	    String smena = "";
	    Calendar x = new GregorianCalendar();
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	    String dateToday = sdf1.format(x.getTime());
	    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HHmm");
	
	    String amStart = "0000";
	    String amEnd = "1200";
	    String pmStart = "1201";
	    String pmEnd = "2359";
//	    String amStart = "0800";
//	    String amEnd = "1500";
//	    String pmStart = "1501";
//	    String pmEnd = "2300";
	    Date amStartD = sdf2.parse(dateToday + " " + amStart);
	    Date amEndD = sdf2.parse(dateToday + " " + amEnd);
	    Date pmStartD = sdf2.parse(dateToday + " " + pmStart);
	    Date pmEndD = sdf2.parse(dateToday + " " + pmEnd);
	    if (amStartD.before(now) && amEndD.after(now)) {
	    	smena = "Prepodne";
	    } else if (pmStartD.before(now) && pmEndD.after(now)) {
	    	smena = "Poslepodne";
	    } else {
	    	smena = "Zatvoren";
	    }
	    return smena;
	}


	public boolean isteklaRezervacija(Rezervacija r ) {
		Calendar danas = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String todayString = sdf.format(danas.getTime());
        
		String stringDatumRezervacije =r.getDatumRezervacije();
		try {
			Date datumRezervacije=new SimpleDateFormat("dd-MM-yyyy").parse(stringDatumRezervacije);
			Date danasnjiDatum=new SimpleDateFormat("dd-MM-yyyy").parse(todayString);
			if(datumRezervacije.before(danasnjiDatum)) {
				return true;
			}
		} catch (ParseException e) {
		}
		
		
		
		return false;
	}


	public static void saveReservations() throws FileNotFoundException, IOException, ClassNotFoundException {
	  
	    File file = new File("Rezervacije.txt");

	    ArrayList<Rezervacija> nizRezervacije = listaRezervacije.getListaRezervacija();

	    FileOutputStream fo = new FileOutputStream(file);
	    ObjectOutputStream output = new ObjectOutputStream(fo);
	    
	    for (int i = 0; i < nizRezervacije.size(); i++) {
	        output.writeObject(nizRezervacije.get(i));
	
	    }
//	    System.out.println("Rezervacije uspešno saèuvane.");
	    output.close();
	    fo.close();
	}
	
	public void loadReservations() throws IOException, ClassNotFoundException {
        File file = new File("Rezervacije.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        ArrayList<Rezervacija> nizRezervacije = new ArrayList<Rezervacija>();

        try {
            while (true) {
            	Rezervacija r = (Rezervacija) input1.readObject();
            	if(!isteklaRezervacija(r)) {
            		nizRezervacije.add(r);
            	}
            }
        } catch (EOFException ex) {
            listaRezervacije.setListaRezervacija(nizRezervacije);
            System.out.println("Rezervacije uspešno uèitane");
        }

    }


    
    public void proveriRezervaciju() {
        Scanner unos = new Scanner(System.in);
        System.out.println("Unei datum za proveru: ");
        String datum = unos.next();
        listaRezervacije.prikaziRezervacijePoDatumu(datum);

    }
    
    public void prikazOpcijaMenija() {		
        System.out.println(RAZMAK1);
        System.out.println("*            OPCIJE REZERVACIJE                *");
        System.out.println("*  Odabratio opciju: (Uneti -1 za korak nazad) *");
        System.out.println("*  1. Kreiraj rezervaciju                      *");
        System.out.println("*  2. Proveri rezervaciju                      *");
        System.out.println("*  3. Obriši rezervaciju (po broju telefona)   *");
        System.out.println("*  4. Obriši rezervaciju (po datumu)           *");
        System.out.println("*  5. Prikaz svih rezervacija                  *");
        System.out.println("*  6. Prikaz listu èekanja                     *");
        System.out.println(RAZMAK1);
    }
}
