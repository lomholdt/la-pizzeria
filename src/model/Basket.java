package model;

import java.util.Map;
import java.util.TreeMap;


public class Basket {
	
	public Map<Integer, Item> basket = new TreeMap<Integer, Item>();
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
	
	public boolean contains(int id){
		return basket.containsKey(id);
	}
	
	public void addOne(int id){
		Item i = basket.get(id);
		if(i.getQuantity() > 0){
			i.setQuantity(i.getQuantity() + 1);
			quantity++;
			totalPrice += i.getPrice();
		}
	}
	
	public void removeOne(int id){
		Item i = basket.get(id);
		if(i.getQuantity() > 1){
			i.setQuantity(i.getQuantity() - 1);
			quantity--;		
			totalPrice -= i.getPrice();
		}
	}
}
