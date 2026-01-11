import Model.*;
import Services.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        DeliveryService deliveryService = new DeliveryService(orderService);

        // 1. Setup Delivery Executives
        DeliveryExecutive de1 = new DeliveryExecutive("fdafadsf", 5, 1, 1);
        deliveryService.registerDeliveryExecutive(1, de1); // Register at Location 1

        Dish pizza = new Dish(1, "Pizza", "Cheese Pizza", 300);
        OrderItem item = new OrderItem(pizza, 2, pizza.getDishPrice());

        // Order is placed at Location 1 (see Order constructor in OrderService)
        Order order = orderService.placeOrder(List.of(item));

        orderService.processPayment(order.getOrderId(), new UPIPaymentStrategy());
        deliveryService.assignDeliveryPartner(order.getOrderId(), new RatingBasedStrategy());

        System.out.println("Order status: " + order.getStatus());
        if (order.getAssignedTo() != null) {
            System.out.println("Assigned to: " + order.getAssignedTo().getName());
        }
    }
}
