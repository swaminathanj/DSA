
public class CircularQueueEmptyTest {
    public static void main(String[] args) {
        CircularQueue c = new CircularQueue(5);
        c.dequeue(); // program terminates due to exception

        // try .. catch if you don't want program to terminate
    }
}
