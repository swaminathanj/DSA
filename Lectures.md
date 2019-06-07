## 1. Linked List

### Limitations of arrays
  - size is static and bounded, dynamic resizing not possible
  - insert requires shifting out subsequent elements to right
  - delete requires shifting out subsequent elements to left

### What is a linked list?
  - A data structure that holds a pointer **head** to a chained link of nodes.
  - Each node contains 2 parts:
    1. **data**: holds the data
    2. **next**: holds link to the next node

### Visualizing linked list
![Linked list](vll.png)

### Properties of linked list
  - No random access.
  - To reach a node, always start from the head and traverse.

### Operations on linked list
  - insert
  - delete
  - search

### Problems based on linked list properties
  - Given a sequence of operations, print the state after each operation

### Implementing linked list
```
class Node {  
  int data;  
  Node next;  
 
  Node(int d);  
  int getData(); 
  Node getNext();
}
```

```
class LinkedList {  
  Node head;  
  
  Node createHead(); // creates first node  
  boolean insertFirst(int key); // returns new head  
  void insert(int pos, int key);  
  void insert(int key);  
  void deleteHead(); // delete last remaining node  
  boolean delete(int pos);  
  boolean search(int key); // returns position  
  int size();  
}
```

### LinkedList in Collections
  - Examples

### Problems on linked list (use Collections' LinkedList)
  1. In a single traversal find the middle element/position
  2. Create a sorted list as you read elements (sort a linked list)
  3. Merge two sorted lists into one
  4. Rotate a linked list by k positions
  5. Remove duplicates from a sorted list (shrink the list)
  6. Partition list based on > and <= x (based on pivot, odd/even)
  7. Union of two linked lists keep duplicates (remove duplicates)
  8. Intersection of two linked lists
  9. Reverse a linked list
  10. Check if a linked list has a cycle

### ArrayList in Collections
  - When to use LinkedList or ArrayList?

---

## 2. Circular Linked List

### What is it?
  - Last node links back to first node

### Visualization
![Circular Linked list](vcll.png)

### Properties
   - Can reach the first node from the last node

### Operations
   - insert
   - delete
   - search
   - Rotate right **k** times
   - Rotate left = rotate right **size - k** times

### Implementation changes needed
  - insert and delete needs modification to set the **next** back to **head**
  - search needs modification to stop traversal as soon as **next** points to **head**

---

## 3. Doubly Linked List

### What is it?
  - Contains pointer to first node **head**
  - Each node contains 3 parts:
    1. **data**: holds the data
    2. **prev**: holds pointer to previous node
    3. **next**: holds pointer to next node

### Visualization
![Doubly Linked list](vdll.png)

### Properties
  - Can traverse in both left and right directions

### Operations
  - insert
  - delete
  - search

### Implementation changes needed
  - insert and delete needs modification to set both **prev** and **next**
 
---

## 4. Stack

### What is it?
  - A data structure that holds pointer (**top**) to a chained link of nodes
  - A new node is pushed onto the stack, and it becomes the new top
  - Delete pops the top node from the stack, the below node becomes the top
  - It can be seen as restricted linked list that supports insert last and delete last

### Visualization
![Stack](vstack.png)

### Properties
  - **LIFO**: Last-In-First-Out
  - Insert and delete at one end only

### Operations
  - push
  - pop
  - peek

### Implementation
```
class Node {  
  int data;  
  Node next;  
 
  Node(int d);  
  int getData(); 
  Node getNext();
}
```

```
class Stack {  
  Node top;  
  
  void push(int key);  
  int pop();   
  int peek();  
  int size();  
}
```

### Stack in Collections
  - Examples

### Problems
  1. Reverse
  2. Check palindrome
  3. Balanced parantheses
  4. Infix to prefix
  5. Infix to postfix
  6. Min stack
  7. Basic calculator*
  8. Decode string
  9. Backspace string compare
  10. Score of parantheses

---

## 5. Queue

### What is it?
  - A data structure that holds pointers (front, rear) to the first and last nodes of chained link of nodes
  - A new node is enqueued to the rear
  - Node is removed from the front
  - It can be seen as restricted linked list that supports insert last and delete first

### Visualization
![Queue](vqueue.png)

### Properties
  - **FIFO**: First-In-First-Out
  - Insert at one end and remove from the other end

### Operations
  - enqueue
  - dequeue
  - peek

### Implementation
```
class Node {  
  int data;  
  Node next;  
 
  Node(int d);  
  int getData(); 
  Node getNext();
}
```

```
class Queue {  
  Node front;  
  Node rear;
  
  void enqueue(int key);  
  int dequeue();   
  int peek();  
  int size();  
}
```

### ArrayDeque in Collections
  - Examples

### Problems
  - Task scheduler
  - Stack using queue
  - Queue using stack

---

## 6. Binary tree

### What is it?
  - A non-linear representation of nodes where each node links to 0, 1 or 2 child nodes
  - Child nodes are referred to as left and right
  - Topmost node is called root

### Visualization
![Binary Tree](vbt.png)

### Properties
  - Access to any node always starts with the root

### Traversals
  - Level-order: Level-by-level from top to bottom, at each level left to right
    - **25 48 16 11 37 64 76 83 51 93**
  - Preorder: visit(node), preorder(left), preorder(right)
    - **25 48 11 76 83 37 51 16 64 93**
  - Inorder: inorder(left), visit(node), inorder(right)
    - **76 11 83 48 37 51 25 16 93 64**
  - Postorder: postorder(left), postorder(right), visit(node)
    - **76 83 11 51 37 48 93 64 16 25**

### Operations
  - insert
  - delete
  - search
  - preorder
  - inorder
  - postorder

### Implementation

```
class Node {  
  - int data;  
  - Node left;  
  - Node right;  
.  
  Node(int d);  
  boolean search(int d);  
  boolean insert(int d);  
  boolean delete(int d);  
  void preorder(Node n);  
  void inorder(Node n);  
  void postorder(Node n);  
}
```

```
class BinaryTree {  
  Node root;  
 
  boolean search(int d) { root.search(d); }   
  boolean insert(int d) { root.insert(d); }  // Implementation depends on the type of binary tree
  boolean delete(int d) { root.delete(d); }  // Implementation depends on the type of binary tree
  void preorder() { preorder(root); }  
  void inorder() { inorder(root); }  
  void postorder() { postorder(root); }  
}
```

### Applications
  - Expression tree
  - Huffman encoding

---

## 7. Binary Heap

### Types
  - Min heap
  - Max heap

### What is min heap?
  - A binary tree in which each node's value is less than that of its left and right nodes
![Min Heap](vminheap.png)

### What is max heap?
  - A binary tree in which each node's value is greater than that of its left and right nodes

![Max Heap](vmaxheap.png)

### Properties
  - Access starts from the root
  - Min Heap property: **parent &lt; left, right**
    - Min element at the root
  - Max Heap property: **parent &gt; left, right**
    - Max element at the root
  - No constraints between left and right
  - Can be implemented as array for fast access

### Traversal
  - Level-order (array implementation suits this)
  - parent(i) = (i-1)/2
  - left(i) = 2i + 1
  - right(i) = 2i + 2
  
![Heap-array correspondence](vheaparray.png)

### Operations
  - insert
  - getMin (or getMax)

### Implementation

```
class MinHeap {
  int[] arr;
  int root;
  int size;
  
  MinHeap() { arr = int[100]; root = 0; size = 0; }
  int getParent(int i) { return (i-1)/2; }
  int getLeft(int i) { return 2*i + 1; }
  int getRight(int i) { return 2*i + 2; }
  void buildHeap();
  void insert(int key);
  void fixHeap();
  int getMin();
}
```

### Applications
  - Heap sort
  - Implement priority queue

### PriorityQueue in Collections
  - Examples

### Problems
  1. Kth largest element in an array
  2. Top K frequent elements
  3. Split array into consecutive subsequences
  4. Last stone weight
  5. K closest points to origin

## 8. Binary Search Tree

### What is it?
  - A binary tree in which a node's value is greater than its left and smaller than its right

### Why is it needed?
 - To search faster
 - It takes O(n) time to search in a linked list
 - BST provides a way to reduce search time to O(logn)
   - Not always though!!!

### Visualization
![Binary Search Tree](vbst.png)

### Properties
  - Access starts from the root
  - left &lt; data &lt; right
  - Inorder traversal results in sorted sequence

### Traversal
  - Usually inorder to retrieve sorted sequence
  - Other traversals are also common

### Operations
  - insert
  - delete
  - search
  - preorder, inorder, postorder

### Implementation

```
class Node {  
  int data;  
  Node left;  
  Node right;  
  
  Node(int d);  
  boolean search(int d);  
  boolean insert(int d);  
  boolean delete(int d);  
  void preorder(Node n);  
  void inorder(Node n);  
  void postorder(Node n);  
}
```

```
class BST extend Binary Tree {  
  Node root;  
 
  boolean search(int d) { root.search(); }  
  boolean insert(int d) { root.insert(); }  
  boolean delete(int d) { root.delete(); }  
  void preorder() { preorder(root); }  
  void inorder() { inorder(root); }  
  void postorder() { postorder(root); }  
}
```

### Additional Information

  - **search**: traverse recursively taking appropriate path at each level until node containing search element is found or leaf node is reached
  - **insert**: traverse recursively taking appropriate path at each level until point of insertion identified
  - **delete**: search, remove the node, replace it with the node containing next higher element
  - **preorder**: starting from root, traverse in node, preorder(left), preorder(right) manner
  - **inorder**: starting from root traverse in inorder(left), node, inorder(right) manner
  - **postorder**: starting from root traverse in postorder(left), postorder(right), node manner

### Problems
  1. Compute height of the tree
  2. Compute height of the root's left subtree and root's right subtree
  3. Compute height of every node of the tree
  4. Compute weight of the tree
  5. Compute weight of the root's left and root's right
  6. Compute weight of every node of the tree
  7. Compute the diameter of the tree
  8. Compute the lowest common ancestor of two nodes
  9. Count the number of internal and leaf nodes of the tree
  10. Check if the tree is complete, perfect

## 9. AVL Tree

### What is it?
  - A binary search tree which is height-balanced

### Visualization

### Properties
  - Height balanced: The height of the left and right subtrees differ by at most 1

### Traversal
  - Usually inorder 
    - for retrieving sorted sequence
  - Other traversals are also common

### Operations
  - insert, delete, search
  - preorder, inorder, postorder
  - llrotate
  - lrrotate
  - lrrotate
  - rlrotate

### Implementation
```
class Node {  
  int data;  
  Node left;  
  Node right;  
  
  Node(int d);  
  boolean search(int d);  
  boolean insert(int d);  
  boolean delete(int d);  
  void preorder(Node n);  
  void inorder(Node n);  
  void postorder(Node n);  
}
```

```
class AVLTree extend BST {  
  Node root;  

  void llRotate(Node n);
  void rrRotate(Node n);
  void lrRotate(Node n);
  void rlRotate(Node n);
  boolean search(int d) { root.search(); }  
  boolean insert(int d) { root.insert(); }  
  boolean delete(int d) { root.delete(); }  
  void preorder() { preorder(root); }  
  void inorder() { inorder(root); }  
  void postorder() { postorder(root); }  
}
```


### Problems
  1. Median of two sorted arrays
