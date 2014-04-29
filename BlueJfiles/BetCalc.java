
/**
 * Determines whether to raise, call, or fold based on effective hand strength and a predetermined boldness value.
 */
public class BetCalc
{
   public int betAmount(float ehpot, float boldness, int[] playersMoney, int ownMoney, int betMin, int betIncr){
       float bet = 0;
       
       if (ehpot > 0.9)
       {
            bet = 0.7 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       else if (ehpot > 0.7) 
       {
            bet = 0.3 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       else if (ehpot > 0.5)
       {
           bet = 0.1 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       else if (ehpot > 0.3)
       {
           bet = 0.05 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       else 
       {
           bet = 0 + Math.random(0, boldness/2) - betMin/ownMoney;
       }

           
       if (bet < 0) 
       {
           return 0;
       }
       else if (bet > 1)
       {
           return ownMoney;
       }
       else 
       {
           if (bet * ownMoney < betIncr) 
           {
               return betMin;
           }
           else
           {
               return bet * ownMoney + betMin;
           }

       }
    
    }
}