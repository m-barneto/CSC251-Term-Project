package csc251.team.project;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection con;
    public Database(String name, boolean clearDatabase) {
        try {
            this.con = DriverManager.getConnection("jdbc:sqlite:" + name + ".db");
            Statement stmt = this.con.createStatement();
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
            this.con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars;");
            while (rs.next()) {
                Car car = new Car(rs.getString(0), rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4));
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
