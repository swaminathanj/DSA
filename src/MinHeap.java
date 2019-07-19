// Aum Amma

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

    void exchange(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void fixHeap(int i) {
        int j = checkProperty(i);
        if (i == j)
            return;
        else {
            exchange(i,j);
            fixHeap(j);
        }
    }

    void buildHeap() {
        for (int i=(size-2)/2; i>=0; i--) {
            fixHeap(i);
		}
    }

    int extractMin() {
        int val = arr[0]; // First copy the value at root
        arr[0] = arr[size-1]; // Bring last element to root
		fixHeap(0); // Fix the heap from the root
		size--;

        return val;
    }
}
