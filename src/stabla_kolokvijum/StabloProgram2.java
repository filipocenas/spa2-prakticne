package stabla_kolokvijum;

import java.util.LinkedList;

import org.svetovid.Svetovid;

class Stablo2 {

	static class Cvor {

		Patuljak info;
		Cvor levo;
		Cvor desno;

		public Cvor(Patuljak info, Cvor levo, Cvor desno) {
			super();
			this.info = info;
			this.levo = levo;
			this.desno = desno;
		}

	}

	private Cvor koren;

	private Stablo2(Cvor koren) {
		this.koren = koren;
	}

	// ------ dodati opisane metode ------------
	// ------ ne dodavati polja u klasu!
	// nesto trece
	
	public void ispisiNaSlovo(String slovo) {
		ispisiNaSlovo(koren, slovo);
	}
	
	private void ispisiNaSlovo(Cvor cvor, String slovo) {
		if(cvor == null) {
			return;
		}
		if(cvor.info.getIme().startsWith(slovo)) {
			System.out.println(cvor.info.getIme());
		}
		
		ispisiNaSlovo(cvor.levo, slovo);
		ispisiNaSlovo(cvor.desno, slovo);
	}
	
	public double sumaZlataNaSlovo(String slovo) {
		return sumaZlataNaSlovo(koren, slovo);
	}
	
	private double sumaZlataNaSlovo(Cvor cvor, String slovo) {
		if(cvor == null) {
			return 0.0;
		}
		double suma = 0.0;
		if(cvor.info.getIme().startsWith(slovo)) {
			suma =+ cvor.info.getIskopaoZlata();
		}
		
		suma += sumaZlataNaSlovo(cvor.levo, slovo);
		suma += sumaZlataNaSlovo(cvor.desno, slovo);
		
		return suma;
	}
	
	public Stablo2 iskopaoJos(String ime, double kolicina) {
		Cvor noviKoren = iskopaoJos(koren, ime, kolicina);
		return new Stablo2(noviKoren);
	}
	
	private Cvor iskopaoJos(Cvor cvor, String ime, double kolicina) {
		if(cvor == null) {
			return null;
		}
		if(cvor.info.getIme().equals(ime)) {
			Patuljak p = new Patuljak(ime, cvor.info.getUbioGoblina(), cvor.info.getGodinaRodjenja(), 
									cvor.info.getIskopaoZlata() + kolicina);
			cvor = new Cvor(p, cvor.levo, cvor.desno);
		}
		Cvor noviCvor = new Cvor(cvor.info, null, null);
		noviCvor.levo = iskopaoJos(noviCvor.levo, ime, kolicina);
		noviCvor.desno = iskopaoJos(noviCvor.desno, ime, kolicina);
		
		return noviCvor;
	}
	
	public boolean ponavljaSeGodinaRodjenja() {
		if(koren == null) {
			return false;
		}
		
		LinkedList<Integer> lista = new LinkedList<>();
		ponavljaSeGodinaRodjenja(koren, lista);
		int max = 0, brojac = 0;
		
		for(int j = 0; j < lista.size(); j++) {
			int test = lista.get(j);
			for(int i = 0; i < lista.size(); i++) {
				if(j != i && test == lista.get(i)) {
					brojac++;
				}
			}
			max = Math.max(max, brojac);
		}
		return max > 1 ? true : false;
	}
	
	private void ponavljaSeGodinaRodjenja(Cvor cvor, LinkedList<Integer> lista) {
		if(cvor == null) {
			return;
		}
		
		lista.add(cvor.info.getGodinaRodjenja());
		ponavljaSeGodinaRodjenja(cvor.levo, lista);
		ponavljaSeGodinaRodjenja(cvor.desno, lista);
	}

}

// Glavna klasa
public class StabloProgram2 {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo2> io = new TreeIO<>(Stablo2.class);

		// Procitamo stablo iz fajla
		Stablo2 stablo = io.read(Svetovid.in("resources/Malo.txt"));
		// Stablo stablo = io.read(Svetovid.in("res/sumarno-test.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		// Stablo stablo = io.read(Svetovid.in("res/Veliko.txt"));
		// Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);

		// -------- ovde dodati pozive metoda ----------
		
		
	}
}

