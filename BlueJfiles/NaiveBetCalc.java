/**
 * Description Here Later
 */

public class NaiveBetCalc
{
   public double boldness;
   
   public NaiveBetCalc ()
   {
       boldness = 0;
   }
    
   public NaiveBetCalc (double agg)
   {
       boldness = agg;
   }
   
   public int betAmount(double ehp, int ownMoney, int valueToCall, int sBetIncr, int pot){
       
       double frac = 0;
       int bet = 0;
       
       if (ehp > 0.9)
       {
           frac = 0.7 + (Math.random() * boldness/2) - valueToCall/ownMoney;           
       }
       else if (ehp > 0.7) 
       {
            frac = 0.3 + Math.random() * boldness/2 - valueToCall/ownMoney;
       }
       else if (ehp > 0.5)
       {
           frac = 0.1 + Math.random() * boldness/2 - valueToCall/ownMoney;
       }
       else if (ehp > 0.3)
       {
           frac = 0.05 + Math.random() * boldness/2 - valueToCall/ownMoney;
       }
       else 
       {
           frac = 0 + Math.random() * boldness/2 - valueToCall/ownMoney;
       }

           
       if (frac < 0) 
       {
           return 0;
       }
       else if (frac > 1)
       {
           return ownMoney;
       }
       else 
       {
           if (frac * ownMoney < sBetIncr) 
           {
               return valueToCall;
           }
           else
           {
               bet = (int) (frac * ownMoney - ((frac * ownMoney + valueToCall) % (sBetIncr)));
               return bet;
           }

       }
    
    }

}