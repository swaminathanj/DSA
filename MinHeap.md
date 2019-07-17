# MinHeap Implementation step-by-step

## 1. Define MinHeap, initialize, getter, printer and toString methods

As a first step, we define an array based MinHeap, a constructor that takes an array passed as argument to initialize the heap and a print method that outputs the contents of the heap. We override toString to stringify the object and a getter method to get the value at a specific index.

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

    /** This function is used to stringfy the object */
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
MinHeapInitTest.java

public class MinHeapInitTest {

    public static void main(String[] args) {
        int[] keys = {5, 3, 8, 6, 2, 1, 7, 9, 4, 0};
        MinHeap m = new MinHeap(keys);
        m.print();  // prints the above array
        System.out.println(m); // does same job as m.print();
        System.out.println( m.get(3) );  // prints 6 - data at index 3
    }
}
```

## 2. Add methods to get parent, left child and right children

## 3. Add method to swap elements at given indices

## 4. Add method to check if swap is required

## 5. Add method to fix heap underneath a node

## 6. Add method to build heap
