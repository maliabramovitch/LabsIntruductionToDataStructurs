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

    private void calculateHeight() {
        height = Math.max(l != null? l.height :-1 , r != null? r.height : -1) +1;
    }

    private void calculateBalance() {
        balance = (l != null ? l.height : -1) - (r != null ? r.height : -1);
    }

    //add the value to the tree, and return the updated root of the tree.
    public AVLTree<T> add(T value) {
        if (value.compareTo(this.value) < 0) {
            if (l != null) {
                l = l.add(value);
                calculateHeight();
                calculateBalance();
                if (balance == 2) {
                    if (l.balance == -1) {
                        return lr();
                    } else if (l.balance == 1 || l.balance == 0) {
                        return ll();
                    }
                }
            } else {
                l = new AVLTree<>(value);
                l.calculateHeight();
                calculateHeight();
                calculateBalance();
            }
        } else {
            if (r != null) {
                r = r.add(value);
                calculateHeight();
                calculateBalance();
                if (balance == -2) {
                    if (r.balance == 1) {
                        return rl();
                    } else if (r.balance == -1  || r.balance == 0) {
                        return rr();
                    }
                }

            } else {
                r = new AVLTree<>(value);
                r.calculateHeight();
                calculateHeight();
                calculateBalance();
            }
        }
        return this;

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
    private AVLTree<T> ll() {
        AVLTree<T> tmp = l;
        l = tmp.r;
        tmp.r = this;
        tmp.l.calculateHeight();
        tmp.r.calculateHeight();
        tmp.calculateHeight();
        return tmp;
    }

    private AVLTree<T> rr() {
        AVLTree<T> tmp = r;
        r = tmp.l;
        tmp.l = this;
        tmp.l.calculateHeight();
        tmp.r.calculateHeight();
        tmp.calculateHeight();
        return tmp;
    }

    private AVLTree<T> lr() {
        AVLTree<T> tmp = l;
        l = tmp.r;
        tmp.r = l.l;
        l.l = tmp;
        return ll();
    }

    private AVLTree<T> rl() {
        AVLTree<T> tmp = r;
        r = tmp.l;
        tmp.l = r.r;
        r.r = tmp;
        return rr();
    }
}
