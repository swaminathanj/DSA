# AVLTree implementation

The implementation of AVL Tree builds on BST implementation. The enhancements to BST includes:

  - Definition of **balanceFactor** in Node class to keep track of balance at each node.
  - Changes to **insert** and **delete** methods to recompute balanceFactor after insertion/deletion and trigger rotations to balance itself if the balanceFactor equals 2.
  - Defining rotations with **left** and **right** children.
  - **insert** and **delete** will now return a node object since the rotations might cause the subtree rooted at a node to get changed.  
  
In the code below, except for changes to**delete** method, all other changes are done. 

To keep the BST and AVL implementations separate and distinguish them, the classes have been renamed as **AVLNode**, **AVLTree** and **AVLDriver**. The internal implementations are same except for the above mentioned changes to AVL.

``` java
// AVLNode.java

public class AVLNode {
    protected int data;
    protected AVLNode left;
    protected AVLNode right;

    protected int balanceFactor;

    public AVLNode() { };
    public AVLNode(int d) {
        data = d;
        balanceFactor = 0;
    }

    public void inorder() { ... }
    public void preorder() { ... }
    public void postorder() { ... }
    public boolean search(int key) { ... }
    public boolean isLeaf() { ... }
    public boolean hasOnlyLeft() { ... }
    public boolean hasOnlyRight() { ... }
    public int height() { ... }
    public int max(int a, int b) { ... }

    public int computeBalance() {
        if (isLeaf()) return 0;
        else if (hasOnlyLeft()) return left.height()+1;
        else if (hasOnlyRight()) return -1 * (right.height()+1);
        else	return left.height() - right.height();
    }

    public AVLNode rotateWithLeftChild() {
    	AVLNode lc = left;
    	left = lc.right;
    	lc.right = this;
    	computeBalance(); lc.computeBalance();
    	return lc;
    }

    public AVLNode rotateWithRightChild() {
    	AVLNode rc = right;
    	right = rc.left;
    	rc.left = this;
    	computeBalance(); rc.computeBalance();
    	return rc;
    }

    public AVLNode insert(int key) {
        if (key < data)  { // 1. go leftwards
            if (left == null)   // left is available for insertion
                left = new AVLNode(key);
            else  // left is not available, so delegate insert to left child
                left = left.insert(key);
        }
        else {  // 2. go rightwards
            if (right == null)  // right is available for insertion
                right = new AVLNode(key);
            else  // right is not available, so delegate insert to right child
                right = right.insert(key);
        }

		/* Compute balanceFactor and trigger appropriate rotation */
        balanceFactor = computeBalance();
        switch(balanceFactor) {
        case 2:  // Left imbalance 
        	if (left.balanceFactor >= 0)  // Left-left imbalance
        		return rotateWithLeftChild();
                /*        15 (2)        10 (0)           15 (2)           10 (-1)
                         /             /  \              /                /  \
                       10 (1)    ==>  5    15          10 (0)    ==>     5    15 (1)
                      /                               /  \                   /
                     5                               5    12                12
                               CASE 1                          CASE 2
                (triggered after insertion of 5)  (triggered after deletion of 15's right)

        	    */
        	else {                        // Left-right imbalance
        		left = left.rotateWithRightChild();
       			return rotateWithLeftChild();
        	/*      15 (2)         15 (2)         12 (0)         15 (2)         15 (3)         12 (1) 
                       /              /              /  \           /              /              /  \
                     10 (-1)  ==>   12 (1)   ==>  10    15        10 (0)  ==>     12 (2)   ==>  10    15
                       \            /                            /   \           /             /
                        12        10                            5     12        10 (1)        5
                                                                               /
                                                                              5
                                    CASE 1                                    CASE 2
 	                   (triggered after insertion of 12)        (triggered after deletion of 15's right)
        		*/
         	}

       	case -2:  // Right imbalance
       		if (right.balanceFactor <= 0)  // Right-right imbalance
       			return rotateWithRightChild();
       		else {                         // Right-left imbalance
       			right = right.rotateWithLeftChild();
       			return rotateWithRightChild();
       		}
        }
        return this;
    }

    public void delete(int key) {
    	if (left != null && left.data == key) { // left to be deleted
    		if (left.isLeaf())             // 1. It is a leaf
    			left = null;
    		else if (left.hasOnlyLeft())   // 2. It has only left subtree underneath
    			left = left.left;
    		else if (left.hasOnlyRight())  // 3. It has only right subtree underneath
    			left = left.right;
    		else {                         // 4. It has both left and right subtree
    			// 4a. Find predecessor. left.left is the potential candidate
    			if (left.left.hasOnlyLeft() || left.left.isLeaf()) { // left.left does not have right
    				left.data = left.left.data;  // Copy left.left.data to left.data
    				left.left = left.left.left;  // Reassign left.left to left.left.left
    			}
    			else { // 4b. Find predecessor. The rightmost from left.left
    				AVLNode predParent = left.left;
    				AVLNode pred = predParent.right;
    				while (pred.right != null) {   // Slide along the right direction
    					predParent = pred;  // Parent of the predecessor
    					pred = pred.right;  // The predecessor
    				}
    				left.data = pred.data;  // Copy predecessor data to left
    				predParent.right = pred.left;  // Remove predecessor node
    			}    			
    		}
    	}
    	else if (right != null && right.data == key) {  // Analogous case
    		if (right.isLeaf())
    			right = null;
    		else if (right.hasOnlyLeft())
    			right = right.left;
    		else if (right.hasOnlyRight())
    			right = right.right;
    		else {
    			if (right.left.hasOnlyLeft() || right.left.isLeaf()) {
    				right.data = right.left.data;
    				right.left = right.left.left;
    			}
    			else {
    				AVLNode predParent = right.left;
    				AVLNode pred = predParent.right;
    				while (pred.right != null) {
    					predParent = pred;
    					pred = pred.right;
    				}
    				right.data = pred.data;
    				predParent.right = pred.left;
    			}    			
    		}
    	}
    	else if (left != null && key < data)
    		left.delete(key);
    	else if (right != null && key >= data)
    		right.delete(key);
    }
}
```

AVLTree.java (equivalent to BST.java) does not required any changes since detection of the need for rotations and triggering rotations are from the node itself.

``` java
// AVLTree.java - remains the same as BST

import java.util.ArrayDeque;

public class AVLTree {
    protected AVLNode root;

    public void insert(int key) { ... }
    public void inorder() { ... }
    public void preorder() { ... }
    public void postorder() { ... }    
    public boolean search(int key) { ... }
    public void delete(int key) { ... }
    public void levelorder() { ... }
    public int height() { ... }
}
```

The driver covers test cases that trigger all rotation scenarios. Apart from them it also includes test cases for random and increasing sequences. Note that height is printed to check if it is about log n.

``` java
// AVLDriver.java

import java.util.Random;

public class AVLDriver {
	public static void main(String[] args) {
		AVLDriver driver = new AVLDriver();
		driver.llTest1();
		driver.llTest2();
		driver.rrTest1();
		driver.rrTest2();
		driver.lrTest1();
		driver.rlTest1();

		driver.randomTest();
		driver.incrTest();
	}

	public void llTest1() { // left-left imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 10, 5");
		avl.insert(15); avl.insert(10); avl.insert(5);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void llTest2() { // left-left/left-right imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 10, 5, 12");
		avl.insert(15); avl.insert(10); avl.insert(5); avl.insert(12);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void rrTest1() { // right-right imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 20, 25");
		avl.insert(15); avl.insert(20); avl.insert(25);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void rrTest2() { // right-right/right-left imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 20, 25, 18");
		avl.insert(15); avl.insert(20); avl.insert(25); avl.insert(18);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void lrTest1() { // left-right imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 5, 10");
		avl.insert(15); avl.insert(5); avl.insert(10);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void rlTest1() { // right-left imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 25, 20");
		avl.insert(15); avl.insert(25); avl.insert(20);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void randomTest() { // random input
		AVLTree avl = new AVLTree();
		int[] arr = new int[100];
		Random r = new Random();

		System.out.print("Insertion sequence: ");
		for (int i=0; i<100; i++) {
			arr[i] = r.nextInt(1000);
			System.out.print(arr[i] + " ");
			avl.insert( arr[i] );
		}
		System.out.println();
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();	
		System.out.println("height: " + avl.root.height());	
	}

	public void incrTest() { // increasing input
		AVLTree avl = new AVLTree();
		int[] arr = new int[100];

		System.out.print("Insertion sequence: ");
		for (int i=0; i<100; i++) {
			System.out.print(i + " ");
			avl.insert( i );
		}
		System.out.println();
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();	
		System.out.println("height: " + avl.height());	
	}
}
```
