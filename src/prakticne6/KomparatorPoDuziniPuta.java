package prakticne6;

import java.util.Comparator;

public class KomparatorPoDuziniPuta implements Comparator<Put> {

	@Override
	public int compare(Put p1, Put p2) {
		return p1.getLength() - p2.getLength();
	}
	

}
