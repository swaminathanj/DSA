# Queue

## Implementing Queue by extending LinkedList

In a queue, elements are added at one end (last) and removed from the other end (first). FIFO property.

``` java
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
```

``` java
public class QueueTest {
    public static void main(String[] args) {
        Queue q = new Queue();
        System.out.println( q.isEmpty() ); // prints true
        q.enqueue(45);
        q.enqueue(20);
        q.enqueue(15);
        System.out.println( q.front() ); // prints 45
        q.dequeue();
        System.out.println( q.front() ); // prints 20
        q.enqueue(30);
        q.enqueue(50);
        System.out.println( q.isEmpty() ); // prints false
        q.print(); // prints 20 15 30 50
    }
}
```
