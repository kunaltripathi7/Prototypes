package StorageMechanisms;

import StorageMechanisms.Interfaces.EvictionAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K, V> implements EvictionAlgorithm<K> {
    private DoublyLinkedList<K> dll = new DoublyLinkedList<K>();
    private Map<K, Node<K>> valuesMap = new HashMap<>();

    @Override
    public synchronized K evictKey() {
        Node<K> nodeToEvict = dll.getTail();
        if (nodeToEvict == null) return null;
        dll.removelastNode();
        return nodeToEvict.value;
    }

    @Override
    public synchronized void updateKeyAccessed(K key) {
        if (valuesMap.containsKey(key)) {
        Node<K> accessedNode = valuesMap.get(key);
        dll.detachNode(accessedNode);
        dll.insertAfterHead(accessedNode);
        }
        else {
            Node<K> newNode =new  Node<K>(key);
            dll.insertAfterHead(newNode);
            valuesMap.put(key, newNode);
        }
    }
}
