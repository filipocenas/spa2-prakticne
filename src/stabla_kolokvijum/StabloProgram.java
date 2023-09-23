package stabla_kolokvijum;

import java.util.LinkedList;

import org.svetovid.Svetovid;

class Stablo {

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

	private Stablo(Cvor koren) {
		this.koren = koren;
	}

	// ------ dodati opisane metode ------------
	// ------ ne dodavati polja u klasu!
	// nesto trece

	public void ispisiNaSlovo(String slovo) {
		ispisiNaSlovo(koren, slovo);
	}

	private void ispisiNaSlovo(Cvor cvor, String slovo) {
		if (cvor == null) {
			return;
		}

		if (cvor.info.getIme().substring(0, 1).equals(slovo)) {
			System.out.println(cvor.info.getIme());
		}

		ispisiNaSlovo(cvor.levo, slovo);
		ispisiNaSlovo(cvor.desno, slovo);
	}

	public double sumaZlataNaSlovo(String slovo) {
		return sumaZlataNaSlovo(koren, slovo);
	}

	private double sumaZlataNaSlovo(Cvor cvor, String slovo) {
		if (cvor == null) {
			return 0.0;
		}

		double suma = 0.0;

		if (cvor.info.getIme().substring(0, 1).equals(slovo)) {
			suma += cvor.info.getIskopaoZlata();
		}

		suma += sumaZlataNaSlovo(cvor.levo, slovo);
		suma += sumaZlataNaSlovo(cvor.desno, slovo);

		return suma;
	}

	public Stablo iskopaoJos(String ime, double kolicina) {
		Cvor noviKoren = iskopaoJos(koren, ime, kolicina);
		return new Stablo(noviKoren);
	}

	private Cvor iskopaoJos(Cvor cvor, String ime, double kolicina) {
		if (cvor == null) {
			return null;
		}

		if (cvor.info.getIme().equals(ime)) {
			Patuljak p = new Patuljak(ime, cvor.info.getUbioGoblina(), cvor.info.getGodinaRodjenja(),
					cvor.info.getIskopaoZlata() + kolicina);
			cvor = new Cvor(p, cvor.levo, cvor.desno);
		}

		Cvor noviCvor = new Cvor(cvor.info, null, null);
		noviCvor.levo = iskopaoJos(cvor.levo, ime, kolicina);
		noviCvor.desno = iskopaoJos(cvor.desno, ime, kolicina);

		return noviCvor;
	}

	public boolean ponavljaSeGodinaRodjenja() {
		if (koren == null) {
			return false;
		}

		LinkedList<Integer> lista = new LinkedList<>();
		ponavljaSeGodinaRodjenja(koren, lista);

		int brojac = 0, max = 0;

		for (int j = 0; j < lista.size(); j++) {
			int test = lista.get(j);
			for (int i = 0; i < lista.size(); i++) {
				if (j != i && test == lista.get(i)) {
					brojac++;
				}
			}
			max = Math.max(max, brojac);
		}

		return max > 1 ? true : false;
	}

	private void ponavljaSeGodinaRodjenja(Cvor cvor, LinkedList<Integer> lista) {
		if (cvor == null) {
			return;
		}

		lista.add(cvor.info.getGodinaRodjenja());
		ponavljaSeGodinaRodjenja(cvor.levo, lista);
		ponavljaSeGodinaRodjenja(cvor.desno, lista);
	}

	// Ako se ne varam, ovde sam testirao 2 nacina za proveru sumarnog stabla
	// jeSumarnoStablo() je jedan nacin i jeSumernoStablo() je drugi nacin
	// Oba ispisuju false tako da valjda rade oba kako treba :)

	public boolean jeSumarnoStablo() {
		return jeSumarnoStablo(koren);
	}

	private boolean jeSumarnoStablo(Cvor cvor) {
		if (cvor == null || (cvor.levo == null && cvor.desno == null)) {
			return true;
		}

		if ((cvor.info.getUbioGoblina() == (sumaCvorova(cvor.levo) + sumaCvorova(cvor.desno)))
				&& jeSumarnoStablo(cvor.levo) && jeSumarnoStablo(cvor.desno)) {
			return true;
		}

		return false;
	}

	private int sumaCvorova(Cvor cvor) {
		if (cvor == null) {
			return 0;
		}
		return cvor.info.getUbioGoblina() + sumaCvorova(cvor.levo) + sumaCvorova(cvor.desno);
	}

	public boolean jeSumernoStablo() {
		if (koren == null)
			return true;
		if (jeSumernoStabloPom(koren) != -1)
			return true;
		else
			return false;
	}

	private int jeSumernoStabloPom(Cvor koren) {
		if (koren == null)
			return 0;
		if (koren.levo == null && koren.desno == null) {
			return koren.info.getUbioGoblina();
		}
		if (koren.info.getUbioGoblina() == jeSumernoStabloPom(koren.levo) + jeSumernoStabloPom(koren.desno)) {
			return 2 * koren.info.getUbioGoblina();
		}
		return -1;
	}

}

// Glavna klasa
public class StabloProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo> io = new TreeIO<>(Stablo.class);

		// Procitamo stablo iz fajla
		Stablo stablo = io.read(Svetovid.in("resources/Malo.txt"));
		// Stablo stablo = io.read(Svetovid.in("res/sumarno-test.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		// Stablo stablo = io.read(Svetovid.in("res/Veliko.txt"));
		// Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);

		// -------- ovde dodati pozive metoda ----------

		System.out.println("\nSvi na slovo O:");
		stablo.ispisiNaSlovo("O");

		System.out.println("\nSuma zlata ovih na O je " + stablo.sumaZlataNaSlovo("O"));

		System.out.println();
		io.print(Svetovid.out, stablo.iskopaoJos("Osin Gvozdeni", 105.85));

		System.out.println("\nDa li ima bar 2 goblina iste godine: " + stablo.ponavljaSeGodinaRodjenja());

		// Testiranje oba nacina
		System.out.println(
				"\nSumarno stablo: " + stablo.jeSumarnoStablo() + ", drugi nacin: " + stablo.jeSumernoStablo());
	}
}
