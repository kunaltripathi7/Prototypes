package Model;

public class OrderItem {
    private final Dish dish;
    private final int quantity;
    private final double priceAtOrderTime;

    public OrderItem(Dish dish, int quantity, double priceAtOrderTime) {
        this.dish = dish;
        this.quantity = quantity;
        this.priceAtOrderTime = priceAtOrderTime;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceAtOrderTime() {
        return priceAtOrderTime;
    }

    public double getItemTotal() {
        return priceAtOrderTime * quantity;
    }
}
