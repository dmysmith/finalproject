
import java.util.ArrayList;
public class FourOfAKind
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private int index1;
    private int index2;
    private int index3;
    private int index4;
    public FourOfAKind(ArrayList<Card> handToTest)
    {
        hand=handToTest;
    }

    public boolean isEqual()
    {
       boolean yes=false;
       for(int k=0;k<hand.size()-3;k++)
       {
           for(int j=k+1;j<hand.size()-2;j++)
           {
               if(hand.get(k).returnValue()==hand.get(j).returnValue())
               {
                   for(int i=j+1;i<hand.size()-1;i++)
                   {
                       if(hand.get(j).returnValue()==hand.get(i).returnValue())
                       {
                           for(int b=i+1;b<hand.size();b++)
                           {
                                if(hand.get(i).returnValue()==hand.get(b).returnValue())
                                {
                                    yes=true;
                                    index1=k;
                                    index2=j;
                                    index3=i;
                                    index4=b;
                                }
                           }
                       }
                    }
               }    
           }    
       }    
        return yes;
    }
    
    public ArrayList<Card> getFourOfAKind()
    {
        ArrayList<Card> fourOf=new ArrayList<Card>();
        fourOf.add(hand.get(index1));
        fourOf.add(hand.get(index2));
        fourOf.add(hand.get(index3));
        fourOf.add(hand.get(index4));
        
        return fourOf;
    }
}
