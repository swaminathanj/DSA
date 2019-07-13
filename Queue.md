# Queue

## Implementing Queue by extending LinkedList

In a queue, elements are added at one end (last) and removed from the other end (first). i.e. queue adheres to First-In-First-Out (FIFO) property.

In this implementation, we will implement Queue by extending LinkedList although one could implement Queue from scratch.

``` java
// Queue.java

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
// QueueTest.java

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

## Implementation of Doubly-Ended-Queue (Deque)

Doubly-Ended-Queue is an enhanced implementation of the queue wherein insertion and deletion can be done at both ends. Thus, Deque supports superset of operations in comparison to Queue.

Here, we will implement Deque by extending Queue. It supports insertFront, insertRear, deleteFront, deleteRear and rear methods. 
  - The insertRear and deleteFront methods call enqueue and dequeue methods of Queue. 
  - The insertFront method calls insertFirst of LinkedList.
  - The deleteLast method needs to be implemented completely since no such method exists in LinkedList
  - The rear method provides the element at the rear node.
  
  ``` java
  // Deque.java
  
public class Deque extends Queue {

    public void insertFront(int key) {
        insertFirst(key);  // Calls LinkedList's insertFirst
    }

    public int deleteFront() {
        return dequeue();  // Calls Queue's dequeue method
    }

    public void insertRear(int key) {
        enqueue(key);  // Calls Queue's enqueue method
    }

    public int deleteRear() {
        /* Note: We don't have deleteLast in either LinkedList 
           or Queue. So, we have to implement the logic here */

        // Find the size of the queue
        int len = size();  // Calls LinkedList's size method

        // Last node is at (len-1)th position
        Node curr = head;
        for (int i=0; i<len-2; i++) // reach last but one node
            curr = curr.next;

        // Copy the data in last node
        int val = curr.next.data;

        // Reset curr's next to null thereby dropping last node
        curr.next = null;

        return val;
                    // Takes O(n) time. How to make it O(1)?
    }

    public int rear() {
        int len = size();  // Find the length

        Node curr = head;
        for (int i=0; i<len-1; i++)  // Get to last node
            curr = curr.next; 

        return curr.data; 
    }
}
  ```
``` java
// DequeTest.java
public class DequeTest {
    public static void main(String[] args) {
        Deque d = new Deque();
        System.out.println( d.isEmpty() ); // prints true
        d.insertFront(45);
        d.insertRear(20);
        d.insertFront(15);
        d.insertFront(25);
        System.out.println( d.rear() ); // prints 20
        d.deleteRear();
        System.out.println( d.rear() ); // prints 45
        d.enqueue(30);
        d.dequeue();
        System.out.println( d.isEmpty() ); // prints false
        d.print(); // prints 15 45 30
    }
}
```
