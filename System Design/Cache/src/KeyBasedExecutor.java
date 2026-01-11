import java.sql.Array;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyBasedExecutor {
    private final ExecutorService[] executors;
    private int numberOfExecutors;

    public KeyBasedExecutor(int numberOfExecutors) {
        this.numberOfExecutors = numberOfExecutors;
        executors = new ExecutorService[numberOfExecutors];
        for (int i = 0; i < numberOfExecutors; i++) {
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int index = indexOfTask(key);
        ExecutorService executor = executors[index];
        return CompletableFuture.supplyAsync(task, executor);
    }


    public int indexOfTask(Object key) {
        return Math.abs(key.hashCode()%numberOfExecutors); // if you want 5 outputs mod with 5
    }

    public void shutdown() {
        for (ExecutorService service : executors) {
            service.shutdown();
        }
    }
}
