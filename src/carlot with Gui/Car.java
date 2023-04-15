import java.util.Scanner;

public class Car {
	private String id;
	private int mileage;
	private int mpg;
	private double cost;
	private double salesPrice;
	private boolean sold;
	private double priceSold;
	private double profit;
	
	public Car() {
		this.setId(null);
		this.setMileage(-1);
		this.setMpg(-1);
		this.setCost(-10);
		this.setSalesPrice(-10);
		this.setSold(false);
		this.setPriceSold(-1);
		this.setProfit(-1);
	}
	
	public Car(String id, int mileage, int mpg, double cost, double salesPrice) {
		this();
		this.setId(id);
		this.setMileage(mileage);
		this.setMpg(mpg);
		this.setCost(cost);
		this.setSalesPrice(salesPrice);
	}
	
	public Car(String aCSVString) {
		this();
		Scanner s = new Scanner(aCSVString);
		s.useDelimiter("[,]{1}[\\s]*");
		this.setId(s.next());
		this.setMileage(s.nextInt());
		this.setMpg(s.nextInt());
		this.setSold(s.nextBoolean());
		this.setCost(s.nextDouble());
		this.setSalesPrice(s.nextDouble());
		this.setPriceSold(s.nextDouble());
		this.setProfit(s.nextDouble());
		s.close();
	}
	
	public String toString() {
		
		String printable = String.format("Car: %-20s, Mileage: %6d, MPG: %3d, Sold: %4s, Cost: $%5.2f, Selling price: $%6.2f",
				this.getId(), this.getMileage(), this.getMpg(), (this.isSold() ? "Yes" : " No"), this.getCost(), this.getSalesPrice());
		
		printable = printable + (this.isSold() ? 
				String.format(", Sold For $%5.2f, Profit: $%5.2f", this.getPriceSold(), this.getProfit()) : "");
		
		return printable;
	}
	
	public String toCSVString() {
		return String.format("%s, %6d, %3d, %4s, %5.2f, %5.2f, %5.2f",
				this.getId(), this.getMileage(), this.getMpg(), this.isSold(), this.getCost(), this.getSalesPrice(), this.getPriceSold(), this.getProfit());
	}
	
	public void sellCar(double priceSold) {
		this.setSold(true);
		this.setPriceSold(priceSold);
		this.setProfit(this.getPriceSold()-this.getCost());
	}
	
	public int compareMPG(Car otherCar) { return this.getMpg() - otherCar.getMpg(); }
	
	public int compareMileage(Car otherCar) { return this.getMileage() - otherCar.getMileage(); }
	
	public double compareSalesPrice(Car otherCar) { return this.getSalesPrice() - otherCar.getSalesPrice(); }
	
	public String getId() { return id; }
	
	public void setId(String id) { this.id = id; }
	
	public int getMileage() { return mileage; }
	
	public void setMileage(int mileage) { this.mileage = mileage; }
	
	public int getMpg() { return mpg; }
	
	public void setMpg(int mpg) { this.mpg = mpg; }
	
	public double getCost() { return cost; }
	
	public void setCost(double cost) { this.cost = cost; } 
	
	public double getSalesPrice() { return salesPrice; }
	
	public void setSalesPrice(double salesPrice) { this.salesPrice = salesPrice; }
	
	public boolean isSold() { return sold; }
	
	public void setSold(boolean sold) { this.sold = sold; }
	
	public double getPriceSold() { return priceSold; }
	
	public void setPriceSold(double priceSold) { this.priceSold = priceSold; }
	
	public double getProfit() { return profit; }
	
	public void setProfit(double profit) { this.profit = profit; } 
}
