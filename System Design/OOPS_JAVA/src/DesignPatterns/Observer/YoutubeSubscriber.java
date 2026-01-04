package DesignPatterns.Observer;

public class YoutubeSubscriber implements Subscriber {
    public String name;

    public YoutubeSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String video) {
        // send notification to youtube
    }
}
