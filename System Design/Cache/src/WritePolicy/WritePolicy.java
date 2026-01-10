package WritePolicy;

// jb miss ho jaaega. write policy.write krenge

import StorageMechanisms.Interfaces.CacheStorage;
import StorageMechanisms.Interfaces.DBStorage;

public interface WritePolicy<K, V >{
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage);
}
