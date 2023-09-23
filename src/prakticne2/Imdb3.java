package prakticne2;

import java.util.Comparator;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;
import org.svetovid.io.SvetovidWriter;

public class Imdb3 {
	public static void main(String[] args) {
		
		String inFile = Svetovid.in.readLine("Unesite naziv fajla za citanje: ");
		Film2[] filmovi = ucitaj(inFile);
		
		System.out.println("Unesite opciju za sortiranje: ");
		System.out.println("1: prirodno sortiranje");
		System.out.println("2: id opadajuce");
		System.out.println("3: naziv");
		System.out.println("4: reditelj");
		System.out.println("5: reditelj/naziv");
		
		int opcija = Svetovid.in.readInt();
		
		switch (opcija) {
		case 1:
			sortiraj(filmovi, new KomparatorPoID());
			System.out.println("Prirodno sortirani: ");
			break;
		case 2:
			sortiraj(filmovi, new ObrnutiKomparator(new KomparatorPoID()));
			System.out.println("ID opadajuce: ");
			break;
		case 3:
			sortiraj(filmovi, new KomparatorPoNazivu());
			System.out.println("Naziv: ");
			break;
		case 4:
			sortiraj(filmovi, new KomparatorPoReditelju());
			System.out.println("Reditelj: ");
		case 5:
			sortiraj(filmovi, new KomparatorPoRedNaz());
			System.out.println("Reditelj/Naziv: ");
			break;
		default:
			System.out.println("Elementi nisu sortirani!");
		}
		
		
		
		stampaj(filmovi);
		
		String outFile = Svetovid.in.readLine("Unesite naziv fajla za snimanje: ");
		snimi(filmovi, outFile);
	}
	
	public static Film2[] ucitaj(String inFile) {
		if(!Svetovid.testIn(inFile)) {
			return null;
		}
		SvetovidReader in = Svetovid.in(inFile);
		int brojFilmova = in.readInt();
		Film2 [] filmovi = new Film2[brojFilmova];
		for(int i = 0; i < brojFilmova; i++) {
			int id = in.readInt();
			String naziv = in.readLine();
			String reditelj = in.readLine();
			
			Film2 film = new Film2(id, naziv, reditelj);
			filmovi[i] = film;
		}
		in.close();
		return filmovi;
	}
	
	public static void stampaj(Film2[] filmovi) {
		for(Film2 f: filmovi) {
			System.out.println(f);
		}
	}
	
	public static void snimi(Film2[] filmovi, String outFile) {
		if(!Svetovid.testOut(outFile)) {
			return;
		}
		SvetovidWriter out = Svetovid.out(outFile);
		for(int i = 0; i < filmovi.length; i++) {
			out.println(filmovi[i].getId());
			out.println(filmovi[i].getNaziv());
			out.println(filmovi[i].getReditelj());
		}
		out.close();
	}

	public static void sortiraj(Film2[] niz, Comparator<Film2> c) {
		/*
		for(int i = niz.length - 1; i >= 1; i--) {
			int maxIndex = 0;
			for(int j = 1; j <= i; j++) {
				if(c.compare(niz[maxIndex], niz[j]) < 0) {
					maxIndex = j;
				}
			}
			if(maxIndex != i) {
				Film2 tmp = niz[i];
				niz[i] = niz[maxIndex];
				niz[maxIndex] = tmp;
			}
		}
		*/
		
		/*for(int i = 1; i < niz.length; i++) {
			Film2 current = niz[i];
			int j = i - 1;
			while(j >= 0 && c.compare(niz[i], current) > 0) {
				niz[j + 1] = niz[j];
				j--;
			}
			niz[j + 1] = current;
		} */
		
		for(int i = niz.length - 1; i >= 1; i--) {
			for(int j = 1; j <= i; i++) {
				if(c.compare(niz[j], niz[j + 1]) > 0) {
					Film2 tmp = niz[j];
					niz[j] = niz[j + 1];
					niz[j + 1] = tmp;
				}
			}
		}
		
	}
	
	static class KomparatorPoNazivu implements Comparator<Film2> {

		@Override
		public int compare(Film2 f1, Film2 f2) {
			return f1.getNaziv().compareTo(f2.getNaziv());
		}
		
	}

	static class KomparatorPoID implements Comparator<Film2> {

		@Override
		public int compare(Film2 f1, Film2 f2) {
			return f1.getId() - f2.getId();
		}
		
	}
	
	static class KomparatorPoReditelju implements Comparator<Film2> {
		
		@Override
		public int compare(Film2 f1, Film2 f2) {
			return f1.getReditelj().compareTo(f2.getReditelj());
		}
		
	}
	
	static class ObrnutiKomparator implements Comparator<Film2> {
		
		private final Comparator<Film2> original;
		
		public ObrnutiKomparator(Comparator<Film2> original) {
			this.original = original;
		}
		
		@Override
		public int compare(Film2 f1, Film2 f2) {
			return -original.compare(f1, f2);
		}
		
	}
	
	static class KompozitniKomparator implements Comparator<Film2> {
		
		private final Comparator<Film2> primarni;
		private final Comparator<Film2> sekundarni;
		
		public KompozitniKomparator(Comparator<Film2> primarni, Comparator<Film2> sekundarni) {
			this.primarni = primarni;
			this.sekundarni = sekundarni;
		}
		
		@Override
		public int compare(Film2 f1, Film2 f2) {
			int res = primarni.compare(f1, f2);
			if(res == 0) {
				res = sekundarni.compare(f1, f2);
			}
			return res;
		}
		
	}
	
	static class KomparatorPoRedNaz extends KompozitniKomparator {

		public KomparatorPoRedNaz() {
			super(new KomparatorPoReditelju(), new KomparatorPoNazivu());
		}
		
	}
}
