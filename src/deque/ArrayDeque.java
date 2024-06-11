package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class ArrayDeque<T> implements Deque<T> {
    int size;
    int first;
    int last;
    T[] arr;
    private static final int START_SIZE = 8;

    public ArrayDeque() {
        arr = (T[]) new Object[START_SIZE];
        first = 0;

        last = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) obj;
        Deque<T> another = (Deque<T>) obj;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<T> thisIterator = iterator();
        Iterator<T> otherIterator = another.iterator();

        while (thisIterator.hasNext()) {
            T thisElement = thisIterator.next();
            T otherElement = otherIterator.next();
            if (!thisElement.equals(otherElement)) {
                return false;
            }
        }

        return true;
    }


    @Override
    public String toString() {
        return toList().toString();
    }


    @Override
    public void addFirst(Object x) {
        T a = (T) x;
        if (size == 0) {
            first = 0;
            last = 0;
            arr[0] = a;
            size++;
            return;
        }
        if (size == arr.length) {
            resize(arr.length * 2, "up");
        }
        if (first == 0) {
            first = arr.length - 1;
        } else {
            first--;
        }
        arr[first] = a;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> a = new ArrayList<T>();
        int temp = first;
        for (int i = 0; i < size(); i++) {
            a.add(arr[temp % arr.length]);
            temp++;
        }
        return a;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            first = 0;
            last = 0;
            arr[0] = x;
            size++;
            return;
        }
        if (size == arr.length) {
            resize(arr.length * 2, "up");
        }
        if (last == arr.length - 1) {
            last = 0;
        } else {
            last++;
        }
        arr[last] = x;
        size++;
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            return null;
        }
        return arr[(first + index) % arr.length];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }


    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedNode = arr[first];
        arr[first] = null;
        if (first == arr.length - 1) {
            first = 0;
        } else {
            first++;
        }
        size--;
        if (size == 0) {
            first = 0;
            last = 0;
        }
        if (size < arr.length / 4) {
            resize(arr.length, "down");
        }
        return removedNode;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedNode = arr[last];
        arr[last] = null;
        if (last == 0) {
            last = arr.length - 1;
        } else {
            last--;
        }
        size--;
        if (size == 0) {
            first = 0;
            last = 0;
        }
        if (size < arr.length / 4) {
            resize(arr.length / 2, "down");
        }
        return removedNode;
    }

    private void resize(int newlength, String a) {

        T[] newarr = (T[]) new Object[newlength];

        //write for up

        if (a.equals("up")) {

            int copySize = arr.length - first;

            System.arraycopy(arr, first, newarr, 0, copySize);

            System.arraycopy(arr, 0, newarr, copySize, size - copySize);

        }

        //write for down

        if (a.equals("down")) {

            if (last < first) {

                int copySize = arr.length - first;

                System.arraycopy(arr, first, newarr, 0, copySize);

                System.arraycopy(arr, 0, newarr, copySize,

                        size - copySize);

            } else {

                System.arraycopy(arr, first, newarr, 0, size);

            }

        }

        //changing indexes

        arr = newarr;

        first = 0;

        last = size - 1;

    }

    public class ArrayDequeIterator implements Iterator<T> {
        int currentIndex;

        public ArrayDequeIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            T element = arr[(first + currentIndex) % arr.length];
            currentIndex++;
            return element;
        }
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

}
