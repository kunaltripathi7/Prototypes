import Interfaces.DeliveryAssignmentStrategy;
import Model.DeliveryExecutive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingBasedStrategy implements DeliveryAssignmentStrategy {

//    delivery Strategy -> goal to find the best delivery executive out of all. not store them not know locked ones. Strategy doesn't own data.
    @Override
    public DeliveryExecutive assignDeliveryExecutive(List<DeliveryExecutive> executives) {
//        if (executives.isEmpty()) throw new IllegalArgumentException("Executives list can't be emtpy"); // throw exceptions when its not business outcome and its caller's fault to send wrong arguments
        if (executives.isEmpty()) return null; // return null when that case is possible for business outcomes -> delivery executives can't be available at some times.
        return executives.stream().max((a, b) -> Integer.compare(a.getRating(), b.getRating())).orElse(null);
    }
}
