package Services;

import Enums.OrderStatus;
import Interfaces.PaymentStrategy;
import Model.Order;
import Model.OrderItem;
import Model.Payment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Job of this class is to initiate the order handle the payment assign the delivery partner all of it.
public class OrderService {
    private Map<Integer, Order>  ordersMap = new ConcurrentHashMap<>();
    private AtomicInteger orderCounter = new AtomicInteger(1);

    public Order placeOrder(List<OrderItem> items) {
        if (items == null) throw new IllegalArgumentException("can''t be nulll");
        Order currentOrder = new Order(orderCounter.getAndIncrement(), 1);
        for (OrderItem item : items) currentOrder.addItem(item);
        ordersMap.put(currentOrder.getOrderId(), currentOrder);
        return currentOrder;
    }



    // don't lock on external processesse those can be slow.
    public  void processPayment(int orderId, PaymentStrategy paymentStrategy) {
        Order order = ordersMap.get(orderId);
        if (order == null) throw new IllegalArgumentException("Order not found");
        double totalAmount = 0;
        synchronized (order) {
            if (order.getStatus() == OrderStatus.PAID) throw new IllegalStateException("Order already paid");
            if (order.getStatus() == OrderStatus.PAYMENT_IN_PROGRESS) return; // another thread is doing the payment;
            order.markPaymentInProgress();
            List<OrderItem> items = order.getItems();
            for (OrderItem orderItem : items) totalAmount += orderItem.getItemTotal();
        }
        Payment payment = new Payment(totalAmount);
        payment.processPayment(paymentStrategy);
        synchronized (order) {
            if (payment.isSuccessful()) order.markPaid(payment);
            else order.markFailed();
        }
    }

    public Order getOrder(int orderId) {
        if (!ordersMap.containsKey(orderId)) throw new IllegalArgumentException("OrderId is not valid");
        return ordersMap.get(orderId);
    }

}
