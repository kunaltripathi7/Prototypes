package StorageMechanisms.Interfaces;

public interface EvictionAlgorithm<K> {
    void evictKey();
    void updateKeyAccessed(K Key);
}
