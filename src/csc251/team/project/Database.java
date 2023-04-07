package csc251.team.project;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection conn;

    private String connUrl;

    public Database(String name, boolean clearDatabase) {
        try {
            this.connUrl = "jdbc:sqlite:" + name + ".db";
            Connection conn = DriverManager.getConnection(this.connUrl);
            Statement stmt = conn.createStatement();
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS cars (" +
                    "car_id STRING PRIMARY KEY," +
                    "mileage INT," +
                    "mpg INT," +
                    "cost NUMBER," +
                    "salesPrice NUMBER," +
                    "sold BOOL," +
                    "priceSold NUMBER," +
                    "profit NUMBER);");
            if (clearDatabase) {
                stmt.execute("DELETE FROM cars;");
            }
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(this.connUrl);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars;");
            while (rs.next()) {
                Car car = new Car(rs.getString(0), rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4));
                cars.add(car);
            }
            conn.close();
            return cars;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void addCar(Car car) {
        try {
            System.out.println("EEEE");
            Connection conn = DriverManager.getConnection(this.connUrl);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cars(car_id, mileage, mpg, cost, salesPrice, sold, priceSold, profit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            //stmt.execute(, car.getId(), car.getMileage(), car.getMpg(), car.getCost(), car.getSalesPrice(), car.isSold(), car.getPriceSold(), car.getProfit());
            ps.setString(1, car.getId());
            ps.setInt(2, car.getMileage());
            ps.setInt(3, car.getMpg());
            ps.setDouble(4, car.getCost());
            ps.setDouble(5, car.getSalesPrice());
            ps.setBoolean(6, car.isSold());
            ps.setDouble(7, car.getPriceSold());
            ps.setDouble(8, car.getProfit());
            System.out.println("ERRRRR");
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
