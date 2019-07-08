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
        if ( isEmpty() )
            throw new RuntimeException("Stack is empty");
            
        int top = head.data;
        deleteFirst();
        return top;
    }
    
    int peek() throws RuntimeException {
        if ( isEmpty() )
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
// StackTest.java

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

## Stack Application 1: Reversing the input

  - **Problem**: Given a series of integers as input, the problem is to output the integers in reverse order.
  - **Solution idea**: As you read the input, push them onto the stack. Now, pop them out one-by-one and print them. Since stack holds LIFO property, popping the elements amounts to reversal of input.

``` java
// Reverse.java

import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        // Read the integer series from the terminal
        // As you read push them onto the stack
        Scanner sc = new Scanner(System.in);
        Stack s = new Stack();
        
        while ( sc.hasNext() )  // As long as input is incoming
            s.push( sc.nextInt() ); // read and push onto the stack
        
        // Now pop the elements and print them
        while ( !s.isEmpty() )  // As long as stack is not empty
            System.out.println( s.pop() );  // pop them out and print them  
    }
}
```

### Execution:
CMD> javac Reverse.java

CMD> java Reverse

25 38 67 93 46 18 75 
(CTRL+D)

75 18 46 93 67 38 25

**Note**: CTRL+D is pressed to signal the end of input.

## Stack Application 2: Check Palindrome

  - **Problem**: Given an integer series as input, check if it is palindromic.
  - **Example**: 10 20 30 40 40 30 20 10 is palindromic integer series while 10 20 30 40 10 is not
  - **Solution**: Push half the series onto stack. While processing the second half check if the same integer exists on top of the stack. If so, pop and proceed. If not, exit with false. At the end, stack should be empty.
  
``` java
// Palindrome.java

public class Palindrome {

    public static void main(String[] args) {
        Palindrome p = new Palindrome();

        int[] a = {10, 20, 30, 30, 20, 10};
        System.out.println( p.check(a) );  // prints true

        int[] b = {10, 20, 30, 10, 20, 30};
        System.out.println( p.check(b) );  // prints false
    }

    public boolean check(int[] arr) {
        Stack s = new Stack();

        int mid = arr.length/2;
        for (int i=0; i<mid; i++) // Push first half onto stack
            s.push( arr[i] );

        for (int j=mid; j<arr.length; j++) {
            if ( arr[j] == s.peek() )
                s.pop();
            else
                return false;
        }

        if ( s.isEmpty() ) // Confirm if stack is empty
            return true;
        else
            return false;
    }

    // This program only works for even lengthed series
    // How will you modify it to handle odd lengthed series?
}
```

## Stack Application 3: Balanced Parentheses

  - **Problem**: Given a series of integers made up of 0's and 1's, 0 signifying open parenthesis '(' and 1 signifying close parenthesis ')', the goal is check if parentheses are balanced. i.e. every 0 should be followed by an equivalent 1. And every 1 should be preceded by an equivalent 0.
  - **Examples**:
     - 0 1 Balanced
     - 1 0 Not balanced
     - 0 0 1 0 1 1 Balanced
     - 0 1 0 1 0 Not balanced
     - 0 1 0 1 1 Not balanced
  - **Solution**: Whenever 0 is encountered, push it onto stack. When 1 is encountered, check for the matching 0 on top of the stack. 

``` java
// BalancedParentheses.java

import java.util.Scanner;

public class BalancedParentheses {
    public static void main(String[] args) {
        Stack s = new Stack();
        Scanner sc = new Scanner(System.in);
  
        while ( sc.hasNext() ) {
            int input = sc.nextInt();
            switch( input ) {
            case 0:  // open parenthesis
                    s.push(input);
                    break;
            case 1:  // closed parenthesis
                if ( s.isEmpty() ) { // 1 seen without 0
                    System.out.println("Not balanced");
                    return;
                }
                else if ( s.peek() == 0 ) // 1 matches with a 0
                    s.pop();
                break;
            default: // Some other char is encountered
                System.out.println("Not balanced");
                return;
             }
        }
        if ( s.isEmpty() )
            System.out.println("Balanced");
        else   // 0 seen with no matching 1
            System.out.println("Not balanced");
    }
}
```

**Note**: In this example, we have dealt with only one type of parentheses i.e. '(' and ')'. A more general version of the problem involves checking balance of multiple types of parenthesis. Examples below:

  - ( ) \[ { } ] Balanced
  - ( { ) } Not balanced
  
Improve the implementation to deal with these cases. You can use the integer mapping for different types of parentheses as follows:

  - ( -> 0
  - ) -> 1
  - \[ -> 2
  - ] -> 3
  - { -> 4
  - } -> 5

## Stack Application 4: Postfix (Reverse-Polish-Notation) Evaluation

  - **Problem**: Evaluate the postfix expression
  - **Solution**: When an integer is encountered, push it onto the stack. When an operator is encountered, pop the top two elements, perform the respective computation, and push the result on the stack.

``` java
// PostFixEval.java

public class PostFixEval {

   public static void main(String[] args) {
       PostFixEval p = new PostFixEval();

       // Infix: 2 + 3 * 5
       String[] expr1 = {"2", "3", "5", "*", "+"};
       System.out.println( p.evaluate(expr1) );  // prints 17

       // Infix: (2 + 3) * 5
       String[] expr2 = {"2", "3", "+", "5", "*"};
       System.out.println( p.evaluate(expr2) );  // prints 25
   }

   int evaluate(String[] tokens) {
       Stack s = new Stack();

       for (int i=0; i<tokens.length; i++) {
           int t1, t2;
           switch( tokens[i] ) {
           case "+":
               t2 = s.pop();
               t1 = s.pop();
               s.push(t1 + t2);
               break;

           case "-":
               t2 = s.pop();
               t1 = s.pop();
               s.push(t1 - t2);
               break;

           case "*":
               t2 = s.pop();
               t1 = s.pop();
               s.push(t1 * t2);
               break;

           case "/":
               t2 = s.pop();
               t1 = s.pop();
               s.push(t1 / t2);
               break;

           default: // Must be an integer, push it on stack
               s.push(Integer.parseInt( tokens[i] ));
           }
       }
       return s.peek();
   }
}
```

