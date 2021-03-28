package MaxBinHeap_A3;

public interface Heap_Interface {
  /*
    Interface: The BHEAP will provide this collection of operations:

    insert
      in: a double (assume no duplicates will be inserted)
      return: void
    delMin
      in: nothing
      return: void
    getMin
      in: nothing
      return: a double
    size
      in: nothing
      return: integer 0 or greater
    build
      in: array of double that needs to be in the heap
      return: void
      (assume for a build that the bheap will start empty)
    sort
      in: array of double
      return: array of double
    getheap:
      in: nothing
      return: array of double (the internal array that holds the heap elements
      (this will be called by the grader to examine your data structure)
  */

  // ADT operations
  void insert(double element);
  void delMax();
  double getMax();
  void clear();
  int size();
  void build(double [] elements);
  double[] getHeap();
  double[] sort(double[] elements);
}