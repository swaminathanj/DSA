# Doubly Linked List Implementation

``` java
// DNode.java

public class DNode {
    int data;
    DNode prev;
    DNode next;

    DNode(int d) {
        data = d;
    }
}
```

``` java
// DoublyLinkedList.java

public class DoublyLinkedList {
    DNode head;
    DNode tail;

    /** Insert at the last. It is faster with tail */
    void insert(int key) {
        DNode temp = new DNode(key); // create the node to be inserted

        // Two cases to deal - List empty and otherwise
        if (head == null) { // 1. List empty
            head = temp; // First node added
            tail = head; // head and tail point to first node
        }
        else { // 2. List has at least one element
            tail.next = temp; // Add temp after tail
            temp.prev = tail;
            tail = temp;  // Make temp the new tail
        }
    }

    void print() {
        DNode curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    int size() {
        int s = 0;
        DNode curr = head;
        while (curr != null) {
            s++; 
            curr = curr.next;
        }
        return s;
    }

    /** Insert at a given position */
    void insert(int pos, int key) {
        DNode temp = new DNode(key); // create the node to be inserted

        // Four cases cases to deal
        if (pos == 0 && head == null) { // 1. insert first when linked list is empty
            head = temp;
            tail = head;
        }
        else if (pos == 0 && head != null) { // 2. insert first when linked list is not empty
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        else if (pos >= size()) { // 3. position exceeds size - insert at the end
            tail.next = temp; // Add temp after tail
            temp.prev = tail;
            tail = temp;  // Make temp the new tail
        }
        else { // 4. insert in between
            DNode curr = head;
            for (int i=0; i<pos-1; i++)
                curr = curr.next; // Go to prior position

            temp.next = curr.next; // Reassign the pointers
            curr.next.prev = temp;
            curr.next = temp;
            temp.prev = curr;
        }
    }

    /** Search if key is present in the linked list */
    int search(int key) {
        DNode curr = head;
        int pos = 0;

        while (curr != null) {  // Till the end 
            if ( curr.data == key)  // key is found
                return pos;
            else {          // continue if key is not found
                curr = curr.next;
                pos++;
            }
        }
        return -1;  // key was never found
    }

    /** Delete the node containing key */
    void delete(int key) {
        int pos = search(key); // find the pos of the key
        System.out.println(pos + " " + size());
        
        // Four cases to be dealt with based on pos
        if (pos == -1) // 1. key not found, so nothing to delete
            return;
            
        if (pos == 0)  { // 2. first node to be deleted, second node becomes the head
            head = head.next;
            return;
        }

        if (pos == size()-1) { // 3. last node to be deleted, reassign tail to previous node
            DNode curr = tail;
            curr = curr.prev;
            tail.prev = null;
            curr.next = null;
            tail = curr;
            return;
        }
                
        // 4. Navigate till the prior node of the to-be-deleted node
        DNode curr = head;
        for (int i=0; i<pos-1; i++)
            curr = curr.next;
        
        // temp is the node to be deleted (next to curr)
        DNode temp = curr.next;
        curr.next = temp.next; // reassign the links
        temp.next = null;
    }
}
```

``` java
// DLLTest.java

public class DLLTest {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insert(0,5);
        dll.insert(10);
        dll.insert(15);
        dll.insert(20);
        dll.print();

        dll.insert(4,30);
        dll.insert(4,25);
        dll.insert(0,2);
        dll.print();

        dll.delete(30);
        dll.insert(10,100);
        dll.print();
    }
}
```

Another Solution for better understanding
class DoublyLinkedList {    
    //A node class for doubly linked list
    class Node{  
        int item;  
        Node previous;  
        Node next;  
   
        public Node(int item) {  
            this.item = item;  
        }  
    }  
    //Initially, heade and tail is set to null
    Node head, tail = null;  
   
    //add a node to the list  
    public void addNode(int item) {  
        //Create a new node  
        Node newNode = new Node(item);  
   
        //if list is empty, head and tail points to newNode  
        if(head == null) {  
            head = tail = newNode;  
            //head's previous will be null  
            head.previous = null;  
            //tail's next will be null  
            tail.next = null;  
        }  
        else {  
            //add newNode to the end of list. tail->next set to newNode  
            tail.next = newNode;  
            //newNode->previous set to tail  
            newNode.previous = tail;  
            //newNode becomes new tail  
            tail = newNode;  
            //tail's next point to null  
            tail.next = null;  
        }  
    }  
   
//print all the nodes of doubly linked list  
    public void printNodes() {  
        //Node current will point to head  
        Node current = head;  
        if(head == null) {  
            System.out.println("Doubly linked list is empty");  
            return;  
        }  
        System.out.println("Nodes of doubly linked list: ");  
        while(current != null) {  
            //Print each node and then go to next.  
            System.out.print(current.item + " ");  
            current = current.next;  
        }  
    }  
}
class Main{
    public static void main(String[] args) {  
        //create a DoublyLinkedList object
        DoublyLinkedList dl_List = new DoublyLinkedList();  
        //Add nodes to the list  
        dl_List.addNode(10);  
        dl_List.addNode(20);  
        dl_List.addNode(30);  
        dl_List.addNode(40);  
        dl_List.addNode(50);  
   
        //print the nodes of DoublyLinkedList  
        dl_List.printNodes();  
    }  
}  
