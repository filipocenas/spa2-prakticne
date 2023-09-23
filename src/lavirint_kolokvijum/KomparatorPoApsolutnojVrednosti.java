package lavirint_kolokvijum;

import java.util.Comparator;

public class KomparatorPoApsolutnojVrednosti implements Comparator<Put> {
	@Override
	public int compare(Put p1, Put p2) {
		return p1.apsolutnaVrednost() - p2.apsolutnaVrednost();
	}
}
