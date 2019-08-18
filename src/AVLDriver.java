// Aum Amma

import java.util.Random;

public class AVLDriver {
	public static void main(String[] args) {
		AVLDriver driver = new AVLDriver();
		driver.llTest1();
		driver.llTest2();
		driver.rrTest1();
		driver.rrTest2();
		driver.lrTest1();
		driver.rlTest1();

		driver.randomTest();
		driver.incrTest();
	}

	public void llTest1() { // left-left imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 10, 5");
		avl.insert(15); avl.insert(10); avl.insert(5);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void llTest2() { // left-left/left-right imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 10, 5, 12");
		avl.insert(15); avl.insert(10); avl.insert(5); avl.insert(12);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void rrTest1() { // right-right imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 20, 25");
		avl.insert(15); avl.insert(20); avl.insert(25);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void rrTest2() { // right-right/right-left imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 20, 25, 18");
		avl.insert(15); avl.insert(20); avl.insert(25); avl.insert(18);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void lrTest1() { // left-right imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 5, 10");
		avl.insert(15); avl.insert(5); avl.insert(10);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void rlTest1() { // right-left imbalance
		AVLTree avl = new AVLTree();
		System.out.println("Insertion sequence: 15, 25, 20");
		avl.insert(15); avl.insert(25); avl.insert(20);
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();
	}

	public void randomTest() { // random input
		AVLTree avl = new AVLTree();
		int[] arr = new int[100];
		Random r = new Random();

		System.out.print("Insertion sequence: ");
		for (int i=0; i<100; i++) {
			arr[i] = r.nextInt(1000);
			System.out.print(arr[i] + " ");
			avl.insert( arr[i] );
		}
		System.out.println();
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();	
		System.out.println("height: " + avl.root.height());	
	}

	public void incrTest() { // increasing input
		AVLTree avl = new AVLTree();
		int[] arr = new int[100];

		System.out.print("Insertion sequence: ");
		for (int i=0; i<100; i++) {
			System.out.print(i + " ");
			avl.insert( i );
		}
		System.out.println();
		System.out.print("preorder: "); avl.preorder();
		System.out.print("inorder: "); avl.inorder();	
		System.out.println("height: " + avl.height());	
	}
}
