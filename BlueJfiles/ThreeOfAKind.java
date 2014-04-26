
import java.util.ArrayList;
public class ThreeOfAKind
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private int index1;
    private int index2;
    private int index3;
    public ThreeOfAKind(ArrayList<Card> handToTest)
    {
        hand=handToTest;
    }

    public boolean isEqual()
    {
       boolean yes=false;
       for(int k=0;k<hand.size()-2;k++)
       {
           for(int j=k+1;j<hand.size()-1;j++)
           {
               if(hand.get(k).returnValue()==hand.get(j).returnValue())
               {
                   for(int i=j+1;i<hand.size();i++)
                   {
                       if(hand.get(k).returnValue()==hand.get(i).returnValue()&&hand.get(j).returnValue()==hand.get(i).returnValue())
                       {
                            yes=true;
                            index1=k;
                            index2=j;
                            index3=i;
                       }
                    }
               }    
           }    
       }    
        return yes;
    }
    
    public ArrayList<Card> getThreeOfAKind()
    {
        ArrayList<Card> threeOf=new ArrayList<Card>();
        threeOf.add(hand.get(index1));
        threeOf.add(hand.get(index2));
        threeOf.add(hand.get(index3));
        return threeOf;
    }
}
