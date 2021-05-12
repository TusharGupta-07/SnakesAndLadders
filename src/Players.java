import java.util.Scanner;

public class Players {

    private String Name;
    private static Die die = new Die();

    //Initialization of field
    public Players(String Name)
    {
        this.Name = Name;
    }

    //Player's turn
    public int PlayerTurn()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print(Name + "'s turn : ");

        //ask user for roll
        String input = scan.nextLine();

        int rolled = 0;

        //roll die
        rolled = die.rollDie();

        //print the rolled value
        System.out.println(Name + " rolled " + rolled + ".");

        return rolled;
    }

    public String toString()
    {
        return Name;
    }


}
