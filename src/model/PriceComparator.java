package model;

import java.util.Comparator;

public class PriceComparator implements Comparator<Pizza> {
	
	    public int compare(Pizza a, Pizza b) {
	    	System.out.println("Testing: " + a.getPrice() + b.getPrice());
	    	return a.getPrice() > b.getPrice() ? 1 : a.getPrice() < b.getPrice() ? -1 : 0;
	    }
}
