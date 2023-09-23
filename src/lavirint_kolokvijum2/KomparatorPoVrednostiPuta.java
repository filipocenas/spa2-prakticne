package lavirint_kolokvijum2;

import java.util.Comparator;

public class KomparatorPoVrednostiPuta implements Comparator<Put> {
	@Override
	public int compare(Put p1, Put p2) {
		return p1.velicina() - p2.velicina();
	}
}
