
public class Jack extends Card
{
    private String suitOfCard;
    
    public Jack(String suit)
     {
        super(suit,11);
        suitOfCard=suit;
    }
    
    public int returnValue()
    {
        return 11;
    }
   
    public String toString()
    {
        return  "J: " + suitOfCard.toUpperCase();
    }    
}
