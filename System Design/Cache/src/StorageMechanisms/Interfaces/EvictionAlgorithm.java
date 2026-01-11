package StorageMechanisms.Interfaces;

public interface EvictionAlgorithm<K> {
    K evictKey();
    void updateKeyAccessed(K Key);
}
