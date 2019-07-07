// Aum Amma

// Stack.java

class Stack extends LinkedList {

    void push(int key) {
        insertFirst(key); // Merely calls LinkedList's method to achieve it
    }
    
    int pop() throws RuntimeException {
        if ( isEmpty() ) // i.e. stack is empty
            throw new RuntimeException("Stack is empty");
            
        int top = head.data;
        deleteFirst();
        return top;
    }
    
    int peek() throws RuntimeException {
        if ( isEmpty() ) // i.e. stack is empty
            throw new RuntimeException("Stack is empty");

        return head.data;
    }
    
    boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }
}
