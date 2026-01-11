import StorageMechanisms.Interfaces.CacheStorage;
import StorageMechanisms.Interfaces.DBStorage;
import StorageMechanisms.Interfaces.EvictionAlgorithm;
import WritePolicy.WritePolicy;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Cache<K, V> {
    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K,V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;
    private final KeyBasedExecutor keyBasedExecutor;

    public Cache(CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage, WritePolicy<K, V> writePolicy, EvictionAlgorithm<K> evictionAlgorithm,  int numberOfExecutors) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
        this.keyBasedExecutor = new KeyBasedExecutor(numberOfExecutors);
    }


    public CompletableFuture<V> accessData(K key) {
        return keyBasedExecutor.submitTask(key, () -> {
            Optional<V> value = cacheStorage.getKey(key);
            if (value.isPresent()) {
                evictionAlgorithm.updateKeyAccessed(key);
                return value.get();
            }
            V valueDb = dbStorage.read(key);
            if (cacheStorage.getCapacity() == cacheStorage.size()) {
                K evictedKey = evictionAlgorithm.evictKey();
                cacheStorage.remove(evictedKey);
            }
            cacheStorage.put(key, valueDb);
            return valueDb;
        });
    }

    public CompletableFuture<Void> updateData(K key, V value) {
        return keyBasedExecutor.submitTask(key, () -> {
           if (cacheStorage.containsKey(key)) {
               writePolicy.write(key, value, cacheStorage, dbStorage);
               evictionAlgorithm.updateKeyAccessed(key);
           }
           else {
                if (cacheStorage.size()  == cacheStorage.getCapacity()) {
                    K evictedKey = evictionAlgorithm.evictKey();

                    // vImp -> removal of the key should haappen via same executor.
//
                    if (evictedKey != null) {
                        int currentIndex = keyBasedExecutor.indexOfTask(key);
                        int evictedIndex = keyBasedExecutor.indexOfTask(evictedKey);
                        if (currentIndex == evictedIndex) cacheStorage.remove(evictedKey);
                        else {
                            CompletableFuture<Void> removalFuture = keyBasedExecutor.submitTask(evictedKey, () -> {
                                cacheStorage.remove(evictedKey);
                                return null;
                            });
                            removalFuture.join();
                        }
                    }
                }
                writePolicy.write(key, value, cacheStorage, dbStorage);
                evictionAlgorithm.updateKeyAccessed(key);
           }
           return null;
        });
    }

    public void shutdown() {
        keyBasedExecutor.shutdown();
    }


}
