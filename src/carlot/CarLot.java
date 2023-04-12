package carlot;

import java.util.ArrayList;
import java.util.Collections;

public class CarLot {
	private ArrayList<Car> inventory;
	private int numberOfCars = 0;
	private int capacity = 0;

	private Database database;
	
	public CarLot() { 
		this(100); 
	}

	public CarLot(int capacity) {
		this.capacity = capacity;
		System.out.println("AAAAA");
		this.database = new Database();
		System.out.println("BBBB");
		this.inventory = this.database.getCars();
		System.out.println("CCCCCC");
	}
	public void addCar(String id, int mileage, int mpg, double cost, double salesPrice) {
		if (numberOfCars < capacity) {
			Car car = new Car(id, mileage, mpg, cost, salesPrice);
			this.inventory.add(car);
			this.database.addCar(car);
			numberOfCars++;
		}
	}
	
	public Car[] getInventory() {
		Car[] allCars = new Car[numberOfCars];
		for (int i = 0; i < numberOfCars; i++) {
			allCars[i] = this.inventory.get(i);
		}
		return allCars;
	}
	
	public Car findCarByIdentifier(String identifier) {
		for (int x = 0; x < this.inventory.size(); x++) {
			Car aCar = this.inventory.get(x);
			if (aCar.getId().equals(identifier)) {
				return aCar;
			}
		}
		return null;
	}
	
	public void sellCar(String identifier, double priceSold) throws IllegalArgumentException {
		Car aCar = this.findCarByIdentifier(identifier);
		if (aCar != null) {

			aCar.sellCar(priceSold);
			database.updateCar(aCar);
		} else {
			throw new IllegalArgumentException("No car with identifier " + identifier);
		}
	}
	
	public Car[] getCarsInOrderOfEntry() { return (Car[]) this.inventory.toArray(); }
	
	public ArrayList<Car> getCarsSortedByMPG() {
		ArrayList<Car> allCars = new ArrayList<>();
		for (int i = 0; i < numberOfCars; i++) {
			allCars.add(this.inventory.get(i));
		}
		Collections.sort(allCars, (Car c1, Car c2) -> c2.compareMPG(c1));
		return allCars;
	}
	
	public Car getCarWithBestMPG() {
		Car rtn = null;
		int bestMpg = -1;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			if (aCar.getMpg() > bestMpg) {
				bestMpg = aCar.getMpg();
				rtn = aCar;
			}
		}
		return rtn;
	}
	
	public Car getCarWithHighestMileage() {
		Car rtn = null;
		int highestMileage = -1;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			if (aCar.getMileage() > highestMileage) {
				highestMileage = aCar.getMileage();
				rtn = aCar;
			}
		}
		return rtn;
	}
	
	public double getAverageMpg() {
		double totalMpg = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			totalMpg += aCar.getMpg();
		}
		return totalMpg / this.numberOfCars;
	}
	
	public double getTotalProfit() {
		double profit = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			profit += (aCar.isSold()?aCar.getProfit():0);
		}
		return profit;
	}
	
}
