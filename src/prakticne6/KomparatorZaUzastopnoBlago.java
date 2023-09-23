package prakticne6;

import java.util.Comparator;

public class KomparatorZaUzastopnoBlago implements Comparator<Put> {

	@Override
	public int compare(Put o1, Put o2) {
		return o2.getNajduziPutSaBlagom() - o1.getNajduziPutSaBlagom();
	}

}
