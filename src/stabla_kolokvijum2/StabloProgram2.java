package stabla_kolokvijum2;

import java.util.Comparator;
import java.util.LinkedList;

import org.svetovid.Svetovid;


class Stablo2 {

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

	private Stablo2(Cvor koren) {
		this.koren = koren;
	}
	
	// ------ dodati opisane metode ------------
	// ------ po potrebi i pomocne metode, konstruktore, klase i sl
	// ------ ne dodavati polja u klasu!
	
	public void ispisiSkupe() {
		ispisiSkupe(koren);
	}
	
	public void ispisiSkupe(Cvor cvor) {
		if(cvor == null) {
			return;
		}
		
		if(cvor.info.getCena() > 1000) {
			System.out.println(cvor.info.getNaziv());
		}
		
		
		ispisiSkupe(cvor.levo);
		ispisiSkupe(cvor.desno);
	}
	
	public int nemaNaStanju() {
		LinkedList<String> naziviProizvoda = new LinkedList<>();
		return nemaNaStanju(koren, naziviProizvoda);
	}
	
	public int nemaNaStanju(Cvor cvor, LinkedList<String> naziviProizvoda) {
		if(cvor == null) {
			return 0;
		}
		int kolicina = 0;
		
		if(!naziviProizvoda.contains(cvor.info.getNaziv()) && cvor.info.getKolicina() == 0) {
			naziviProizvoda.add(cvor.info.getNaziv());
			kolicina ++;
		}
		
		kolicina += nemaNaStanju(cvor.levo, naziviProizvoda);
		kolicina += nemaNaStanju(cvor.desno, naziviProizvoda);
		
		return kolicina;
		
	}
	
	public Stablo2 snizenje() {
		Cvor noviKoren = snizenje(koren);
		return new Stablo2(noviKoren);
	}
	
	private Cvor snizenje(Cvor cvor) {
		if(cvor == null) {
			return null;
		}
		Proizvod p = new Proizvod(cvor.info.getNaziv(), cvor.info.getCena() * 0.9, cvor.info.isAkcija(), cvor.info.getKolicina());
		Cvor noviCvor = new Cvor(p, null, null);
		
		noviCvor.levo = snizenje(cvor.levo);
		noviCvor.desno = snizenje(cvor.desno);
		
		return noviCvor;
		
	}
	
	public class KomparatorPoCeni implements Comparator<Proizvod> {

		@Override
		public int compare(Proizvod o1, Proizvod o2) {
			return Double.compare(o1.getCena(), o2.getCena());
		}
		
	}
	
	public Proizvod drugiNajjeftiniji() {
		if(koren == null) {
			return null;
		}
		
		Proizvod p = koren.info;
		LinkedList<Proizvod> lista = new LinkedList<>();
		
		drugiNajjeftiniji(koren, lista);
		lista.sort(new KomparatorPoCeni());
		
		if(lista.size() > 1) {
			p = lista.get(1);
		}
		
		return p;
	}
	
	private void drugiNajjeftiniji(Cvor cvor, LinkedList<Proizvod> lista) {
		if(cvor == null) {
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
		if(cvor1 == null && cvor2 == null) {
			return true;
		}
		
		if(cvor1 != null && cvor2 != null && cvor1.info.isAkcija() == cvor2.info.isAkcija()) {
			return jeSimetricno(cvor1.levo, cvor2.desno) && jeSimetricno(cvor1.desno, cvor2.levo);
		}
		
		return false;
	}
	
	
	
	
}
	
// Glavna klasa
public class StabloProgram2 {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<Stablo2> io = new TreeIO<>(Stablo2.class);

		// Procitamo stablo iz fajla
		Stablo2 stablo = io.read(Svetovid.in("res/simetricno-test.txt"));
		// alternativno mozemo iz nekog drugog fajla
		// otkomentarisati neki od redova dole, a skloniti ovaj iznad
		// Stablo stablo = io.read(Svetovid.in("Veliko.txt"));
		// Stablo stablo = io.read(Svetovid.in("Prazno.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);
			
		// -------- ovde dodati pozive metoda ----------
			
			
	}
}

