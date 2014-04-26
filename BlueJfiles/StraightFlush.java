import java.util.ArrayList;
public class StraightFlush
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private ArrayList<Card> holder=new ArrayList<Card>();
    private ArrayList<Card> isStraightFlush=new ArrayList<Card>();
    public StraightFlush(ArrayList<Card> handToTest)
    {
        hand=handToTest;
          for(int i=0;i<handToTest.size();i++)
        {
            holder.add(handToTest.get(i));
        }
    }
    
    //must be 7 cards
    public boolean isEqual()
    {
        
        Flush findFlush1=new Flush(holder);
       
        if(findFlush1.isEqual())
        {
            isStraightFlush=findFlush1.getFlush();
            Straight findStraight1=new Straight(isStraightFlush);
            if(findStraight1.isEqual())
                return true;
            else 
            {   
                holder.remove(holder.size()-1);
                Flush findFlush2=new Flush(holder);
                
                if(findFlush2.isEqual())
                {
                    isStraightFlush=findFlush2.getFlush();
                    Straight findStraight2=new Straight(isStraightFlush);
                    if(findStraight2.isEqual())
                    return true;
                    else 
                    {
                        holder.remove(holder.size()-1);
                        Flush findFlush3=new Flush(holder);
                         
                        if(findFlush3.isEqual())
                        {
                            isStraightFlush=findFlush3.getFlush();
                            Straight findStraight3=new Straight(isStraightFlush);
                            if(findStraight3.isEqual())
                                return true;
                            else 
                                return false;
                        }  
                        else
                            return false;
                    }
                }
                else
                    return false;
            }
        }
        else
            return false;
    }    
    
    public ArrayList<Card> getStraightFlush()
    {
        return isStraightFlush;
    }    
}
