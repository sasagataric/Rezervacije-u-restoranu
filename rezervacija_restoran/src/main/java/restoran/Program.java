package restoran;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        SveOsobeNaListiCekanja listaCekanja = new SveOsobeNaListiCekanja();
        
        rezervacije.setListaStolova(stolovi.listaStolova);
        try {
        	rezervacije.loadReservations();
        }catch (FileNotFoundException e) {
        	System.out.println("Fajl Rezervacija.txt ne postoji, kreiraæe se novi");
		}catch (EOFException e) {
			System.out.println("Sadržaj fajla Rezervacija.txt ne može da se proèita, kreiraæe se novi");
		}
        
        try {
        	listaCekanja.loadListaCekanja();
        }catch (FileNotFoundException e) {
        	System.out.println("Fajl Lista cekanja.txt ne postoji, kreiraæe se novi");
		}catch (EOFException e) {
			System.out.println("Sadržaj fajla Lista cekanja.txt ne može da se proèita, kreiraæe se novi");
		}
        
        rezervacije.setListaCekanja(listaCekanja);
        
        boolean x = true;	
        boolean y;
        int choice = 0;
        try {
        	stolovi.pokreni();
        	System.out.println(RAZMAK2);
        } catch (InputMismatchException e) {
            System.out.println("Greška prilikom sinhronizacije stolova sa rezervacijama");
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
                		stolovi.getSlobodneStolove();
                    break;
                case (3):
                    	stolovi.prikazStatusaSvihStolova();
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
        System.out.println("*    2. Trenutno slobodni stolovi            *");
        System.out.println("*    3. Status svih stolova                  *");
        System.out.println(RAZMAK1);

	}
}
