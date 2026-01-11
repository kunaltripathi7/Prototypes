User Flow:
Admin side:
Restaurant can come to register on the app and start taking Orders.
Delivery Excecutive can come and registre., Will receive DeliveryOrder.
Restaurant have menu, Dish Will receive RestaurantOrder. (with slight change in data)

User Flow:
User will search restaurant.
Restaurant will have menu,
user will place order & receive order.

Clarifying Questions:
How will we mathc the delivery partners? like ratings, geospatial based and all.
So what do you want me to focus on specially? -> Food or Delivery.
In food? -> Addon's will be considered as a seaparte dish or not?
Can restaurant cancel the order? if yes then till which state? -> order state? -> supposing -> restaurant hit then delivery mathcing.
Should we assign delivery partners as soon as order is received or after restaurant confirms the order? -> triggers delivery matching
pricing will be decided by platform or restaurant? -> Decides the pricing logic.


Core Entities -> Restaurant, Order, User, Menu, Dish, DeliveryPartner, payment.
Relationship classes -> OrderItem, DeliveryAssignment (Has its own state -> assignment status)
OrderService -> “OrderService ensures atomic delivery assignment per order to prevent double assignment.”
Main()

