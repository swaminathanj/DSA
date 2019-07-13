// Aum Amma

public class Deque extends Queue {

    public void insertFront(int key) {
        insertFirst(key);  // Calls LinkedList's insertFirst
    }

    public int deleteFront() {
        return dequeue();  // Calls Queue's dequeue method
    }

    public void insertRear(int key) {
        enqueue(key);  // Calls Queue's enqueue method
    }

    public int deleteRear() {
        /* Note: We don't have deleteLast in either LinkedList 
           or Queue. So, we have to implement the logic here */

        // Find the size of the queue
        int len = size();  // Calls LinkedList's size method

        // Last node is at (len-1)th position
        Node curr = head;
        for (int i=0; i<len-2; i++) // reach last but one node
            curr = curr.next;

        // Copy the data in last node
        int val = curr.next.data;

        // Reset curr's next to null thereby dropping last node
        curr.next = null;

        return val;
                    // Takes O(n) time. How to make it O(1)?
    }

    public int rear() {
        int len = size();  // Find the length

        Node curr = head;
        for (int i=0; i<len-1; i++)  // Get to last node
            curr = curr.next; 

        return curr.data; 
    }
}
