package entity;

public class Buyer {
    private int id;
    private String name;
    private String phoneNumber;
    private Car car;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "Carid=" + car.getId() +
                ", Carname='" + car.getName() + '\'' +
                ", Caryear=" + car.getYear() +
                ", Carbrand='" + car.getBrand() + '\'' +
                ", Carprice=" + car.getPrice() +
                ", CarsellerName='" + car.getSellerName() + '\'' +
                ", carType=" + car.getCarType() +
                '}';
    }
}
