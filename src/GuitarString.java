public class GuitarString {

    private RingBuffer string;

    // create a guitar string of the given frequency, using a sampling rate of 44,100
    GuitarString(double frequency){
        string = new RingBuffer((int) Math.ceil(44100/frequency));
    }

    // create a guitar string whose size and initial values are given by the array
    GuitarString(double[] init){
        for(int i = 0; i < init.length;i++){
            string.enqueue(init[i]);
        }
    }

    // set the buffer to white noise
    public void pluck(){

    }

    // advance the simulation one time step
    public void tic(){

    }

    // return the current sample
    public double sample(){

        return 0.0;
    }

    // return number of tics
    public int time(){

        return 0;
    }

}
