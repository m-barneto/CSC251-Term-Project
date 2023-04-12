package carlot;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection conn;

    private String connUrl;

    public Database() {
        try {
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS cars (" +
                    "car_id STRING PRIMARY KEY," +
                    "mileage INT," +
                    "mpg INT," +
                    "cost NUMBER," +
                    "salesPrice NUMBER," +
                    "sold BOOL," +
                    "priceSold NUMBER," +
                    "profit NUMBER);");

            stmt.executeUpdate("DELETE FROM cars;");
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars;");
            while (rs.next()) {
                Car car = new Car(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
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
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cars(car_id, mileage, mpg, cost, salesPrice, sold, priceSold, profit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, car.getId());
            ps.setInt(2, car.getMileage());
            ps.setInt(3, car.getMpg());
            ps.setDouble(4, car.getCost());
            ps.setDouble(5, car.getSalesPrice());
            ps.setBoolean(6, car.isSold());
            ps.setDouble(7, car.getPriceSold());
            ps.setDouble(8, car.getProfit());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateCar(Car car) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE cars SET mileage=?, mpg=?, cost=?, salesPrice=?, sold=?, priceSold=?, profit=? WHERE car_id=?");
            ps.setInt(1, car.getMileage());
            ps.setInt(2, car.getMpg());
            ps.setDouble(3, car.getCost());
            ps.setDouble(4, car.getSalesPrice());
            ps.setBoolean(5, car.isSold());
            ps.setDouble(6, car.getPriceSold());
            ps.setDouble(7, car.getProfit());
            ps.setString(8, car.getId());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
