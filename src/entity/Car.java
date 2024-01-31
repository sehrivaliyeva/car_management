package entity;

public class Car {
    private int id;
    private String name;
    private int year;
    private String brand;
    private int price;
    private String sellerName;
    private CarType carType;
    private Buyer buyer;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", sellerName='" + sellerName + '\'' +
                ", carType=" + carType +
                ", buyerId=" + buyer.getId() +
                ", buyerName=" + buyer.getName() +
                ", buyerPhoneNumber=" + buyer.getPhoneNumber() +
                '}';
    }
}
