package il.ac.telhai.ds.trees;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionTree<T> extends FullBinaryTree<T> {
    private StringBuilder sb;
    StringTokenizer strT;

    public ExpressionTree(T value, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        super(value, left, right);
    }

    public ExpressionTree(T value) {
        super(value);
    }

    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        double d;
        ExpressionTree root;
        int type = tokenizer.ttype;
        if (type == StreamTokenizer.TT_EOF || type == StreamTokenizer.TT_WORD)
            return null;
        if (type == StreamTokenizer.TT_NUMBER) {
            d = tokenizer.nval;
            int n = (int) d;
            root = new ExpressionTree(n, null, null);
            return root;
        } else {
            char c = (char) type;
            root = new ExpressionTree(c, createTree(tokenizer), createTree(tokenizer));
        }
        return root;
    }

    @Override
    public String inOrder() {
        sb = new StringBuilder();
        if (getLeft() == null) {
            int num = (int) getValue();
            sb.append(num);
        } else {
            sb.append("(");
            sb.append(getLeft().inOrder());
            sb.append(" ");
            sb.append(getValue().toString());
            sb.append(" ");
            sb.append(getRight().inOrder());
            sb.append(")");
        }
        return sb.toString();
    }

    public String infix() {
        return inOrder();
    }

    public String prefix() {
        sb = new StringBuilder(preOrder());
        return sb.toString();
    }

    public double evaluate() {
        return evaluateHelper(this);
    }

    public double evaluateHelper(FullBinaryTree bs) {
            if (bs.getValue().equals('+')) {
                return evaluateHelper((FullBinaryTree) bs.getLeft()) + evaluateHelper((FullBinaryTree) bs.getRight());
            }
            if (bs.getValue().equals('-')) {
                return evaluateHelper((FullBinaryTree) bs.getLeft()) - evaluateHelper((FullBinaryTree) bs.getRight());
            }
            if (bs.getValue().equals('*')) {
                return evaluateHelper((FullBinaryTree) bs.getLeft()) * evaluateHelper((FullBinaryTree) bs.getRight());
            }
            if (bs.getValue().equals('/')) {
                return evaluateHelper((FullBinaryTree) bs.getLeft()) / evaluateHelper((FullBinaryTree) bs.getRight());
            }
        return Double.parseDouble(bs.getValue().toString());
    }
    }