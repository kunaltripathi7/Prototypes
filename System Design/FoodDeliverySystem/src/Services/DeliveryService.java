package Services;

import Enums.OrderStatus;
import Interfaces.DeliveryAssignmentStrategy;
import Model.DeliveryExecutive;
import Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// who is gonna handle data -> delivery service -> multiple threads will do read/write -> make it concurrent.
public class DeliveryService {
    private final OrderService orderService;
    private final Map<Integer, List<DeliveryExecutive>> executivesByLocation = new ConcurrentHashMap<>();
//    private final Set<Integer> unavailableExecutives = ConcurrentHashMap.newKeySet();

    public DeliveryService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void registerDeliveryExecutive(int locationId, DeliveryExecutive executive) {
        executivesByLocation.computeIfAbsent(locationId, k -> new ArrayList<>()).add(executive);
    }

    public void assignDeliveryPartner(int orderId, DeliveryAssignmentStrategy strategy) {
        // lock until the system is safe, not until the work is done.
        Order order = orderService.getOrder(orderId);
        if (order == null) throw new IllegalArgumentException("Order not found");

        synchronized (order) {
            if (order.getStatus() != OrderStatus.PAID) return;
            if (order.getAssignedTo() != null) return;
        }

        List<DeliveryExecutive> available =
                executivesByLocation.getOrDefault(order.getLocationId(), List.of())
                        .stream()
                        .filter(e -> !unavailableExecutives.contains(e.getId()))
                        .toList();

        DeliveryExecutive selected = strategy.assignDeliveryExecutive(available);

        synchronized (order) {
            if (selected == null) {
                System.out.println("No delivery executive found for order " + orderId);
                order.markFailed();
                return;
            }
            order.assignDeliveryExecutive(selected);
            unavailableExecutives.add(selected.getId());
        }
    }
}
