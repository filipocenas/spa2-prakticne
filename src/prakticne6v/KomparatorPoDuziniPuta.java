package prakticne6v;

import java.util.Comparator;

public class KomparatorPoDuziniPuta implements Comparator<Put> {

	@Override
	public int compare(Put o1, Put o2) {
		return o1.getLength() - o2.getLength();
	}

}
