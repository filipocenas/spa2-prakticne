package prakticne1;

public class Film implements Comparable<Film> {

	final private int id;
	final private String naziv;
	final private String reditelj;
	
	public Film(int id, String naziv, String reditelj) {
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
		return "#" + id + " " + naziv + " - " + reditelj;
	}

	@Override
	public int compareTo(Film that) {
//		int rez = 0;
//		if(this.id > that.id) {
//			rez = 1;
//		}
//		if(this.id < that.id) {
//			rez = -1;
//		}
//		return rez;
		int rez =  this.reditelj.compareTo(that.reditelj);
		if(rez == 0) {
			rez = this.naziv.compareTo(that.naziv);
		}
		return rez;
	}
	
}
