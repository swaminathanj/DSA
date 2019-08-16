// Aum Amma

import java.util.ArrayDeque;

public class AVLTree {
    protected AVLNode root;

    public void insert(int key) {
        if (root == null)
            root = new AVLNode(key);
        else
            root = root.insert(key);
    }

    public void inorder() {
        if (root == null)
            return;
        else
            root.inorder();
        System.out.println();
    }

    public void preorder() {
        if (root == null)
            return;
        else
            root.preorder();
        System.out.println();
    }

    public void postorder() {
        if (root == null)
            return;
        else
            root.postorder();
        System.out.println();
    } 
    
    public boolean search(int key) {
        if (root == null)
            return false;
        else
            return root.search(key);
    }

    public void delete(int key) {
        if (root == null)
            return;
        else if (root.data != key)
            root.delete(key);
        else { // root.data == key
        	// Special case implementation - courtesy Deepak Naik
        	AVLNode dummy = new AVLNode(); // Doesn't matter what data it has
            dummy.left = root;
            root = dummy;  // Make dummy the root

            root.delete(key); // This deletes the original root which is to the left of dummy

            root = root.left; // Remove dummy to get the real root back
        }
    }

	public void levelorder() {
		ArrayDeque<AVLNode> deq = new ArrayDeque<AVLNode>();
		deq.addLast(root);

		while ( !deq.isEmpty() ) { // until queue is not empty
			AVLNode n = deq.removeFirst();
			System.out.print(n.data + " ");
			if (n.left != null)
				deq.addLast(n.left);
			if (n.right != null)
				deq.addLast(n.right);
		}
		System.out.println();
	}

	public int height() {
		if (root == null)
			return 0;
		else
			return root.height();
	}
}
