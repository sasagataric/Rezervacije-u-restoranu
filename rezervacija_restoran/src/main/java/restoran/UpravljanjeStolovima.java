package restoran;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import intefejsi.IRadSaPodacima;


/**
 * Klasa UpravljanjeStolovima  postavlja broj stolova,
 * odredjuje koji stolovi su rezercisani
 * 
 * @author Saša Gatariæ
 *
 */
public class UpravljanjeStolovima implements IRadSaPodacima{

	public static SviStolovi listaStolova;
	private SveRezervacije rl;
	
	
	public UpravljanjeStolovima(int s10, int s8, int s4, int s2){
		listaStolova = new SviStolovi(s10,s8,s4,s2);
		rl= UpravljanjeRezervacijama.listaRezervacije;
	}
	
	public UpravljanjeStolovima() {
		
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
	}
	
	public void saveData() throws FileNotFoundException, IOException, ClassNotFoundException {
	    File file = new File("Stolovi.txt");
	    SviStolovi stolovi = listaStolova;
	    FileOutputStream fo = new FileOutputStream(file);
	    ObjectOutputStream output = new ObjectOutputStream(fo);
	    output.writeObject(stolovi);
	    output.close();
	    fo.close();
	}
	
	public void loadData() throws IOException, ClassNotFoundException {
        File file = new File("Stolovi.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        SviStolovi stolovi = null;
        try {
            while (true) {
            	stolovi = (SviStolovi) input1.readObject(); 
            }
        } catch (EOFException ex) {
        	
        	listaStolova=stolovi;
        }
    }
	

	public void prikazStatusaSvihStolova(){
		System.out.println("Status svih stolova: ");
		listaStolova.prikazStatusaStola();
	}
	
}
