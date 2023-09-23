package prakticne2;

import java.util.Arrays;
import java.util.Comparator;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;
import org.svetovid.io.SvetovidWriter;

public class Imdb {
	public static void main(String[] args) {
		
		String imeFajla = Svetovid.in.readLine("Unesite ime fajla sa filmovima: ");
		Film [] filmovi = ucitajFilmove(imeFajla);
		
		if(filmovi == null) {
			System.out.println("Problem sa citanjem fajlova!");
			return;
		}
		
		System.out.println("Opcija 1: prirodno sortiranje");
		System.out.println("Opcija 2: id opadajuce");
		System.out.println("Opcija 3: po nazivu");
		System.out.println("Opcija 4: po reditelju");
		System.out.println("Opcija 5: po reditelju/nazivu");
		System.out.println("Za druge opcije niz nece biti sortiran. Unesite validnu opciju.");
		
		int opcija = Svetovid.in.readInt("Unesite zeljenu opciju za sortiranje: ");
		
		switch(opcija) {
		case 1:
			Arrays.sort(filmovi);
			break;
		case 2:
			sortiraj(new ObrnutiKomparator(new KomparatorID()), filmovi);
			break;
		case 3:
			sortiraj(new KomparatorNaziv(), filmovi);
			break;
		case 4:
			sortiraj(new KomparatorReditelj(), filmovi);
			break;
		case 5:
			//sortiraj(new KomparatorRedNaz(), filmovi); ili
			//sortiraj(new KompozitniKomparator(new KomparatorReditelj(), new KomparatorNaziv()), filmovi); ili
			sortiraj(new KomparatorZaRedNaz(), filmovi);
			break;
			default:
				System.out.println("Niz nije sortiran.");
		
		}
			stampaj(filmovi);
			
			String outFajl = Svetovid.in.readLine("Unesite naziv fajla za snimanje: ");
			snimiFilmove(filmovi, outFajl);
		
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
	
	static class KomparatorID implements Comparator<Film> {

		@Override
		public int compare(Film f1, Film f2) {
			return f1.getId() - f2.getId();
		}
		
	}
	
	static class KomparatorNaziv implements Comparator<Film> {

		@Override
		public int compare(Film f1, Film f2) {
			return f1.getNaziv().compareTo(f1.getNaziv());
		}
	}
	
	static class KomparatorReditelj implements Comparator<Film> {

		@Override
		public int compare(Film f1, Film f2) {
			return f1.getReditelj().compareTo(f2.getReditelj());
		}
		
	}
	
	static class KomparatorRedNaz implements Comparator<Film> {

		@Override
		public int compare(Film f1, Film f2) {
			int rez = f1.getReditelj().compareTo(f2.getReditelj());
			if(rez == 0) {
				rez = f1.getNaziv().compareTo(f2.getNaziv());
			}
			return rez;
		}
		
	}
	
	//ili
	
	static class KompozitniKomparator implements Comparator<Film> {
		
		private final Comparator<Film> primarni;
		private final Comparator<Film> sekundarni;
		
		public KompozitniKomparator(Comparator<Film> primarni, Comparator<Film> sekundarni) {
			this.primarni = primarni;
			this.sekundarni = sekundarni;
		}
		
		@Override
		public int compare(Film f1, Film f2) {
			int rez = primarni.compare(f1, f2);
			if(rez == 0) {
				rez = sekundarni.compare(f1, f2);
			}
			return rez;
		}
		
	}
	
	static class ObrnutiKomparator implements Comparator<Film> {
		
		private final Comparator<Film> original;
		public ObrnutiKomparator(Comparator<Film> original) {
			this.original = original;
		}
		
		@Override
		public int compare(Film f1, Film f2) {
			
			return -original.compare(f1, f2);
		}
		
	}
	//selection sort
	public static void sortiraj(Comparator<Film> comparator, Film [] niz) {
		for(int i = niz.length - 1; i >= 1; i--) {
			int maxIndex = 0;
			for(int j = 1; j <= i; j++) {
				if(comparator.compare(niz[maxIndex], niz[j]) <= 0) {
					maxIndex = j;
				}
			}
			
			if(maxIndex != i) {
				Film tmp = niz[i];
				niz[i] = niz[maxIndex];
				niz[maxIndex] = tmp;
			}
		}
	}
	
	static class KomparatorZaRedNaz extends KompozitniKomparator {

		public KomparatorZaRedNaz() {
			super(new KomparatorReditelj(), new KomparatorNaziv());
		}
		
	}
	
	
	
	
}
