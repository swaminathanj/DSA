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

  - The distjoint set of size n consists initially of n nodes. 
  - The makeSet(int i) creates the i^th node denoting ith set and sets its label as i. 
  - The parent of each node is null. i.e. each set contains one node.

``` java
// DSNode.java -- Definition remains the same
```

``` java
// DisjointSet.java

public class DisjointSet {
    DSNode[] node;
    int size;

    public DisjointSet(int n) {
       size = n;
       node = new DSNode[size];
    }

    public void makeSet(int index) {
        node[index] = new DSNode(index);
    }
}
```

``` java
// DisjointSetTest.java

public class DisjointSetTest {
    public static void main(String[] args) {
        DisjointSet d = new DisjointSet(10);
        for (int i=0; i<10; i++)
            d.makeSet(i);
    }
}
```


## 3. Implementing Find

  - The find(key) method invokes getRoot() method of node[key] which determines the root the node by recursively invoking getRoot() of its parent until topmost node is reached. 
  - The print() method invokes find() of each node to determine its root.
  - Note that initially the parent of each node is null. Therefore, each node is the root of itself. 

``` java
// DSNode.java

public class DSNode {
    int label;
    DSNode parent;

    public DSNode(int l) { label = l; }
    }

    public DSNode getRoot() {
        if (parent == null)
            return this;
        else
            return parent.getRoot();
    }
}
```

``` java
// DisjointSet.java

public class DisjointSet {
    DSNode[] node;
    int size;

    public DisjointSet(int n) { ... }
    public void makeSet(int index) { ... }
    
    public DSNode find(int key) {
        return node[key].getRoot();
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
        DisjointSet d = new DisjointSet(10);
        for (int i=0; i<10; i++)
            d.makeSet(i);
        d.print(); // prints (0,0) (1,1) ... (9,9)
    }
}
```

## 4. Implementing Union

The union(key1,key2) merges the sets containing key1 and key2. This is accomplished by making root of the tree containing key1 parent of the root of the tree containing key2.

``` java
// DSNode.java -- Definition remains as above
```

``` java
// DisjointSet.java

public class DisjointSet {
    DSNode[] node;
    int size;

    public void makeSet(int n) { ... }
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
        for (int i=0; i<10; i++)
            d.makeSet(i);
        d.print();  // prints (0,0) (1,1) (2,2) (3,3) (4,4) (5,5) (6,6) (7,7) (8,8) (9,9)
        
        d.union(0,1);
        d.union(1,2);
        d.print();  // prints (0,0) (1,0) (2,0) (3,3) (4,4) (5,5) (6,6) (7,7) (8,8) (9,9)
    }
}
```

## 5. Optimizing Union-Find

Please refer to any standard Data Structures and Algorithms book for fast-find, fast-union and other optimizations through **union-by-rank** and **path compression** techniques. These  are really important to understand. A good tutorial is found in [Hacker Earth Disjoint Set Notes](https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/). The long and short of it is that you can implment a O(1) find or O(1) union but not both.
