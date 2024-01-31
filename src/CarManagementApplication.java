import db.DbContext;
import entity.Buyer;
import entity.Car;
import entity.CarType;
import service.BuyerService;
import service.CarService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CarManagementApplication {

    static final String url = "jdbc:postgresql://localhost:5432/postgres";
    static final String username = "postgres";
    static final String password = "Post7027472";

    public static void main(String[] args) {
        DbContext db = new DbContext();
        Connection connection = db.connect(url, username, password);
        CarService carService = new CarService();
        BuyerService buyerService = new BuyerService();
        carService.createTable(connection);
        buyerService.createTable(connection);


        Car car = new Car();
        Buyer buyer = new Buyer();
//        car.setName("Elcinin masini");
//        car.setCarType(CarType.OFF_ROAD);
//        car.setBrand("Ford");
//        car.setPrice(10000000);
//        car.setSellerName("Vusal");
//        carService.insert(connection,car);


//        car.setId(2);
//        buyer.setCar(car);
//        buyer.setName("Vaqif Cavadov");
//        buyer.setPhoneNumber("555");
//        buyerService.insert(connection, buyer);

//        buyerService.delete(connection,3);

//        carService.delete(connection,2);



        buyerService.findAll(connection).forEach(System.out::println);




    }
}
