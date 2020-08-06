/** circular implementation of doubly linked list*/
public class LinkedListDeque<T> {
    private class Tnode{
        public Tnode next;
        public T item;
        public Tnode prev;

        public Tnode(T i, Tnode n, Tnode p){
            item = i;
            next = n;
            prev = p;
        }
    }

    /** The first item (if it exists) is at sentinel.next. */
    private Tnode sentinel;
    private int size;

    /** Creates an empty doubly linked list. */
    public LinkedListDeque(){
        sentinel = new Tnode(null, null, null );
        sentinel.prev = sentinel;
        sentinel.next = sentinel.prev;
        size = 0;
    }

    /** deep copy of other*/
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Tnode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i)); // (T) is cast, since type of other is unknown
        }
    }

    public void addFirst(T item){
        size += 1;
        sentinel.next = new Tnode(item, sentinel.next,sentinel);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(T item){
        size += 1;
        sentinel.prev = new Tnode(item, sentinel,sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public void printDeque(){
        Tnode A = sentinel.next;
        while (A != sentinel){
            System.out.print(A.item + " ");
            A = A.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()){
            size -= 1;
        }
        return sentinel.next.item;
    }

    public T removeLast(){
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (!isEmpty()){
            size -= 1;
        }
        return sentinel.prev.item;
    }

    public T get(int index){
        if (index > size){
            return null;
        }
        Tnode A = sentinel.next;
        for (int i = 0; i < index; i++){
            A = A.next;
        }
        return A.item;
    }

    public T getHelper(int index, Tnode A){
        if (index == 0){
            return A.item;
        }
        else{
            return getHelper(index -1,A.next);
        }
    }

    public T getRecursive(int index){
        return getHelper(index, sentinel.next);
    }

    public static void main(String[] args){
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addFirst("a");
        deque.addFirst("b");
        System.out.println(deque.size());
        deque.addLast("1");
        deque.addLast("2");
        System.out.println(deque.size());
        deque.removeFirst();
        deque.removeLast();
        deque.printDeque();
        System.out.println(deque.get(0));
        System.out.println(deque.get(1));
        System.out.println(deque.get(2));
        System.out.println(deque.getRecursive(0));
        System.out.println(deque.getRecursive(1));
        System.out.println(deque.getRecursive(2));

    }
}
