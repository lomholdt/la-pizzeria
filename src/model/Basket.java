package model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
	
	public List<Item> basket = new ArrayList<Item>();
	private int totalPrice;
	
	public void add(Item i){
		totalPrice += i.getPrice();
		basket.add(i);
	}
	
	public void remove(int i){
		Item p = basket.get(i);
		totalPrice -= p.getPrice();
		basket.remove(i);
	}

	public int getSize() {
		return basket.size();
	}
	
	public List<Item> getList(){
		return basket;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}
