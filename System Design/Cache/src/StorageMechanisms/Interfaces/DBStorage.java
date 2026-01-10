package StorageMechanisms.Interfaces;

import java.util.Optional;

public interface DBStorage<K, V> {
    V read(K key);
    void write(K key, V value);
    void delete(K key);

}
