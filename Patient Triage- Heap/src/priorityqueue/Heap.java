package priorityqueue;

import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      //TODO: Implement this method.
      this.comparator = comparator;
      this.isMaxHeap = isMaxHeap;
      heap = (T[]) new Object[INIT_SIZE];
      numElements = 0;
  }

  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleUp(int index) {
      //TODO: Implement this method. // recursively call bubleup
    if(index > getSize() || index < 0){
      throw new IndexOutOfBoundsException();
    }
    while(index > 0){
      int parentIndex = getParentOf(index);
      if(compareElements(heap[index],heap[parentIndex]) <= 0){
        return;
      }
      else{
        swapIndices(index, parentIndex);
        index = parentIndex;
      } 
    }
  }

  private int getParentOf(int childIndex){
    int parentIndex = (childIndex - 1) / 2;
    return parentIndex;
  }

  private void swapIndices(int index1, int index2){
    T temp;
    temp = heap[index1];
    heap[index1] = heap[index2];
    heap[index2] = temp;
  }
  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleDown(int index) {
    //TODO: Implement this method.
    if(index > getSize() || index < 0){
      throw new IndexOutOfBoundsException();
    }
    T value = heap[index];
    int childIndex = getLeftChildOf(index);
    while(childIndex < numElements){
      T maxVal = value;
      int  maxIndex = -1;
      for(int i = 0; i < 2 && i + childIndex < numElements ; i++){
        if(compareElements(heap[i + childIndex], maxVal) > 0){
          maxVal = heap[i + childIndex];
          maxIndex = i + childIndex;
        }
      }
      if ( maxVal == value){
        return;
      }
      else{
        swapIndices(index, maxIndex);
        index = maxIndex;
        childIndex = (2 * index) + 1;
      }
    }
  }

  private int getLeftChildOf(int parentIndex){
    int leftChild = (2 * parentIndex) + 1;
    return leftChild;
  }


  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = false;
      //TODO: Implement this method.
    if(numElements == 0){
      return true;
    }
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int getSize(){
    int size = numElements;
      //TODO: Implement this method.
    return size;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a variable, compareSign, 
   * based on the state of the boolean variable isMaxHeap. It then calls the compare method from the 
   * comparator object and multiplies its output by compareSign.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * negative int otherwise (if isMaxHeap),
   * return negative int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * positive int otherwise (if ! isMinHeap).
   */
  public int compareElements(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek()throws QueueUnderflowException{
    T data;
      //TODO: Implement this method.
    if(numElements == 0){
      throw new QueueUnderflowException();
    }
    data = heap[0];
    return data;
  }
  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeueElement() throws QueueUnderflowException{
    T data;
      //TODO: Implement this method
    if(isEmpty()){
      throw new QueueUnderflowException();
    }
    data = heap[0];
    swapIndices(0, numElements-1);
    heap[numElements-1] = null;
    numElements--;
    bubbleDown(0);
    return data;
  }
    
  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueueElement(T newElement) {
      //TODO: Implement this method.
    expandCapacity();
    heap[numElements] = newElement;
    bubbleUp(numElements);
    numElements++;
  }
    
  

  private void expandCapacity(){
    if(heap.length == numElements){
      int capacity = numElements * 2;
      T[] heapArray = (T[]) new Object[capacity];
      
      for(int i = 0; i < heap.length; i++){
        heapArray[i] = heap[i]; 
      }
      heap = heapArray;
    }
  }

}