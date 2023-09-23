package prakticne5;

import java.util.ArrayList;
import java.util.List;

import org.svetovid.Svetovid;

public class PostojanjePutaV {
	
	public static final int ZID = -11; 
	public static final int IZLAZ = -99;
	public static final int RUPA = -1;
	public static int [][] lavirint;
	public static boolean [][] posecenost;
	public static int sirina, visina;
	
	public static boolean ucitaj(String fajl) {
		if(!Svetovid.testIn(fajl))
			return false;
		sirina = Svetovid.in(fajl).readInt();
		visina = Svetovid.in(fajl).readInt();
		lavirint = new int [sirina][visina];
		posecenost = new boolean[sirina][visina];
		for(int j = 0; j < visina; j++) {
			for(int i = 0; i < sirina; i++) {
				lavirint[i][j] = Svetovid.in(fajl).readInt();
			}
		}
		Svetovid.closeIn(fajl);
		return true;
	}
	
	public static void stampaj() {
		System.out.println("Lavirint: ");
		System.out.println(sirina + " " + visina);
		for(int j = 0; j < visina; j++) {
			for(int i = 0; i < sirina; i++) {
				System.out.printf("%1$5d", lavirint[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean postojiPut(int x, int y, List<String> resenje, boolean rupa) {
		if(x < 0 || x >= sirina || y < 0 || y >= visina)
			return false;
		
		if(posecenost[x][y])
			return false;
		
		if(lavirint[x][y] == ZID)
			return false;
		
		if(lavirint[x][y] == IZLAZ)
			return true;
		
		if(rupa && lavirint[x][y] == RUPA)
			return false;
		
		posecenost[x][y] = true;
		resenje.add(x + " " + y);
		
		if(postojiPut(x + 1, y, resenje, lavirint[x][y] == RUPA))
			return true;
		if(postojiPut(x - 1, y, resenje, lavirint[x][y] == RUPA))
			return true;
		if(postojiPut(x, y + 1, resenje, lavirint[x][y] == RUPA))
			return true;
		if(postojiPut(x, y - 1, resenje, lavirint[x][y] == RUPA))
			return true;
		
		resenje.remove(x + " " + y);
		posecenost[x][y] = false;
		return false;
	}
	
	public static void main(String [] args) {
		String inFajl = Svetovid.in.readLine("Unesite naziv fajla: ");
		if(inFajl.equalsIgnoreCase("")) {
			inFajl = "resources/lav1.txt";
		}
		if(ucitaj(inFajl)) {
			
			List<String> resenje = new ArrayList<String>();
			System.out.println("Unesite pocetnu tacku: ");
			int x = Svetovid.in.readInt("Unesite x koordinatu: ");
			int y = Svetovid.in.readInt("Unesite y koordinatu: ");
			if(postojiPut(x, y, resenje, false)) {
				stampaj();
				System.out.println("Postoji put :)");
				System.out.println("Resenje: " + resenje);
			} else {
				System.out.println("Ne postoji put :(");
			}
		}
	}

}