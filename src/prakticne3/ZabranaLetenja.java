package prakticne3;


import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;

public class ZabranaLetenja {
	public static void main(String[] args) {
		StatSet<Putnik> putniciSaZabranom  = ucitajPutnike("resources/noflightlist.txt");
		
		putniciSaZabranom.printStats();
		//pojedinacnaProvera(putniciSaZabranom);
//		masovnaProvera(putniciSaZabranom);
	}
	
	@SuppressWarnings("unused")
	private static void masovnaProvera(StatSet<Putnik> putniciSaZabranom) {
		StatSet<Putnik> testPutnici = ucitajPutnike("resources/flight.txt");
		
		testPutnici.retainAll(putniciSaZabranom);
		
		for(Putnik p : testPutnici) {
			Svetovid.out.println(p + " ne sme da leti");
		}
	}

	@SuppressWarnings("unused")
	private static void pojedinacnaProvera(StatSet<Putnik> putniciSaZabranom) {
		String prezime = Svetovid.in.readLine();
		String ime = Svetovid.in.readLine();
		int godRodj = Svetovid.in.readInt();
		
		Putnik p = new Putnik(ime, prezime, godRodj);
		
		
		if(putniciSaZabranom.contains(p)) {
			System.out.println(p + " ne sme da leti");
		} else {
			System.out.println(p + " sme da leti");
		}
	}

	private static StatSet<Putnik> ucitajPutnike(String inFile) {
		StatSet<Putnik> setToReturn = new StatSet<>();
		if(!Svetovid.testIn(inFile))
			return null;
		SvetovidReader in = Svetovid.in(inFile);
		
		int brPutnika = in.readInt();
		for(int i = 0; i < brPutnika; i++) {
			String prezime = in.readLine();
			String ime = in.readLine();
			int godRodj = in.readInt();
			
			setToReturn.add(new Putnik(ime, prezime, godRodj));
		}
			
		in.close();	
		return setToReturn;
	}
}

//TODO 46:53
