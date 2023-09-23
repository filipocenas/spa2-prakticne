package prakticne1;
/*
 * O sortiranju
------------
 ~1 NIVO~
============

Za sortiranje koristiti metod `sort` iz klase `Arrays` iz
paketa `java.util`.

Klasa koja predstavlja knjigu treba da implementira interfejs
`Comparable` i da ima svoj `compareTo` metod da bi niz
mogao biti sortiran gore pomenutim metodom. Na primer

    class Film implements Comparable<Film> {
    ...
	public int compareTo(Film drugiFilm) {
	....
	}
    ...
    }


Najjednostavnija varijanta je da se niz filmova sortira po id-u
filma.

Kada se testira da to radi, prepraviti da se filmovi sortiraju
po reditelju i po nazivu, odnosno kod filmova kod kojih je reditelj
isti filmovi treba da su sortirane po nazivu.

Sortirani niz snimiti u novi fajl u formatu kao u ulaznom fajlu.
Ovo se najbolje testira tako sto se program ponovo pokrene i 
ucita se novonapravljeni fajl.  Ako program normalno ucita i 
snimi podatke trebalo bi da je zadovoljen format.

 */

import java.io.IOException;
import java.util.Arrays;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;
import org.svetovid.io.SvetovidWriter;

public class Imdb2 {
	
	public static void main(String[] args) throws IOException {
		
		String inFile = Svetovid.in.readLine("Unesite ime fajla za citanje: ");
		Film2 [] filmovi = ucitajFilmove(inFile);
		if(filmovi != null) {
			System.out.println("Filmovi pre sortiranja: ");
			stampaj(filmovi);
			Arrays.sort(filmovi);
			System.out.println("Filmovi posle sortiranja: ");
			stampaj(filmovi);
			
			String outFile = Svetovid.in.readLine("Unesite ime fajla za cuvanje: ");
			if(!snimiUFile(filmovi, outFile)) {
				System.out.println("Neuspesno!");				
			} else {
				System.out.println("Uspesno snimanje!");
				
			}
		}
	}
	
	
	public static Film2[] ucitajFilmove (String inFile) throws IOException {
		if(!Svetovid.testIn(inFile)) {
			System.out.println("Fajl nije dostupan za citanje...");
			return null;
		}
		SvetovidReader in = Svetovid.in(inFile);
		int broj = in.readInt();
		Film2[] filmovi = new Film2[broj];
		for(int i = 0; i < broj; i++) {
			int id = in.readInt();
			String naziv = in.readLine();
			String reditelj = in.readLine();
			
			Film2 film = new Film2(id, naziv, reditelj);
			filmovi[i] = film;
		}
		in.close();
		return filmovi;
	}
	
	
	
	public static boolean snimiUFile(Film2[] filmovi, String outFile) {
		if(!Svetovid.testOut(outFile)) {
			System.out.println("Fajl nije dostupan sa pisanje...");
			return false;
		}
		SvetovidWriter out = Svetovid.out(outFile);
		out.println(filmovi.length);
		for(Film2 f: filmovi) {
			out.println(f.getId());
			out.println(f.getNaziv());
			out.println(f.getReditelj());
		}
		out.close();
		return true;
	}
	
	public static void stampaj(Film2[] filmovi) {
		for(Film2 f: filmovi) {
			System.out.println(f);
		}
	}
	
}
