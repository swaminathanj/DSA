// Aum Amma

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
