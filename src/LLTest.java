// Aum Amma

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insert(5);
        l.insert(20);
        l.insert(12);
        l.insert(25);
        l.print();
        System.out.println( l.size() );

        l.delete(12);
        l.print();  // prints 5 20 25
        l.delete(30); // 30 not in the list, nothing is removed
        l.print();  // prints 5 20 25
        System.out.println( l.size() );
    }
}
