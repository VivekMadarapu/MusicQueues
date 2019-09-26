public class GuitarString {

    private RingBuffer string;
    private int N;
    private double EDF = 0.988;
    private int numTics = 0;

    // create a guitar string of the given frequency, using a sampling rate of 44,100
    GuitarString(double frequency){
        N = (int) Math.ceil(44100/frequency);
        string = new RingBuffer(N);
        for (int i = 0; i < N; i++) {
            string.enqueue(0);
        }
    }

    // create a guitar string whose size and initial values are given by the array
    GuitarString(double[] init){
        N = init.length;
        string = new RingBuffer(N);
        for (double v : init) {
            string.enqueue(v);
        }
    }

    // set the buffer to white noise
    public void pluck(){
        string = new RingBuffer(N);
        for (int i = 0; i < N; i++) {
            string.enqueue(Math.random() - 0.5);
        }
    }

    // advance the simulation one time step
    public void tic(){
        string.enqueue(((string.dequeue()+string.peek())/2)*EDF);
        numTics++;
    }

    // return the current sample
    public double sample(){

        return string.peek();
    }

    // return number of tics
    public int time(){

        return numTics;
    }

}