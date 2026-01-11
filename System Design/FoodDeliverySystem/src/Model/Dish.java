package Model;

public class Dish {
    private final int id;
    private final String name;
    private final String desc;
    private int dishPrice;

    public Dish(int id, String  name, String desc, int price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.dishPrice = price;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getId() {
        return id;
    }


    public String getDesc() {
        return desc;
    }
}
