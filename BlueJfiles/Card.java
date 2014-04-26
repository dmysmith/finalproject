

public class Card
{
    private int valueOfCard;
    private String suitOfCard;

    public Card(String suit, int value)
    {
        suitOfCard=suit;
        valueOfCard=value;
    }
    
    public int returnValue()
    {
        return valueOfCard;
    }    
    
    public String returnSuit()
    {
        return suitOfCard;
    }    
    
    public String toString()
    {
        return valueOfCard + ": " + suitOfCard.toUpperCase();
    }    
    
    public int compareTo(Card c) 
    {
        if(this.returnValue()>c.returnValue())
            return 1;
        else if(this.returnValue()==c.returnValue())
            return 0;
        else 
            return -1;
        
    }
}

