package prakticne7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class StabloOsoba {

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
    public StabloOsoba() {
        koren = null;
    }

    // Kreiramo stablo sa jednom osobom u korenu
    // i praznim levim i desnim podstablom
    public StabloOsoba(Osoba osoba) {
        koren = new Cvor(osoba, null, null);
    }

    // Specijalan konstruktor koji koriste neki metodi ove klase
    protected StabloOsoba(Cvor koren) {
        this.koren = koren;
    }

    public boolean jePrazno() {
    	if (koren == null) {
    		return true;
    	}
    	return false;
    }
    
    public boolean postojiElement(Cvor cvor, Osoba element) {
    	if (cvor == null) {
    		return false;
    	}
    	if(Objects.equals(cvor.osoba, element)) {
    		return true;
    	}
    	
    	boolean postojiLevo = postojiElement(cvor.levo, element);
    	if(postojiLevo) {
    		return true;
    	}
    	
    	boolean postojiDesno = postojiElement(cvor.desno, element);
    	return postojiDesno;
    }
    
    public void stampajPreorder(Cvor cvor) {
    	if (cvor == null) {
    		return;
    	}
    	
    	System.out.println(cvor.osoba);
    	stampajPreorder(cvor.levo);
    	stampajPreorder(cvor.desno);
        	
    }
 
    public void stampajInorder(Cvor cvor) {
    	
    	if(cvor == null) {
    		return;
    	}
    	
    	stampajInorder(cvor.levo);
    	System.out.println(cvor.osoba);
    	stampajInorder(cvor.desno);
    	
    }
    
    public void stampajPostorder(Cvor cvor) {
    	if (cvor == null) {
    		return;
    	}
    	
    	stampajPostorder(cvor.levo);
    	stampajPostorder(cvor.desno);
    	System.out.println(cvor.osoba);
    }
    
    public void stampajListove(Cvor cvor) {
    	if (cvor == null) {
    		return;
    	}
    	
    	if(cvor.levo == null && cvor.desno == null) {
    		System.out.println(cvor.osoba);
    	}
    	
    	stampajListove(cvor.levo);
    	stampajListove(cvor.desno);
    }
    
    public StabloOsoba pronadji(Osoba element) {
    	Cvor cvor = pronadjiCvor(koren, element);
    	if(cvor != null) {
    		return new StabloOsoba(cvor);
    	}
    	return null;
    }
    
    private Cvor pronadjiCvor(Cvor cvor,Osoba element) {
    	if (cvor == null) {
    		return null;
    	}
    	if(Objects.equals(cvor.osoba, element)) {
    		return cvor;
    	}
    	
    	Cvor postojiLevo = pronadjiCvor(cvor.levo, element);
    	if(postojiLevo != null) {
    		return postojiLevo;
    	}
    	
    	Cvor postojiDesno = pronadjiCvor(cvor.desno, element);
    	return postojiDesno;
    
    }
    
    public List<Osoba> stampajSveIspod(Osoba element) {
    	List<Osoba> osobe = new ArrayList<Osoba>();
    	Cvor cvor = pronadjiCvor(koren, element);
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
    
    public boolean ubaci(Osoba roditelj, Osoba potomak, boolean levo) {
    	Cvor cvorRoditelj = pronadjiCvor(koren, roditelj);
    	if (cvorRoditelj == null) {
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
    
    public Osoba roditeljOd(Osoba potomak) {    	
    	Cvor cvor = roditelj(null, koren, potomak);
    	if(cvor != null) {
    		return cvor.osoba;
    	}
    	
    	return null;
    }
    
    private Cvor roditelj(Cvor roditelj, Cvor cvor, Osoba potomak) {
    	if(cvor == null) {
    		return null;
    	}
    	
    	if(Objects.equals(cvor.osoba, potomak)) {
    		return roditelj;
    	}
    	
    	Cvor levo = roditelj(cvor, cvor.levo, potomak);
    	if(levo != null) {
    		return levo;
    	}
    	
    	Cvor desno = roditelj(cvor, cvor.desno, potomak);
    	if(desno != null) {
    		return desno;
    	}
    	
    	return null;
    }
    
    public Osoba optimalnaOsoba(Comparator<Osoba> komparator, Cvor cvor) {
    	if (cvor == null) {
    		return null;
    	}
    	
    	Osoba optimalnaLevo, optimalnaDesno;
    	Osoba optimalna = cvor.osoba;
    	
    	if(cvor.levo != null ) {
    		optimalnaLevo = optimalnaOsoba(komparator, cvor.levo);
    		if(optimalnaLevo != null && komparator.compare(optimalnaLevo, optimalna) > 0) {
    			optimalna = optimalnaLevo;
    		}
    	}
    	
    	if(cvor.desno != null) {
    		optimalnaDesno = optimalnaOsoba(komparator, cvor.desno);
    		if(optimalnaDesno != null && komparator.compare(optimalnaDesno, optimalna) > 0) {
    			optimalna = optimalnaDesno;
    		}
    	}
    	return optimalna;
    }
    
    public double prosecnaPlata() {
    	Cuvanje cuvanje = new Cuvanje(0, 0);
    	prosecnaPlata(koren, cuvanje);
    	if(koren != null) {
    		return cuvanje.getSumaPlata()/cuvanje.getBrojOsoba();    		
    	}
    	return 0.0;
    }
    
    public void prosecnaPlata(Cvor cvor, Cuvanje cuvanje) {
    	if(cvor == null) {
    		return;
    	}
    	
    	cuvanje.setBrojOsoba(cuvanje.getBrojOsoba() + 1);
    	cuvanje.setSumaPlata(cuvanje.getSumaPlata() + cvor.osoba.getPlata());
    	prosecnaPlata(cvor.levo, cuvanje);
    	prosecnaPlata(cvor.desno, cuvanje);
    	
    }
    
    public List<Osoba> sviNadredjeni(Osoba o) {
    	List<Osoba> nadredjeni = new ArrayList<>();
    	sviNadredjeni(koren, o, nadredjeni);
    	Collections.reverse(nadredjeni); //ovo ce obrnuti listu
    	return nadredjeni;
    }
    
    private boolean sviNadredjeni(Cvor cvor, Osoba o, List<Osoba> nadredjeni) {
    	if(cvor == null) {
    		return  false;
    	}
    	
    	if(Objects.equals(cvor.osoba, o)) {
    		return true;
    	}
    	
    	boolean levo = sviNadredjeni(cvor.levo, o, nadredjeni);
    	boolean desno = false;
    	if(!levo) {
    		desno = sviNadredjeni(cvor.desno, o, nadredjeni);    		
    	}
    	if(levo || desno) {
    		nadredjeni.add(cvor.osoba);
    	}
    	
    	return levo || desno;
    }
    
    










}