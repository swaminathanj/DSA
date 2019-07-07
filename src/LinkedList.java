public class LinkedList {
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

    void insert(int pos, int key) {
        Node temp = new Node();  // create the new node
        temp.data = key;

        if (pos == 0) { // head is reassigned
            temp.next = head;
            head = temp;
        }
        else {
            Node curr = head;  // Navigate to prior node
            for (int i=0; i < pos-1; i++)
                curr = curr.next;
                
            temp.next = curr.next;
            curr.next = temp;
        }
    }

    void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    int search(int key) {
        Node curr = head;
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

    void insertFirst(int key) {
        Node temp = new Node();
        temp.data = key;

        temp.next = head;
        head = temp;
    }

    void deleteFirst() {
        head = head.next;
    }

    int size() {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }

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
        
        insert(pos, key);
    }
    
    void append(LinkedList ll) {
    
        if (this.head == null) {  // 'this' keyword is optional 
            this.head = ll.head;
            return;
        }
           
        // Reach till the end of 'this' list
        Node curr = this.head;
        while (curr.next != null)
            curr = curr.next;
        
        // Attach l's head to the end of 'this' list
        curr.next = ll.head;
    }    
}
