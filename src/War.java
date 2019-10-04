import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class War {

    public static void main(String[] args) {
        Scanner file = null;
        try {
            file = new Scanner(new File("war.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RingBuffer player1;
        RingBuffer player2;

        assert file != null;
        while (file.hasNext()){
            player1 = new RingBuffer(52);
            player2 = new RingBuffer(52);
            String values = "0023456789TJQKA";
            Scanner line = new Scanner(file.nextLine());
            while (line.hasNext()){
                player1.enqueue(values.indexOf(line.next().charAt(0)));
            }
            line = new Scanner(file.nextLine());
            while (line.hasNext()){
                player2.enqueue(values.indexOf(line.next().charAt(0)));
            }

            int numTurns = 0;
            while(numTurns < 100000){

                if(player1.isEmpty()){
                    System.out.println("Player 2 wins!");
                    break;
                }
                else if(player2.isEmpty()){
                    System.out.println("Player 1 wins!");
                    break;
                }

                double p1 = player1.dequeue();
                double p2 = player2.dequeue();

                if(p1 > p2){
                    player1.enqueue(p1);
                    player1.enqueue(p2);
                }
                else if(p2 > p1){
                    player2.enqueue(p1);
                    player2.enqueue(p2);
                }
                else{
                    RingBuffer table = new RingBuffer(52);
                    do {
                        table.enqueue(p1);
                        table.enqueue(p2);
                        table.enqueue(player1.dequeue());
                        table.enqueue(player2.dequeue());
                        p1 = player1.dequeue();
                        p2 = player2.dequeue();
                    }while(p1 == p2);
                    if(p1 > p2){
                        while(!table.isEmpty()){
                            player1.enqueue(table.dequeue());
                        }
                        player1.enqueue(p1);
                        player1.enqueue(p2);
                    }
                    else if(p2 > p1){
                        while(!table.isEmpty()){
                            player2.enqueue(table.dequeue());
                        }
                        player2.enqueue(p1);
                        player2.enqueue(p2);
                    }
                }
                numTurns++;
            }
            if(numTurns >= 100000){
                System.out.println("Tie game stopped at 100000 plays.");
            }
        }
    }
}
