import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PointListCommandLine {

    public static void main(String[] args) throws IOException {
        PointList polygon = new ArrayPointList();
        Point vertex;
        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer tokens = new StreamTokenizer(reader);
        tokens.nextToken();
        while (!(tokens.sval).equals("quit")) {
            switch (tokens.sval) {
                case "add":
                    vertex = new Point();
                    tokens.nextToken();
                    vertex.x = (int) tokens.nval;
                    tokens.nextToken();
                    vertex.y = (int) tokens.nval;
                    polygon.append(vertex);
                    break;

                case "curr":
                    String str = "(";
                    str = str + polygon.getCursor().x;
                    str = str + ", ";
                    str = str + polygon.getCursor().y;
                    str = str + ")";
                    System.out.println(str);
                    break;

                case "next":
                    if(polygon.goToNext()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "prev":
                    if(polygon.goToPrior()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "start":
                    if(polygon.goToBeginning()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "end":
                    if(polygon.goToEnd()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "empty":
                    if (polygon.isEmpty()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "full":
                    if (polygon.isFull()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "clear":
                    polygon.clear();
                    break;

                default:
                    break;
            }
            tokens.nextToken();
        }
    }
}
