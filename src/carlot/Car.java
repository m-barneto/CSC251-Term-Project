package carlot;
import java.util.Scanner;
//Preprocessor

public class Car {
    private String id;
    private int mileage;
    private int mpg;
    private double cost;
    private double salesPrice;
    private boolean sold;
    private double priceSold;
    private double profit;
    //Private Member Variables

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
    //Default Constructor

    public Car(String id, int mileage, int mpg, double cost, double salesPrice) {
        this();
        this.setId(id);
        this.setMileage(mileage);
        this.setMpg(mpg);
        this.setCost(cost);
        this.setSalesPrice(salesPrice);
    }
    //Overloaded Constructor

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
    //Overloaded Constructor

    public String toString() {

        String printable = String.format("Car: %-20s, Mileage: %6d, MPG: %3d, Sold: %4s, Cost: $%5.2f, Selling price: $%6.2f",
                this.getId(), this.getMileage(), this.getMpg(), (this.isSold() ? "Yes" : " No"), this.getCost(), this.getSalesPrice());

        printable = printable + (this.isSold() ?
                String.format(", Sold For $%5.2f, Profit: $%5.2f", this.getPriceSold(), this.getProfit()) : "");

        return printable;
    }
    //Takes Private Members and convert's it to a String, Printable which it returns

    public String toCSVString() {
        return String.format("%s, %6d, %3d, %4s, %5.2f, %5.2f, %5.2f",
                this.getId(), this.getMileage(), this.getMpg(), this.isSold(), this.getCost(), this.getSalesPrice(), this.getPriceSold(), this.getProfit());
    }
    //Returns a String containing the private member variables as text to be saved in a text file

    public void sellCar(double priceSold) {
        this.setSold(true);
        this.setPriceSold(priceSold);
        this.setProfit(this.getPriceSold()-this.getCost());
    }
    //Sets the sold boolean variable to true, Sets the Price sold variable to the priceSold argument, and sets the Profit Variable to the difference of the price sold and cost
    public int compareMPG(Car otherCar) { return this.getMpg() - otherCar.getMpg(); }
    //Returns the difference in MPG
    public int compareMileage(Car otherCar) { return this.getMileage() - otherCar.getMileage(); }
    //Returns the difference in MPG
    public double compareSalesPrice(Car otherCar) { return this.getSalesPrice() - otherCar.getSalesPrice(); }
    //Returns the difference in MPG
    public String getId() { return id; }
    //Returns ID
    public void setId(String id) { this.id = id; }
    //Sets ID
    public int getMileage() { return mileage; }
    //Returns Mileage
    public void setMileage(int mileage) { this.mileage = mileage; }
    //Sets Mileage
    public int getMpg() { return mpg; }
    //Returns MPG
    public void setMpg(int mpg) { this.mpg = mpg; }
    //Sets MPG
    public double getCost() { return cost; }
    //Returns Cost
    public void setCost(double cost) { this.cost = cost; }
    //Sets Cost
    public double getSalesPrice() { return salesPrice; }
    //Returns Price
    public void setSalesPrice(double salesPrice) { this.salesPrice = salesPrice; }
    //Sets Sales Price
    public boolean isSold() { return sold; }
    //Returns Sold
    public void setSold(boolean sold) { this.sold = sold; }
    //Sets Sold
    public double getPriceSold() { return priceSold; }
    //Returns Price Sold
    public void setPriceSold(double priceSold) { this.priceSold = priceSold; }
    //Sets Price Sold
    public double getProfit() { return profit; }
    //Returns Profit
    public void setProfit(double profit) { this.profit = profit; }
    //Sets Profit
}
