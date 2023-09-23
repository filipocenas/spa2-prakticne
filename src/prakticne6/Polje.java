package prakticne6;

public class Polje {
	private int x, y, v;

	public Polje(int x, int y, int v) {
		this.x = x;
		this.y = y;
		this.v = v;
	}

	public Polje() {
	}


	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public int getV() { return v; }
	public void setV(int v) { this.v = v; }
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + v + ")";
	}
	
}
