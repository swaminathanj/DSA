// Aum Amma

class NodeTest {
    public static void main(String[] args) {
        Node n = new Node();
        n.data = 7;

        Node m = new Node();
        m.data = 11;
        n.next = m;  // Make m next to n

        System.out.println(n.data);  // prints 7
        System.out.println(n.next.data);  // prints 11
    }
}
