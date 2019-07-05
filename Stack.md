# Stack and its Applications

## Stack implementation

The stack implementation extends the LinkedList.

``` java
class Stack extends LinkedList {

    void push(int key) {
        insertFirst(key); // Merely calls LinkedList's method to achieve it
    }
    
    int pop() {
        if ( isEmpty() ) // i.e. stack is empty
            throw new Exception("Stack is empty");
            
        int val = head.data;
        head = head.next;
        return val;
    }
    
    int peek() {
        if ( isEmpty() ) // i.e. stack is empty
            throw new Exception("Stack is empty");

        return head.data;
    }
    
    boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }
}
```
