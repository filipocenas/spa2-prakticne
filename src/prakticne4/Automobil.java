package prakticne4;

import java.util.Objects;

import org.svetovid.io.SvetovidReader;

public class Automobil extends InfoTip {
	
	private String model;
	private Boja bojaKaroserije, bojaSedista, bojaVolana;
	
	public Automobil(String model, Boja bojaKaroserije, Boja bojaSedista, Boja bojaVolana) {
		this.model = model;
		this.bojaKaroserije = bojaKaroserije;
		this.bojaSedista = bojaSedista;
		this.bojaVolana = bojaVolana;
	}

	public Automobil() { }

	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(!(o instanceof Automobil))
			return false;
		
		Automobil a = (Automobil) o;
				
		if(!Objects.equals(this.model, a.model))
			return false;
		
		if(!Objects.equals(this.bojaKaroserije, a.bojaKaroserije))
			return false;
		
		if(!Objects.equals(this.bojaSedista, a.bojaSedista))
			return false;
		
		if(!Objects.equals(this.bojaVolana, a.bojaVolana))
			return false;
		
		return true;
	}

	@Override
    public Automobil ucitaj(SvetovidReader read) {
		//TODO zavrsi
		String model = read.readLine();
		Boja temp = new Boja();
		Boja b1 = (Boja) temp.ucitaj(read);
		Boja b2 = (Boja) temp.ucitaj(read);
		Boja b3 = (Boja) temp.ucitaj(read);
		read.readLine();
		
		return new Automobil(model, b1, b2, b3);
	}
	
	public int hashCode() {
		return model.hashCode() + 7 * bojaKaroserije.hashCode() + 83 * bojaSedista.hashCode() + 193 * bojaVolana.hashCode();
	}
	
	public static void main(String[] args) {
		
		new TestHash(new Automobil(), "resources/", "a").run();
	}
}
