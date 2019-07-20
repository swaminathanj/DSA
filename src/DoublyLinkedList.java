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
