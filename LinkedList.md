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
