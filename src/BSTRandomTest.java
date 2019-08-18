// Aum Amma

import java.util.Random;

public class BSTRandomTest {
	public static void main(String[] args) {
		BSTRandomTest driver = new BSTRandomTest();
		driver.randomTest();
		driver.incrTest();
	}

	public void randomTest() { // random input
		BST b = new BST();
		int[] arr = new int[100];
		Random r = new Random();

		System.out.print("Insertion sequence: ");
		for (int i=0; i<100; i++) {
			arr[i] = r.nextInt(1000);
			System.out.print(arr[i] + " ");
			b.insert( arr[i] );
		}
		System.out.println();
		System.out.print("preorder: "); b.preorder();
		System.out.print("inorder: "); b.inorder();	
		System.out.println("height: " + b.height());	
	}

	public void incrTest() { // increasing input
		BST b = new BST();
		int[] arr = new int[100];

		System.out.print("Insertion sequence: ");
		for (int i=0; i<100; i++) {
			System.out.print(i + " ");
			b.insert( i );
		}
		System.out.println();
		System.out.print("preorder: "); b.preorder();
		System.out.print("inorder: "); b.inorder();	
		System.out.println("height: " + b.height());	
	}
}
