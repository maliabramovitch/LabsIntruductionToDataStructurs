package il.ac.telhai.ds.trees;

public class AVLTree<T extends Comparable<T>> {

    AVLTree<T> l;
    AVLTree<T> r;
    T value;
    int balance;
    int height;


    public AVLTree(T value) {
        this.l = null;
        this.r = null;
        balance = 0;
        height = 0;
        this.value = value;
    }

    //add the value to the tree, and return the updated root of the tree.
    public AVLTree<T> add(T value) {
        if (value.compareTo(this.value) < 0) {
            if (l != null) {
                l = l.add(value);
                height = Math.max(l.height, (r != null ? r.height : -1)) + 1;
                balance = l.height - r.height;
                if (balance == 2) {
                    if (l.balance == -1) {
                        lr();
                        ll();
                    } else if (l.balance == 1) {
                        ll();
                    }
                }
            } else {
                l = new AVLTree<>(value);
            }
            return this;
        } else {
            if (r != null) {
                r = r.add(value);
                height = Math.max(r.height, (l != null ? l.height : -1)) + 1;
                balance = l.height - r.height;
                if (balance == -2) {
                    if (l.balance == -1) {
                        rl();
                        rr();
                    } else if (r.balance == -1) {
                        rr();
                    }
                }
            } else {
                r = new AVLTree<>(value);
            }
            return this;
        }

    }


    //return the value in this node
    public T getValue() {
        return value;
    }

    //return the left subTree of this node
    public AVLTree<T> getLeft() {
        return l;
    }

    //return the right subTree of this node
    public AVLTree<T> getRight() {
        return r;
    }

    private void ll() {
        AVLTree<T> tmp = l;
        l = tmp.r;
        tmp.r = this;
        this.value = tmp.value;
        l = tmp.l;
        r = tmp.r;
        --height;
    }

    private void rr() {
        AVLTree<T> tmp = r;
        r = tmp.l;
        tmp.l = this;
        this.value = tmp.value;
        l = tmp.l;
        r = tmp.r;
        --height;
    }

    private void lr() {

    }

    private void rl() {

    }
}
