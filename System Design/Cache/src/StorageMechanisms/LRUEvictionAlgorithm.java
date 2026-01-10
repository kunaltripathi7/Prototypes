package StorageMechanisms;

import StorageMechanisms.Interfaces.EvictionAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K, V> implements EvictionAlgorithm<K> {
    private DoublyLinkedList<K> dll = new DoublyLinkedList<K>();
    private Map<K, Node<K>> valuesMap = new HashMap<>();

    @Override
    public void evictKey() {
        dll.removelastNode();
    }

    @Override
    public void updateKeyAccessed(K key) {
        Node<K> accessedNode = valuesMap.get(key);
        dll.detachNode(accessedNode);
        dll.insertAfterHead(accessedNode);
    }
}
