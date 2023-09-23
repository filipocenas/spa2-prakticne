package prakticne2;


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
		return this.id - that.id;
	}
	
}
