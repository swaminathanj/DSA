# Stack and its Applications

## Stack implementation

We implement a Stack as an extension of LinkedList. One can implement Stack from the scratch too.

``` java
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
```

``` java
class StackTest {
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(45);
        s.push(30);
        s.push(17);
        s.push(56);
        s.push(84);
        s.push(23);
        System.out.println( s.peek() );  // prints 23
        s.pop();
        System.out.println( s.peek() );  // prints 84
        s.pop();
        System.out.println( s.isEmpty() );  // prints false
        
        s.print();  // What does this print? 
                    // Note: print is defined in LinkedList and hence accessible to Stack
    }
}
```
