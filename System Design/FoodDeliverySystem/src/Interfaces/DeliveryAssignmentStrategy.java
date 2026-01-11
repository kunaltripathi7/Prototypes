package Interfaces;

import Model.DeliveryExecutive;

import java.util.List;

public interface DeliveryAssignmentStrategy {
    DeliveryExecutive assignDeliveryExecutive(List<DeliveryExecutive> executives);
}
