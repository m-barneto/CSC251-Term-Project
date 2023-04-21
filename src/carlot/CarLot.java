package carlot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
//Preprocessor

public class CarLot {
	private ArrayList<Car> inventory;
	private int numberOfCars = 0;
	private int capacity = 0;
	private Database database;
	//Private Member Variables
	
	public CarLot() { 
		this(100); 
	}
	//Default Constructor

	public CarLot(int capacity) {
		this.capacity = capacity;
		this.database = new Database();
		this.inventory = this.database.getCars();
	}
	//Overloaded Constructor
	public void addCar(String id, int mileage, int mpg, double cost, double salesPrice) {
		if (numberOfCars < capacity) {
			Car car = new Car(id, mileage, mpg, cost, salesPrice);
			this.inventory.add(car);
			this.database.addCar(car);
			numberOfCars++;
		}
	}
	//Function Creates a car with the the agruements and then adds it to the array list
	
	public Car[] getInventory() {
		Car[] allCars = new Car[numberOfCars];
		for (int i = 0; i < numberOfCars; i++) {
			allCars[i] = this.inventory.get(i);
		}
		return allCars;
	}
	//Returns all cars in the array list in a car array
	
	public Car findCarByIdentifier(String identifier) {
		for (int x = 0; x < this.inventory.size(); x++) {
			Car aCar = this.inventory.get(x);
			if (aCar.getId().equals(identifier)) {
				return aCar;
			}
		}
		return null;
	}
	//Returns car with matching identifier arguement or null if no match
	
	public void sellCar(String identifier, double priceSold) throws IllegalArgumentException {
		Car aCar = this.findCarByIdentifier(identifier);
		if (aCar != null) {

			aCar.sellCar(priceSold);
			database.updateCar(aCar);
		} else {
			throw new IllegalArgumentException("No car with identifier " + identifier);
		}
	}
	//Takes identifier arguement and updates the matching cars sold & priceSold member variables
	
	public Car[] getCarsInOrderOfEntry() { return (Car[]) this.inventory.toArray(); }
	//Returns an array of cars in the order of entry
	
	public ArrayList<Car> getCarsSortedByMPG() {
		ArrayList<Car> allCars = new ArrayList<>();
		for (int i = 0; i < numberOfCars; i++) {
			allCars.add(this.inventory.get(i));
		}
		Collections.sort(allCars, (Car c1, Car c2) -> c2.compareMPG(c1));
		return allCars;
	}
	//returns a list of cars sorted by MPG
	public Car getCarWithBestMPG() {
        try {
            String query = "SELECT * FROM Car ORDER BY mpg DESC LIMIT 1";
            Statement stmt = DbConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {

                String id = rs.getString("id");
                int mileage = rs.getInt("mileage");
                int mpg = rs.getInt("mpg");
                double cost = rs.getDouble("cost");
                double salesPrice = rs.getDouble("sales_price");

                Car car = new Car(id, mileage, mpg, cost, salesPrice);
                return car;

            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	//Uses a Database Query to fint the car with the best MPG
	}
	
	public Car getCarWithHighestMileage() {
        try {
            String query = "SELECT * FROM Car ORDER BY mileage DESC LIMIT 1";
            Statement stmt = DbConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String id = rs.getString("id");
                int mileage = rs.getInt("mileage");
                int mpg = rs.getInt("mpg");
                double cost = rs.getDouble("cost");
                double salesPrice = rs.getDouble("sales_price");

                Car car = new Car(id, mileage, mpg, cost, salesPrice);
                return car;

            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	//Uses a Database Query to fint the car with the Highest Mileage
	public double getAverageMpg() {
		double totalMpg = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			totalMpg += aCar.getMpg();
		}
		return totalMpg / this.numberOfCars;
	}
	//returns the average MPG of all cars in array list
	public double getTotalProfit() {
		double profit = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			profit += (aCar.isSold()?aCar.getProfit():0);
		}
		return profit;
	}
	//Returns Profit From all Cars Sold
	public void clear() {
		this.database.clear();
		this.inventory = this.database.getCars();
		this.numberOfCars = 0;
	}
	//Clears all Cars in Database
}
