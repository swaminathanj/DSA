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

## LinkedList with insert method
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

