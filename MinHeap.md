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

## 5. Add method extractMin to retrieve the (minimum) element

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
        for (int i=(size-2)/2; i>=0; i--) {
            fixHeap(i);
		}
    }

    /** Retrieve the minimum element - that is at root (index 0) */
    int extractMin() {
        int val = arr[0]; // First copy the value at root
        arr[0] = arr[size-1]; // Bring last element to root
		fixHeap(0); // Fix the heap from the root
		size--;

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
        m.print();

        System.out.println( m.extractMin() ); // prints 0
        m.print(); // prints 1 2 5 4 3 8 7 9 6
                   // only 9 elements are printed
    }
}
```
