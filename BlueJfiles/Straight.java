import java.util.ArrayList;
public class Straight
{
    
    private ArrayList<Card> hand=new ArrayList<Card>();
    private ArrayList<Card> holder=new ArrayList<Card>();
    
    private int index1=-1;
    public Straight(ArrayList<Card> handToTest)
    {
        for(int i=0;i<handToTest.size();i++)
        {
            holder.add(handToTest.get(i));
        }
        hand=handToTest;
        int ace=0;
        for(Card card:handToTest)
        {
            if(card.returnValue()==14)
                ace++;
        }
        if(ace>0)
        {
            Onekinda one=new Onekinda();
            holder.add(0,one);
        }    
    }
    
    public boolean isEqual()
    {
       int index=0;
        boolean yes=true;
        while(yes==true)
        {
            yes=false;
            int k=0;
            while(k<(holder.size()-1))
            {
                int j=k+1;
                while(j<holder.size())
                {
                    if(holder.get(k).returnValue()==holder.get(j).returnValue())
                    {
                        yes=true;
                        index=k;
                    
                    }    
                    j++;
                }   
                k++;
            }    
            if(yes==true)
            {
                holder.remove(holder.get(index));
            }    
        }
        
        int checker=0;
        if(holder.size()>4)
        {
            for(int i=0; i<holder.size()-4;i++)
            {
                checker=0;
                for(int m=i;m<(i+4);m++)
                {   
                    if(holder.get(m).returnValue()!=(holder.get(m+1).returnValue()-1))
                        checker++;
                }  
                if(checker==0)
                    index1=i;
            }    
            if(index1>-1)
                return true;
            else 
                return false;
        }
        else 
            return false;
    }    
    
    public ArrayList<Card> getStraight()
    {
        ArrayList<Card> straight=new ArrayList<Card>();
        int helper=index1;
        if (holder.get(index1).returnValue()==1)
            helper=holder.size()-1;
        
        straight.add(holder.get(helper));
        straight.add(holder.get(index1+1));
        straight.add(holder.get(index1+2));
        straight.add(holder.get(index1+3));
        straight.add(holder.get(index1+4));
        
       
        return straight;
    }
}
