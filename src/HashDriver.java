// Aum Amma

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));
        System.out.println(h.hash(102));
        System.out.println(h.hash(103));

        h.put(10, 100);
        h.put(8, 64);
        h.put(6, 36);

        if ( h.contains(10) )
            System.out.println( h.get(10) );   // prints 100
        if ( h.contains(50) )
            System.out.println( h.get(50) );  // This statement is not executed

        h.print();
        h.remove(6);
        h.print();
    }
}