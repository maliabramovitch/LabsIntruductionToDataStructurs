public class SparseMatrix<T> implements Matrix<T> {

    private final int size;
    private final T defaultValue;
    boolean transpose = false;
    private final DLinkedList<SparseMatrixEntry> wirdows;


    public SparseMatrix(int size, T defaultValue) {
        this.defaultValue = defaultValue;
        this.size = size;
        wirdows = new DLinkedList<>();
    }

    public SparseMatrix(T defaultValue) {
        this(MAX_SIZE, defaultValue);
    }
    private SparseMatrixEntry search(int row, int col) {
        this.wirdows.goToBeginning();
        SparseMatrixEntry tmp = this.wirdows.getCursor();
        while (tmp != null) {
            if (wirdows.getCursor().getRow() == row && wirdows.getCursor().getCol() == col) {
                return wirdows.getCursor();
            }
            tmp = wirdows.getNext();
        }
        return null;
    }

    private SparseMatrixEntry rightSearch(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
        if (transpose) {
            return search(col, row);
        }
        return search(row, col);
    }

    @Override
    public T get(int row, int col) {
        SparseMatrixEntry entry = rightSearch(row, col);
        if (entry == null) {
            return defaultValue;
        }
        return entry.getValue();
    }

    @Override
    public void set(int row, int col, T element) {
        SparseMatrixEntry entry = rightSearch(row, col);
        if (entry == null) {
            entry = new SparseMatrixEntry(row, col, element);
            this.wirdows.insert(entry);
        }
        else {
            entry.setValue(element);
        }
    }

    @Override
    public void transpose() {
        transpose = !transpose;
    }

    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T val) {
            this.row = row;
            this.col = col;
            this.value = val;
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return this.col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T newVal) {
            this.value = newVal;
        }
    }

}
