
public class Queen extends Card
{
    private String suitOfCard;
 
    public Queen(String suit)
   {
        super(suit,12);
        suitOfCard=suit;
    }
    
    public int returnValue()
    {
        return 12;
    }
   
     public String toString()
    {
        return  "Q: " + suitOfCard.toUpperCase();
    }    
}
