package lavirint_kolokvijum2;

import java.util.Comparator;
import java.util.LinkedList;

import org.svetovid.Svetovid;

public class NalazenjePuta {

	public static final int IZLAZ = -1;
	public static LinkedList<Put> putevi = new LinkedList<>();

	private static int RED;
	private static int visina, sirina;
	private static int[][] mat;
	private static boolean[][] pos;
	private static final Put put = new Put();
	private static Put najboljiPut = null;
	private static boolean[][] najboljiPos;

	public static void ucitajFajl(String fajl) {
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		mat = new int[visina][sirina];
		pos = new boolean[visina][sirina];

		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				mat[i][j] = Svetovid.in(fajl).readInt();
				if(mat[i][j] == IZLAZ) {
					RED = i;
				}
			}
		}

		Svetovid.closeIn(fajl);
	}

	public static void main(String[] args) {
		String fajl = Svetovid.in.readLine("Unesite naziv fajla: ");
		System.out.println();

		if (!Svetovid.testIn(fajl)) {
			throw new RuntimeException("Nemoguce ucitati fajl.");
		}

		ucitajFajl(fajl);
		ispisi();
		System.out.println();

		backtrack(0, 0, put, new KomparatorPoVrednostiPuta(), vrednostMatrice(0, 0), 0, true);

		if (!putevi.isEmpty()) {
			System.out.println("Pronadjeno " + putevi.size() + " puta. Jedan od njih: \n");
			ispisiPut(putevi.get(0));
		} else {
			System.out.println("Nema resenja.");
		}

		ispisiNajboljiPut();
	}

	public static int vrednostMatrice(int i, int j) {
		return mat[i][j];
	}

	public static void ispisi() {
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				System.out.printf("%1$5d", mat[i][j]);
			}
			System.out.println();
		}
	}

	public static void ispisiPut(Put p) {
		int matrica[][] = new int[visina][sirina];
		for (int i = 0; i < p.getLista().size(); i++) {
			matrica[p.getLista().get(i).getX()][p.getLista().get(i).getY()] = 1;
		}
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				System.out.printf("%1$5d", matrica[i][j]);
			}
			System.out.println();
		}
	}

	public static void ispisiNajboljiPut() {
		if (najboljiPos == null) {
			System.out.println("Ne postoji najbolji put");
			return;
		}
		System.out.println("\nNajbolji put\n");
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				System.out.printf("%1$5d", najboljiPos[i][j] ? 1 : 0);
			}
			System.out.println();
		}
	}

	private static boolean[][] kopiraj() {
		boolean matrica[][] = new boolean[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				matrica[i][j] = pos[i][j];
			}
		}
		return matrica;
	}

	public static void backtrack(int x, int y, Put p, Comparator<Put> c, int sacuvanaPoz, int brojac, boolean provera) {
		if (x < 0 || y < 0 || x > RED || y > sirina - 1) { //x > visina - 1
			return;
		}

		if (pos[x][y]) {
			return;
		}

		if (mat[x][y] > sacuvanaPoz) {
			brojac++;
		}

		if (brojac == 1) {
			provera = false;
		}

		if (brojac > 1) {
			return;
		}

		if (mat[x][y] == IZLAZ) {
			pos[x][y] = true;
			p.dodaj(new Put.Polje(x, y, mat[x][y]));

			putevi.add(p.kopiraj());

			if (provera) {
				if (najboljiPut == null || c.compare(p, najboljiPut) < 0) {
					najboljiPut = p.kopiraj();
					najboljiPos = kopiraj();
				}
			}

			pos[x][y] = false;
			p.obrisi();
			return;
		}

		pos[x][y] = true;
		p.dodaj(new Put.Polje(x, y, mat[x][y]));

		backtrack(x + 1, y, p, c, mat[x][y], brojac, provera);
		backtrack(x - 1, y, p, c, mat[x][y], brojac, provera);
		backtrack(x, y + 1, p, c, mat[x][y], brojac, provera);
		backtrack(x, y - 1, p, c, mat[x][y], brojac, provera);

		pos[x][y] = false;
		p.obrisi();
		return;
	}

}
