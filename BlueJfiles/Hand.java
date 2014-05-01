import java.util.ArrayList;
import java.util.*;

public class Hand
{
    private ArrayList<Card> hand=new ArrayList<Card>();
    private int strength;
    private ArrayList<Card> bestHand=new ArrayList<Card>();
    public Hand()
    {
    }
    public Hand(ArrayList<Card> thisHand)
    {
        hand=thisHand;
        for (int i = 0; i < hand.size() - 1; i++) 
        {
            for (int j = i + 1; j < hand.size(); j++) 
            {
                if (hand.get(i).compareTo(hand.get(j)) == 1) 
                {
                    
                    Card temp = hand.get(i);
                    hand.set(i, hand.get(j));
                    hand.set(j, temp);
                }
            }
        }
        
    }

    
    public void printFullHand()
    {
        System.out.println(hand);
    }    
    public int getStrength()
    {
        RoyalFlush roFl= new RoyalFlush(hand);
        if(roFl.isEqual())
        {
            bestHand=roFl.getRoyalFlush();
            strength= 10;
        }    
        else 
        {
            StraightFlush stFl= new StraightFlush(hand);
            if(stFl.isEqual())
            {
                bestHand=stFl.getStraightFlush();
                strength= 9;
            }    
            else
            {
                FourOfAKind foOf= new FourOfAKind(hand);
                if(foOf.isEqual())
                {
                    bestHand=foOf.getFourOfAKind();
                    strength= 8;
                }    
                else
                {
                    FullHouse fuHo= new FullHouse(hand);
                    if(fuHo.isEqual())
                    {
                         bestHand=fuHo.getFullHouse();
                        strength=7;
                    }    
                    else
                    {
                        Flush fl=new Flush(hand);
                        if(fl.isEqual())
                        {
                            bestHand=fl.getFlush();
                            strength=6;
                        }    
                        else
                        {
                            Straight st=new Straight(hand);
                            if(st.isEqual())
                            {
                                 bestHand=st.getStraight();
                                strength=5;
                            }    
                            else
                            {
                                ThreeOfAKind thOf=new ThreeOfAKind(hand);
                                if(thOf.isEqual())
                                {
                                    bestHand=thOf.getThreeOfAKind();
                                    strength=4;
                                }    
                                else
                                {
                                    TwoPair twPa=new TwoPair(hand);
                                    if(twPa.isEqual())
                                    {
                                        bestHand=twPa.getTwoPair();
                                        strength= 3;
                                    }    
                                    else
                                    {
                                        Pair pa=new Pair(hand);
                                        if(pa.isEqual())
                                        {
                                            bestHand=pa.getPair();
                                            strength=2;
                                        }    
                                        else 
                                        {
                                            HighCard HiCa=new HighCard(hand);
                                            bestHand=HiCa.getHighCard();
                                            strength=1;
                                        }    
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return strength;
    }    
    
    public String printStrength()
    {
        if(strength==10)
            return("Royal Flush");
        else if(strength==9)
            return("Straight Flush");
        else if(strength==8)
            return("Four Of A Kind");
        else if(strength==7)
            return("Full House");
        else if(strength==6)
            return("Flush");
        else if(strength==5)
            return("Straight");
        else if(strength==4)
            return("Three Of A Kind");
        else if(strength==3)
            return("Two Pair");
        else if(strength==2)
            return("Pair");
        else
            return("High Card");
    }        
    public ArrayList<Card> getBestHand()
    {
        return bestHand;
    }
    public ArrayList<Card> getExtra()
    {
        ArrayList<Card> bestEx = new ArrayList<Card>();
        if(bestHand.size() == 5)
            return null;
        else
        {
            //hand -bestHand
            int count = hand.size() - 1;
            while(bestHand.size() + bestEx.size() < 5)
            {
                boolean yes = false;
                for(int i = 0; i < bestHand.size(); i++)
                {
                    if(hand.get(count).isSame(bestHand.get(i)))
                    {
                        yes = true;
                    }
                }
                if(!yes)
                {
                    bestEx.add(hand.get(count));
                }
                count--;
            }
        }
        System.out.println(bestEx);

        return bestEx;
        
    }
    public boolean containsIndivid(ArrayList<Card> individ)
    {
        boolean doesIt=false;
        for(int i=0;i<bestHand.size();i++)
        {
            if(individ.get(0)==bestHand.get(i)||individ.get(1)==bestHand.get(i))
                doesIt=true;
                
        }
        return doesIt;
    }
    public String toString()
    {
        String str="";
        for(int i=0;i<hand.size();i++)
        {
            str+= hand.get(i) + ", ";
        }
        return str;
    }
    public void printBestHand()
    {
        System.out.println(bestHand);
    }
}
