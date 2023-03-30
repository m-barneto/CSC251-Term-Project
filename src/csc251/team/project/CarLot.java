package csc251.team.project;

import java.util.ArrayList;
import java.util.Collections;

public class CarLot {
	private Car[] inventory;
	private int numberOfCars = 0;
	private int capacity = 0;
	
	public CarLot() { 
		this(100); 
	}
	
	public CarLot(int capacity) {
		this.capacity = capacity;
		this.inventory = new Car[capacity];
	}
	
	public void addCar(String id, int mileage, int mpg, double cost, double salesPrice) {
		if (numberOfCars < capacity) {
			this.inventory[numberOfCars] = new Car(id, mileage, mpg, cost, salesPrice);
			numberOfCars++;
		}
	}
	
	public Car[] getInventory() {
		Car[] allCars = new Car[numberOfCars];
		for (int i = 0; i < numberOfCars; i++) {
			allCars[i] = this.inventory[i];
		}
		return allCars;
	}
	
	public Car findCarByIdentifier(String identifier) {
		for (int x = 0; x < this.inventory.length; x++) {
			Car aCar = this.inventory[x];
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
		} else {
			throw new IllegalArgumentException("No car with identifier " + identifier);
		}
	}
	
	public Car[] getCarsInOrderOfEntry() { return this.inventory; }
	
	public ArrayList<Car> getCarsSortedByMPG() {
		ArrayList<Car> allCars = new ArrayList<>();
		for (int i = 0; i < numberOfCars; i++) {
			allCars.add(this.inventory[i]);
		}
		Collections.sort(allCars, (Car c1, Car c2) -> c2.compareMPG(c1));
		return allCars;
	}
	
	public Car getCarWithBestMPG() {
		Car rtn = null;
		int bestMpg = -1;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory[i];
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
			Car aCar = this.inventory[i];
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
			Car aCar = this.inventory[i];
			totalMpg += aCar.getMpg();
		}
		return totalMpg / this.numberOfCars;
	}
	
	public double getTotalProfit() {
		double profit = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory[i];
			profit += (aCar.isSold()?aCar.getProfit():0);
		}
		return profit;
	}
	
}
