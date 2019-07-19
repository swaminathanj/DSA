import java.util.Random;

public class MinHeapRandomTest {

    public static void main(String[] args) {
        //int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
	
	// Decide the size randomly
	Random r = new Random();
	int size = r.nextInt(100)+1; // Any number between 1-100
	int[] keys = new int[size]; // creat an array of such size
	
	// Decide the element randomly
	for (int i=0; i<size; i++)
	    keys[i] = r.nextInt(100); // Any number between 0-99
	    
        MinHeap m = new MinHeap(keys);
        m.print();  // prints the above array

        m.buildHeap();
        m.print();  // prints 0 2 1 4 3 8 7 9 6 5
	
	// Check if every internal node holds heap property
	for (int i=0; i<=(size-2)/2; i++) {
        if ( m.right(i) != -1 ) { // if both left and right child exists
	        if ( m.get(i) > m.get(m.left(i)) || m.get(i) > m.get(m.right(i)) ) {
	            System.out.println("Test failed");
                return;
            }      
        }
        else { // if left child alone exists
            if ( m.get(i) > m.get(m.left(i)) ) {
	            System.out.println("Test failed");
                return;
            }  
        }
	}
	System.out.println("Test passed");
    }
}