package futuresexample;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            System.out.println("Fetching user on: " + Thread.currentThread().getName());
            return "User Alice";
        });

        CompletableFuture<String> ordersFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching orders on: " + Thread.currentThread().getName());
            return "Orders [101, 102]";
        });

        CompletableFuture<String> combinedFuture = userFuture.thenCombineAsync(ordersFuture, (user, orders) -> {
            System.out.println("Combining results on: " + Thread.currentThread().getName());
            return user + " -> " + orders;
        });

        combinedFuture.thenAccept(result -> {
            System.out.println("Final Output: " + result);
        });

        // Block main thread so async tasks complete before program exits
        combinedFuture.join(); // otherwise it wont' wait fro daemon threads.
        System.out.println("Main thread exiting now...");
    }
}