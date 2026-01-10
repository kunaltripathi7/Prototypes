package StorageMechanisms.Storage;

import StorageMechanisms.Interfaces.CacheStorage;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCacheStorage<K, V> implements CacheStorage<K, V> {
    private final Map<K, V> cacheMap = new ConcurrentHashMap<>(); // wrong understand again || -> real data -> "A" -> Apple
    private int capacity;

    public InMemoryCacheStorage(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Optional<V> getKey(K key) {
        if (!cacheMap.containsKey(key)) {
            return Optional.empty();
        }
        else {
            return Optional.ofNullable(cacheMap.get(key));
        }
    }

    @Override
    public V updateKey(K key, V value) {
        if (!cacheMap.containsKey(key)) throw new IllegalArgumentException("Not found");
        return cacheMap.put(key, value);
    }

    @Override
    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    @Override
    public void remove(K key) {
        cacheMap.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
