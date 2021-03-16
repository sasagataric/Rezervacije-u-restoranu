package restoran;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import mejl.MailSender;


public class Program {
	private final static String RAZMAK1 = "**********************************************"; 
    private final static String RAZMAK2 = "##############################################";
  
	public static void main(String[] arg) throws ClassNotFoundException, IOException {
		System.out.println(RAZMAK2);
        Scanner input = new Scanner(System.in);
        UpravljanjeRezervacijama rezervacije= new UpravljanjeRezervacijama();
        
        UpravljanjeStolovima stolovi = new UpravljanjeStolovima();
        
        try { 
        	stolovi.loadData();
        	stolovi = new UpravljanjeStolovima(stolovi.listaStolova.getBrDesetMesta(),stolovi.listaStolova.getBrOsamMesta(),stolovi.listaStolova.getBrCetiriMesta(),stolovi.listaStolova.getBrDvaMesta());
        }catch (Exception e) {
        	System.out.println("Broj stolova sa 10 mesta:");
        	int s10 = input.nextInt();
        	System.out.println("Broj stolova sa 8 mesta:");
        	int s8 = input.nextInt();
        	System.out.println("Broj stolova sa 4 mesta:");
        	int s4 = input.nextInt();
        	System.out.println("Broj stolova sa 2 mesta:");
        	int s2 = input.nextInt();
        	
        	stolovi = new UpravljanjeStolovima(s10,s8,s4,s2);
        	stolovi.saveData();
		}
        
        SveOsobeNaListiCekanja listaCekanja = new SveOsobeNaListiCekanja();
        
        rezervacije.setListaStolova(stolovi.listaStolova);
        try {
        	rezervacije.loadData();
        }catch (FileNotFoundException e) {
        	System.out.println("Fajl Rezervacija.txt ne postoji, kreira�e se novi");
		}catch (EOFException e) {
			System.out.println("Sadr�aj fajla Rezervacija.txt ne mo�e da se pro�ita, kreira�e se novi");
		}
        
        try {
        	listaCekanja.loadData();
        }catch (FileNotFoundException e) {
        	System.out.println("Fajl Lista cekanja.txt ne postoji, kreira�e se novi");
		}catch (EOFException e) {
			System.out.println("Sadr�aj fajla Lista cekanja.txt ne mo�e da se pro�ita, kreira�e se novi");
		}
        
        rezervacije.setListaCekanja(listaCekanja);
        
        boolean x = true;	
        boolean y;
        int choice = 0;
        try {
        	stolovi.pokreni();
        	rezervacije.saveData();
        	System.out.println(RAZMAK2);
        } catch (InputMismatchException e) {
            System.out.println("Gre�ka prilikom sinhronizacije stolova sa rezervacijama");
        }    
        while (x) {
            try {
            	prikaziMeni();
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
            }
         switch (choice) {
                case (1):
                    	rezervacije.pokreni();
                    break;
                case (2):
                    	stolovi.prikazStatusaSvihStolova();
                    break;
                case (3):
                	listaCekanja.pokreni();
                break;
                case (4):
                	System.exit(0);
                break;
                    
                default:
                    System.out.println("Uneti validnu opciju");
            }
        }
    }

	
    public static void prikaziMeni() {
        System.out.println(RAZMAK1);
        System.out.println("*                  RESTORAN                  *");
        System.out.println("*    1. Rezervacije                          *");
        System.out.println("*    2. Status svih stolova                  *");
        System.out.println("*    3. Lista �ekanja                        *");
        System.out.println("*    4. Zaustavi aplikaciju                  *");
        System.out.println(RAZMAK1);

	}
}
