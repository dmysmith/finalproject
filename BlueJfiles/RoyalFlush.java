import java.util.ArrayList;
public class RoyalFlush
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private ArrayList<Card> holder=new ArrayList<Card>();
    private ArrayList<Card> royalFlush=new ArrayList<Card>();
    public RoyalFlush(ArrayList<Card> handToTest)
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
            royalFlush=findFlush1.getFlush();
            Straight findStraight1=new Straight(royalFlush);
            if(findStraight1.isEqual())
            {
                if(royalFlush.get(0).returnValue()==10)
                    return true;
                else 
                    return false;
            }        
            else 
            {   
                holder.remove(holder.size()-1);
                Flush findFlush2=new Flush(holder);
                
                if(findFlush2.isEqual())
                {
                    royalFlush=findFlush2.getFlush();
                    Straight findStraight2=new Straight(royalFlush);
                    if(findStraight2.isEqual())
                    {
                        if(royalFlush.get(0).returnValue()==10)
                        
                            return true;
                        else
                            return false;
                    }        
                    else 
                    {
                        holder.remove(holder.size()-1);
                        Flush findFlush3=new Flush(holder);
                         
                        if(findFlush3.isEqual())
                        {
                            royalFlush=findFlush3.getFlush();
                            Straight findStraight3=new Straight(royalFlush);
                            if(findStraight3.isEqual())
                            {
                                if(royalFlush.get(0).returnValue()==10)
                                    return true;
                                else 
                                    return false;
                            }       
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
    
    public ArrayList<Card> getRoyalFlush()
    {
        return royalFlush;
    }    
}
