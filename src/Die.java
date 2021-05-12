import java.util.Random;

public class Die {

    private Random random;

    //Initialization of field
    public Die()
    {
        random = new Random();
    }

    //roll a die and return the value
    public int rollDie()
    {
        //random value between 1 - 6 (inclusive)
        return random.nextInt(6)+1;
    }
}
