package StockManagerment;

public class Stock {

	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Stock(int proid, String proname, int stock, int price) {
		this.proid = proid;
		this.proname = proname;
		this.stock = stock;
		this.price = price;
	}
	
	public Stock(String proname, int stock, int price) {
		
		this.proname = proname;
		this.stock = stock;
		this.price = price;
	}
	private int proid;
	private String proname;
	private int stock;
	private int price;
}
