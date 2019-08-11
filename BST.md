# Binary Search Tree Implementation Step-by-Step

We use recursion to implement the operations in an elegant manner. Implementing iterative function for each operation can be a very good programming exercise. However it is not advised as the grasping may be difficult.

**Note**: Recursion in object-oriented programming should exercise delegation of responsibilities from BST to root which in turn to its left or right, so on and so forth. For example, it is advised to avoid implementing **insert(root,key)** and rather implement as **root.insert(key)**.

## 1. Defining a BSTNode and BST

``` java
// BSTNode.java

public class BSTNode {
    protected int data;
    protected BSTNode left;
    protected BSTNode right;

    public BSTNode() { };
    public BSTNode(int d) {
        data = d;
    }
}
```

``` java
// BST.java

public class BST {
    protected BSTNode root;
}
```

``` java
// BSTDriver.java

public class BSTDriver {
    public static void main(String[] args) {
        BST b = new BST();
        b.root = new BSTNode(50);
        System.out.println(b.root.data);  // prints 50
    }
}
```

## 2. Defining insert operation

Before we can do anything with a BST, we have to insert some keys into it. Let's implement insert operation. The insert operation is implemented as follows.

 - The test driver invokes insert on BST instance b.
 - The BST instance b creates root if it is not present. Otherwise, it invokes insert on root (i.e. delegates the insert responsibility to the root).
 - The root recursively delegates to its left or right based on whether key is less than its data or not.

``` java
// BSTNode.java

public class BSTNode {
    protected int data;
    protected BSTNode left;
    protected BSTNode right;

    public BSTNode() { };
    public BSTNode(int d) {
        data = d;
    }
    
    public void insert(int key) {
        // Two cases - decide left or right
        if (key < data)  { // 1. go leftwards
            // Two sub cases - left slot is empty or not
            if (left == null)  // left is available for insertion
                left = new BSTNode(key);
            else  // left is not available, so delegate insert to left child
                left.insert(key);
        }
        else {  // 2. go rightwards
            // Two sub cases - right slot is empty or not
            if (right == null)  // right is available for insertion
                right = new BSTNode(key);
            else  // right is not available, so delegate insert to right child
                right.insert(key);
        }
    }
}
```

``` java
// BST.java

public class BST {
    protected BSTNode root;
    
    public void insert(int key) {
        if (root == null)
            root = new BSTNode(key);
        else
            root.insert(key);
    }
}
```

``` java
// BSTDriver.java

public class BSTDriver {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(50);
        b.insert(20);
	b.insert(80);
        b.insert(10);
        b.insert(30);
        b.insert(5);
	b.insert(15);
	b.insert(25);
        b.insert(35);
        b.insert(70);
        b.insert(90);
        b.insert(65);
        b.insert(75);
        b.insert(85);
        b.insert(95);
    }
}

/*
                  -----------------50-----------------
                  |                                  |
          -------20------                   --------80--------
          |             |                   |                |
    -----10----     ----30----         -----70----      -----90-----
    |         |     |        |         |         |      |          |
    5        15    25        35       65         75     85         95

*/
```


