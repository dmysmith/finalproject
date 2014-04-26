import java.util.ArrayList;
public class Pair
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private int index1;
    private int index2;
    public Pair(ArrayList<Card> handToCheck)
    {
        hand=handToCheck;
    }

    public boolean isEqual()
    {
        boolean yes=false;
        for(int k=0;k<hand.size()-1;k++)
        {
            for(int j=k+1;j<hand.size();j++)
            {
                if(hand.get(k).returnValue()==hand.get(j).returnValue())
                {
                    yes=true;
                    index1=k;
                    index2=j;
                }    
            }    
        }    
        return yes;
    }    
    
    public ArrayList<Card> getPair()
    {
        ArrayList<Card> pair= new ArrayList<Card>();
        pair.add(hand.get(index1));
        pair.add(hand.get(index2));
        return pair;
          
    }    
}
