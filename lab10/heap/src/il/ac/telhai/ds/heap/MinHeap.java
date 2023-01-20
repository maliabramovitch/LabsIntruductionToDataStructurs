
package il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int lastElement;
    private int size;


    @SuppressWarnings({"unchecked", "rawtypes"})
    public MinHeap(int length) {
        heap = (T[]) new Comparable[(length + 1)];
        size = length;

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MinHeap(T[] arr) {
        size = arr.length;
        heap = (T[]) new Comparable[(size + 1)];
        lastElement = 0;
        for (int i =0; i < arr.length; i++) {
            lastElement++;
            heap[lastElement] = arr[i];
        }
        for (int i = lastElement; i > 0; i--) {

            spotify(i);
        }

    }

    public boolean isFull() {
        return size == lastElement;
    }

    public boolean isEmpty() {
        return lastElement == 0;
    }

    public void insert(T element) {
        if (isFull()) throw new RuntimeException("heap is full");
        lastElement++;
        heap[lastElement] = element;
        heapify(lastElement);
    }

    public T getMin() throws RuntimeException {
        if (lastElement == 0) throw new RuntimeException("Heap is empty");
        return heap[1];
    }

    public T deleteMin() {
        if (lastElement == 0) return null;
        T tmp = heap[1];
        heap[1] = heap[lastElement];
        lastElement--;
        spotify(1);
        return tmp;
    }

    /**
     * return a string represents the heap. The order of the elements are according
     * to The string starts with "[", ends with "]", and the values are seperated
     * with a comma
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 1; i < lastElement + 1; ++i) {
            sb.append(heap[i]);
            if (i != lastElement) sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }

    private void heapify(int i) {
        if (i == 1 || heap[i / 2].compareTo(heap[i] ) < 1) {
            return;
        }
        T tmp = heap[i];
        heap[i] = heap[i / 2];
        heap[i/2] =tmp;
        heapify(i / 2);
    }

    private void spotify(int i) {
        if (2*i > lastElement) return;
        if (heap[i].compareTo(heap[i*2])<0){
            if (i*2+1 > lastElement) return;
            else if (heap[i].compareTo(heap[i*2+1]) < 0) return;
        }
        int min = findMax((i * 2), (i * 2 + 1));
        if (heap[i].compareTo(heap[min]) < 0) return;
        if (heap[i].compareTo(heap[min]) > 0) {
            T tmp = heap[i];
            heap[i] = heap[min];
            heap[min] = tmp;
        }
        spotify(min);
    }


    private int findMax(int i, int j) {
        if (j > lastElement) return i;
        if (heap[i].compareTo(heap[j]) < 0) return i;
        if (heap[i].compareTo(heap[j]) > 0) return j;
        else return -1;
    }
}