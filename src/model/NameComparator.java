package model;

import java.util.Comparator;

public class NameComparator implements Comparator<Pizza>{
	
	    public int compare(Pizza o1, Pizza o2) {
	        return o1.getName().compareTo(o2.getName());
	    }
}
