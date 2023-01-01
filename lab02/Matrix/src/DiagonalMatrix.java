public class  nDiagonalMatrix implements Matrix {

    private final int size;
    private double[] dMat;
    boolean transpose;

    public DiagonalMatrix(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        dMat = new double[2 * size - 1];
        this.size = size;
        transpose = false;
    }

    public DiagonalMatrix() {
        this(MAX_SIZE);
    }

    private void checkLigArg(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IllegalArgumentException();
        }
    }


    private int ifTranspose(int i, int j) {
        return ((i - j) + size - 1);
    }

    @Override
    public double get(int i, int j) {
        checkLigArg(i, j);
        if (transpose) {
            return dMat[ifTranspose(i, j)];
        }
        return dMat[(j - i) + size - 1];
    }

    @Override
    public void set(int i, int j, double n) {
        checkLigArg(i, j);
        if (transpose) {
            dMat[ifTranspose(i, j)] = n;
        }
        dMat[(j - i) + size - 1] = n;
    }

    @Override
    public void transpose() {
        if (transpose) transpose = false;
        else transpose = true;
    }

    @Override
    public Matrix getTranspose() {
        DiagonalMatrix nDmat = new DiagonalMatrix(size);
        nDmat.dMat = this.dMat;
        nDmat.transpose();
        return nDmat;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                str = str + Double.toString(get(i, j));
                if (j < size) str = str + "\t";
            }
            str = str + "\n";
        }
        return str;
    }
}
