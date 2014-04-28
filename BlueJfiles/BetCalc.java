
/**
 * Determines whether to raise, call, or fold based on effective hand strength and a predetermined boldness value.
 */
public class BetCalc
{
   public int betAmount(float ehpot, float bold, int[] playersMoney, int ownMoney, int betMin, int betIncr){
       float bet = 0;
       
       if (ehpot > 0.9)
       {
            bet = 0.7 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       elseif (ehpot > 0.7) 
       {
            bet = 0.3 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       elseif (ehpot > 0.5)
       {
           bet = 0.1 + Math.random(0, boldness/2) - betMin/ownMoney;
       }
       elseif (ehpot > 0.3)
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
       elseif (bet > 1)
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
    
    
    // takes in following parameters/variables
    // ehpot = effective hand potential value (0 - 1)
    // bold = boldness (0 - 1)
    // playersMoney = array of the money of all the other players who have not folded 
    // ownMoney = amount of money you have
    // betMin = current bet amount
    // betIncr = smallest betting amount 
    
    
   //if (ehpot + bold > 0.9)
            //bet = 0.7 + random(0, boldness/2) - betMin/ownMoney
   // elseif ((ehpot + bold > 0.7) 
            //bet = 0.3 + random(0, boldness/2) - betMin/ownMoney
   // elseif ((ehpot + bold  > 0.5)
            //bet = 0.1 + random(0, boldness/2) - betMin/ownMoney
   // elseif ((ehpot + bold  > 0.3)
           //bet = 0.05 + random(0, boldness/2) - betMin/ownMoney
   // else 
           //bet = 0 + random(0, boldness/2) - betMin/ownMoney

   //if bet < 0
        //0
   //elseif bet > 1
        //return ownMoney
   //else 
        //if (bet * ownMoney < betIncr) then
            //betMin
        //else 
            //raise bet * ownMoney + betMin
    
}
