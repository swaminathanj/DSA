// Aum Amma

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
