// PostFixEval.java

public class PostFixEval {

   public static void main(String[] args) {
       PostFixEval p = new PostFixEval();

       // Infix: 2 + 3 * 5
       String[] expr1 = {"2", "3", "5", "*", "+"};
       System.out.println( p.evaluate(expr1) );

       // Infix: (2 + 3) * 5
       String[] expr2 = {"2", "3", "+", "5", "*"};
       System.out.println( p.evaluate(expr2) );
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
               s.push( Integer.parseInt(tokens[i]) );
           }
       }
       return s.peek();
   }
}
