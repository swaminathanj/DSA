// Aum Amma

public class Queue extends LinkedList {

    public void enqueue(int key) {
        insert(key); // Calling LinkedList's method
                     // To insert at the end. O(n)
                     // How to do it in O(1) time?
    }

    public int dequeue() throws RuntimeException {
        if ( isEmpty() )
            throw new RuntimeException("Queue is empty");
        else {
            int val = head.data; // Copy data
            deleteFirst();  // Remove first node. O(1)
            return val;
        }
    }

    public int front() throws RuntimeException {
        if ( isEmpty() )
            throw new RuntimeException("Queue is empty");
        else
            return head.data;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }
}
