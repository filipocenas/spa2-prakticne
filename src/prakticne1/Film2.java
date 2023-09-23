package prakticne1;
/*
 *
Napisati program koji ucitava niz filmova iz fajla, sortira
ih i ispisuje u drugi fajl. Imena fajlova zadaje korisnik.

Format fajla
------------
Fajl je formatiran na sledeci nacin:
U prvom redu stoji broj N.
Posle njega sledi opis N elemenata niza.

Svaki element je predstavljen u tri reda. U prvom redu se
nalazi Id filma, tipa `int`, u sledecem je naziv filma, a
u trecem ime reditelja. Imena su stringovi.

  Id
  Naziv
  Reditelj

Dati su fajlovi filmovi12.txt i filmovi146.txt sa po 12 i 146
filmova u njima, respektivno, na kojima se moze testirati 
program.

Predpostaviti ako fajl postoji da je ispravan, odnosno da
je u tacno opisanom formatu.

O sortiranju
------------
 ~1 NIVO~
============

Za sortiranje koristiti metod `sort` iz klase `Arrays` iz
paketa `java.util`.

Klasa koja predstavlja knjigu treba da implementira interfejs
`Comparable` i da ima svoj `compareTo` metod da bi niz
mogao biti sortiran gore pomenutim metodom. Na primer

    class Film implements Comparable<Film> {
    ...
	public int compareTo(Film drugiFilm) {
	....
	}
    ...
    }


Najjednostavnija varijanta je da se niz filmova sortira po id-u
filma.

Kada se testira da to radi, prepraviti da se filmovi sortiraju
po reditelju i po nazivu, odnosno kod filmova kod kojih je reditelj
isti filmovi treba da su sortirane po nazivu.

Sortirani niz snimiti u novi fajl u formatu kao u ulaznom fajlu.
Ovo se najbolje testira tako sto se program ponovo pokrene i 
ucita se novonapravljeni fajl.  Ako program normalno ucita i 
snimi podatke trebalo bi da je zadovoljen format.

 */
public class Film2 implements Comparable<Film2>{
	
	final private int id;
	final private String naziv;
	final private String reditelj;
	
	public Film2(int id, String naziv, String reditelj) {
		this.id = id;
		this.naziv = naziv;
		this.reditelj = reditelj;
	}

	public int getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getReditelj() {
		return reditelj;
	}

	@Override
	public String toString() {
		return "#" + id + " " + reditelj + " - " + naziv;
	}
	
	@Override
	public int compareTo(Film2 that) {
		int rez = this.reditelj.compareTo(that.reditelj);
		if(rez == 0) {
			rez = this.naziv.compareTo(that.naziv);
		}
		return rez;
	}
	
	
}
