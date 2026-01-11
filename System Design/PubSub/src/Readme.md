
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


clarifying:

Do you require ordering guarantees per key/partition or only eventual delivery? — decides partitioning and whether per-key sequencing is required.

Which delivery semantics do you want: at-most-once, at-least-once, or exactly-once? — drives use of transactions, idempotence, and consumer logic.

What are expected message sizes, throughput (msgs/sec), and retention window? — impacts batching, compression, and storage design.

How should consumer failures be handled: retries, dead-letter queue, or skip? — affects DLQ design and retry policies.

Do consumers belong to consumer groups (competing consumers) or should each get all messages (pub-sub fanout)? — defines subscription model and offsets.



public class TopicProducerController {  the name shows what objects it will coordinate to -> these two refrences it will hold ofcourse

[//]: # (public class TopicConsumerController {)

[//]: # (private Topic topic;)

[//]: # ()
[//]: # (    private IConsumer consumer; // code by interfaces execute by concrete classes)



