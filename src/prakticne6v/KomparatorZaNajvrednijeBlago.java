package prakticne6v;

import java.util.Comparator;

public class KomparatorZaNajvrednijeBlago implements Comparator<Put> {

	@Override
	public int compare(Put o1, Put o2) {
		return o2.getNajvrednijeBlago() - o1.getNajvrednijeBlago();
	}

}
