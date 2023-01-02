package il.ac.telhai.ds.hash;

import il.ac.telhai.ds.linkedlist.DLinkedList;

public class HashTable<V> {
    public static final int DEF_MAX_HASH_SIZE = 10;
    DLinkedList<V>[] hashTable;
    int hashSize;
    int size;

    /**
     * c'tor
     * construct a hash-table of max-size "DEF_MAX_HASH_SIZE".
     */
    public HashTable() {
        this(DEF_MAX_HASH_SIZE);
    }

    /**
     * construct a hash-table of size 'hashSize'.
     *
     * @param hashSize, the size of the constructed hash-table.
     */
    public HashTable(int hashSize) {
        this.hashSize = hashSize;
        hashTable = new DLinkedList[hashSize];
        for (int i = 0; i < hashSize; ++i) {
            hashTable[i] = new DLinkedList<V>();
        }
        size = 0;
    }

    /**
     * @param val
     * @return true if the hash-table contains val, otherwise return false
     */
    public boolean contains(V val) {
        DLinkedList<V> tmp = getDLLBYVakue(val);
        if (tmp.isEmpty()) {
            return false;
        }
        tmp.goToBeginning();
        while (true) {
            if (tmp.getCursor().equals(val)) return true;
            if (tmp.getNext() == null) {
                break;
            }
        }
        return false;
    }

    /**
     * Add val to the hash-table.
     *
     * @param val
     * @return If the val has already exist in the the hash-table, it will not be
     * added again. Return true if the val was added successfully. Otherwise
     * return false.
     */
    public boolean add(V val) {
        if (contains(val)) return false;
        DLinkedList<V> tmp = getDLLBYVakue(val);
        tmp.insert(val);
        ++size;
        return true;
    }

    /**
     * Remove val from the hash-table.
     *
     * @param val
     * @return Return true if the val was removed successfully. Otherwise return
     * false.
     */
    public boolean remove(V val) {
        if (!contains(val)) return false;
        getDLLBYVakue(val).remove(val);
        --size;
        return true;
    }

    /**
     * clear al the data in the hash-table
     */
    public void clear() {
        for (int i = 0; i < hashSize; ++i) {
            hashTable[i].clear();
        }
        size = 0;
    }

    /**
     * @return true if the hase-table is empty, otherwise return false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private DLinkedList<V> getDLLBYVakue(V val) {
        return hashTable[Math.abs(val.hashCode() % hashSize)];
    }

}