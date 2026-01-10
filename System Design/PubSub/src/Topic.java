import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Topic {
    private final List<Message> messages = new ArrayList<>();
    private final String topicId;
    private String topicName;

    public Topic(String id, String name) {
        this.topicId = id;
        this.topicName = name;
    }

    public synchronized void addMessage(Message message) {
        this.messages.add(message);
    }

    public synchronized List<Message> getMessages() {
        return Collections.unmodifiableList(messages); // returns with only read operation.
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

}



