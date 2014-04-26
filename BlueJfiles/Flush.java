import java.util.ArrayList;
public class Flush
{
    ArrayList<Card> hand=new ArrayList<Card>();
    private int hearts;
    private int diamonds;
    private int clubs;
    private int spades;
    public Flush(ArrayList<Card> handToCheck)
    {
        hand=handToCheck;
      
    }
    
    public boolean isEqual()
    {
         hearts=0;
         diamonds=0;
         clubs=0;
         spades=0;
         String suit;
         for(int i=0;i<hand.size();i++)
         {
             suit=hand.get(i).returnSuit();
             if(suit.equals("hearts"))
                hearts++;
             else if(suit.equals("spades") )  
                spades++;
             else if(suit.equals("clubs"))
                clubs++;
             else if(suit.equals("diamonds"))
                diamonds++;
         }      
        if (hearts>=5||diamonds>+5||clubs>=5||spades>=5)
            return true;
        else 
            return false   ; 
    }    
    
    public ArrayList<Card> getFlush()
    {
        ArrayList<Card> flush=new ArrayList<Card>();
        if(hearts>=5)
        {
            for(int i=0;i<hand.size();i++)
            {
                if(hand.get(i).returnSuit().equals("hearts"))
                    flush.add(hand.get(i));
            }
        }
        else if(diamonds>=5)
        {
            for(int i=0;i<hand.size();i++)
            {
                if(hand.get(i).returnSuit().equals("diamonds"))
                    flush.add(hand.get(i));
            }
        }
        else if(clubs>=5)
        {
            for(int i=0;i<hand.size();i++)
            {
                if(hand.get(i).returnSuit().equals("clubs"))
                    flush.add(hand.get(i));
            }
        }
        else if(spades>=5)
        {
            for(int i=0;i<hand.size();i++)
            {
                if(hand.get(i).returnSuit().equals("spades"))
                    flush.add(hand.get(i));
            }
        }    
        while(flush.size()>5)
             flush.remove(0);
        return flush;     
    }
    

}
