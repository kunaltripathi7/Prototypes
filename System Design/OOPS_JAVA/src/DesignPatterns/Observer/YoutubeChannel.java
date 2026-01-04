package DesignPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel {

    public List<Subscriber> subscribers = new ArrayList<>();
    public String video;

    public void addSubscriber(YoutubeSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void uploadVideo(String video) {
        this.video = video;
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this.video);
            //send notification

            //The problem -> what if i have a lot of different varaitions over here bell icon/ sms and all -> scale to 1k variations -> if -> else ->bad code
            // soltuion -> one -> make individual subscribersClass and have implementation over there
            // Subscribe ->
            // other -> Maintain state in a subscriber class -> What methods it is been subscribed to.
        }
    }
}
