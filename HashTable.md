# Step-by-step Implementation of Hash Table

## 1. Define HashNode

A HashNode holds a single key-value pair. A constructor is used to initialize key and value.

``` java
// HashNode.java

public class HashNode {
    int key; // It is not necessary that key and value
    int value; // must be integers.

    HashNode(int k, int v) {
        key = k;
        value = v;
    }
}
```

## 2. Define a Hash Table

A HashTable contains an array of HashNode. The size of the the array is initialized to a large enough value and is usually a prime number.

``` java
// HashNode.java  -- remains same as above
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
    }
}
```

## 3. Define a hash function

The purpose of hash function is to take the key as a parameter, apply some computation and return a number within the range 0 ... SIZE-1. The function should be good enough to return indices that uniformly distribute over the range 0 and SIZE-1. Essentially, the goal is to minimize collisions.

Here we define hash function to be: hash(x) = x<sup>3</sup> + 3x<sup>2</sup> + 1.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    public int hash(int x) {
        return (x*x*x + 3*x*x + 1) % SIZE;
    }
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));  // prints 97
        System.out.println(h.hash(102));  // prints 706
        System.out.println(h.hash(103));  // prints 936
    }
}
```

## 4. Implement put method

The put method inserts a <key,value> pair to the hash table.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    public int hash(int x) { ... }
    
    // Add a key-value pair to the hash table
    public void put(int k, int v) {
        int index = hash(k);
        HashNode hn = new HashNode(k,v);
        harr[index] = hn;
    }    
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));  // prints 97
        System.out.println(h.hash(102));  // prints 706
        System.out.println(h.hash(103));  // prints 936
        
        h.put(10, 100);
        h.put(8, 64);
        h.put(6, 36);
    }
}
```

## 5. Implement get method

The put method inserts a <key,value> pair to the hash table.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    public int hash(int x) { ... }
    public void put(int k, int v) { ... }
    
    // Retrieve a value from hashtable based on a key
    public int get(int k) {
        HashNode n = harr[hash(k)];
        if ( n == null)  // k is not present
            return Integer.MAX_VALUE;
        else
            return n.value;
    }    
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));  // prints 97
        System.out.println(h.hash(102));  // prints 706
        System.out.println(h.hash(103));  // prints 936
        
        h.put(10, 100);
        h.put(8, 64);
        h.put(6, 36);
        
        System.out.println( h.get(10) );  // prints 100
        System.out.println( h.get(50) );  // prints invalid value -2147483648
    }
}
```

## 6. Implement contains method

The contains method checks if an entry with key k is present in the hash table. If so, it returns true. Otherwise false.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    public int hash(int x) { ... }
    public void put(int k, int v) { ... }
    public int get(int k) { ... }
    
    // Check if the hashtable contains key k
    public boolean contains(int k) {
        if ( harr[hash(k)] != null )
            return true;
        else
            return false;
    }
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));  // prints 97
        System.out.println(h.hash(102));  // prints 706
        System.out.println(h.hash(103));  // prints 936
        
        h.put(10, 100);
        h.put(8, 64);
        h.put(6, 36);
        
        if ( h.contains(10) )
            System.out.println( h.get(10) );   // prints 100
        if ( h.contains(50) )
            System.out.println( h.get(50) );  // Statement not reached
    }
}
```

## 7. Implement remove method

Given a key k, the remove method deletes the corresponding entry from the hash table.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    public int hash(int x) { ... }
    public void put(int k, int v) { ... }
    public int get(int k) { ... }
    public boolean contains(int k) { ... }
    
    // Remove a key-value pair from hashtable given a key
    public void remove(int k) {
        int index = hash(k);
        harr[index] = null;
    }
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));  // prints 97
        System.out.println(h.hash(102));  // prints 706
        System.out.println(h.hash(103));  // prints 936
        
        h.put(10, 100);
        h.put(8, 64);
        h.put(6, 36);
        
        if ( h.contains(10) )
            System.out.println( h.get(10) );   // prints 100
        if ( h.contains(50) )
            System.out.println( h.get(50) );  // Statement not reached
            
        h.remove(6);
    }
}
```

## 8. Implement print method

The print method prints the <key, value> pairs in the hash table. It scans through the harr from 0 and SIZE-1 and if the entry is not null, it prints the key and value of the entry.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    public int hash(int x) { ... }
    public void put(int k, int v) { ... }
    public int get(int k) { ... }
    public boolean contains(int k) { ... }
    public void remove(int k) { ... }
    
    // Print the entries in the hashtable
    public void print() {
        for (int i=0; i<harr.length; i++) {
            if ( harr[i] != null )
                System.out.println(harr[i].key 
                        + " : " + harr[i].value 
                        + " stored at index " + hash(harr[i].key));
        }
}
```

``` java
// HashDriver.java

public class HashDriver {
    public static void main(String[] args) {
        HashTable h = new HashTable();
        System.out.println(h.hash(101));  // prints 97
        System.out.println(h.hash(102));  // prints 706
        System.out.println(h.hash(103));  // prints 936
        
        h.put(10, 100);
        h.put(8, 64);
        h.put(6, 36);
        
        if ( h.contains(10) )
            System.out.println( h.get(10) );   // prints 100
        if ( h.contains(50) )
            System.out.println( h.get(50) );  // Statement not reached
        
        h.print();  // prints 3 entries (10, 100) (8, 64) and (6, 36) in arbitrary order
        h.remove(6);
        h.print();  // prints 3 entries (10, 100) (8, 64) in arbitrary order
    }
}
```

## 9. Handling collisions - Separate Chaining

It is possible that for two keys, say k1 and k2, hash(k1) is same as hash(k2). This is referred to as **collision**. In such a scenario, the existing <k1,v1> pair in the hash table will be replaced by <k2,v2>. This is because, the current implementation is defined to hold only one <key,value> entry at each index. This is a serious limitation.

To overcome this limitation, instead of having a single HashNode at each index, we can have a LinkedList of HashNodes.
  - To put a <key,value> pair, hash(key) is applied to reach the index i. Now the HashNode(key,value) is inserted into the linked list at harr[i].
  - To get a value for a given key, hash(key) is applied to reach the index i. Now, the linked list at harr[i] is searched to check for the presence of key, and if found, the corresponding value is returned.
  - Other methods, such as, contains, remove and print, are modified similarly.

The resulting code is given below.

``` java
// HashNode.java

public class HashNode {
    int key; // It is not necessary that key and value
    int value; // must be integers.

    HashNode(int k, int v) {
        key = k;
        value = v;
    }
}
```

``` java
// HashTable.java

import java.util.LinkedList;

public class HashTable {
    int SIZE = 997; // typically a large enough prime number
    LinkedList<HashNode>[] harr;

    HashTable() {
        harr = new LinkedList[SIZE];
        for (int i=0; i<SIZE; i++)
            harr[i] = new LinkedList<HashNode>();
    }

    public int hash(int x) {
        return (x*x*x + 3*x*x + 1) % SIZE;
    }

    // Add a key-value pair to the hash table
    public void put(int k, int v) {
        int index = hash(k);
        HashNode hn = new HashNode(k,v);
        harr[index].add(hn);
    }

    // Check if the hashtable contains key k
    public boolean contains(int k) {
        LinkedList<HashNode> llhn = harr[hash(k)];
        if ( llhn != null ) {
            for (int i=0; i<llhn.size(); i++) {
                if (llhn.get(i).key == k)
                    return true;
            }
        }
        return false;
    }

    // Retrieve a value from hashtable based on a key
    public int get(int k) {
        LinkedList<HashNode> llhn = harr[hash(k)];
        if ( llhn != null ) {
            for (int i=0; i<llhn.size(); i++) {
                if (llhn.get(i).key == k)
                    return llhn.get(i).value;
            }
        }
        return Integer.MIN_VALUE;      
    }

    // Remove a key-value pair from hashtable given a key
    public void remove(int k) {
        LinkedList<HashNode> llhn = harr[hash(k)];
        if ( llhn != null) {
            for (int i=0; i<llhn.size(); i++) {
                if (llhn.get(i).key == k)
                    llhn.remove(i);
            }
        }
    }

    // Print the entries in the hashtable
    public void print() {
        for (int i=0; i<harr.length; i++) {
            if ( harr[i] != null )
                for (int j=0; j<harr[i].size(); i++)
                    System.out.println(harr[i].get(j).key 
                        + " : " + harr[i].get(j).value);
        }
            
    }
}
```

``` java
// HashDriver.java

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
```

To summarize, an operation on HashTable is now a two-step process. Firstly, applying hash function and reaching the index. Followed by scanning of the linked list at that index. This brings down the performance.

**Worst case scenario**: In rare circumstances, it is possible that all the keys inserted into the hash table results in same index, causing all the <key,value> pairs to be inserted in the single linked list at that index. In such a scenario, retrieving a value degenerates to O(n) time instead of O(1) time. 
  - It is important that hash function is designed to be good enough to produce a wider spread indices. 
  - Also, the size of the harr should be large enough.
  
