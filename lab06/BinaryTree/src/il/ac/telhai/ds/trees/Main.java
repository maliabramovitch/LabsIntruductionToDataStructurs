package il.ac.telhai.ds.trees;

import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
            ExpressionTree et = ExpressionTree.createTree(tokenizer);
            System.out.println(et.preOrder());
            System.out.println(et.inOrder());
            System.out.println(et.postOrder());

            System.out.println();
            System.out.println(et.prefix());
            System.out.println();
            System.out.println(et.evaluate());
            System.out.println();
            System.out.println(et.infix());
        }
        catch (Exception e) {
            System.out.println("Fuck my life");
            System.exit(0);
        }

    }
}