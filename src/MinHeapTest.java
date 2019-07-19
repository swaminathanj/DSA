// Aum Amma

public class MinHeapTest {

    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // prints the above array
        /*  
        System.out.println(m); // does same job as m.print();
        System.out.println( m.get(3) );  // prints 6 - data at index 3
        
        System.out.println( m.parent(0) );  // prints 0
        System.out.println( m.get(m.parent(0)) );  // prints 5
        System.out.println( m.parent(8) );  // prints 3
        System.out.println( m.get(m.parent(5)) );  // prints 6

        int l = m.left(3);
        if (l != -1)  // print only if left child exists
            System.out.println( m.get(l) );  // prints 9 
        l = m.left(5);
        if (l != -1)  // print only if left child exists
            System.out.println( m.get(l) );  // unreachable 

        int r = m.right(3);
        if (r != -1)  // print only if right child exists
            System.out.println( m.get(r) );  // prints 4 
        r = m.left(5);
        if (r != -1)  // print only if right child exists
            System.out.println( m.get(4) );  // unreachable 
        
        int j = m.checkProperty(0);
        if (j != 0)
            m.exchange(0,j);
        m.print();
        System.out.println( m.checkProperty(2) ); 
        */

        m.buildHeap();
        m.print();

        System.out.println( m.extractMin() ); // prints 0
        m.print(); // prints 1 2 5 4 3 8 7 9 6
                   // only 9 elements are printed
    }
}
