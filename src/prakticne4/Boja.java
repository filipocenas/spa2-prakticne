package prakticne4;

import org.svetovid.io.SvetovidReader;

public class Boja extends InfoTip {
	
	private int r, g, b;
	
	public Boja(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Boja() { }
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(! (o instanceof Boja))
			return false;
		
		Boja b = (Boja) o;
		
		if(this.r != b.r)
			return false;

		if(this.g != b.g)
			return false;
		
		if(this.b != b.b)
			return false;
		
		return true;
	}
	
	public int hashCode() {
		return r * 257*257 + g *257 + b;
	}

	@Override
	public InfoTip ucitaj(SvetovidReader read) {
		int r = read.readInt();
		int g = read.readInt();
		int b = read.readInt();
		
		return new Boja(r, g, b);
	}
	
	public static void main(String[] args) {
		new TestHash(new Boja(), "resources/", "boje").run();
	}
	
	public void setR(int r) { this.r = r; }
	public void setG(int g) { this.g = g; }
	public void setB(int b) { this.b = b; }
}
