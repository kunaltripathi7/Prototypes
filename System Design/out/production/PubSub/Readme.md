
//Publisher sends message to topic.
//        topic receives it and stores temporatily adn notiffies subscribers to subscribe
//subscribe read from the topic and processm
//main core of the problem -> multiple subscribers running in parallel to consume the message produced in the topic
//
//Entities: -> message, topic, consumer, producer
//Relationship Behaviour -> topic notifying consumers to consume (relationship) who will store which topic is subscibe to which consumer -> separate class
//    RELATIONSHIP(TOPIC-CONSUMER/SUBSCRIBER) HAS STATE (OFFSET) -> RELATIONSHIP WILL HAVE CLASS
//TopicProducer as well No there's no state such as offset/
//Kafka controller for producing, consuming, and shutdown operations;
//
//TopicPublisherControlle/consumer for handling all TopicPublisher adn TopicConsumer
//
//variation points -> subscriber -> multiple types  -> interface
//
//Why TopicSubscriber/PublisherController class? -> single responsiblity principle -. a class should have only one reason to change. -> message delivery over time is a new responsiblity and it requires a new class ->
//see the logic -> if it coordinates over time but doesn't own core domain state -> its a controller
//
//controller.produce(message, topic);
Why? means -> one class shouldn't know another.
Producer knew Topic


Ask this question:

Is this logic global or per subscriber?

Global → KafkaController

Per subscriber → TopicSubscriberController

That’s the separation.



public class TopicProducerController {  the name shows what objects it will coordinate to -> these two refrences it will hold ofcourse

[//]: # (public class TopicConsumerController {)

[//]: # (private Topic topic;)

[//]: # ()
[//]: # (    private IConsumer consumer; // code by interfaces execute by concrete classes)



