package StorageMechanisms;

public class Node<K> {
    Node<K> next;
    Node<K> prev;
    K value;

    public Node(K value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

}
