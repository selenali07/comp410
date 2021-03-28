package SKPLIST_A4;

import java.util.Arrays;
import java.util.Random;

public class SkipList implements SkipList_Interface {
	private SkipList_Node root;
	private final Random rand;
	private double probability;
	private int size;
	private final int MAXHEIGHT = 30;
	private int _maxHeight;

	public SkipList(int maxHeight) {
		if(maxHeight >= MAXHEIGHT){
			maxHeight = MAXHEIGHT;
		} else {
			maxHeight = root.getLevel();
		}
		_maxHeight = maxHeight;
		root = new SkipList_Node(Double.NaN, maxHeight);
		rand = new Random();
		probability = 0.5;
		size = 0;
	}

	@Override
	public void setSeed(long seed) {
		rand.setSeed(seed);
	}

	@Override
	public void setProbability(double probability) {
		this.probability = probability;
	}

	private boolean flip() {
		// use this where you "roll the dice"
		// call it repeatedly until you determine the level
		// for a new node
		return rand.nextDouble() < probability;
	}

	@Override
	public SkipList_Node getRoot() {
		return root;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		int levels;
		for (levels = 0; levels < root.getNext().length && root.getNext(levels) != null; levels++)
			;

		StringBuilder[] sbs = new StringBuilder[levels];

		for (int i = 0; i < sbs.length; i++) {
			sbs[i] = new StringBuilder();
			sbs[i].append("level ").append(i).append(":");
		}

		SkipList_Node current = root;

		while (current.getNext(0) != null) {
			current = current.getNext(0);
			for (int i = levels - 1; i >= current.getNext().length; i--) {
				sbs[i].append("\t");
			}
			for (int i = current.getNext().length - 1; i >= 0; i--) {
				if (current.getNext(i) == null) {
					levels--;
				}
				sbs[i].append("\t").append(current.getValue());
			}
		}

		for (int i = sbs.length - 1; i >= 0; i--) {
			sb.append(sbs[i]).append("\n");
		}

		return sb.toString();
	}
	/*
  insert:
    in: a double (the element to be stored into the skiplist)
    return: boolean, return true if insert is successful, false otherwise
    effect: if the double is already in the skiplist, then there is no change to
            the skiplist state, and return false
            if the double is not already in the skiplist, then a new skiplist node
              is created, the double put into it as data, the new node is linked
              into the skiplist at the proper place; size is incremented by 1,
              and return a true
	 */
	@Override
	public boolean insert(double value) {
		if (this.contains(value)) {
			return false;
		}
		int level = 1;
		while(this.flip()&& level < _maxHeight - 1) {
			level++;
		}
		SkipList_Node current = getRoot();
		level %= root.getLevel();
		SkipList_Node n = new SkipList_Node(value, level);
		for (int i = root.getLevel() - 1; i > -1; i--) {
			while ((current.getNext(i) != null) && current.getNext(i).getValue() < value) {
				current = current.getNext(i);
			}
			if (i < level) {
				n.setNext(i, current.getNext(i));
				current.setNext(i, n);
			}
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(double value) {
		if (this.contains(value)) {
			SkipList_Node head = getRoot();
			for (int i = root.getLevel() - 1; i > -1; i--) {
				while ((head.getNext(i) != null) && head.getNext(i).getValue() < value) {
					head = head.getNext(i);
				}
				if (head.getNext(i) != null && head.getNext(i).getValue() == value) {
					head.setNext(i, head.getNext(i).getNext(i));
				}
			}
			size--;
			return true;
		}
		return false;
	}
	@Override
	public boolean contains(double value) {
		if (this.empty()) {
			return false;
		}
		SkipList_Node current = getRoot();
		for (int level = root.getLevel() - 1; level > -1; level--) {
			while ((current.getNext(level) != null) && current.getNext(level).getValue() < value) {
				current = current.getNext(level);			
			}
			if (current.getNext(level) != null && current.getNext(level).getValue() == value) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double findMin() {
		if (this.empty()) {
			return Double.NaN;
		}
		return root.getNext(0).getValue();
	}

	@Override
	public double findMax() {
		if (this.empty()) {
			return Double.NaN;
		} 
		int level = level();
		SkipList_Node max = getRoot();
		while(level != 0 || max.getNext(level) != null) {
			if (max.getNext(level) == null) {
				level--;
			} else {
				max = max.getNext(level);
			}
		}
		return max.getValue();
	}

	@Override
	public boolean empty() {
		return this.size() <= 0;
	}
	/*
	 *  clear:
    in: none
    return: void
    effect: all elements in the skip list are unhooked so that no elements are in the list
            size is set to 0
            sentinel node remains
            the effect is to create a skip list state that exists when it is first 
            produced by the constructor
	 */
	@Override
	public void clear() {
		int level = root.getLevel() - 1;
		while(level > -1) {
			root.setNext(level, null);
			level--;
		}
		size = 0;
	}
	/*
  size:
    in: nothing
    return: number of elements stored in the skiplist
    effect: no change to skiplist state
	 */
	@Override
	public int size() {
		return size;
	}
	/*
  level:
    in: none
    return: integer, the level of the highest node in the skiplist
    effect: no change in skiplist state
    error: return -1 if skiplist is empty (size is 0, only sentinel node is there)
	 */
	@Override
	public int level() {
		if (this.empty()) {
			return -1;
		} 
		int level = root.getLevel() - 1;
		while(level > -1) {
			if (root.getNext(level) != null) {
				return level;
			}
			level--;
		}
		return -1;
	}
	/*
  max: 
    in: none
    return: integer, the value of MAXHEIGHT that is set in the list constructor
    effect: no change in skip list state
	 */
	@Override
	public int max() {
		return _maxHeight;
	}
}