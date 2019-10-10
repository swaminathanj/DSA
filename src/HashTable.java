// Aum Amma

public class HashTable {
    int SIZE = 997; // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];

    public int hash(int x) {
        return (x*x*x + 3*x*x + 1) % SIZE;
    }

    // Add a key-value pair to the hash table
    public void put(int k, int v) {
        int index = hash(k);
        HashNode hn = new HashNode(k,v);
        harr[index] = hn;
    }

    // Check if the hashtable contains key k
    public boolean contains(int k) {
        if ( harr[hash(k)] != null )
            return true;
        else
            return false;
    }

    // Retrieve a value from hashtable based on a key
    public int get(int k) {
        HashNode n = harr[hash(k)];
        if ( n == null)  // k is not present
            return Integer.MIN_VALUE;
        else
            return n.value;
    }

    // Remove a key-value pair from hashtable given a key
    public void remove(int k) {
        int index = hash(k);
        harr[index] = null;
    }

    // Print the entries in the hashtable
    public void print() {
        for (int i=0; i<harr.length; i++) {
            if ( harr[i] != null )
                System.out.println(harr[i].key 
                        + " : " + harr[i].value 
                        + " stored at index " + hash(harr[i].key));
        }
            
    }
}