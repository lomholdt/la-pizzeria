package model;

import java.util.Comparator;

public class PriceComparator implements Comparator<Pizza> {
	
	    public int compare(Pizza a, Pizza b) {
	    	return a.getPrice() > b.getPrice() ? 1 : a.getPrice() < b.getPrice() ? -1 : 0;
	    }
}
