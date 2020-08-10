import java.lang.reflect.Array;

public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static final int FACTOR = 2;

    public ArrayDeque(){
        items = (T []) new  Object [8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /** deep copy of other*/
    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /** helper function that compute array(circular) indices*/
    /** computes the index immediately before a given index*/
    private int minusOne(int x){
        return (x -1 + items.length)  % items.length;
    }

    /** computes the index immediately after a given index*/
    private int plusOne(int x){
        return (x + 1) % items.length;
    }

    private boolean isFull(){
        return size == items.length;
    }


    public int size(){
        return size;
    }

    private void resize(int capacity){
        T[] a = (T []) new Object[capacity];
        int index = minusOne(nextFirst);
        for(int i = 0; i< items.length; i++){
            a[i] = items[index];
            index = plusOne(index);
        }
        items = a;
    }


    public void printDeque(){
        int First = minusOne(nextFirst);
        for (int i = 0; i < size; i++){
            System.out.println(items[First] + " ");
            First = plusOne(First);
        }
    }

    public void addFirst(T x){
        if(!isFull()) {
            resize(size * FACTOR);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }


    public void addLast(T x){
        if(!isFull()) {
            resize(size * FACTOR);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        int index = plusOne(nextFirst);
        T removeItem = items[index];
        items[index] = null;
        nextFirst = index;
        size -= 1;

        /* minimum size of 8 */
        if (items.length >= 16 && size < items.length * 0.25){
            resize(items.length / 2);
        }

        return removeItem;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        int index = minusOne(nextLast);
        T removeItem = items[index];
        items[index] = null;
        nextLast = index;
        size -= 1;

        /* minimum size of 8 */
        if (items.length >= 16 && size < items.length * 0.25){
            resize(items.length / 2);
        }

        return removeItem;
    }

    public T getLast(){
        return items[size - 1];
    }

    public T get(int x){
        if (x > size) {
            return null;
        }
        return items[x];
    }
}
