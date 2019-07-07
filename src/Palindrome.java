// Palindrome.java

public class Palindrome {

    public static void main(String[] args) {
        Palindrome p = new Palindrome();

        int[] a = {10, 20, 30, 30, 20, 10};
        System.out.println( p.check(a) );

        int[] b = {10, 20, 30, 10, 20, 30};
        System.out.println( p.check(b) );
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
