import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuitarHeroPlayer {

    public static void main(String[] args) throws FileNotFoundException {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] notes = new GuitarString[37];

        for (int i = 0; i < 37; i++) {
            notes[i] = new GuitarString(440 * Math.pow(1.05956, (i - 24)));
        }

        Scanner console = new Scanner(System.in);
        System.out.print("file: ");
        Scanner file = new Scanner(new File(console.next()));

        int curPause = 0;
        String key = "";

        while (true) {

            if (file.hasNext() && curPause == 0) {

                key = file.next();

                int indexOfKeyboard = keyboard.indexOf(key);

                if (key.length() == 1 && indexOfKeyboard != -1) {
                    notes[keyboard.indexOf(key)].pluck();
                    System.out.print(key + " ");
                } else if (key.length() != 1) {
                    curPause = Integer.parseInt(key);
                }

            }
            else if (curPause > 0) {
                curPause--;
            }

            double sample = 0;
            for (GuitarString note : notes) {
                sample += note.sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < 37; i++) {
                notes[i].tic();
            }
        }
    }
}