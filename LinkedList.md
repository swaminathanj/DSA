# Stage-by-stage implementation of linked list

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



