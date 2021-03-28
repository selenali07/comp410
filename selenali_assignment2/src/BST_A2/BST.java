package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	@Override
	public boolean insert(String s) {
		if (size == 0) {
			root = new BST_Node(s);
			size++;
			return true;
		}
		if (this.contains(s)) {
			return false;
		}
		if (root.insertNode(s)) {
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(String s) {
		  if (root == null) {
			  return false;
		  } else {
			  if (root.data.compareTo(s) == 0) {
				BST_Node temp = new BST_Node(root.data);
			  	temp.left = root;
			  	boolean remove = root.removeNode(s, temp);
			  	root = temp.left;
			  	size--;
			  	return remove;
			  } else {
				  if (root.removeNode(s, null)) {
					  size--;
					  return true;
				  }
			  }
		  }
		  return false;
	  }

	@Override
	public String findMin() {
		if (root == null) {
			return null;
		}
		return root.findMin().getData();
	}

	@Override
	public String findMax() {
		if (root == null) {
			return null;
		}
		return root.findMax().getData();
	}

	@Override
	public boolean empty() {
		return size == 0;
	}

	@Override
	public boolean contains(String s) {
		if (size == 0 || s == null) {
			return false;
		}
		return root.containsNode(s);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (root != null) {
			return root.getHeight() - 1;
		}
		return 0;
	}
}