import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    //Constant values
    private final int Rows = 10;
    private final int Cols = 10;
    private final int num_Snakes = 8;
    private final int num_Ladders = 8;

    //Board Variables
    private int[][] gameBoard;
    private int[][] snakes;
    private int[][] ladders;

    //Player Position Map -> Key = player, Value = position
    Map<Players, Integer> playerPositions;

    //No of players
    public Board(List<Players> players)
    {
        //Initialization of player position
        this.playerPositions = new HashMap<Players, Integer>();

        for(Players player : players)
        {
            this.playerPositions.put(player, 0);
        }

        //Rows X Cols board
        gameBoard = new int[Rows][Cols];
        for(int row = 0; row < Rows; row++)
        {
            for(int col = 0; col < Cols; col++)
            {
                gameBoard[row][col] = row*Rows + col +1;
            }
        }

        //Snakes, Ladders function
        setSnakes();
        setLadders();

    }

    //Player moves based on value of die
    //return true if player reaches 100
    public boolean movePlayer(Players player, int value)
    {
        int position = playerPositions.get(player);

        //A player can start the game after first 6
        if(position == 0 && value != 6)
        {
            System.out.println("To Start the Game you need a 6");
            return false;
        }
        else if(position == 0 && value == 6)
        {
            System.out.println("Hurray...! " + player + " got first 6");
            playerPositions.put(player, 1);
            return false;
        }

        //storing previous position of the player
        int prevPosition = position;
        position += value;

        //if position is equal to 100 player won
        if(position ==  100)
        {
            playerPositions.put(player, 100);
            return true;
        }

        //case for Snake bite
        //Loop to check if new position is the starting point of snake
        for(int i = 0; i < num_Snakes; i++)
        {

            //if true move player to Snake's end point
            if(snakes[i][0] == position)
            {
                position = snakes[i][1];
                playerPositions.put(player, position);

                System.out.println("Sad...! Snake took " + player + " from " + snakes[i][0] + " to " + snakes[i][1]);
                return false;
            }
        }

        //case for Ladder
        //Loop to check if new position is the starting point of ladder
        for(int i = 0; i< num_Ladders; i++)
        {

            //if true move player to ladder end point
            if(ladders[i][0] == position)
            {
                position = ladders[i][1];
                playerPositions.put(player, position);

                System.out.println("Hurray...! " + player + " takes ladder from " + ladders[i][0] + " to " + ladders[i][1]);
                return false;
            }
        }

        //if new position is greater than 100, rest on previous position
        if(position > 100)
        {
            playerPositions.put(player, prevPosition);
            return false;
        }

        //if player does not meet snake or ladder, update position of player normally
        playerPositions.put(player, position);
        return false;

    }

    //Snakes on board
    private void setSnakes()
    {
        snakes = new int[num_Snakes][2];

        snakes[0][0] = 17;
        snakes[0][1] = 7;
        snakes[1][0] = 54;
        snakes[1][1] = 34;
        snakes[2][0] = 62;
        snakes[2][1] = 19;
        snakes[3][0] = 64;
        snakes[3][1] = 60;
        snakes[4][0] = 87;
        snakes[4][1] = 24;
        snakes[5][0] = 93;
        snakes[5][1] = 73;
        snakes[6][0] = 95;
        snakes[6][1] = 75;
        snakes[7][0] = 99;
        snakes[7][1] = 78;
    }

    //Ladders on board
    private void setLadders(){
        ladders = new int[num_Ladders][2];

        ladders[0][0] = 4;
        ladders[0][1] = 14;
        ladders[1][0] = 9;
        ladders[1][1] = 31;
        ladders[2][0] = 20;
        ladders[2][1] = 38;
        ladders[3][0] = 28;
        ladders[3][1] = 84;
        ladders[4][0] = 40;
        ladders[4][1] = 59;
        ladders[5][0] = 51;
        ladders[5][1] = 67;
        ladders[6][0] = 63;
        ladders[6][1] = 81;
        ladders[7][0] = 71;
        ladders[7][1] = 91;
    }

    //Human readable board
    public String toString()
    {

        //using StringBuilder for creating the string
        StringBuilder sb = new StringBuilder();
        boolean oddRow = true;

        //if Rows are odd (9, 7, 5, 3, 1) print in forward direction
        //if Rows are even (10, 8, 6, 4, 2) print in reverse direction
        for (int row = Rows-1; row >= 0; row--)
        {
            for (int col = 0; col < Cols; col++)
            {

                //condition for odd Rows
                if (oddRow)
                {
                    // if any player holds a place on current location
                    String pl = "";
                    boolean occupied = false;
                    for (Players temp : playerPositions.keySet())
                    {

                        //if true update flag to true
                        if (playerPositions.get(temp) == gameBoard[row][Cols-1-col])
                        {
                            occupied = true;
                            pl += temp + " ";
                        }
                    }

                    //if atleast one person occupied the location print that player
                    if (occupied)
                    {
                        pl += "\t";
                        sb.append(pl);
                    }

                    //else position number
                    else
                    {
                        sb.append(gameBoard[row][Cols-1-col] + "\t");
                    }
                }

                //case for even Rows
                else
                {

                    // if any player holds a place on current location
                    boolean occupied = false;
                    String pl = "";
                    for (Players temp : playerPositions.keySet())
                    {
                        if (playerPositions.get(temp) == gameBoard[row][col])
                        {
                            occupied = true;
                            pl += (temp + " ");
                        }
                    }

                    //if atleast one person occupied the location print that player
                    if (occupied)
                    {
                        pl += "\t";
                        sb.append(pl);
                    }

                    //else position number
                    else
                    {
                        sb.append(gameBoard[row][col] + "\t");
                    }
                }
            }

            //Switching oddRow flag and print new line
            oddRow = !oddRow;
            sb.append("\n");
        }
        sb.append("\n");

        return sb.toString();
    }
}
