package lavirint_kolokvijum2;

import java.util.LinkedList;

public class Put {

	static class Polje {

		private int x, y, val;

		public Polje(int x, int y, int val) {
			super();
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

	public LinkedList<Polje> getLista() {
		return lista;
	}

	public Put() {
		this.lista = new LinkedList<Polje>();
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

	public int velicina() {
		return lista.size();
	}

	public Put kopiraj() {
		return new Put(new LinkedList<Polje>(lista));
	}

	public int najvrednijiPut() {
		int brojac = 0;
		for (Polje p : lista) {
			brojac += p.val;
		}
		return brojac;
	}

	@Override
	public String toString() {
		String test = "";
		if (!lista.isEmpty()) {
			test += lista.get(0).val;
			for (int i = 1; i < lista.size(); i++) {
				test += "->" + lista.get(i).val;
			}
		}
		return test;
	}

}
