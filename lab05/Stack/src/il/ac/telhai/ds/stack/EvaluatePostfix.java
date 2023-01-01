package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

    private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
    private static Stack<Double> myStack = new DLinkedListStack<Double>();

    private static void laazazel() {
        System.err.println(tokenizer);
        System.err.println(myStack);
        System.exit(1);
    }

    public static void main(String[] args) throws IOException {
        tokenizer.slashSlashComments(false);
        tokenizer.ordinaryChar('/');
        while (true) {
            tokenizer.nextToken();
            int typeVal = tokenizer.ttype;
            if (typeVal == StreamTokenizer.TT_EOF || typeVal == StreamTokenizer.TT_WORD)
                break;
            if (typeVal == StreamTokenizer.TT_NUMBER) {
                double val1 = tokenizer.nval;
                myStack.push(val1);
            } else {
                if (myStack.isEmpty()) {
                    laazazel();
                }
                double num1 = myStack.pop();
                if (myStack.isEmpty()) {
                    laazazel();
                }
                double num2 = myStack.pop();
                switch ((char)typeVal) {
                    case '+':
                        myStack.push(num2 + num1);
						break;
                    case '*':
                        myStack.push(num2 * num1);
						break;
                    case '-':
                        myStack.push(num2 - num1);
						break;
                    case '/':
                        myStack.push(num2 / num1);
						break;
                    default:
                        laazazel();
						break;
                }
            }
        }
		if (myStack.isEmpty()){
			laazazel();
		}
		double vel = myStack.pop();
		if (!myStack.isEmpty()) {
			laazazel();
		}
		System.out.println(vel);
    }


}
