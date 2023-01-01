package il.ac.telhai.ds.stack;
public class DLinkedListStack<T> implements Stack<T> {

    DLinkedList<T> stack;
    int size;

    public DLinkedListStack() {
        this.stack = new DLinkedList<T>();
        this.size = 0;
    }


    @Override
    public void push(T t) {
        this.stack.goToEnd();
        this.stack.insert(t);
        size++;
    }

    @Override
    public T pop() {
        this.stack.goToEnd();
        T ret = this.stack.remove();
        this.stack.goToEnd();
        return ret;
    }

    @Override
    public T top() {
        return this.stack.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }


    @Override
    public String toString() {
        String str = "";
        str = str + '[';
        if (this.stack.isEmpty()) {
            str = str + ']';
            return str;
        }
        this.stack.goToEnd();
        while (this.stack.hasPrev()) {
            str = str + this.stack.getCursor();
            str = str + ", ";
            this.stack.getPrev();
        }
        str = str + this.stack.getCursor();
        str = str + ']';
        return str;

    }
}
