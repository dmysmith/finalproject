
public class King extends Card
{
    private String suitOfCard;
     
    public King(String suit)
    {
        
        super(suit,13);
        suitOfCard=suit;
    }
    
    public int returnValue()
    {
        return 13;
    }
   
    public String toString()
    {
        return  "K: " + suitOfCard.toUpperCase();
    }    
}
