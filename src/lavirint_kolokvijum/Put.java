package lavirint_kolokvijum;

import java.util.LinkedList;

public class Put {

	static class Polje {

		private int x, y, val;

		public Polje(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return "Polje [x=" + x + ", y=" + y + ", val=" + val + "]";
		}

	}

	private LinkedList<Polje> lista;

	public Put() {
		this.lista = new LinkedList<>();
	}

	public Put(LinkedList<Polje> lista) {
		this.lista = lista;
	}

	public void dodaj(Polje p) {
		lista.add(p);
	}

	public void obrisi() {
		lista.removeLast();
	}

	public Polje getPolje(int poz) {
		return lista.get(poz);
	}

	public int duzina() {
		return lista.size();
	}

	public Put kopiraj() {
		return new Put(new LinkedList<Polje>(lista));
	}

	public int apsolutnaVrednost() {
		int brojac = 0;
		for (Polje p : lista) {
			brojac += Math.abs(p.val);
		}
		return brojac;
	}

	@Override
	public String toString() {
		String test = "";
		if (!lista.isEmpty()) {
			test += lista.get(0).val;
			for (Polje p : lista) {
				test += "->" + p.val;
			}
		}
		return test;
	}

}
