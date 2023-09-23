package prakticne7;

import org.svetovid.Svetovid;

import java.util.List;

// Konkretno stablo koje sadrzi ocene

// Glavna klasa
public class StabloOsobaProgram {

	// Glavni program
	public static void main(String[] args) {

		// Napravimo pomocni objekat za ucitavanje i ispisivanje
		TreeIO<StabloOsoba> io = new TreeIO<>(StabloOsoba.class);

		// Procitamo stablo iz fajla
		StabloOsoba stablo = io.read(Svetovid.in("resources/Osobe-mini.txt"));

		// Ispisemo ucitano stablo
		io.print(Svetovid.out, stablo);

//		//poziv metode jePrazno
//		System.out.println(stablo.jePrazno() ? "Da" : "Ne");
//		
//		//poziv metode postojiElement
		Osoba osobaX = new Osoba("Nina", "Ninkov", 219710);
//		String postoji = stablo.postojiElement(stablo.koren, osobaX) ? " postoji " : " ne postoji ";
//		System.out.println("Osoba " + osobaX + postoji);
//		
//		//poziv metoda stampajInorder, Postorder, Preorder
//		stablo.stampajInorder(stablo.koren);
//		System.out.println();
//		stablo.stampajPostorder(stablo.koren);
//		System.out.println();
//		stablo.stampajPreorder(stablo.koren);
//		
//		//poziv metode stampajListove
//		stablo.stampajListove(stablo.koren);
//		
//		//poziv metode pronadji
//		StabloOsoba novoStablo = stablo.pronadji(osobaX);
//		io.print(Svetovid.out, novoStablo);
//		
//		//poziv metode stampajSveIspod
//		System.out.println(stablo.stampajSveIspod(osobaX));
//		
//		
//		//poziv metode optimalnaOsoba
//		Comparator<Osoba> komparator = new Comparator<Osoba>() {
//
//			@Override
//			public int compare(Osoba o1, Osoba o2) {
//				return o1.getPlata() - o2.getPlata();
//			}
//			
//		};
//		
//		System.out.println(stablo.optimalnaOsoba(komparator, stablo.koren));
		
		//poziv metode sviNadredjeni
		System.out.println();
		List<Osoba> nadredjeni = stablo.sviNadredjeni(osobaX);
		System.out.println(nadredjeni);
	}
}
