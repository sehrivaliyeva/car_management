package service;

import entity.Buyer;
import entity.Car;
import entity.CarType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerService {

    public void createTable(Connection connection) {
        String sql = " CREATE TABLE IF NOT EXISTS buyer(" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "phone_number VARCHAR(30), " +
                "car_id  INTEGER REFERENCES car(id)) ";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Connection connection, Buyer buyer) {
        var sql = """
                INSERT INTO buyer(
                name,phone_number,car_id) values(?,?,?);
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getPhoneNumber());
            preparedStatement.setInt(3, buyer.getCar().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Buyer> findAll(Connection connection) {
        var sql = """
                SELECT buyer.*,car.id as carId,car.name as carName,car.year as carYear,car.brand as carBrand,
                car.price as carPrice,car.seller_name as sellerName,car.car_type as carType FROM buyer JOIN car on buyer.car_id=car.id
                """;
        List<Buyer> buyers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                var buyer = new Buyer();
                buyer.setId(resultSet.getInt("id"));
                buyer.setName(resultSet.getString("name"));
                buyer.setPhoneNumber(resultSet.getString("phone_number"));
                var car = new Car();
                car.setId(resultSet.getInt("carId"));
                car.setName(resultSet.getString("carName"));
                car.setYear(resultSet.getInt("carYear"));
                car.setBrand(resultSet.getString("carBrand"));
                car.setPrice(resultSet.getInt("carPrice"));
                car.setSellerName(resultSet.getString("sellerName"));
                car.setCarType(CarType.valueOf(resultSet.getString("carType")));
                buyer.setCar(car);
                car.setBuyer(buyer);
                buyers.add(buyer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return buyers;
    }
}
