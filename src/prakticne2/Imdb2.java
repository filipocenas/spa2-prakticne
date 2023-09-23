package prakticne2;

import java.util.Arrays;
import java.util.Comparator;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;
import org.svetovid.io.SvetovidWriter;

public class Imdb2 {
	
	public static void main(String[] args) {
		String inFile = Svetovid.in.readLine("Unesite naziv fajla za citanje: ");
		Film[] filmovi = ucitaj(inFile);
		if(filmovi != null) {
			System.out.println("Unesite opciju za sortiranje: ");
			System.out.println("1. prirodno soriranje");
			System.out.println("2. id opadajuce");
			System.out.println("3. naziv");
			System.out.println("4. reditelj");
			System.out.println("5. reditelj/naziv");
			
			int opcija = Svetovid.in.readInt();
			switch(opcija) {
			case 1:
				Arrays.sort(filmovi);
				System.out.println("Po ID-u rastuce: ");
				break;
			case 2:
				sortiraj(new ObrnutiKomparator(new KomparatorPoID()), filmovi);
				System.out.println("Po ID-u opadajuce: ");
				break;
			case 3:
				sortiraj(new KomparatorPoNazivu(), filmovi);
				System.out.println("Po nazivu filma: ");
				break;
			case 4:
				sortiraj(new KomparatorPoReditelju(), filmovi);
				System.out.println("Po reditelju filma: ");
				break;
			case 5:
				sortiraj(new KomparatorRezNaz(), filmovi);
				System.out.println("Po reditelju pa po nazivu filma: ");
				break;
			default:
				System.out.println("Niz nije sortiran.");
			}
			
			stampaj(filmovi);
			String outFile = Svetovid.in.readLine("Unesite naziv fajla za snimanje: ");
			snimi(filmovi, outFile);			
		}
	}
	
	public static Film[] ucitaj(String inFile) {
		if(!Svetovid.testIn(inFile)) {
			System.out.println("Fajl je nedostupan za citanje!");
			return null;			
		}
		SvetovidReader in = Svetovid.in(inFile);
		int brojFilmova = in.readInt();
		Film[] filmovi = new Film[brojFilmova];
		for(int i = 0; i < filmovi.length; i++) {
			int id = in.readInt();
			String naziv = in.readLine();
			String reditelj = in.readLine();
			filmovi[i] = new Film(id, naziv, reditelj);
		}
		in.close();
		return filmovi;
	}
	
	public static void stampaj(Film[] filmovi) {
		for(Film f: filmovi) {
			System.out.println(f);
		}
	}
	
	public static void snimi(Film[] filmovi, String outFile) {
		if(!Svetovid.testOut(outFile)) {
			System.out.println("Fajl je nedostupan za snimanje!");
			return;
		}
		SvetovidWriter out = Svetovid.out(outFile);
		out.println(filmovi.length);
		for(Film f: filmovi) {
			out.println(f.getId());
			out.println(f.getNaziv());
			out.println(f.getReditelj());
		}
		out.close();
	}
	
	public static void sortiraj(Comparator<Film> c, Film[] niz) {
		for(int i = niz.length - 1; i >= 1; i--) {
			int maxIndex = 0;
			for(int j = 1; j <= i; j++) {
				if(c.compare(niz[maxIndex], niz[j]) <= 0) {
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
	
	static class KomparatorPoID implements Comparator<Film> {
		
		@Override
		public int compare(Film f1, Film f2) {
			return f1.getId() - f2.getId();
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
	
	static class KomparatorPoNazivu implements Comparator<Film> {

		@Override
		public int compare(Film f1, Film f2) {
			return f1.getNaziv().compareTo(f2.getNaziv());
		}
		
	}
	
	static class KomparatorPoReditelju implements Comparator<Film> {

		@Override
		public int compare(Film f1, Film f2) {
			return f1.getReditelj().compareTo(f2.getReditelj());
		}
		
	}
	
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
	
	static class KomparatorRezNaz extends KompozitniKomparator {
		
		public KomparatorRezNaz() {
			super(new KomparatorPoReditelju(), new KomparatorPoNazivu());
		}
		
	}
	
}
