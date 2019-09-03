# Disjoint Set

## 1. Defining a Disjoint Set Node and test its working

A disjoint set node contains a label and a reference to is parent. You can include data in form of any type apart from these.

``` java
// DSNode.java

public class DSNode {
    int label;
    DSNode parent;

    public DSNode(int l) {
        label = l;
    }
}
```

``` java
// DSNodeTest.java

public class DSNodeTest {
    public static void main(String[] args) {
        DSNode n1 = new DSNode(5);
        DSNode n2 = new DSNode(8);
        n1.parent = n2;
        System.out.println(n1.label);
        System.out.println(n2.label);
        System.out.println(n1.parent.label);
    }
}
```

## 2. Defining DisjointSet

  - The distjoint set of size n consists initially of n nodes denoting n distinct sets. The makeSet() method creates these distinct sets (nodes), each with a single item labelled 0, 1, ..., n-1. 
  - The parent of each node is null.

``` java
// DSNode.java -- Definition remains the same
```

``` java
// DisjointSet.java

public class DisjointSet {
    DSNode[] node;
    int size;

    public void makeSets(int n) {
       size = n;
       node = new DSNode[size];
       for (int i=0; i<size; i++)
            node[i] = new DSNode(i);
    }
}
```

``` java
// DisjointSetTest.java

public class DisjointSetTest {
    public static void main(String[] args) {
        DisjointSet d = new DisjointSet();
        d.makeSets(10);
    }
}
```


## 3. Implementing Find

  - The find(x) method determines the root of each node/set by navigating through the parent pointer until parent is null. 
  - Note that initially the parent of each node is null. Therefore, each node is the root of itself. 
  - Let's add a print() method to print each set and its root.

``` java
// DSNode.java -- Definition remains the same
```

``` java
// DisjointSet.java

public class DisjointSet {
    DSNode[] node;
    int size;

    public void makeSets(int n) { ... }
    
    public DSNode find(int key) {
        DSNode root = node[key];
        while (root.parent != null)
            root = root.parent;
        return root;
    }

    // Prints the labels of each node and its root
    public void print() {
        for (int i=0; i<size; i++)
            System.out.print("(" + i + "," + find(i).label + ") ");
        System.out.println();
    }
}
```

``` java
// DisjointSetTest.java

public class DisjointSetTest {
    public static void main(String[] args) {
        DisjointSet d = new DisjointSet();
        d.makeSets(10);
        d.print(); // prints (0,0) (1,1) ... (9,9)
    }
}
```

## 4. Implementing Union

The union(x,y) merges the sets containing x and y. This is accomplished by making root of the tree containing y with the root of the tree containing x.

``` java
// DSNode.java -- Definition remains the same
```

``` java
// DisjointSet.java

public class DisjointSet {
    DSNode[] node;
    int size;

    public void makeSets(int n) { ... }
    public DSNode find(int key) { ... }
    public void print() { ... }
    
    public void union(int key1, int key2) {
         DSNode root1 = find(key1);
         DSNode root2 = find(key2);
         if ( root1 != root2 )
             root2.parent = root1;
    }
}
```

``` java
// DisjointSetTest.java

public class DisjointSetTest {
    public static void main(String[] args) {
        DisjointSet d = new DisjointSet();
        d.makeSets(10);
        d.print();  // prints (0,0) (1,1) (2,2) (3,3) (4,4) (5,5) (6,6) (7,7) (8,8) (9,9)
        
        d.union(0,1);
        d.union(1,2);
        d.print();  // prints (0,0) (1,0) (2,0) (3,3) (4,4) (5,5) (6,6) (7,7) (8,8) (9,9)
    }
}
```
