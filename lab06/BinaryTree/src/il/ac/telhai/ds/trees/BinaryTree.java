package il.ac.telhai.ds.trees;

public class BinaryTree<T> implements BinaryTreeI<T>{
    private BinaryTreeI<T> left;
    private BinaryTreeI<T> right;
    private T value;

    public BinaryTree(T value) {
        left = null;
        right = null;
        this.value = value;
    }

    public BinaryTree(T value,BinaryTreeI<T> left, BinaryTreeI<T> right) {
        this.left = left;
        this.right = right;
        this.value = value;
    }


    @Override
    public BinaryTreeI<T> getLeft() {
        return left;
    }

    @Override
    public BinaryTreeI<T> getRight() {
        return right;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    @Override
    public int height() {
        int hightL = 0, hightR = 0;
        if (left != null)
            hightL = 1 + left.height();
        if (right != null)
            hightR += 1 + right.height();
        return Math.max(hightL, hightR);
    }

    @Override
    public int size() {
        int size = 0;
        if (left != null)
            size += left.size();
        if (right != null)
            size += right.size();
        return size + 1 ;
    }

    @Override
    public void clear() {
        this.right = null;
        this.left = null;
        value = null;
    }

    @Override
    public String preOrder() {
        return preOrder(" ", " ");
    }

    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        str.append(separationBeforeVal).append(value.toString()).append(separationAfterVal);
        if (left != null) {
            str.append(left.preOrder(separationBeforeVal, separationAfterVal));
        }
        if (right != null) {
            str.append(right.preOrder(separationBeforeVal, separationAfterVal));
        }
        return str.toString();
    }

    @Override
    public String inOrder() {
        return inOrder(" ", " ");
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        if (left != null) {
            str.append(left.inOrder(separationBeforeVal, separationAfterVal));
        }
        str.append(separationBeforeVal).append(value.toString()).append(separationAfterVal);
        if (right != null) {
            str.append(right.inOrder(separationBeforeVal, separationAfterVal));
        }
        return str.toString();
    }

    @Override
    public String postOrder() {
        return postOrder(" ", " ");
    }

    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        if (left != null) {
            str.append(left.postOrder(separationBeforeVal, separationAfterVal));
        }

        if (right != null) {
            str.append(right.postOrder(separationBeforeVal, separationAfterVal));
        }
        str.append(separationBeforeVal).append(value.toString()).append(separationAfterVal);
        return str.toString();
    }
}
