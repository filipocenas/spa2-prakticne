package prakticne3;

import org.svetovid.io.SvetovidReader;

public class Putnik extends InfoTip {
	private String ime;
	private String prezime;
	private int godRodjenja;
	
	public Putnik() {
		
	}
	
	public Putnik(String prezime, String ime, int godRodjenja) {
		this.prezime = prezime;
		this.ime = ime;
		this.godRodjenja = godRodjenja;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public int getGodRodjenja() {
		return godRodjenja;
	}

	@Override
	public String toString() {
		return "Putnik " + ime + " " + prezime + " " + godRodjenja;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(o == null)
			return false;
		
		if(!(o instanceof Putnik))
			return false;
		
		Putnik p = (Putnik) o;
		
		if(this.godRodjenja != p.godRodjenja)
			return false;
		
		if(this.ime != null && !this.ime.equals(p.ime) || (this.ime == null && p.ime != null))
			return false;
		
		if(this.prezime != null && !this.ime.equals(p.prezime) || (this.prezime == null && p.prezime != null))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		int c;
		c = this.godRodjenja;
		result = 37 * result + c;
		
		
		//proveravamo da li je null jer nije primitivnog tipa vec je String
		if(this.ime != null) {
			c = this.ime.hashCode();
			result = 23 * result + c;
		}
		
		if(this.prezime != null) {
			c = this.prezime.hashCode();
			result = 17 * result + c;
		}
		
		return result;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader r) {
		String ime = r.readLine();
		String prezime = r.readLine();
		int godinaRodjenja = r.readInt();
		return new Putnik(ime , prezime, godinaRodjenja);
	}
	
	public static void main(String[] args) {
		new TestHash(new Putnik(), "resources/", "p").run();
	}
	
	
}
