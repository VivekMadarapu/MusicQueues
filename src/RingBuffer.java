public class RingBuffer {

    private int front;
    private int back;
    private int size;
    private double[] buffer;


    // create an empty ring buffer, with given max capacity
    public RingBuffer(int capacity) {
        size = 0;
        front = 0;
        back = -1;
        buffer = new double[capacity];
    }

    // return number of items currently in the buffer
    public int size() {
        return size;
    }

    // add item x to the end
    public void enqueue(double value) {
        if (!this.isFull()) {
            if(back == buffer.length-1) {
                back = 0;
                buffer[back] = value;
            }
            else{
                back++;
                buffer[back] = value;
            }
            size++;
        }
        else{
            throw new IllegalStateException("enqueued full buffer");
        }
    }

    // delete and return item from the front
    public double dequeue() {
        if (!this.isEmpty()) {
            double tempFront = buffer[front];
            buffer[front] = 0.0;
            size--;
            if(front == buffer.length-1){
                front = 0;
            }
            else {
                front++;
            }
            return tempFront;
        }
        throw new IllegalStateException("dequeued empty buffer");
    }

    // return (but do not delete) item from the front
    public double peek() {
        if (!this.isEmpty()) {
            return buffer[front];
        }
        throw new NullPointerException("peeked empty buffer");
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
