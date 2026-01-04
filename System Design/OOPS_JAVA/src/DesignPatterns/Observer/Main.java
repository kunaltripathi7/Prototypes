package DesignPatterns.Observer;

public class Main {

    public static void main(String[] args) {
        YoutubeChannel youtubeChannel = new YoutubeChannel();
        YoutubeSubscriber  alice = new YoutubeSubscriber("Alice");
        YoutubeSubscriber bob = new YoutubeSubscriber("Bob");
        youtubeChannel.addSubscriber(alice);
        youtubeChannel.addSubscriber(bob);
    }
}
