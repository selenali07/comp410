package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

	public boolean insertNode(String s) {
		if (this.getData().equals(s)) {
			return false;
		} else if (this.getData().compareTo(s) > 0) {
			if (left == null) {
				left = new BST_Node(s);
				return true;
			} else {
				return left.insertNode(s);
			}
		} else {
			if (right == null) {
				right = new BST_Node(s);
				return true;
			} else {
				return right.insertNode(s);
			}
		}

	}

	public boolean containsNode(String s) {
		if (s.equals(data)) {
			return true;
		} else if (data.compareTo(s) > 0) {
			if (this.left == null) {
				return false;
			} else {
				return this.left.containsNode(s);
			}
		} else {
			if (this.right == null) {
				return false;
			} else {
				return this.right.containsNode(s);
			}
		}
	}

	public boolean removeNode(String s, BST_Node remove) {
		if (!containsNode(s)) {
			return false;
		} else if (this.data.compareTo(s) > 0) {
			if (left != null) {
				return left.removeNode(s, this);
			} else {
				return false;
			}
		} else if (this.data.compareTo(s) < 0) {
			if (right != null) {
				return right.removeNode(s, this);
			} else {
				return false;
			}
		} else {
			if (left == null && right == null) {
				if (remove.left == this) {
					remove.left = null;
					return true;
				} else if (remove.right == this) {
					remove.right = null;
					return true;
				}
			} else if (left == null && right != null) {
				if (remove.left == this) {
					remove.left = this.right;
					return true;
				} else if (remove.right == this) {
					remove.right = this.right;
					return true;
				}
			} else if (left != null && right == null) {
				if (remove.left == this) {
					remove.left = this.left;
					return true;
				} else if (remove.right == this) {
					remove.right = this.left;
					return true;
				}
			} else {
				this.data = this.left.findMax().data;
				return this.left.removeNode(this.data, this);
			}
			return true;
		}
	}

	public BST_Node findMin() {
		if (this.left == null) {
			return this;
		} else {
			return this.left.findMin();
		}
	}

	public BST_Node findMax() {
		if (this.right == null) {
			return this;
		} else {
			return this.right.findMax();
		}
	}

	public int getSize() {
		int leftSize = 0;
		int rightSize = 0;

		if (left != null) {
			leftSize = left.getSize();
		}
		if (right != null) {
			rightSize = right.getSize();
		}

		return leftSize + rightSize + 1;
	}

	public int getHeight() {
		int leftHeight = 0;
		int rightHeight = 0;
		if (left != null) {
			leftHeight = left.getHeight();
		}
		if (right != null) {
			rightHeight = right.getHeight();
		}

		return Math.max(leftHeight, rightHeight) + 1;
	}

}