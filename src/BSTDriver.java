// Aum Amma

public class BSTDriver {
    public static void main(String[] args) {
        BST b = new BST();

		int[] arr = {50, 20, 80, 10, 30, 5, 15, 25, 35, 70, 90, 65,75, 85, 95};
		for (int i=0; i< arr.length; i++)
			b.insert(arr[i]);

        b.inorder();
        b.preorder();
        b.postorder();
		b.levelorder();
        System.out.println( b.search(43) );
        System.out.println( b.search(50) );
		b.delete(50);
		b.inorder();
		System.out.println( b.height() );
    }

}

/*
                  -----------------50-----------------
                  |                                  |
          -------20------                   --------80--------
          |             |                   |                |
    -----10----     ----30----         -----70----      -----90-----
    |         |     |        |         |         |      |          |
    5        15    25        35       65         75     85         95

*/
