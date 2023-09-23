package prakticne6;

import java.util.Comparator;

public class KomparatorPoBrojuBlaga implements Comparator<Put> {

	@Override
	public int compare(Put o1, Put o2) {
		return o2.getBrojBlaga() - o1.getBrojBlaga();
	}

}
