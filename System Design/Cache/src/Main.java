import StorageMechanisms.Interfaces.CacheStorage;
import StorageMechanisms.Interfaces.DBStorage;
import StorageMechanisms.Interfaces.EvictionAlgorithm;
import StorageMechanisms.LRUEvictionAlgorithm;
import StorageMechanisms.Storage.InMemoryCacheStorage;
import StorageMechanisms.Storage.MongoDBStorage;
import WritePolicy.WritePolicy;
import WritePolicy.WriteThroughPolicy;

public class Main {

    public static void main(String[] args) {
        CacheStorage<String, String> cacheStorage = new InMemoryCacheStorage<>(5);
        // The underlying persistent store (DB storage) can be assumed to have large or unlimited capacity.
        DBStorage<String, String> dbStorage = new MongoDBStorage<>();
        // Create the write-through policy (writes concurrently to both storages).
        WritePolicy<String, String> writePolicy = new WriteThroughPolicy<String, String>();
        // Create the LRU eviction algorithm.
        EvictionAlgorithm<String> evictionAlg = new LRUEvictionAlgorithm<>();
        // Create the cache with 4 executor threads to guarantee per-key ordering.
        // Change <> to <String, String>
        Cache<String, String> cache = new Cache<String, String>(cacheStorage, dbStorage, writePolicy, evictionAlg, 4);

        cache.updateData("A", "Apple").join(); // join join join
        cache.updateData("B", "Banana").join();
        cache.updateData("C", "Cherry").join();
        cache.updateData("D", "Durian").join();
        cache.updateData("E", "Elderberry").join();

        cache.updateData("F", "Fig").join();

        String valueA = cache.accessData("A").join();
        System.out.println("A: " + valueA);

        String valueF = cache.accessData("F").join();
        System.out.println("F: " + valueF);

        cache.updateData("B", "Blueberry").join();
        String valueB = cache.accessData("B").join();
        System.out.println("B: " + valueB);

        cache.shutdown();
    }
}
