public interface Matrix {
    int MAX_SIZE = 100;

    /**
     * returns the value of the element at position (i,j).
     */
    double get(int i, int j);

    /**
     * update the value of the element at position (i,j) to x.
     */
    void set(int i, int j, double n);

    /**
     * replace the current matrix wıth its transpose.
     */
    void transpose();

    /**
     * returns a new matrix which equals to the transpose of the current matrix.
     */
    Matrix getTranspose();
}
