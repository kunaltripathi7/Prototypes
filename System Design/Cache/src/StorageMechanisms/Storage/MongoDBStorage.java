package StorageMechanisms.Storage;

import StorageMechanisms.Interfaces.DBStorage;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MongoDBStorage<K,V> implements DBStorage<K, V> {
    private Map<K, V> dbMap = new ConcurrentHashMap<>();
    @Override
    public V read(K key) {
        if (!dbMap.containsKey(key)) throw new IllegalArgumentException("Not found");
        return dbMap.get(key);
    }

    @Override
    public void write(K key, V value) {
        dbMap.put(key, value);
    }

    @Override
    public void delete(K key) {
        dbMap.remove(key);
    }
}
