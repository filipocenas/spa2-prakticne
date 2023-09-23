package stabla_kolokvijum2;

import java.util.Comparator;
import java.util.LinkedList;

import org.svetovid.Svetovid;

class Stablo {

	private static class Cvor {
		Proizvod info;
		Cvor levo;
		Cvor desno;

		public Cvor(Proizvod info, Cvor levo, Cvor desno) {
			this.info = info;
			this.levo = levo;
			this.desno = desno;
		}

	}

	private Cvor koren;

//	public Stablo() {
//		this.koren = null;
//	}
//
//	public Stablo(Proizvod pz) {
//		this.koren = new Cvor(pz, null, null);
//	}

	private Stablo(Cvor koren) {
		this.koren = koren;
	}

	// ------ dodati opisane metode ------------
	// ------ po potrebi i pomocne metode, konstruktore, klase i sl
	// ------ ne dodavati polja u klasu!

	public void ispisiSkupe() {
		ispisiSkupe(koren);
	}

	private void ispisiSkupe(Cvor cvor) {
		if (cvor == null) {
			return;
		}

		if (cvor.info.getCena() > 1000) {
			System.out.println(cvor.info.getNaziv());
		}

		ispisiSkupe(cvor.levo);
		ispisiSkupe(cvor.desno);
	}

	public int nemaNaStanju() {
		LinkedList<String> naziviProizvoda = new LinkedList<>();
		return nemaNaStanju(koren, naziviProizvoda);
	}

	private int nemaNaStanju(Cvor cvor, LinkedList<String> naziviProizvoda) {
		if (cvor == null) {
			return 0;
		}

		int kolicina = 0;

		if (!naziviProizvoda.contains(cvor.info.getNaziv()) && cvor.info.getKolicina() == 0) {
			naziviProizvoda.add(cvor.info.getNaziv());
			kolicina++;
		}

		kolicina += nemaNaStanju(cvor.levo, naziviProizvoda);
		kolicina += nemaNaStanju(cvor.desno, naziviProizvoda);

		return kolicina;
	}

	public Stablo snizenje() {
		Cvor noviKoren = snizenje(koren);
		return new Stablo(noviKoren);
	}

	private Cvor snizenje(Cvor cvor) {
		if (cvor == null) {
			return null;
		}

		Proizvod pz = new Proizvod(cvor.info.getNaziv(), cvor.info.getCena() * 0.9, cvor.info.isAkcija(),
				cvor.info.getKolicina());
		Cvor noviCvor = new Cvor(pz, null, null);

		noviCvor.levo = snizenje(cvor.levo);
		noviCvor.desno = snizenje(cvor.desno);

		return noviCvor;
	}

	private class KomparatorPoCeni implements Comparator<Proizvod> {
		@Override
		public int compare(Proizvod o1, Proizvod o2) {
			return Double.compare(o1.getCena(), o2.getCena()); // da smo stavilli o2 pa o1 bio bi opadajuci raspored cena
		}
	}

	public Proizvod drugiNajjeftiniji() {
		if(koren == null) {
			return null;
		}
		
		Proizvod p = koren.info; // krecemo od pocetka stabla tj od korena
		LinkedList<Proizvod> lista = new LinkedList<>(); // moramo inicijalizovati listu u koju smestamo proizvode

		drugiNajjeftiniji(koren, lista);

		lista.sort(new KomparatorPoCeni()); //sortiramo proizvode po ceni rastuce

		if (lista.size() > 1) {
			p = lista.get(1); // na indeksu 0 u listi je najmanja cena sto znaci da je na indeksu jedan druga najmanja cena
		}

		return p;
	}

	private void drugiNajjeftiniji(Cvor cvor, LinkedList<Proizvod> lista) {
		if (cvor == null) {
			return;
		}

		lista.add(cvor.info);
		drugiNajjeftiniji(cvor.levo, lista);
		drugiNajjeftiniji(cvor.desno, lista);
	}

	public boolean jeSimetricno() {
		return jeSimetricno(koren, koren);
	}

	private boolean jeSimetricno(Cvor cvor1, Cvor cvor2) {
		if (cvor1 == null && cvor2 == null) {
			return true;
		}

		if (cvor1 != null && cvor2 != null && cvor1.info.isAkcija() == cvor2.info.isAkcija()) {
			return jeSimetricno(cvor1.levo, cvor2.desno) && jeSimetricno(cvor1.desno, cvor2.levo);
		}

		return false;
	}

}

// Glavna klasa
public class StabloProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo> io = new TreeIO<>(Stablo.class);

		// Procitamo stablo iz fajla
		Stablo stablo = io.read(Svetovid.in("res/simetricno-test.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		// Stablo stablo = io.read(Svetovid.in("Veliko.txt"));
		// Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);

		System.out.println("\nSkupi proizvodi:\n");
		stablo.ispisiSkupe();

		System.out.println("Nema " + stablo.nemaNaStanju() + " razlicitih proizvoda na stanju.\n");

		System.out.println("Snizenje:\n");
		io.print(Svetovid.out, stablo.snizenje());

		System.out.println("\nDrugi najjeftinji proizvod " + stablo.drugiNajjeftiniji());
		
		System.out.println("\nSimetricno: " + stablo.jeSimetricno());

		// -------- ovde dodati pozive metoda ----------
	}
}
