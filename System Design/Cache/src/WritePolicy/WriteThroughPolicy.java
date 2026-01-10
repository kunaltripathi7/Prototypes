package WritePolicy;

import StorageMechanisms.Interfaces.CacheStorage;
import StorageMechanisms.Interfaces.DBStorage;

import java.util.concurrent.CompletableFuture;

public class WriteThroughPolicy<K, V> implements WritePolicy<K, V>{

    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) {
        CompletableFuture<Void> cacheFuture = CompletableFuture.runAsync(() -> {
            cacheStorage.put(key, value);
        });
        CompletableFuture<Void> dbFuture = CompletableFuture.runAsync(() -> {
        dbStorage.write(key, value);
        });

//        these two tasks will be running on a different thread but main thread be one -> and waiting for them to complete.

        CompletableFuture.allOf(cacheFuture, dbFuture).join(); // blocking the sequential thread.
    }
}
