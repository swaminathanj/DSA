class StackTest {
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(45);
        s.push(30);
        s.push(17);
        s.push(56);
        s.push(84);
        s.push(23);
        System.out.println( s.peek() );
        s.pop();
        System.out.println( s.peek() );
        s.pop();
        System.out.println( s.isEmpty() );
        
        s.print();  // What does this print? 
                    // Note: print is defined in LinkedList and hence accessible to Stack
    }
}
