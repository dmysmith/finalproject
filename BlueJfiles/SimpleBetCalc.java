/**
 * A simple AI that determines whether to raise, call, or fold using values based on Kelly's Criterion 
 * for betting and based off of effective hand strength and a predetermined boldness value.
 * Sources: http://en.wikipedia.org/wiki/Kelly_criterion
 * 
 * Optimal bet fraction = p + p/b - 1/b where p is the probability of winning and b is the return on $1. 
 * The value of "b" needs to be converted because the return on investment varies with the bet made. 
 * The final report will go into more depth about this.
 */

public class SimpleBetCalc extends NaiveBetCalc
{
    
    public SimpleBetCalc()
    {
        super();
    }

    public SimpleBetCalc(float agg)
    {
        super(agg);
    }
        
    public int betAmount(float ehp, int ownMoney, int valueToCall, int sBetIncr, int pot){
       
       float frac = 0;
 
       frac = ehp / (1 - (ownMoney / ((ehp - 1) * (pot))));
       
       if (frac > 1) 
       {
           return ownMoney;
       }
       else if ((frac * ownMoney) < valueToCall)
       {
           return 0;
        }
       else 
       {
           return ((ownMoney * frac) - ((ownMoney * frac) % sBetIncr));
        }
    }
}
