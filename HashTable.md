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
    int SIZE = 997; // typically a large prime number
    HashNode[] harr = new HashNode[SIZE];
}
```
