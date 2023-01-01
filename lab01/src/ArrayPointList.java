import java.awt.*;
import java.util.Arrays;

public class ArrayPointList implements PointList {

    private Point[] arrPoint;
    private final int size;
    private int cursor;
    private int lastElement;


    ArrayPointList(int size) {
        arrPoint = new Point[size];
        cursor = 0;
        lastElement = -1;
        this.size = size;
    }

    ArrayPointList() {
        this(MAX_SIZE);
    }


    @Override
    public void append(Point newPoint) throws RuntimeException{
        lastElement++;
        arrPoint[lastElement] = (Point) newPoint.clone();
        cursor = lastElement;
    }

    @Override
    public void clear() {
        lastElement = -1;
        cursor = 0;
    }

    @Override
    public boolean isEmpty() {
        return lastElement == -1;
    }

    @Override
    public boolean isFull() {
        return (size == lastElement + 1);
    }

    @Override
    public boolean goToBeginning() {
        cursor = 0;
        return lastElement != -1;
    }

    @Override
    public boolean goToEnd() {
        cursor = lastElement;
        return lastElement != -1 ;
    }

    @Override
    public boolean goToNext() {

        if (cursor == lastElement || lastElement == -1) return false;
        cursor++;
        return true;
    }

    @Override
    public boolean goToPrior() {
        if (cursor == 0) return false;
        cursor --;
        return true;
    }

    @Override
    public Point getCursor() {
        if (lastElement == -1) return null;
        Point p = arrPoint[cursor];
        return p;
    }

    @Override
    public String toString() {
        if (lastElement == -1) return "Empty List";
        return arrPoint.toString();
    }
}
