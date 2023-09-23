package prakticne7;

import org.svetovid.Svetovid;

import java.util.Comparator;

// Konkretno stablo koje sadrzi ocene

// Glavna klasa
public class StabloOsobaProgram2 {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<StabloOsoba2> io = new TreeIO<>(StabloOsoba2.class);

		// Procitamo stablo iz fajla
		StabloOsoba2 stablo = io.read(Svetovid.in("resources/Osobe-mini.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);
		
		//1)
		System.out.println(stablo.jePrazno() ? "Da" : "Ne");
		
		//2)
		Osoba osobaX = new Osoba("Maja", "Majic", 41972);
		String postoji = stablo.postojiElement(stablo.koren, osobaX) ? " postoji." : " ne postoji.";
		System.out.println("Osoba " + osobaX + postoji);
		
		//3)
		stablo.stampajInorder(stablo.koren);
		System.out.println();
		stablo.stampajPreorder(stablo.koren);
		System.out.println();
		stablo.stampajPostorder(stablo.koren);
		
		//4)
		stablo.stampajListove(stablo.koren);
		
		//5)
		StabloOsoba2 novoStablo = stablo.pronadji(osobaX);
		io.print(Svetovid.out, novoStablo);
		
		//6)
		System.out.println(stablo.stampajSveIspod(osobaX));
		
		//7)
		Comparator<Osoba> komparator = new Comparator<Osoba>() {

			@Override
			public int compare(Osoba o1, Osoba o2) {
				return o1.getPlata() - o2.getPlata();
			}
	    	
	    };
	    
	    System.out.println(stablo.optimalnaOsoba(komparator, stablo.koren));
		
	}
	
}
