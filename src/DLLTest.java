// DLLTest.java

public class DLLTest {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insert(0,5);
        dll.insert(10);
        dll.insert(15);
        dll.insert(20);
        dll.print();

        dll.insert(4,30);
        dll.insert(4,25);
        dll.insert(0,2);
        dll.print();

        dll.delete(30);
        dll.insert(10,100);
        dll.print();
    }
}
