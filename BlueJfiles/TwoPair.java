
import java.util.ArrayList;
public class TwoPair
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private int index1;
    private int index2;
    private int index3;
    private int index4;
    private ArrayList<Card> holder=new ArrayList<Card>();
    public TwoPair(ArrayList<Card> handToTest)
    {
        hand=handToTest;
        for(int i=0;i<handToTest.size();i++)
        {
            holder.add(handToTest.get(i));
        }
    }

    public boolean isEqual()
    {
        boolean yes=false;
        
        
      
        int k=0;
        while(k<(holder.size()-1))
        {
            int j=k+1;
            while(j<holder.size())
            {
                if(holder.get(k).returnValue()==holder.get(j).returnValue())
                {
                    yes=true;
                    index1=k;
                    index2=j;
                }    
                j++;
            }   
            k++;
        }    
        if(yes==true)
        {
            holder.remove(index1);
            holder.remove(index2-1);
            yes=false;
            for(int m=0;m<holder.size()-1;m++)
            {
                for(int n=m+1;n<holder.size();n++)
                {
                    if(holder.get(m).returnValue()==holder.get(n).returnValue())
                    {
                        yes=true;
                        index3=m;
                        index4=n;
                    }    
                }    
            }    
        }
        return yes;
    }
    
    public ArrayList<Card> getTwoPair()
    {
        ArrayList<Card> twoPair=new ArrayList<Card>();
        twoPair.add(hand.get(index1));
        twoPair.add(hand.get(index2));
        twoPair.add(holder.get(index3));
        twoPair.add(holder.get(index4));
        return twoPair;
        
    }    
}
