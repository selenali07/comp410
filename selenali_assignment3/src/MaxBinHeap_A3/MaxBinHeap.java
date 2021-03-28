package MaxBinHeap_A3;

public class MaxBinHeap implements Heap_Interface {
	private double[] array; // load this array
	private int size;
	private static final int arraySize = 10000;
	// Everything in the array will initially
	// be null. This is ok! Just build out
	// from array[1]

	public MaxBinHeap() {
		this.array = new double[arraySize];
		this.array[0] = Double.NaN;
		size = 0;
		// 0th will be unused for simplicity
		// of child/parent computations...
		// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public double[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(double element) {
		if (array == null) {
			array = new double[arraySize];
			this.array[0] = Double.NaN;
		}
		size++;
		this.array[size] = element;
		bubbleUp(size);
	}

	@Override
	public void delMax() {
		if (size <= 0) {
			return; // void
			// If delMax is done on an empty heap, treat it as a no-op... i.e.,
			// do nothing other than return void.
		}
		bubbleDown(array[size], 1);
		size--;
	}

	@Override
	public double getMax() {
		return size == 0 ? Double.NaN : array[1];
	}

	@Override
	public void clear() {
		this.array = new double[arraySize];
		this.array[0] = Double.NaN;
		size = 0;
	}

	@Override
	public int size() {
		if (size <= 0) {
			size = 0;
		}
		return size;
	}

	@Override
	public void build(double[] elements) {
		this.clear();
		this.size = elements.length;
		// placing all elements into the heap array with no regard to heap order.
		for (int i = 0; i < size; i++) {
			if (elements[i] != Double.NaN) {
				this.array[i + 1] = elements[i];
			}
		}
		// then go to the parent of the last node, and bubble down as needed.
		heapify(size);
	}

	@Override
	public double[] sort(double[] elements) {
		this.build(elements.clone());
		double[] sortedArray = elements.clone();
		// return an array containing those same elements in increasing
		// order with the smallest element in array slot 0.
		for (int i = sortedArray.length - 1; i >= 0; i--) {
			sortedArray[i] = this.getMax();
			delMax();
			// repeated delMax() operations should be done to
			// extract the elements in sorted order,
		}
		return sortedArray;
	}

	// HELPER METHODS/FUNCTIONS

	private boolean hasLeftChild(int index) {
		return getLeft(index) <= this.size;
	}

	private boolean hasRightChild(int index) {
		return getRight(index) <= this.size;
	}

	private int getLeft(int index) {
		return index * 2;
	}

	private int getRight(int index) {
		return index * 2 + 1;
	}

	private void bubbleUp(int index) {
		int parentIdx = index / 2;
		double current = array[index];

		while (array[parentIdx] < current && index > 1) {
			array[index] = array[parentIdx];
			index = parentIdx;
			parentIdx = parentIdx / 2;
		}
		array[index] = current;
	}

	private void bubbleDown(double root, int index) {
		double left = array[getLeft(index)];
		double right = array[getRight(index)];
		if (getLeft(index) > size || (root > left && (root > right || getRight(index) > size))) {
			array[index] = root;
		} else if (left > right || getRight(index) > size) {
			array[index] = left;
			bubbleDown(root, getLeft(index));
		} else {
			array[index] = right;
			bubbleDown(root, getRight(index));
		}

	}

	private void heapify(int index) {
		for (int i = index / 2; i >= 1; i--) {
			maxHeapify(this.array, i);
		}
	}

	private void maxHeapify(double elements[], int index) {
		int root = index;
		if (hasLeftChild(index) && elements[getLeft(index)] > elements[index]) {
			root = getLeft(index);
		}
		if (hasRightChild(index) && elements[getRight(index)] > elements[root]) {
			root = getRight(index);
		}
		if (root != index) { // swap
			double temp = elements[index];
			elements[index] = elements[root];
			elements[root] = temp;
			maxHeapify(elements, root);
		}
	}
}