# Step-by-step Implementation of (basic) Hash Table

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
## 3. Define a hash function

The purpose of hash function is to take the key as a parameter, apply some computation and return a number within the range 0 ... SIZE-1. The function should be good enough to return indices that uniformly distribute over the range 0 and SIZE-1. Essentially, the goal is to minimize collisions.

``` java
// HashNode.java  -- remains same
```

``` java
// HashTable.java

public class HashTable {
    int SIZE = 997;   // typically a large enough prime number
    HashNode[] harr = new HashNode[SIZE];
    
    private int hash(int x) {
        return (x*x*x + 3*x*x + 1) % SIZE;
    }
}
```
