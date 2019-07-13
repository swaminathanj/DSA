// Aum Amma

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
