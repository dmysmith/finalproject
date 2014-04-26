import java.util.ArrayList;
public class Player
{
    private int money;
    private int bet; 
    private ArrayList<Card> individHand=new ArrayList<Card>();
    private ArrayList<Card> hand=new ArrayList<Card>();
 
    public Player()
    {
    }    
    public Player(int buyin)
    {
        money=buyin;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public int playBlind(boolean n, int bigBlind)
    {
        if (n)
        {
            if(money>bigBlind)
            {
                money-=bigBlind;
                return bigBlind;
            }
            else
                return money;
        }
        else
        {
            if(money>(bigBlind/2))
            {
                money-=(bigBlind/2);
                return bigBlind/2;
            }
            else
                return money;
        }        
    }
    
    public boolean isAllIn()
    {
        if (money==0)
            return true;
        else 
            return false;
    }    
        
    public void addMoney(int pot)
    {
        money+=pot;
    }    
       
    public void clearHand()
    {
        while(individHand.size()>0)
            individHand.remove(0);
        while(hand.size()>0)
            hand.remove(0);
    }
    public void addToHand(Card card)
    {
        individHand.add(card);
    }
    
    public ArrayList<Card> getIndividHand()
    {
        if((individHand.get(0).returnValue())>individHand.get(1).returnValue())
        {
            Card holder=individHand.get(0);
            individHand.set(0,individHand.get(1));
            individHand.set(1,holder);
            
        }
        return individHand;
    }
    public void adjustMoney(int bet)
    {
        money-=bet;
    }
    public ArrayList<Card> getWholeHand(ArrayList<Card> publicCards)
    {
        
        while(hand.size()>0)
            hand.remove(0);
        for(int i=0;i<publicCards.size();i++)
        {
            hand.add(publicCards.get(i));
        }
         for(int i=0;i<individHand.size();i++)
         {
             hand.add(individHand.get(i));
            }
      
            return hand;
        }
        
        
      public ArrayList<Card> returnWholeHand()
      {
          return hand ;
        }
}
