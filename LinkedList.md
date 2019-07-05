# Stage-by-stage implementation of linked list

The following page provides the implementation of LinkedList in a stage by stage manner for those who are strugging with implementing linked list. Use this until you get the hang. Once you get comforable with implementation, you should stop following this and instead start doing your own implementation.

## 1. Define a Node and test its working

``` java
// Node.java

class Node {
    int data;
    Node next;
}
```

``` java
// NodeTest.java

class NodeTest {
    public static void main(String[] args) {
        Node n = new Node();
        n.data = 7;

        Node m = new Node();
        m.data = 11;
        n.next = m;  // Make m next node of n

        System.out.println(n.data);  // prints 7
        System.out.println(n.next.data);  // prints 11
    }
}
```

## 2. Define a LinkedList

``` java
// Node.java

class Node {
    int data;
    Node next;
}
```

``` java
// LinkedList.java

class LinkedList {
    Node head; 
}
```

``` java
// LLTest.java 

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
                            // This program prints nothing
    }
}
```

Since the LinkedList does not support any functions, there is nothing much that can be done with it except for creating an instance.

## 3. LinkedList with insert method
The insert method handles 2 cases.
  - Case 1: head is null in which case insert creates a head
  - Case 2: head is not null in which case insert adds a new node to the end

``` java
// Node.java

class Node {
    int data;
    Node next;
}
```

``` java
// LinkedList.java

class LinkedList {
    Node head; 

    void insert(int key) {
        Node temp = new Node();
        temp.data = key;
 
        if (head == null)  // case 1
            head = temp;
        else {             // case 2
            Node curr = head;
            while (curr.next != null) // Reach till end
                curr = curr.next;            
            curr.next = temp;
        }
    }
}
```

``` java
// LLTest.java 

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insert(5);  // 5 becomes the head
        l.insert(20);
        l.insert(12);
        l.insert(25);
                  // Again, this program does not print anything
    }
}
```

## 4. Add a print method to LinkedList
The print is a utility method that prints the contents of the linked list. Defining this can come handy in displaying the state of the linked list.

``` java
// Node.java  - same as above
```

``` java
// LinkedList.java

class LinkedList {
    Node head; 

    void insert(int key) { ... }
    
    void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}


```

``` java
// LLTest.java 

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insert(5);  // 5 becomes the head
        l.insert(20);
        l.insert(12);
        l.insert(25);
        l.print(); // prints 5 20 12 25
    }
}
```

## 5. Implement a search method
The search method scans through the linked list to find if a given key is present. If so, it returns the position. If not, it returns -1.

``` java
// Node.java  - same as above
```

``` java
// LinkedList.java

class LinkedList {
    Node head; 

    void insert(int key) { ... }    
    void print() { ... }
    
    int search(int key) {
        Node curr = head;
        int pos = 0;

        while (curr != null) {  // Search till the end 
            if ( curr.data == key)  // if key is found
                return pos;
            else {          // continue if key is not found
                curr = curr.next;
                pos++;
            }
        }
        return -1;  // key was never found       
    }
}


```

``` java
// LLTest.java 

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insert(5);  // 5 becomes the head
        l.insert(20);
        l.insert(12);
        l.insert(25);
        l.print(); // prints 5 20 12 25
        
        System.out.println( l.search(12) ); // prints 2
        System.out.println( l.search(15) ); // prints -1       
    }
}
```

## 6. Implement insert method which inserts a node at a particular position
The search method scans through the linked list to find if a given key is present. If so, it returns the position. If not, it returns -1.

``` java
// Node.java  - same as above
```

``` java
// LinkedList.java

class LinkedList {
    Node head; 

    void insert(int key) { ... }    
    void print() { ... }    
    int search(int key) { ... }
    
    void insert(int pos, int key) {
        Node temp = new Node();  // create the new node
        temp.data = key;

        if (pos == 0) {  // insert it at the front & make it the head
            temp.next = head;
            head = temp;
        }
        else {
            Node curr = head;  
            for (int i=0; i < pos-1; i++) // Navigate to prior node
                curr = curr.next;
                
            temp.next = curr.next;  // Adjust the links appropriately
            curr.next = temp;
        }
    }
}
```

``` java
// LLTest.java 

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insert(5);  
        l.insert(20);
        l.insert(12);
        l.insert(25);
        l.print(); // prints 5 20 12 25
        
        l.insert(2,30);
        l.print(); // prints 5 20 30 12 25
        l.insert(0,50);  // 50 becomes the head
        l.print(); // prints 50 5 20 30 12 25
    }
}
```

## Implement delete method to delete a node containing a given key

The delete method first searches for the node with the given key and then removes it.

``` java
// Node.java  - same as above
```

``` java
// LinkedList.java

class LinkedList {
    Node head; 

    void insert(int key) { ... }    
    void print() { ... }    
    int search(int key) { ... }    
    void insert(int pos, int key) { ... }

    void delete(int key) {
        int pos = search(key); // find the pos of the key
        
        if (pos == -1) // key not found, so nothing to delete
            ; // do nothing
            
        if (pos == 0)  // first node to be deleted, second node becomes the head
            head = head.next;
                
        // Navigate till the prior node of the to-be-deleted node
        Node curr = head;
        for (int i=0; i<pos-1; i++)
            curr = curr.next;
        
        // temp is the node to be deleted (next to curr)
        Node temp = curr.next;
        curr.next = temp.next; // reassign the links
        temp.next = null;
    }
}
```

``` java
// LLTest.java 

class LLTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insert(5);  
        l.insert(20);
        l.insert(12);
        l.insert(25);
        l.print(); // prints 5 20 12 25
        
        l.delete(12);
        l.print(); // prints 5 20 25
    }
}
```
