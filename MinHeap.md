# MinHeap Implementation step-by-step

## 1. Define MinHeap, add initialize, getter, printer and toString methods

As a first step, we define an array based MinHeap, a **constructor** that takes an array passed as argument to initialize the heap and a **print** method that outputs the contents of the heap. We override **toString** to stringify the object and a **getter** method to get the value at a specific index.

``` java
// MinHeap.java

public class MinHeap {
    int[] arr;

    /** Initializes the array object */
    public MinHeap(int[] keys) {
        arr = keys;  // Make arr point to keys array
    }

    /** An utility method to print contents of MinHeap object */
    public void print()  {
        System.out.print(arr[0]);
        for (int i=1; i<arr.length; i++)
            System.out.print(" " + arr[i]);
        System.out.println();
    }

    /** This overriden function is used to stringfy the contents of the object */
    @override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(arr[0]);
        for (int i=1; i< arr.length; i++)
            sb.append(" " + arr[i]);
        return sb.toString();
    }

    /** Retrieve the value at index i */
    public int get(int i) {
        return arr[i];
    }
}
```

``` java
// MinHeapInitTest.java

public class MinHeapInitTest {

    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // prints the above array
        System.out.println(m); // does same job as m.print();
                               // automatically invokes toString of m
        System.out.println( m.get(3) );  // prints 6 - data at index 3
    }
}
```

## 2. Add methods to get parent, left child and right child

Implement methods to compute the indices of parent, left child and right child, given an index i. With 0-based indexing,
  - parent of node at index i is located at index (i-1)/2.
  - left child of node at index i is located at index (2i+1).
  - right child of node at index i is located at index (2i+2).

Corner cases:
  - **root** (located at index 0) does not have parent
  - **leaf** (located at indices greater (n-1)/2 where n is the number of nodes) does not have both left and right children
  - The internal node at index (n-1)/2 may have left child only if n is even.

``` java
// MinHeap.java

public class MinHeap {
    int[] arr;

    public MinHeap(int[] keys) { ... }
    public void print()  { ... }
    public String toString() { ... }
    public int get(int i) { ... }
    
    /** Given index i, retrieve the index of its parent */
    public int parent(int i) {
        // Two cases to handle - root and non-root cases
        if (i == 0) // root node
            return 0;
        else 
            return (i-1)/2;
        /* Note: if i==0, (i-1)/2 = -1/2 = 0. 
           So last statement alone is enough */
    }

    public int left(int i) {
        // Two cases to handle - internal and leaf nodes
        if ( 2*i+1 < arr.length ) // 1. internal node
            return 2*i+1;
        else                      // 2. leaf node
            return -1;
    }

    public int right(int i) {
        // Two cases to handle - internal and leaf nodes
        if ( 2*i+2 < arr.length ) // 1. internal node
            return 2*i+2;
        else                      // 2. leaf node
            return -1;
    }
}
```

``` java
// MinHeapTest.java

public class MinHeapTest {

    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        
        System.out.println( m.parent(0) );  // prints 0
        System.out.println( m.get(m.parent(0)) );  // prints 5
        System.out.println( m.parent(8) );  // prints 3
        System.out.println( m.get(m.parent(5)) );  // prints 6

        if (m.left(3) != -1)  // print only if left child exists
            System.out.println( m.get(m.left(3)) );  // prints 9 
        if (m.left(5) != -1)  // print only if left child exists
            System.out.println( m.get(m.left(5) );  // unreachable

        if (m.right(3) != -1)  // print only if right child exists
            System.out.println( m.get(m.right(3)) );  // prints 4 
        if (m.right(5) != -1)  // print only if right child exists
            System.out.println( m.get(m.right(5)) );  // unreachable 
    }
}
```

## 3. Add methods to check heap property of a node and exchange elements
Two methods checkHeap(i) and exchange(i,j) are added. **checkHeap(i)** checks if the element at indices i, left(i) and right(i) to determine which the index of the the smallest element.

  - If index i itself is returned, no exchanges are necessary.
  - If left(i) is returned, then elements at i and left(i) need to be exchanged.
  - If right(i) is returned, then elements at i and right(i) need to be exchanged.
    
**exchange(i,j)** swaps the elements at indices i and j if check(i) returned j such that j != i.

``` java
// MinHeap.java

public class MinHeap {
    int[] arr;

    public MinHeap(int[] keys) { ... }
    public void print()  { ... }
    public String toString() { ... }
    public int get(int i) { ... }
    public int parent(int i) { ... }
    public int left(int i) { ... }
    public int right(int i) { ... }

    /** check indices i, left(i) & right(i) and return the index with smallest element */
    public int checkProperty(int i) {
        if (left(i) == -1 && right(i) == -1)
            return i;
        else if (right(i) == -1) {
            if (arr[i] < arr[left(i)])
                return i;
            else
                return left(i);
        }
        else if (arr[i] < arr[left(i)] && arr[i] < arr[right(i)])
            return i;
        else if (arr[i] > arr[left(i)] && arr[i] < arr[right(i)])
            return left(i);
        else if (arr[i] < arr[left(i)] && arr[i] > arr[right(i)])
            return right(i);
        else if (arr[left(i)] < arr[right(i)])
            return left(i);
        else
            return right(i);
    }

    /** Swap the elements at indices i and j */
    void exchange(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

``` java
// MinHeapTest.java

public class MinHeapTest {
    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // 5 3 8 6 2 1 7 9 4 0

        int j = m.checkProperty(0);
        if (j != 0)
            m.exchange(0,j);
            
        m.print(); // prints 3 5 8 6 2 1 7 9 4 0
        System.out.println( m.checkProperty(2) );  // prints 5   
    }
}
```

## 4. Add a method to build heap

Now only thing left to build heap is to start fixing the nodes which do not adhere to heap property. Two points to remember at this stage.

  1. Leaf nodes need no fixing since they don't have children. Given that there are n nodes, only nodes the 0 to (n-2)/2 needs to be fixed. Rest will be leaf nodes. The last node is at index n-1. This implies its parent can be found at index (n-1-1)/2 = (n-2)/2.
  2. Any exchange at higher level nodes can cause the subtree under them to become lose the heap property, one exchange is not sufficient. To this end, we define a **fixHeap** method to ensure the entire subtree under a node is fixed recursively.

``` java
// MinHeap.java

public class MinHeap {
    int[] arr;

    public MinHeap(int[] keys) { ... }
    public void print()  { ... }
    public String toString() { ... }
    public int get(int i) { ... }
    public int parent(int i) { ... }
    public int left(int i) { ... }
    public int right(int i) { ... }
    public int checkProperty(int i) { ... }
    void exchange(int i, int j) { ... }
    
    /** Ensures the entire subtree under index i is fixed */
    void fixHeap(int i) {
        int j = checkProperty(i);
        if (i == j)  // arr[i] < arr[left(i)], arr[right(i)]
            return;  // Nothing to do. Subtree under i is heap.
        else {
            exchange(i,j);  // can make subtree under j unstable
            fixHeap(j);  // fix the underneath node j recursively
        }
    }

    /** Start fixing from last parent till root */
    void buildHeap() {
        for (int i=(arr.length-2)/2; i>=0; i--)
            fixHeap(i);
    }
```

``` java
// MinHeapTest.java

public class MinHeapTest {
    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // prints 5 3 8 6 2 1 7 9 4 0

        m.buildHeap(); // build the heap and print it
        m.print();  // prints 0 2 1 4 3 8 7 9 6 5
    }
}
```

## 5. Add method extractMin to retrieve the minimum element

The minimum element is sitting at the root. i.e. index 0. Once the element at index 0 (root) is removed, the last element of the heap is put in the root's position. Since, this will cause the heap property to be lost at the root level, fixHeap(0) can be called to ensure the tree is turned into a heap once again. In other words, fixHeap(0) triggers some exchanges leading to the next minimum element reaching the root and the heap property satisfied by all nodes. 

``` java
// MinHeap.java

public class MinHeap {
    int[] arr;

    public MinHeap(int[] keys) { ... }
    public void print()  { ... }
    public String toString() { ... }
    public int get(int i) { ... }
    public int parent(int i) { ... }
    public int left(int i) { ... }
    public int right(int i) { ... }
    public int checkProperty(int i) { ... }
    void exchange(int i, int j) { ... }
    void fixHeap(int i) { ... }
    void buildHeap() { ... }
    
    int extractMin() {
        int val = arr[0];  // First copy the value at root
        arr[0] = arr[arr.length-1];  // Bring last element to root
	fixHeap(0);  // Fix the heap from the root

        return val;
    }    
```

``` java
// MinHeapTest.java

public class MinHeapTest {
    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // prints 5 3 8 6 2 1 7 9 4 0

        m.buildHeap(); // build the heap and print it
        m.print();  // prints 0 2 1 4 3 8 7 9 6 5
        
        System.out.println( m.extractMin() );
        m.print();  // prints 1 2 5 4 3 8 7 9 6 5
                    // The 5 at the end is duplicate and invalid
    }
}
```
**Note**: The extractMin causes the heap size to reduce by one. This implies, the last element of the arr is invalid. The print method, in its current implementation, will print it. Since there is no way to shrink arr, a way to handle this scenario is to define an attribute **size** to track the current heap size. As extractMin is called each time, the size is decremented. The print function can be modified to print elements upto size and not till arr.length. In fact, all methods that use arr.length must be modified to use **size** instead of arr.length. i.e. **print, toString, left, right, buildHeap and extractMin**. The size can be initialized to arr.length in the constructor.

## 6. Replace arr.length references to size

The complete program with references to arr.length replaced by size is given below.

``` java
// MinHeap.java

public class MinHeap {
    int[] arr;
    int size; // Tracks the size of the heap

    /** Initializes the array object */
    public MinHeap(int[] keys) {
        arr = keys;  // Make arr point to keys array
	size = arr.length; // initialize size to arr.length
    }

    /** An utility method to print contents of MinHeap object */
    public void print()  {
        System.out.print(arr[0]);
        for (int i=1; i<size; i++)
            System.out.print(" " + arr[i]);
        System.out.println();
    }

    /** This function is used to stringfy the object */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(arr[0]);
        for (int i=1; i< size; i++)
            sb.append(" " + arr[i]);
        //sb.append("\n");
        return sb.toString();
    }

    /** Retrieve the value at index i */
    public int get(int i) {
        return arr[i];
    }

    /** Given index i, retrieve the index of its parent */
    public int parent(int i) {
        // Two cases to handle - root and non-root cases
        if (i == 0) // root node
            return 0;
        else 
            return (i-1)/2;
        /* Note: if i==0, (i-1)/2 = -1/2 = 0. 
           So last statement alone is enough */
    }

    /** Given index i, retrieve the index of its left child */
    public int left(int i) {
        // Two cases to handle - internal and leaf nodes
        if ( 2*i+1 < size ) // 1. internal node
            return 2*i+1;
        else                // 2. leaf node
            return -1;
    }

    /** Given index i, retrieve the index of its right child */
    public int right(int i) {
        // Two cases to handle - internal and leaf nodes
        if ( 2*i+2 < size ) // 1. internal node
            return 2*i+2;
        else                // 2. leaf node
            return -1;
    }

    /** check indices i, left(i) & right(i) and return the index with smallest element */
    public int checkProperty(int i) {
        if (left(i) == -1 && right(i) == -1)
            return i;
        else if (right(i) == -1) {
            if (arr[i] < arr[left(i)])
                return i;
            else
                return left(i);
        }
        else if (arr[i] < arr[left(i)] && arr[i] < arr[right(i)])
            return i;
        else if (arr[i] > arr[left(i)] && arr[i] < arr[right(i)])
            return left(i);
        else if (arr[i] < arr[left(i)] && arr[i] > arr[right(i)])
            return right(i);
        else if (arr[left(i)] < arr[right(i)])
            return left(i);
        else
            return right(i);
    }

    /** Swap the elements at indices i and j */
    void exchange(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /** Ensures the entire subtree under index i is fixed */
    void fixHeap(int i) {
        int j = checkProperty(i);
        if (i == j)
            return;
        else {
            exchange(i,j);
            fixHeap(j);
        }
    }

   /** Start fixing from last parent till root */
   void buildHeap() {
        for (int i=(size-2)/2; i>=0; i--) 
            fixHeap(i);
    }

    /** Retrieve the minimum element - that is at root (index 0) */
    int extractMin() {
        int val = arr[0]; // First copy the value at root
	
        arr[0] = arr[size-1]; // Bring last element to root
	fixHeap(0); // Fix the heap from the root
	size--;  // Decrement the size

        return val;
    }
}
```

``` java
// MinHeapTest.java

public class MinHeapTest {

    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // prints the above array

        m.buildHeap();
        m.print();  // prints 0 2 1 4 3 8 7 9 6 5

        System.out.println( m.extractMin() ); // prints 0
        m.print(); // prints 1 2 5 4 3 8 7 9 6
                   // only 9 elements are printed now
    }
}
```

## 7. Randomizing input array

A way to test the program with different inputs is to use Random class to decide both the size and contents of the array. The test driver can be modified to this effect, so that every run of the program will greate a different series. Also, in order to check if the **buildHeap** really did construct the heap right, we can use the **checkProperty** on every internal node which should return the same index that it took as a parameter confirming parent < left, right always.

``` java
// MinHeapRandomTest.java

import java.util.Random;

public class MinHeapRandomTest {

    public static void main(String[] args) {
        //int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
	
	// 1. Decide the size randomly
	Random r = new Random();
	int size = r.nextInt(100)+1; // Any number between 1-100
	int[] keys = new int[size]; // create an array of such size
	
	// 2. Populate the array with random elements
	for (int i=0; i<size; i++)
	    keys[i] = r.nextInt(100); // Any number between 0-99

      	// 3. Build the heap
	MinHeap m = new MinHeap(keys);
        m.print();  // prints the above array
	m.buildHeap();
        m.print();  // prints the heap constructed
	
	// 4. Check if every internal node satisfied heap property
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
```

### Sample runs of the program

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

66 8 71 36 67 81 3 4 39 50 45 85 76 39 67 38 15 59 94 89 93 31 51 38 62 89 2 56 16 20 81 4 54 10 90 18 50 98 30 71 49

2 4 3 4 31 38 16 8 18 49 45 62 76 39 20 36 10 39 30 50 93 67 51 85 71 89 81 56 66 67 81 38 54 15 90 59 50 98 94 71 89

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

26 12 94 43 95 81 79 94 8 53 8 7 36 80 7 39 12 92 76 46 29 92 2 61 86 6 70 90

2 8 6 8 12 7 7 12 43 29 26 61 36 80 79 39 94 92 76 46 53 92 95 94 86 81 70 90

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

26 63 31 65 37 29 51 85 20 60 36 18 49 34 14 15 53 3 53 55 47 13 66 20 8 30 45 50 80 20 60 42 75 56 95 47 91 51 18 6 62 33 50 62 3 0 95 34 96 29 96 0 11 95 92 28 16 5

0 0 5 3 3 8 14 15 18 6 13 18 11 16 20 42 53 20 51 55 33 37 36 20 29 29 45 28 34 26 60 85 75 56 95 47 91 65 53 60 62 47 50 62 63 66 95 34 96 31 96 30 49 95 92 51 50 80

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

8 58 52 25 55 83 73 16 58 6 91 79 37 63 95 31 68 65 50 98 92 21 4 40 56 39

4 6 37 16 8 39 63 25 50 58 21 40 52 73 95 31 68 65 58 98 92 55 91 79 56 83

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

42 92 9 57 53 67 49 35 56 49 85 81

9 35 42 56 49 67 49 57 92 53 85 81

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

0 67 3 37 92 35 10 50 75 79 38 12 16 17 46 15 19 88 19 80 29 7 64 42 99 61 86 71 64 52 56 53 79 66 62 87 30 46 2 94 26 56 8 33 91 52 61 8 6 8 83 2 46 49 4 54 61 87 72 28 9 15 52 2 8 66 89 57

0 2 2 2 7 3 9 8 19 8 33 6 4 17 10 15 19 30 37 26 29 38 52 8 8 16 35 54 64 28 15 50 66 57 62 87 88 46 75 94 80 56 79 92 91 64 61 12 42 99 83 61 46 49 86 71 61 87 72 46 52 56 52 53 67 79 89 66

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

55 3 94 66 79 59 28 15 9 69 18 62 63 4 58 74 56 84 81 26 19 43 89 3 18 51 77 94 37 90 38 58 45 96 25 51 50 54 60 14 97 84 30 85 15 72 91 19 51 19 99 25 89 1 18 44 9 53 6 67 79 27 70 14 74 17 53 77

1 3 3 9 14 18 4 14 50 19 15 18 25 6 27 15 25 51 54 26 30 18 72 19 19 51 59 9 28 67 38 58 17 77 56 66 84 81 60 69 97 84 79 85 43 89 91 62 51 94 99 55 89 77 63 44 94 53 37 90 79 58 70 74 74 45 53 96

Test passed

C:\Users\swaminathanj\dsa\code>java MinHeapRandomTest

73 28 48 8 81 29 15 21 1 84 79 26 59 76 68 0 63 4 73 7 30 76 61 20 7 49 85 7 78 31 39 43 76 55 82 36 32 58 98 23 9 81 58 76 72 17 79 66 90 74 63 53 51 12 46 41 98 86 98 80 53 26 50

0 1 7 4 7 7 15 8 28 9 17 20 12 41 26 21 55 32 58 23 30 72 61 29 26 49 46 48 78 31 39 43 76 63 82 36 73 73 98 81 84 81 58 76 76 79 79 66 90 74 63 53 51 85 59 76 98 86 98 80 53 68 50

Test passed


