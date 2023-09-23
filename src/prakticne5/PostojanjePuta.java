package prakticne5;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class PostojanjePuta {

	public static final int ZID = -11;
	public static final int IZLAZ = -99;
	public static final int RUPA = -1;
	
	
	public static int visina, sirina;
	
	private static int [][] lavirint;
	private static boolean [][] posecenost;
	
	public static void main(String args[]) {
		System.out.println("Unesite naziv fajla. ");
		String imef = Svetovid.in.readLine();
		//pravimo sta ce se po default ucitati
		if(imef.equalsIgnoreCase("")) {
			imef = "resources/lav1.txt";
		}
		
		if(ucitaj(imef)) {
			//prosirenje broj 1
			System.out.println("Unesite zeljenu pocetnu tacku.");
			int x = Svetovid.in.readInt("Koordinata x: ");
			int y = Svetovid.in.readInt("Koordinata y: ");
			List<String> resenje = new ArrayList<String>();	
			System.out.println();
			stampaj();
			if(postojiPut(x, y, resenje, false)) {
				System.out.println("Postoji put :)");
				System.out.println("Resenje: ");
				System.out.println(resenje);
			} else {
				System.out.println("Ne postoji put :(");
			}
		}	
	}
	
	//metod za ucitavanje matrice
	public static boolean ucitaj(String inFajl) {
		//proveravamo da li je fajl dostupan
		if(!Svetovid.testIn(inFajl)) {
			return false;
		}
		// odredjujemo vrednosti za visinu sirinu i pravimo dve matrice
		sirina = Svetovid.in(inFajl).readInt();
		visina = Svetovid.in(inFajl).readInt();
		lavirint = new int [sirina][visina];
		posecenost = new boolean [sirina][visina];
		
		//popunjavamo matricu lavirint
		for(int j = 0; j < visina; j++) {
			for (int i = 0; i < sirina; i++) {
				lavirint[i][j] = Svetovid.in(inFajl).readInt();
			}
		}
		Svetovid.closeIn(inFajl);
		return true;
		
	}
	
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
	
	//rekurzivni metod za proveru postojanja puta
	public static boolean postojiPut(int x, int y, List <String> resenje, boolean rupa) {
		if(x < 0 || x >= sirina || y < 0 || y >= visina) {
			return false;
		}
		if(posecenost[x][y]) {
			return false;
		}
		if(lavirint[x][y] == ZID) {
			return false;
		}
		if(lavirint[x][y] == IZLAZ) {
			return true;
		}
		
		if(rupa && lavirint[x][y] == RUPA) {
			return false;
		}
		posecenost[x][y] = true;
		resenje.add(x + "-" + y);
		
		if(postojiPut(x+1, y, resenje, lavirint[x][y] == RUPA)) {
			return true;
		}
		if(postojiPut(x-1, y, resenje, lavirint[x][y] == RUPA)) {
			return true;
		}
		if(postojiPut(x, y+1, resenje, lavirint[x][y] == RUPA)) {
			return true;
		}
		if(postojiPut(x, y-1, resenje, lavirint[x][y] == RUPA)) {
			return true;
		}
		resenje.remove(x + "-" + y);
		posecenost[x][y] = false;
		return false;
	}
	
	
	
}
