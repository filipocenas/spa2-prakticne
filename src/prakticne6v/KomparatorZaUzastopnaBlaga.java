package prakticne6v;

import java.util.Comparator;

public class KomparatorZaUzastopnaBlaga implements Comparator<Put> {

	@Override
	public int compare(Put o1, Put o2) {
		return o2.getNajviseUzastopnihBlaga() - o1.getNajviseUzastopnihBlaga();
	}

}
