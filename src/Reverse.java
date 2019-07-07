import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        // Read the integer series from the terminal
        // As you read push them onto the stack
        Scanner sc = new Scanner(System.in);
        Stack s = new Stack();
        
        while ( sc.hasNext() )  // As long as input exists
            s.push( sc.nextInt() ); // read and push onto the stack
        
        // Now pop the elements and print them
        while ( !s.isEmpty() )  // As long as stack in not empty
            System.out.print( s.pop() + " " );  // pop them out and print them  
    }
}
