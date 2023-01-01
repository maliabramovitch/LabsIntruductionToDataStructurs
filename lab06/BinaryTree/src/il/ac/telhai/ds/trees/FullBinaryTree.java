package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T> {

    public FullBinaryTree(T value, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        super(value, left, right);
        if (left == null && right != null)
            throw new IllegalArgumentException("lef or right is null");
        if (right == null && left != null)
            throw new IllegalArgumentException("lef or right is null");
    }

    public FullBinaryTree(T value) {
        super(value);
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        if (left == null && super.getRight() == null) {
            super.setLeft(null);
            return;
        }
        if (!(left instanceof FullBinaryTree) || this.getRight() == null)
            throw new IllegalArgumentException();
        super.setLeft(left);
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if (super.getLeft() == null && right == null) {
            super.setRight(null);
            return;
        }
        if (!(right instanceof FullBinaryTree) || this.getLeft() == null)
            throw new IllegalArgumentException();
        super.setRight(right);
    }
}
