package il.ac.telhai.ds.stack;

public class DLinkedList<T> implements List<T> {
    DNode head;
    DNode cursor;
    DNode end;

    public DLinkedList() {
        head = null;
        end = null;
        cursor = null;
    }

    @Override
    public void insert(T newElement) {
        if (newElement == null) {
            throw new RuntimeException();
        }
        if (isEmpty()) {
            head = new DNode(newElement);
            end = head;
            cursor = head;
        } else {
            DNode newNode = new DNode(newElement);
            newNode.setPrev(cursor);
            newNode.setNext(cursor.getNext());
            if (cursor.getNext() == null) {
                end = newNode;
            }
            cursor.next = newNode;
            cursor = newNode;
        }
    }

    @Override
    public T remove() {
        if (isEmpty() || cursor == null) {
            return null;
        }
        DNode removei = cursor;
        if (cursor.prev == null) {
            if (cursor.next == null) {
                clear();
            } else {
                head = head.next;
                head.prev = null;
                cursor = head;
            }
        } else {
            if (cursor.next == null) {
                end = cursor.prev;
                cursor.prev.next = null;
                cursor.prev = cursor.prev.prev;
                cursor = head;
            } else {
                cursor.prev.next = removei.next;
                cursor.next.prev = removei.prev;
                cursor = removei.next;
            }
        }
        return removei.element;
    }

    @Override
    public T remove(T element) {
        if (element == null) return null;
        DNode findi = head;
        while (findi != null && !findi.element.equals(element)) {
            findi = findi.next;
        }
        if (findi == null) {
            return null;
        }
        cursor = findi;
        return remove();
    }

    @Override
    public void clear() {
        head = null;
        end = null;
        cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty() || newElement == null) {
            throw new RuntimeException();
        }
        DNode newi = new DNode(newElement);
        if (cursor.prev == null) {
            if (cursor.next != null) {
                newi.next = cursor.next;
                cursor.next.prev = newi;
            }
            head = newi;
            cursor = head;
            end = head;
        }
        else {
            if (cursor.next != null) {
                newi.next = cursor.next;
                cursor.next.prev = newi;
                cursor.prev.next = newi;
            }
            newi.prev = cursor.prev;
            cursor = newi;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean goToBeginning() {
        if (!isEmpty()) {
            cursor = head;
            return true;
        }
        return false;
    }

    @Override
    public boolean goToEnd() {
        if (!isEmpty()) {
            cursor = end;
            return true;
        }
        return false;
    }

    @Override
    public T getNext() {
        if (isEmpty() || cursor.next == null) {
            return null;
        }
        cursor = cursor.next;
        return cursor.getElement();
    }

    @Override
    public T getPrev() {
        if (isEmpty() || cursor.prev == null) {
            return null;
        }
        cursor = cursor.prev;
        return cursor.getElement();
    }

    @Override
    public T getCursor() {
        if (isEmpty()) return null;
        return cursor.getElement();
    }

    @Override
    public boolean hasNext() {
        if (isEmpty() || cursor.next == null)
            return false;
        return true;
    }

    @Override
    public boolean hasPrev() {
        if (isEmpty() || cursor.prev == null)
            return false;
        return true;
    }

    @Override
    public String toString() {
        String str = "[";
        DNode toPrint = head;
        while (head != null) {
            if (toPrint.next == null) {
                str = str + toPrint.element.toString() + "]";
                break;
            } else {
                str = str + toPrint.element.toString() + ", ";
                toPrint = toPrint.next;
            }
        }
        if (head == null) str = str + "]";
        return str;
    }

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;
        }
    }
}