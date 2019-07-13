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
  - The **insertRear** and **deleteFront** methods call **enqueue** and **dequeue** methods of Queue respectively. 
  - The **insertFront** method calls **insertFirst** of LinkedList.
  - The **deleteLast** method needs to be implemented completely since no such method exists in LinkedList
  - The **rear** method provides the element at the rear node.
  
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

The **deleteRear** and **rear** methods do not check if the Deque is empty. Add this check and take appropriate action in the event that Deque is empty.

## Implementing Array-based Circular Queue

Array based implementation of circular queue is common for two reasons.
  1. Array-based implementation is lot faster than LinkedList based implementation.
  2. In practical scenarios, working with bounded sized buffer/queue is more common than the need for infinitely-sized queue.

``` java
// CircularQueue.java

public class CircularQueue {
    int[] arr;
    int front, rear;
    int MAXSIZE;

    CircularQueue() { // Default constructor
        MAXSIZE = 10; // queue size is set to 10 by default
        arr = new int[MAXSIZE];
        front = -1;
        rear = -1;
    }

    CircularQueue(int k) {
        MAXSIZE = k;
        arr = new int[MAXSIZE];
        front = -1;
        rear = -1;
    }

    void enqueue(int key) throws RuntimeException {
        // 3 cases to be handled

        if ( isEmpty() ) { // 1. Queue is empty
            front = 0;
            rear = 0;
            arr[rear] = key; // add to rear
        }
        else if ( isFull() ) { // 2. Queue is full
            throw new RuntimeException("Queue is full");
        }
        else { // 3. Queue has at least one item but not full
            rear = (rear+1) % MAXSIZE;
            arr[rear] = key; // add to rear
        }
    }

    int dequeue() throws RuntimeException {
        // 3 cases to be handled

        if ( isEmpty() ) { // 1. Queue is empty
            throw new RuntimeException("Queue is empty");
        }
        else if ( front == rear ) { // 2. Queue has one item
            int val = arr[front];
            front = -1;  // Reset front & back to -1
            rear = -1;
            return val;
        }
        else { // 3. Queue has more than one time
            int val = arr[front];
            front = (front+1) % MAXSIZE;
            return val;
        }
    }

    int getFront() {
        if ( isEmpty() )
            throw new RuntimeException("Queue is empty");
        else
            return arr[front];
    }

    boolean isEmpty() {
        if ( front == -1 && rear == -1 )
            return true;
        else
            return false;
    }

    boolean isFull() {
        if ( (rear+1)%MAXSIZE == front )
            return true;
        else
            return false;
    }

    int size() {
        if ( isEmpty() )
            return 0;
        else if ( isFull() )
            return MAXSIZE;
        else
            return (rear-front+1) % MAXSIZE;
    }

    void print() {
        for (int i=front; i!=rear; i=(i+1)%MAXSIZE)
            System.out.print(arr[i] + " ");
        System.out.println(arr[rear]);
    }
}
```

``` java
// CircularQueueTest.java

public class CircularQueueTest {
    public static void main(String[] args) {
        CircularQueue c = new CircularQueue(5);
        System.out.println( c.size() ); // prints 0
        c.enqueue(10);
        c.enqueue(20);
        c.enqueue(30);
        System.out.println( c.size() ); // prints 3
        c.enqueue(40);
        c.enqueue(50);
        c.print();  // prints 10 20 30 40 50
        System.out.println( c.size() );  // prints 5
        System.out.println( c.getFront() ); // prints 10
        c.dequeue();
        c.enqueue(60);
        System.out.println( c.size() );  // prints 5
        c.dequeue();
        c.print();  // prints 30 40 50 60
        c.dequeue();
        System.out.println( c.getFront() );  // prints 40
        c.dequeue();
        c.dequeue();
        c.dequeue();
        System.out.println( c.size() ); // prints 0
    }
}
```

``` java
// CircularQueueFullTest.java

public class CircularQueueFullTest {
    public static void main(String[] args) {
        CircularQueue c = new CircularQueue(5);
        c.enqueue(10); 
        c.enqueue(20); 
        c.enqueue(30); 
        c.enqueue(40); 
        c.enqueue(50); 
        c.enqueue(60); // program terminates due to exception

        // try .. catch if you don't want program to terminate
    }
}
```

``` java
// CircularQueueEmptyTest.java

public class CircularQueueEmptyTest {
    public static void main(String[] args) {
        CircularQueue c = new CircularQueue(5);
        c.dequeue(); // program terminates due to exception

        // try .. catch if you don't want program to terminate
    }
}
```


  
