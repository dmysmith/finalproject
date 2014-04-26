import java.util.ArrayList;
public class FullHouse
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private ArrayList<Card> holder=new ArrayList<Card>();
    private int threeValue;
    private int twoValue;
    public FullHouse(ArrayList<Card> handToCheck)
    {
        hand=handToCheck;
        for(int i=0;i<handToCheck.size();i++)
        {
            holder.add(handToCheck.get(i));
        }
    }
    
    public boolean isEqual()
    {
        ThreeOfAKind findThree=new ThreeOfAKind(holder);
        
        if(findThree.isEqual())
        {
            ArrayList<Card> helper=new ArrayList<Card>();
            helper=findThree.getThreeOfAKind();
            threeValue=helper.get(0).returnValue();
            int i=0;
            while(i<holder.size())
            {
                if(holder.get(i).returnValue()==threeValue)
                {
                    holder.remove(i);
                }    
                else
                    i++;
            }    
            
            Pair findPair=new Pair(holder);
            if(findPair.isEqual())
            {
                
                ArrayList<Card> helper2=new ArrayList<Card>();
                helper2=findPair.getPair();
                twoValue=helper2.get(0).returnValue();
                return true;
            }
            else 
                return false;
        }
        else
            return false;
    }    
    
    public ArrayList<Card> getFullHouse()
    {
        ArrayList<Card> fullHouse=new ArrayList<Card>();
        for(int k=0; k<hand.size();k++)
        {
             if(hand.get(k).returnValue()==threeValue)
                fullHouse.add(hand.get(k));
        }        
        for(int m=0;m<hand.size();m++)
        {
            if(hand.get(m).returnValue()==twoValue)
                fullHouse.add(hand.get(m));
        }
        return fullHouse;
    }    
}
