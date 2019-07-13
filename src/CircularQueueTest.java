// Aum Amma

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
