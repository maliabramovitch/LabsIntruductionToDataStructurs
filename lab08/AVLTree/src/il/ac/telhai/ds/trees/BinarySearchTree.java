package il.ac.telhai.ds.trees;

import com.sun.source.tree.BinaryTree;

public class BinarySearchTree <T extends Comparable<T>>{

    BstNode root;
    int treeSize;

    // Binary Search Tree Node
    class BstNode {
        T val;
        BstNode left, right;

        public BstNode(T val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
        treeSize = 0;
    }

    public enum Direction {
        LEFT, RIGHT
    }

    // Returns the val given a path from the root.
    // Used for testing. DO NOT DELETE.
    public T getValInPath(Direction... direction) {
        BstNode node = root;
        for (Direction d : direction) {
            if (d.equals(Direction.LEFT) && node.left != null)
                node = node.left;
            else if (d.equals(Direction.RIGHT) && node.right != null)
                node = node.right;
            else
                return null;
        }
        return node.val;
    }

    /**
     * Constructs an empty BinarySearchTree.
     */


    /**
     * returns the number of elements in the tree
     *
     * @param
     */
    public int size() {
        return treeSize;
    }


    private BstNode addNode(BstNode root, T val) {
        if (root == null) {
            root = new BstNode(val);
        } else if (root.val.compareTo(val) > 0) {
            root.left = addNode(root.left, val);
        } else {
            root.right = addNode(root.right, val);
        }
        return root;
    }

    /**
     * Adds the object value to the tree as a leaf according to the parameter.
     *
     * @param val
     * @return True, if the element was added. Otherwise, false.
     */
    public boolean add(T val) {
        if (contains(val))
            return false;
        root = addNode(root, val);
        treeSize++;
        return true;
    }

    private BstNode successorParent(BstNode roott) {
        if (roott.left.left == null || (roott.right.right == null && roott.right.left == null)) {
            return roott;
        }
        return successorParent(roott.right);
    }

    private BstNode successor(BstNode roott) {
        if (roott.left.left == null) {
            return roott;
        }
        return successorParent(roott.right);
    }


    private BstNode finedBstNode(BstNode root, T val) {
        if (root == null) {
            return null;
        } else if (root.val.compareTo(val) < 0) {
            return finedBstNode(root.left, val);
        } else if (root.val.compareTo(val) > 0) {
            return finedBstNode(root.right, val);
        }
        return root;
    }


    private BstNode removeRec(BstNode parent, T val) {
        if (parent == null) return parent;
        if (parent.val.compareTo(val) == 0 && parent.left == null && parent.right == null) {
            return null;
        } else if (parent.val.compareTo(val) == 0 && parent.left != null && parent.right == null) {
            return parent.left;
        } else if (parent.val.compareTo(val) == 0 && parent.left == null && parent.right != null) {
            return parent.right;
        } else if (parent.val.compareTo(val) == 0 && parent.left != null && parent.right != null) {
            BstNode tmpLeft = parent.left;
            BstNode tmpRight = parent.right;
            BstNode successorParent = successorParent(parent);

            BstNode successor;
            if (successorParent != parent)
                successor = successorParent.left;
            else
                successor = parent.right;
            successorParent.left = null;
            parent = successor;
            parent.left = tmpLeft;
            BstNode tmp2 = parent;
            while (tmp2 != null) tmp2 = tmp2.right;
            tmp2 = tmpRight;
            return parent;
        }
        if (parent.val.compareTo(val) < 0) {
            parent.right = removeRec(parent.right, val);
        } else if (parent.val.compareTo(val) > 0) {
            parent.left = removeRec(parent.left, val);
        }
        return parent;

    }

    /**
     * Removes the object in the tree which is equal to the parameter.
     * Nothing is done if not found
     *
     * @param val: the object to be looked for in the tree
     * @return True, if the element was removed. Otherwise, false.
     */
    public boolean remove(T val) {
        if (!contains(val)) return false;
        root = removeRec(root, val);
        treeSize--;
        return true;
    }


    private boolean containsRec(BstNode root, T val) {
        if (root == null)
            return false;
        if (root.val.compareTo(val) == 0)
            return true;
        if (root.val.compareTo(val) > 0)
            return containsRec(root.left, val);
        else
            return containsRec(root.right, val);
    }

    /**
     * Looks for an object which is equal to the parameter.
     *
     * @param val: the object to be looked for in the tree
     * @return true if the tree contains this object. Otherwise, return false.
     */
    public boolean contains(T val) {
        return containsRec(root, val);
    }


    private T findGeRec(BstNode root, T val) {
        if (root == null)
            return null;
        T ret = root.val;

        if (root.val.compareTo(val) == 0) {
            return ret;
        }
        if (root.val.compareTo(val) > 0 && (root.left == null || root.left.val.compareTo(val) < 0))
            return ret;
        else if (root.val.compareTo(val) > 0 && (root.left == null || root.left.val.compareTo(val) > 0))
            ret = findGeRec(root.left, val);
        else if (root.val.compareTo(val) < 0)
             ret = findGeRec(root.right, val);
        return ret;
    }

    /**
     * Looks for the minimal object in the tree, which is greater than or equal to
     * the parameter.
     *
     * @param val: the object to be looked for in the tree
     * @return a reference to the found object.
     */
    public T findGe(T val) {
        if (contains(val)) return val;
        return findGeRec(root, val);
    }

}
