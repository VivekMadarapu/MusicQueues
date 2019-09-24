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
        RingBuffer table;

        while (file.hasNextLine()){
            player1 = new RingBuffer(104);
            player2 = new RingBuffer(104);
            table = new RingBuffer(104);
            Scanner line = new Scanner(file.nextLine());
            while (line.hasNext()){
                char cardValue = line.next().charAt(0);
                switch (cardValue){
                    case 'T':
                        player1.enqueue(10);
                        break;
                    case 'J':
                        player1.enqueue(11);
                        break;
                    case 'Q':
                        player1.enqueue(12);
                        break;
                    case 'K':
                        player1.enqueue(13);
                        break;
                    case 'A':
                        player1.enqueue(14);
                        break;
                    default:
                        player1.enqueue(Integer.parseInt(cardValue+""));
                }
            }
            line = new Scanner(file.nextLine());
            while (line.hasNext()){
                char cardValue = line.next().charAt(0);
                switch (cardValue){
                    case 'T':
                        player2.enqueue(10);
                        break;
                    case 'J':
                        player2.enqueue(11);
                        break;
                    case 'Q':
                        player2.enqueue(12);
                        break;
                    case 'K':
                        player2.enqueue(13);
                        break;
                    case 'A':
                        player2.enqueue(14);
                        break;
                    default:
                        player2.enqueue(Double.parseDouble(cardValue+""));
                }
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
                    for(int i = 0;i < table.size();i++){
                        player1.enqueue(table.dequeue());
                    }
                }
                else if(p2 > p1){
                    player2.enqueue(p1);
                    player2.enqueue(p2);
                    for(int i = 0;i < table.size();i++){
                        player2.enqueue(table.dequeue());
                    }
                }
                else{
                    table.enqueue(p1);
                    table.enqueue(p2);
                }


            }

            if(numTurns >= 100000){
                System.out.println("Tie game stopped at 100000 plays.");
            }

        }



    }


}
