package restoran;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * Klasa UpravljanjeStolovima  postavlja broj stolova,
 * odredjuje koji stolovi su rezercisani
 * 
 * @author Saša Gatariæ
 *
 */
public class UpravljanjeStolovima {

	public static SviStolovi listaStolova;
	private SveRezervacije rl;
	

	public UpravljanjeStolovima(int i, int j, int k, int l){
		listaStolova = new SviStolovi(i, j, k, l);
		
		rl= UpravljanjeRezervacijama.listaRezervacije;
	}
	
	public void setRl(SveRezervacije rl) {
		this.rl = rl;
	}
	
	public void pokreni() throws ClassNotFoundException{
		Date danas = new GregorianCalendar().getTime();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String danasString = df.format(danas);
		
		
		System.out.println("Sinhronizacija stolova sa rezervacijama.");
		listaStolova.sinhronizacijaStolovaSaRezervacijama(danasString, rl);
		try {
			UpravljanjeRezervacijama.saveReservations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	public void getSlobodneStolove(){
		System.out.println("Trenutno slobodni stolovi:");
		ArrayList<Sto> pomlistaStolova = listaStolova.getSlobodniStolovi();
		if(pomlistaStolova.isEmpty()){
			System.out.println("Nema slobodnih stolova.");
		}else{
			for(int i=0;i<pomlistaStolova.size();i++){
				pomlistaStolova.get(i).prikazStola();
			}
		}
	}
	
	public void prikazStatusaSvihStolova(){
		System.out.println("Status svih stolova: ");
		listaStolova.prikazStatusaStola();
	}
	
}
