package StorageMechanisms.Interfaces;

import java.util.Optional;

public interface CacheStorage<K, V> {
    Optional<V> getKey(K key);
    V updateKey(K key, V value);
    void put(K key, V value);
    void remove(K key);
    boolean containsKey(K key);
    int size();
    int getCapacity();
}
