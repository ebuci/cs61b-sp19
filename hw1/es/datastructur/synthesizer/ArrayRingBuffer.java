package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /** return size of the buffer. */
    @Override
    public int capacity() {
        return rb.length;
    }

    /** return number of items currently in the buffer.*/
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("The ring buffer is already full");
        }
        rb[last] = x;
        last += 1;
        fillCount += 1;
        //makes the queue circular
        if (last == capacity()) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The buffer is already empty");
        }
        T deleteValue = rb[first];
        first += 1;
        fillCount -= 1;
        //makes the queue circular
        if (first == capacity()) {
            first = 0;
        }
        return deleteValue;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }



    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int count;
        private int pos;

        ArrayRingBufferIterator() {
            count = 0;
            pos = first;
        }

        public boolean hasNext() {
            return count < fillCount();
        }

        public T next() {
            T item = rb[pos];
            pos += 1;
            if (pos == capacity()) {
                pos = 0;
            }
            count += 1;
            return item;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount() != this.fillCount()) {
            return false;
        }
        Iterator<T> thisIter = this.iterator();
        Iterator<T> otherIter = o.iterator();
        while (thisIter.hasNext() && otherIter.hasNext()) {
            if (thisIter.next() != otherIter.next()) {
                return false;
            }
        }
        return true;
    }
}

