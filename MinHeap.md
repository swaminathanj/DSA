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
Two methods checkHeap(i) and exchanged(i,j) are added.

**checkHeap(i)** checks if the element at indices i, left(i) and right(i) to determine which the index of the the smallest element.
  - If index i itself is returned, no exchanges are necessary.
  - If left(i) is returned, then elements at i and left(i) need to be exchanged.
  - If right(i) is returned, then elements at i and right(i) need to be exchanged.

## 4. Add method to check if swap is required

## 5. Add method to fix heap underneath a node

## 6. Add method to build heap
