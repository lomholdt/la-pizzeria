package model;

import java.util.HashMap;
import java.util.Map;


public class Basket {
	
	public Map<Integer, Item> basket = new HashMap<Integer, Item>();
	private int totalPrice;
	private int quantity;
	
	public void add(Item i){
		if(basket.containsKey(i.getId())){
			Item pz = basket.get(i.getId());
			pz.setQuantity(pz.getQuantity() + 1);
		}
		else{
			basket.put(i.getId(), i);		
		}
		totalPrice += i.getPrice();
		quantity++;
	}
	
	public void remove(int id){
		Item p = basket.get(id);
		totalPrice -= p.getPrice()*p.getQuantity();
		basket.remove(id); // or basket.remove(p);
		quantity -= p.getQuantity();
	}

	public int getSize() {
		return quantity;
	}
	
	public Map<Integer, Item> getList(){
		return basket;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
}
