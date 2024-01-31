package service;

import entity.Buyer;
import entity.Car;
import entity.CarType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    public void createTable(Connection connection) {

        String sql = " CREATE TABLE IF NOT EXISTS car(" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "year INTEGER, " +
                "brand VARCHAR(30), " +
                "price INTEGER, " +
                "seller_name VARCHAR(30), " +
                "car_type VARCHAR(30))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(Connection connection, Car car) {
        var sql = """
                INSERT INTO car(name,year,brand,price,seller_name,car_type)
                VALUES(?,?,?,?,?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, car.getName());
            preparedStatement.setInt(2, car.getYear());
            preparedStatement.setString(3, car.getBrand());
            preparedStatement.setInt(4, car.getPrice());
            preparedStatement.setString(5, car.getSellerName());
            preparedStatement.setString(6, car.getCarType().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Connection connection,int id){
        var sql = """
                DELETE FROM buyer WHERE car_id =?;
                DELETE FROM car WHERE id= ?;
                """;
        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Car> findAll(Connection connection) {
        String sql = "SELECT car.*, b.id as buyerId, b.phone_number as buyerPhoneNumber, b.name as buyerName FROM car LEFT JOIN buyer b ON car.id = b.car_id";

        try (Statement statement = connection.createStatement()) {
            List<Car> cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setName(resultSet.getString("name"));
                car.setBrand(resultSet.getString("brand"));
                car.setSellerName(resultSet.getString("seller_name"));
                car.setCarType(CarType.valueOf(resultSet.getString("car_type")));
                car.setYear(resultSet.getInt("year"));
                car.setPrice(resultSet.getInt("price"));

                Buyer buyer = new Buyer();
                buyer.setId(resultSet.getInt("buyerId"));
                buyer.setPhoneNumber(resultSet.getString("buyerPhoneNumber"));
                buyer.setName(resultSet.getString("buyerName"));
                buyer.setCar(car);
                car.setBuyer(buyer);

                cars.add(car);
            }

            return cars;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
