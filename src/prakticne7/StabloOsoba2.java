package prakticne7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


class StabloOsoba2 {

    // Tip koji opisuje jedan cvor u stablu
    protected static class Cvor {

        // Sadrzaj cvora
        public Osoba osoba;

        // Levo i desno podstablo
        public Cvor levo;
        public Cvor desno;

        // Jedini konstruktor
        public Cvor(Osoba osoba, Cvor levo, Cvor desno) {
            this.osoba = osoba;
            this.levo = levo;
            this.desno = desno;
        }
    }

    // Stablo ima referencu na korenski cvor
    protected final Cvor koren;

    // Kreiramo prazno stablo
    public StabloOsoba2() {
        koren = null;
    }

    // Kreiramo stablo sa jednom osobom u korenu
    // i praznim levim i desnim podstablom
    public StabloOsoba2(Osoba osoba) {
        koren = new Cvor(osoba, null, null);
    }

    // Specijalan konstruktor koji koriste neki metodi ove klase
    protected StabloOsoba2(Cvor koren) {
        this.koren = koren;
    }
    
    //1)
    public boolean jePrazno() {
    	if(koren == null) {
    		return false;
    	}
    	return true;
    }
    
    //2)
    public boolean postojiElement(Cvor cvor, Osoba element) {
    	if(cvor == null) {
    		return false;
    	}
    	
    	if(Objects.equals(cvor.osoba, element)) {
    		return true;
    	}
    	
    	boolean postojiLevo = postojiElement(cvor.levo, element);
    	if(postojiLevo) {
    		return postojiLevo;
    	}
    	
    	boolean postojiDesno = postojiElement(cvor.desno, element);
    	return postojiDesno;
    	
    }
    
    
    //3)
    public void stampajPreorder(Cvor cvor) {
    	if(cvor == null) {
    		return;
    	}
    	System.out.println(cvor.osoba);
    	stampajPreorder(cvor.levo);
    	stampajPreorder(cvor.desno);
    	
    }
    public void stampajInorder(Cvor cvor) {
    	stampajInorder(cvor.levo);
    	System.out.println(cvor.osoba);
    	stampajInorder(cvor.desno);
    }
    public void stampajPostorder(Cvor cvor) {
    	stampajPostorder(cvor.levo);
    	stampajPostorder(cvor.desno);
    	System.out.println(cvor.osoba);
    }
    
    //4)
    public void stampajListove(Cvor cvor) {
    	if(cvor == null) {
    		return;
    	}
    	
    	if(cvor.levo == null && cvor.desno == null) {
    		System.out.println(cvor.osoba);
    	}
    	
    	stampajListove(cvor.levo);
    	stampajListove(cvor.desno);
    }
    
    //5)
    public StabloOsoba2 pronadji(Osoba element) {
    	Cvor cvor = nadjiCvor(koren, element);
    	if(cvor != null) {
    		return new StabloOsoba2(cvor);
    	}
    	return null;
    	
    }
    
    private Cvor nadjiCvor(Cvor cvor, Osoba element) {
    	if(cvor == null) {
    		return null;
    	}
    	
    	if(Objects.equals(cvor.osoba, element)) {
    		return cvor;
    	}
    	
    	Cvor postojiLevo = nadjiCvor(cvor.levo, element);
    	if(postojiLevo != null) {
    		return postojiLevo;
    	}
    	
    	Cvor postojiDesno = nadjiCvor(cvor.desno, element);
    	return postojiDesno;
    }
    
    //6)
    public List<Osoba> stampajSveIspod(Osoba element) {
    	List<Osoba> osobe = new ArrayList<Osoba>();
    	Cvor cvor = nadjiCvor(koren, element);
    	sviIspod(cvor, osobe);
    	return osobe;
    }
    
    private void sviIspod(Cvor cvor, List<Osoba> osobe) {
    	if(cvor == null) {
    		return;
    	}
    	osobe.add(cvor.osoba);
    	sviIspod(cvor.levo, osobe);
    	sviIspod(cvor.desno, osobe);
    }
    
    //7)
    public boolean ubaci(Osoba roditelj, Osoba potomak, boolean levo) {
    	Cvor cvorRoditelj = nadjiCvor(koren, roditelj);
    	
    	if(cvorRoditelj == null) {
    		return false;
    	}
    	
    	if(levo && cvorRoditelj.levo == null) {
    		cvorRoditelj.levo = new Cvor(potomak, null, null);
    		return true;
    	}
    	
    	if(!levo && cvorRoditelj.desno == null) {
    		cvorRoditelj.desno = new Cvor(potomak, null, null);
    		return true;
    	}
    	
    	return false;
    }
    
    //8)
    /*
     * U klasi StabloOsoba, implementirati metod koji
	pronalazi i vraca roditelja datog elementa. Ako
	je prosledjeni element koren celog stabla
	vratiti null.
     */
    public Osoba roditeljOd(Osoba potomak) {
    	Cvor cvor = roditelj(null, koren, potomak);
    	if(cvor != null) {
    		return cvor.osoba;
    	}
    	return null;
    }
    
    private Cvor roditelj (Cvor roditelj, Cvor cvor, Osoba potomak) {
    	if(cvor == null) {
    		return null;
    	}
    	
    	if(cvor.osoba.equals(potomak)) {
    		return roditelj;
    	}
    	
    	Cvor levo = roditelj(cvor, cvor.levo, potomak);
    	if(cvor.levo == null) {
    		return levo;
    	}
    	
    	Cvor desno = roditelj(cvor, cvor.desno, potomak);
    	if(cvor.desno == null) {
    		return desno;
    	}
    	
    	return null;
    }
    
    //9)
    /*
     * U klasi StabloOsoba, implementirati metod koji
	pronalazi i vraca optimalni element stabla.
	Prilikom pretrage koristiti prosledjeni komparator.
     */
    public Osoba optimalnaOsoba(Comparator<Osoba> komparator, Cvor cvor) {
    	if(cvor == null) {
    		return null;
    	}
    	Osoba optimalnaLevo, optimalnaDesno;
    	Osoba optimalna = cvor.osoba;
	    if(cvor.levo != null) {
	    	optimalnaLevo = optimalnaOsoba(komparator, cvor.levo);
	    	if(cvor.levo != null && komparator.compare(optimalnaLevo, optimalna) > 0) {
	    		optimalna = optimalnaLevo;
	    	}
	    }
	    
	    if(cvor.desno != null) {
	    	optimalnaDesno = optimalnaOsoba(komparator, cvor.desno);
	    	if(cvor.desno != null && komparator.compare(optimalnaDesno, optimalna) > 0) {
	    		optimalna = optimalnaDesno;
	    	}
	    }
	    return optimalna;
    }
    
    
}
