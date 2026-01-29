package entity;

public class Service {
    private int id;
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Service(int id, String name, double price) {
        this(name, price);
        this.id = id;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Service{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}