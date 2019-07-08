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

## 7. Implement delete method to delete a node containing a given key

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
            return;
            
        if (pos == 0)  { // first node to be deleted, second node becomes the head
            head = head.next;
            return;
        }
               
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
        l.print();  // prints 5 20 25
        l.delete(30); // 30 not in the list, nothing is removed
        l.print();  // prints 5 20 25
    }
}
```

## 8. Special methods to insert and delete first node

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
    void delete(int key) { ... }
    
    void insertFirst(int key) {
        Node temp = new Node();
        temp.data = key;

        temp.next = head;
        head = temp;
    }

    void deleteFirst() {
        head = head.next;
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
        l.print();  // prints 5 20 25
        l.delete(30); // 30 not in the list, nothing is removed
        l.print();  // prints 5 20 25
        
        l.insertFirst(100);
        l.print();  // prints 100 5 20 25
        l.insertFirst(110);
        l.print();  // prints 110 100 5 20 25
        l.deleteFirst();
        l.print();  // prints 100 5 20 25
        l.deleteFirst();
        l.print();  // prints 5 20 25
    }
}
```

## 9. Adding a size() method to count the number the nodes in LinkedList

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
    void delete(int key) { ... }
    void insertFirst(int key) { ... }
    void deleteFirst() { ...  }
    
    int size() {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
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
        l.print();
        System.out.println( l.size() );  // prints 4

        l.delete(12);
        l.print();  // prints 5 20 25
        l.delete(30); // 30 not in the list, nothing is removed
        l.print();  // prints 5 20 25
        System.out.println( l.size() );  // prints 3
    }
}
```

## 10. Add a insertSort() method that inserts elements in sorted manner

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
    void delete(int key) { ... }
    void insertFirst(int key) { ... }
    void deleteFirst() { ...  }
    int size() { ... }
    
    void insertSort(int key) {
        if (head == null) {
            head = new Node();
            head.data = key;
            return;
        }

        // Find the position to insert
        Node curr = head;
        int pos = 0;
        while (curr != null && curr.data < key) {
            curr = curr.next;
            pos++;
        }
        insert(pos, key); // Insert at that position
    }    
}
```

``` java
// LLSortTest.java

class LLSortTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.insertSort(5);
        l.insertSort(20);
        l.insertSort(12);
        l.insertSort(25);
        l.insertSort(10);
        l.print();  // prints 5 10 12 20 25
    }
}
```

## 11. Append one linked list to the other

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
    void delete(int key) { ... }
    void insertFirst(int key) { ... }
    void deleteFirst() { ...  }
    int size() { ... }    
    void insertSort(int key) { ... }

    void append(LinkedList ll) {
    
        if (this.head == null) {  // 'this' keyword is optional 
            this.head = ll.head;
            return;
        }
           
        // Reach till the end of 'this' list
        Node curr = this.head;
        while (curr.next != null)
            curr = curr.next;
        
        // Attach ll's head to the end of 'this' list
        curr.next = ll.head;
    }
}
```

``` java
// LLAppendTest.java

class LLAppendTest {
    public static void main(String[] args) {
        LinkedList first = new LinkedList();
        first.insert(5);
        first.insert(20);
        first.insert(15);
        first.insert(25);
        first.insert(10);
        first.print();  // prints 5 20 15 25 10
        
        LinkedList second = new LinkedList();
        second.insert(50);
        second.insert(85);
        second.insert(60);
        second.insert(75);
        second.print();  // prints 50 85 60 75
        
        first.append(second);  // second attached to the end of first
        first.print();  // prints 5 20 15 25 10 50 85 60 75 
    }
}
```

#### The complete source code is available at [src](src).
