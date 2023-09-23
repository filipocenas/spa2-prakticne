package prakticne2;

public class Film2 implements Comparable<Film2> {
	private final int id;
	private final String naziv;
	private final String reditelj;

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
		return "#" + id + " " + naziv + " - " + reditelj; 
	}

	@Override
	public int compareTo(Film2 that) {
		return this.id - that.id;
	}
	
	

}
