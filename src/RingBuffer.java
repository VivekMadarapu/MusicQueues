public class RingBuffer<T> {

    private int front;
    private int size;
    private T[] buffer;


    // create an empty ring buffer, with given max capacity
    public RingBuffer(int capacity) {
        size = 0;
        front = 0;
        buffer = (T[]) new Object[capacity];
    }

    // return number of items currently in the buffer
    public int size() {
        return size;
    }

    // add item x to the end
    public void enqueue(T value) {
        if (!this.isFull()) {
            buffer[front%size] = value;
            if(front == this.size()-1){
                front = 0;
            }
            else{
                front++;
            }
            size++;
        }
    }

    // delete and return item from the front
    public T dequeue() {
        if (!this.isEmpty()) {
            T tempFront = buffer[front];
            buffer[front] = null;
            size--;
            if(front != 0){
                front--;
            }

            return tempFront;
        }
        throw new IllegalStateException("dequeued empty buffer");
    }

    // return (but do not delete) item from the front
    public T peek() {
        if (!this.isEmpty()) {
            return buffer[front];
        }
        throw new IllegalStateException("peeked empty buffer");
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        return size == 0;
    }

    // is the buffer full  (size equals capacity)?
    public boolean isFull() {
        return size == buffer.length;
    }

//    public void clear() {
//        size = 0;
//        buffer = (T[]) new Object[10];
//    }

}
