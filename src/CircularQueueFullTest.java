
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
