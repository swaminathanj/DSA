# Binary Search Tree Implementation Step-by-Step

We use recursion to implement the operations in an elegant manner. Implementing iterative function for each operation can be a very good programming exercise. However it is not advised as the grasping may be difficult.

**Note**: Recursion in object-oriented programming should exercise delegation of responsibilities from BST to root which in turn to its left or right, so on and so forth. For example, it is advised to avoid implementing **insert(root,key)** and rather implement as **root.insert(key)**.

## Defining a BSTNode and BST

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
        b.root = new BSTNode(5);
}
```
