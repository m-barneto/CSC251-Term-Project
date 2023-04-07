package csc251.team.project;

public class CarLotTester {
	public static void main(String[] args) {
		new Database("aaaa", false);
		CarLot lot = new CarLot();
		lot.addCar("test1", 10000, 30, 12500.0D, 17500.0D);
		lot.addCar("test2", 10000, 10, 10000D, 10000D);
		lot.addCar("test3", 12000, 20, 12000D, 12000D);
		System.out.println("Inventory: ");
		for (Car car: lot.getInventory()) {
			System.out.println(car);
		}
		System.out.println("Average MPG: " + lot.getAverageMpg());
		System.out.println("Total Profit: " + lot.getTotalProfit());
		System.out.println("Car with the best MPG: " + lot.getCarWithBestMPG());
		System.out.println("Car with the highest mileage: " + lot.getCarWithHighestMileage());
		lot.sellCar("test1", 17000.0D);
		System.out.println("Total profit after selling one car: " + lot.getTotalProfit());
	}

}
