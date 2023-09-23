package prakticne1;

import java.util.Arrays;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;
import org.svetovid.io.SvetovidWriter;

public class Imdb {
	public static void main(String[] args) {
		
		String imeFajla = Svetovid.in.readLine("Unesite ime fajla sa filmovima: ");
		Film [] filmovi = ucitajFilmove(imeFajla);
		if(filmovi != null) {
			System.out.println("Nesortirani: ");
			stampaj(filmovi);			
			System.out.println("Sortirani: ");
			Arrays.sort(filmovi);
			stampaj(filmovi);
			
			String outFajl = Svetovid.in.readLine("Unesite naziv fajla za snimanje: ");
			snimiFilmove(filmovi, outFajl);
			
		}
		
		
		
		
	}	
	
	public static void snimiFilmove(Film [] filmovi, String imeFajla) {
		if(!Svetovid.testOut(imeFajla))
			return;
		
		SvetovidWriter out = Svetovid.out(imeFajla);
		out.println(filmovi.length);
		for(Film f: filmovi) {
			out.println(f.getId());
			out.println(f.getNaziv());
			out.println(f.getReditelj());
		}
		Svetovid.closeOut(imeFajla);
	}
	
	public static Film[] ucitajFilmove(String imeFajla) {
		if(!Svetovid.testIn(imeFajla))
			return null;
		
		SvetovidReader in = Svetovid.in(imeFajla);
		int broj = in.readInt();
		Film [] filmovi = new Film[broj];
		
		for(int i = 0; i < filmovi.length; i++) {
			int id = in.readInt();
			String naziv = in.readLine();
			String reditelj = in.readLine();
			
			Film film = new Film(id, naziv, reditelj);
			filmovi[i] = film;
		}
		Svetovid.closeIn(imeFajla);
		return filmovi;
	}
	
	public static void stampaj(Film [] filmovi) {
		for(Film f: filmovi) {
			System.out.println(f);
		}
	}
	
	
	
	
	
	
	
	
	
}
