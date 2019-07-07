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
