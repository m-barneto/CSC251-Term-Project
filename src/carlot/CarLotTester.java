package carlot;

public class CarLotTester {
	public static void main(String[] args) {
		CarLot lot = new CarLot();
		//Creates a Carlot Object
		lot.clear();
		//removes any cars already in carlot object
		lot.addCar("test1", 10000, 30, 12500.0D, 17500.0D);
		lot.addCar("test2", 10000, 10, 10000D, 10000D);
		lot.addCar("test3", 12000, 20, 12000D, 12000D);
		//Adds Cars to the carlot
		System.out.println("Inventory: ");
		for (Car car: lot.getInventory()) {
			System.out.println(car);
		//Prints Each Cars Details
		}
		System.out.println("Average MPG: " + lot.getAverageMpg());
		//prints the carlots average MPG
		System.out.println("Total Profit: " + lot.getTotalProfit());
		//prints carlots total profits
		System.out.println("Car with the best MPG: " + lot.getCarWithBestMPG());
		//prints car with greatest MPG
		System.out.println("Car with the highest mileage: " + lot.getCarWithHighestMileage());
		//Gets car with Highest Mileage
		lot.sellCar("test1", 17000.0D);
		//sells car with id Test 1 for 17000
		System.out.println("Total profit after selling one car: " + lot.getTotalProfit());
		//prints total profits
	}
}
