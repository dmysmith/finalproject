import java.util.ArrayList;
public class HighCard
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    public HighCard(ArrayList<Card> handToCheck)
    {
        hand=handToCheck;
        
    }

    public ArrayList<Card> getHighCard()
    {
        ArrayList<Card> highCard=new ArrayList<Card>();
        highCard.add(hand.get(hand.size()-1));
        
        return highCard;
    }    
}
