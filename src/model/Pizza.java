package model;

public class Pizza implements Item {
	
	private int id;
	private String name;
	private int price;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setId(int pizzaid){
		this.id = pizzaid;
	}

	public int getId() {
		return id;
	}
	
}
