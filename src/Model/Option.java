package Model;

public class Option implements java.io.Serializable{
	private String name;
	private float price;
	
	Option(){}
	Option(String name){
		this.name = name;
		this.price = 0;
	}
	Option(String name, float price){
		this.name = name;
		this.price = price;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected float getPrice() {
		return price;
	}
	protected void setPrice(float price) {
		this.price = price;
	}
	protected void print(){
		System.out.printf("Name: %s", name);
		System.out.printf("Price: %d", price);
	}
}
