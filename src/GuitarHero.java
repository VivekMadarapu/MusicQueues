import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GuitarHero {

    public static void main(String[] args) throws IOException {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] notes = new GuitarString[37];

        StdDraw.setCanvasSize(500, 500);

        for (int i = 0; i < 37; i++) {
            notes[i] = new GuitarString(440 * Math.pow(1.05956, (i - 24)));
        }

        File file = new File("music.txt");
        int version = 0;
        while (file.exists()) {
            version++;
            file = new File("music" + version + ".txt");
        }

        file.createNewFile();

        FileWriter writer = new FileWriter(file);

        char key = '0';
        int pTick = 0;

        do {
            if(StdDraw.mousePressed()) {
                StdDraw.setPenColor(Color.GREEN);
                StdDraw.circle(StdDraw.mouseX(), StdDraw.mouseY(),0.05);
            }

            if (StdDraw.hasNextKeyTyped()) {
                if (pTick > 0) {
                    writer.write(" " + pTick + " ");
                    pTick = 0;
                }

                key = StdDraw.nextKeyTyped();
                if(key == '1'){
                    StdDraw.clear();
                }

                if (keyboard.contains(key + "")) {
                    writer.write(key);
                    notes[keyboard.indexOf(key)].pluck();
                }

            } else {
                pTick++;
            }

            writer.flush();

            double sample = 0;
            for (GuitarString note : notes) {
                sample += note.sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < 37; i++) {
                notes[i].tic();
            }
        }
        while (key != '\\');
        writer.close();
    }
}