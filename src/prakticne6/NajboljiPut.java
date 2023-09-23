package prakticne6;

import java.util.Comparator;
import org.svetovid.Svetovid;

public class NajboljiPut {
	public static int ZID = -11;
	public static int IZLAZ = -99;
	
	private static int sirina, visina;
	
	private static int [][] lavirint;
	private static boolean [][] posecenost;
	
	Put optimalniPut;
	
	//metod za ucitavanje
	public static boolean ucitaj(String inFajl) {
		if(!Svetovid.testIn(inFajl)) {
			return false;
		}
		
		sirina = Svetovid.in(inFajl).readInt();
		visina = Svetovid.in(inFajl).readInt();
		lavirint = new int[sirina][visina];
		posecenost = new boolean[sirina][visina];
		
		for(int j = 0; j < visina; j++) {
			for(int i = 0; i < sirina; i++) {
				lavirint[i][j] = Svetovid.in(inFajl).readInt();
			}
		}
		Svetovid.closeIn(inFajl);
		return true;
	}
	
	//metod za stampanje
	public static void stampaj() {
		System.out.println(visina + " " + sirina);
		System.out.println("Lavirint: ");
		for(int j = 0; j < visina; j++) {
			for(int i = 0; i < sirina; i++) {
				System.out.printf("%1$5d", lavirint[i][j]);
			}
			System.out.println();
		}
	}
	
	private void optimalniPut(int x, int y, Put p, Comparator<Put> comparator, int brojPuteva, int viseOdTriBlaga) {
		if(x < 0 || x >= sirina || y < 0 || y >= visina)
			return;
		if(posecenost[x][y])
			return;		
		if(lavirint[x][y] == ZID)
			return;
		if(lavirint[x][y] == IZLAZ) {
			p.dodaj(new Polje(x, y, lavirint[x][y]));
			brojPuteva++;
			if(p.imaViseOdTriBlaga()) {
				viseOdTriBlaga++;
			}
			if(optimalniPut == null || comparator.compare(p, optimalniPut) < 0) {
				optimalniPut = p.kopija();
			}
			p.ukloniPoslednji();
			return;
		}
		
		posecenost[x][y] = true;
		p.dodaj(new Polje(x, y, lavirint[x][y]));
		
		optimalniPut(x + 1, y, p, comparator, 0, 0);
		optimalniPut(x - 1, y, p, comparator, 0, 0);
		optimalniPut(x, y + 1, p, comparator, 0, 0);
		optimalniPut(x, y - 1, p, comparator, 0, 0);
		
		posecenost[x][y] = false;
		p.ukloniPoslednji();
	}
	
	public Put najkraciPut(int x, int y) {
		Put put = new Put();
		optimalniPut(x, y, put, new KomparatorPoDuziniPuta(), 0, 0);
		return optimalniPut;
	}
	
	public Put putSaNajvecimBrojemBlaga(int x, int y) {
		Put put = new Put();
		optimalniPut(x, y, put, new KomparatorPoBrojuBlaga(), 0, 0);
		return optimalniPut;
	}
	
	public Put saNajvrednijimBlagom(int x, int y) {
		Put put = new Put();
		optimalniPut(x, y, put, new KomparatorZaNajvrednijeBlago(), 0, 0);
		return optimalniPut;
	}
	
	public Put saUzastopnimBlagom(int x, int y) {
		Put put = new Put();
		optimalniPut(x, y, put, new KomparatorZaUzastopnoBlago(), 0, 0);
		return optimalniPut;
	}
	
	
	
	
	public static void main(String [] args) {
		String fajl = Svetovid.in.readLine("Unesite ime fajla: ");
		if(fajl.equalsIgnoreCase("")) {
			fajl = "resources/blago1.txt";
		}
		if(ucitaj(fajl)) {
			stampaj();
			NajboljiPut np = new NajboljiPut();
			Put najkraciPut = np.najkraciPut(0, 0);
			if(najkraciPut != null) {
				System.out.println(najkraciPut);
			}
			
			Put putSaNajvecimBrojemBlaga = np.putSaNajvecimBrojemBlaga(0, 0);
			if(putSaNajvecimBrojemBlaga != null) {
				System.out.println(putSaNajvecimBrojemBlaga);
			}
			
			Put saNajvrednijimBlagom = np.saNajvrednijimBlagom(0, 0);
			if(saNajvrednijimBlagom != null) {
				System.out.println(saNajvrednijimBlagom);
			}
			
			Put saUzastopnimBlagom = np.saUzastopnimBlagom(0, 0);
			if(saUzastopnimBlagom != null) {
				System.out.println(saUzastopnimBlagom);
			}
		}
	}
	
}
