package model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
	
	List<Item> basket;
	private int totalPrice;
	
	public Basket(){
		basket = new ArrayList<Item>();
	}
	
	public void add(Item i){
		totalPrice += i.getPrice();
		basket.add(i);
	}
	
	public void remove(int i){
		Item p = basket.get(i);
		totalPrice -= p.getPrice();
		basket.remove(i);
	}

	public int getBasketSize() {
		return basket.size();
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}
