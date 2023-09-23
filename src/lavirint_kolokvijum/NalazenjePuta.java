package lavirint_kolokvijum;

import java.util.Comparator;
import java.util.LinkedList;

import org.svetovid.Svetovid;

public class NalazenjePuta {

	private static final int IZLAZ = -9;

	private int visina, sirina;
	private int[][] mat;
	private boolean[][] pos;
	private static final Put put = new Put();
	private Put najboljiPut = null;
	private boolean[][] najboljiPos;

	private LinkedList<Put> putevi = new LinkedList<>();
	private int K, PS, PV;

	public LinkedList<Put> getPutevi() {
		return putevi;
	}

	public int getK() {
		return K;
	}

	public int getPS() {
		return PS;
	}

	public int getPV() {
		return PV;
	}

	public void ucitajFajl(String fajl) {
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		mat = new int[visina][sirina];
		pos = new boolean[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				mat[i][j] = Svetovid.in(fajl).readInt();
			}
		}
		PS = Svetovid.in(fajl).readInt();
		PV = Svetovid.in(fajl).readInt();
		K = Svetovid.in(fajl).readInt();

		Svetovid.closeIn(fajl);
	}

	public static void main(String[] args) {
		NalazenjePuta np = new NalazenjePuta();

		String fajl = Svetovid.in.readLine("Unesite naziv fajla: ");
		System.out.println();

		if (!Svetovid.testIn(fajl)) {
			throw new RuntimeException("Nemoguce ucitati fajl.");
		}

		np.ucitajFajl(fajl);
		np.ispisi();

		np.backtrack(np.getPV(), np.getPS(), put, new KomparatorPoApsolutnojVrednosti(),
				np.vrednostMatrice(np.getPV(), np.getPS()));

		if (!np.getPutevi().isEmpty()) {
			System.out.println("Pronadjeno " + np.getPutevi().size() + " puta, jedan od njih:\n");
			np.ispisiPut(np.getPutevi().get(0));
		} else {
			System.out.println("Ne postoje putevi duzine " + np.getK());
		}

		System.out.println();
		np.ispisiNajboljiPut();

	}

	public void backtrack(int x, int y, Put p, Comparator<Put> c, int sacuvanaPoz) {
		if (x < 0 || y < 0 || x > visina - 1 || y > sirina - 1) {
			return;
		}

		if (pos[x][y]) {
			return;
		}

		if (mat[x][y] == IZLAZ) {
			pos[x][y] = true;
			p.dodaj(new Put.Polje(x, y, mat[x][y]));

			if (p.duzina() >= K) {
				putevi.add(p.kopiraj());
				if (najboljiPut == null || c.compare(p, najboljiPut) > 0) {
					najboljiPut = p.kopiraj();
					najboljiPos = kopiraj();
				}
			}

			pos[x][y] = false;
			p.obrisi();
			return;
		}

		if ((mat[x][y] + sacuvanaPoz) % 2 != 0) {
			return;
		}

		pos[x][y] = true;
		p.dodaj(new Put.Polje(x, y, mat[x][y]));

		backtrack(x + 1, y, p, c, mat[x][y]);
		backtrack(x - 1, y, p, c, mat[x][y]);
		backtrack(x, y + 1, p, c, mat[x][y]);
		backtrack(x, y - 1, p, c, mat[x][y]);

		pos[x][y] = false;
		p.obrisi();
		return;
	}

	public int vrednostMatrice(int i, int j) {
		return mat[i][j];
	}

	public void ispisiNajboljiPut() {
		if (najboljiPut == null) {
			System.out.println("Nemoguce naci optimalan put");
			return;
		}
		System.out.println("Najbolji put\n");
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				System.out.printf("%1$5d", najboljiPos[i][j] ? 1 : 0);
			}
			System.out.println();
		}
	}

	public void ispisiPut(Put p) {
		int matrica[][] = new int[visina][sirina];
		for (int i = 0; i < p.duzina(); i++) {
			matrica[p.getPolje(i).getX()][p.getPolje(i).getY()] = 1;
		}
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				System.out.printf("%1$5d", matrica[i][j]);
			}
			System.out.println();
		}
	}

	public void ispisi() {
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				System.out.printf("%1$5d", mat[i][j]);
			}
			System.out.println();
		}
	}

	private boolean[][] kopiraj() {
		boolean matrica[][] = new boolean[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				matrica[i][j] = pos[i][j];
			}
		}
		return matrica;
	}

}
