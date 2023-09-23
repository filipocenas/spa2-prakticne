package prakticne6;

import java.util.ArrayList;

public class Put {
	private ArrayList<Polje> polja;

	public Put() {
		polja = new ArrayList<Polje>();
	}
	
	public void dodaj(Polje polje) {
		polja.add(polje);
	}
	
	public int getLength() {
		return polja.size();
	}
	
	public void ukloniPoslednji() {
		polja.remove(getLength() - 1);
	}
	
	public Put kopija() {
		Put put = new Put();
		for(Polje p : polja) {
			put.dodaj(p);
		}
		return put;
	}
	
	public int getNajvrednijeBlago() {
	//	Polje maxBlago = polja.stream().max(Comparator.comparing(Polje :: getV).orElseThrow(NoSuchElementException :: new);
	//	return maxBlago.getV();
		int max = Integer.MIN_VALUE;
		for(Polje p : polja) {
			if(p.getV() >= max) {
				max = p.getV();
			}
		}
		return max;
	}
	
	public int getBrojBlaga() {
		long brojPolja = polja.stream().filter(x -> x.getV() > 0).count();
		return (int)brojPolja;
		//int count = 0;
		//for(Polje p : polja) {
		//	if(p.getV() > 0) {
		//		count++;
		//	}
		//}
		//return count;
	}
	
	public int getNajduziPutSaBlagom() {
		int brojac = 0;
		int rez = 0;
		for(Polje p : polja) {
			if(p.getV() == 0) {
				brojac = 0;
			} else if(p.getV() > 0) {
				brojac++;
				rez = Math.max(rez, brojac);
			}
		}
		return rez;
	}
	
	public boolean imaViseOdTriBlaga() {
		int brojac = 0;
		int rez = 0;
		boolean imaViseOdTri = false;
		for(int i = 0; i < polja.size() && !imaViseOdTri; i++) {
			Polje p = polja.get(i);
			if(p.getV() == 0) {
				rez = 0;
			} else if(p.getV() > 0) {
				brojac++;
				rez = Math.max(rez, brojac);
				if(rez > 3) {
					imaViseOdTri = true;
				}
			}
		}
		return imaViseOdTri;
	}
	
	@Override
	public String toString() {
		return "Put [" + polja + "]";
	}
	
	
	
}
