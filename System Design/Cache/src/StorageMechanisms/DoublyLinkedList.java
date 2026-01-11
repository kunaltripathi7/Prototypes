package StorageMechanisms;

public class DoublyLinkedList<K> {
    private final Node<K> dummyHead;
    private final Node<K> dummyTail;

    public DoublyLinkedList() {
        dummyHead = new Node<K>( null);
        dummyTail = new Node<K>(null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    Node<K> getHead() {
        if (dummyHead.next == dummyTail) return null;
        return dummyHead.next;
    }


    Node<K> getTail() {
        if (dummyTail.prev == dummyHead) return null;
        return dummyTail.prev;
    }

    void insertAfterHead(Node<K> node) {
        Node<K> currHeadNode= dummyHead.next;
        node.next = currHeadNode;
        currHeadNode.prev = node;
        node.prev = dummyHead;
        dummyHead.next = node;
    }



    void detachNode(Node<K> node) {
        Node<K> prevNode = node.prev;
        Node<K> nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    void removelastNode() {
        if (dummyTail.prev == dummyHead) throw new IllegalArgumentException("dfasfdsaf");
        detachNode(dummyTail.prev);
    }

}

