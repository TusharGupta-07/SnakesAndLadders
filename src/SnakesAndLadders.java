import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SnakesAndLadders {

    public static void main(String[] args) {
        //Welcome message
        System.out.println("Snake And Ladders");
        System.out.println("Created By Tushar Gupta");

        //initialization of scanner
        Scanner scan = new Scanner(System.in);

        //Number of players cannot be greater than 6 and smaller than and equal to 0
        int numOfPlayers = 0;
        while(numOfPlayers <= 0 || numOfPlayers > 6)
        {
            System.out.print("Enter the number of Players : ");
            numOfPlayers = scan.nextInt();
        }

        //Assigning ID to each Player
        List<Players> players = new ArrayList<Players>();
        for(int i = 0; i < numOfPlayers; i++)
        {
            Players player = new Players("P" + i);
            players.add(player);
        }

        //Initialization of Game Board
        Board board = new Board(players);

        //while loop until a players reaches 100
        // 1. Player will roll die
        // 2 move on the board
        boolean win = false;
        int playerId = 0;

        while(!win)
        {
            //Player turn
            Players currentPlayer = players.get(playerId);

            //roll die
            int rolled = currentPlayer.PlayerTurn();

            //move player
            win = board.movePlayer(currentPlayer, rolled);

            System.out.println(board);
            System.out.println("####################################");

            //if Player won print wins with Player's id
            if(win)
            {
                System.out.println(currentPlayer + " wins");
            }

            //next player
            playerId++;
            if(playerId == numOfPlayers)
            {
                playerId = 0;
            }
        }
    }
}
