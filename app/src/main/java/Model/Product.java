package Model;

public class Product {
    private int id;
    private String name;
    private String description;
    private int category;
    private double calories;
    private double price;

    public Product() {
    }

    public Product(String name, String description, int category, double calories, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.calories = calories;
        this.price = price;
    }

    public Product(int id, String name, String description, int category, double calories, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.calories = calories;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
