package Model;

import Enums.OrderStatus;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int orderId;
    private final int locationId;

    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;
    private Payment payment;
    private DeliveryExecutive assignedTo;

    public Order(int orderId, int locationId) {
        this.orderId = orderId;
        this.locationId = locationId;
    }

    public int getOrderId() { return orderId; }
    public int getLocationId() { return locationId; }
    public OrderStatus getStatus() { return status; }
    public DeliveryExecutive getAssignedTo() { return assignedTo; }

    public void addItem(OrderItem item) {
        if (status != OrderStatus.CREATED)
            throw new IllegalStateException("Cannot add items after order creation");
        items.add(item);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(OrderItem::getItemTotal).sum();
    }

    public void markPaymentInProgress() {
        status = OrderStatus.PAYMENT_IN_PROGRESS;
    }

    public void markPaid(Payment payment) {
        this.payment = payment;
        status = OrderStatus.PAID;
    }

    public void markFailed() {
        status = OrderStatus.FAILED;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void assignDeliveryExecutive(DeliveryExecutive executive) {
        this.assignedTo = executive;
        status = OrderStatus.ASSGINED;
    }
}
