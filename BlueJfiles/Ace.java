

public class Ace extends Card
{
    private String suitOfCard;
    
    public Ace(String suit)
    {
        super(suit, 14);
        suitOfCard=suit;
    }
    
    public int returnValue()
    {
        return 14;
    }
   
    public String toString()
    {
       return  "A: " + suitOfCard.toUpperCase();
    }    
}
