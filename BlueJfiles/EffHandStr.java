/*
 * Description to do later!
 */
import java.util.ArrayList;
import java.util.List;

public class EffHandStr
{
    private ArrayList<ArrayList<Card>> ListToArrayList (List<Card> listToConvert) 
    {
        ArrayList<Card> aList = new ArrayList<Card> ();
        for (int i = 0; i < listToConvert.size(); i++)
        {
            aList.add(listToConvert.get(i));
        }
    }
    
    public ArrayList<ArrayList<Card>> possibleCombinations (ArrayList<Card> myCards, int numberOfCards) 
    {
            DeckOfCards unplayedDeck = new DeckOfCards();
            ArrayList<Card> unplayed = unplayedDeck.returnDeck();  
            ArrayList<ArrayList<Card>> allCombinations = new ArrayList<ArrayList<Card>>();
            
            //creates ArrayList of unplayed cards
            for (int i = 0; i < myCards.size(); i++)
            {
                unplayed.remove(myCards.get(i));
            }
            
            //actual generation of combinations, generated recursively
            for (int i = 0; i < unplayed.size(); i++){
                if (numberOfCards == 0) 
                {
                    return allCombinations;
                }
                else 
                {
                    
                    ArrayList<Card> tmp = unplayed.subList(1, unplayed.size()).toArray(new ArrayList<Card>[unplayed.subList(1, unplayed.size()).size()]);
                    for (int j = 0; j < (possibleCombinations(ListToArrayList(unplayed.subList(1, unplayed.size())), numberOfCards - 1)).size(); j++) 
                    {
                        allCombinations.add((possibleCombinations(ListToArrayList(unplayed.subList(1, unplayed.size())), numberOfCards - 1)).get(j).add(unplayed.get(j)));
                    }
                }
            }
 
            return allCombinations;
           
    }
        
    public float HandStrength(Hand myCards, ArrayList<Card> boardCards) 
    {
        int ahead = 0;
        int tied = 0;
        int behind = 0;
        ArrayList<ArrayList<Card>> oppTwoCards = possibleCombinations(myCards.getHand(), 2);
        int myRank = myCards.getStrength();
        //Consider each possible two card combination of the remaining cards.
        for (int i = 0; i < oppTwoCards.size(); i++)
        {
            Hand oppHand = new Hand (oppTwoCards.get(i).addAll(boardCards));
            oppRank = oppHand.getStrength();
            if (myRank > oppRank) 
            {
                ahead += 1;
            }
            else if (myRank == oppRank) 
            {
                tied += 1;
            }
            else 
            {
                behind += 1;
            } 
        }
        
        handstrength = (ahead + tied / 2) / (ahead + tied + behind);
        return handstrength;
        
    }
       
    
        
    public float EffectiveHandStrength (Hand ourCards, ArrayList<Card> boardCards)
    { 
        
            // Hand potential array, each index represents ahead, tied, and behind.
            int[][] HP = new int[3][3];
            
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                    HP[i][j] = 0;
            }
            
            int[] HPTotal = new int[3];
            
            for (int k = 0; k < 3; k++)
            {
                HPTotal[k] = 0;
            }
            
            ourrank = ourCards.getStrength();
            int index = 0;
            int behind = 1; 
            int tied = 2;
            ArrayList<ArrayList<Card>> oppCombinations = possibleCombinations(ourCards.getHand(), 2);
            
            for (int i = 0; i < oppCombinations.size(); i++)
            {
                oppHand = new Hand (combine(boardCards, oppCombinations[i]));
                opprank = oppHand.getStrength();
                if (ourrank>opprank) 
                {
                    index = ahead;
                }
                else if (ourrank=opprank) 
                {
                    index = tied;
                }
                else 
                {
                    index = behind;
                }
                
                HPTotal[index] += 1;
                int CardsToRandomize;
               
                switch (TexasHoldemRunner.betRound) {
                    case 2: CardsToRandomize = 2;
                        break;
                    case 3: CardsToRandomize = 1;
                        break;
                    default: CardsToRandomize = 0;
                        return HandStrength (ourCards, boardCards) ;
                }
         
                ArrayList<ArrayList<Card>> boardCombs = possibleCombinations(ourCards.getHand(), CardstoRandomize);
              
                for (ArrayList<Card> board : boardCombs) 
                {
                    ourHand = new Hand (combine(ourCards.getHand(), board));
                    ourBest = ourHand.getStrength();
                    oppHand = new Hand(combine(oppHand.getHand(), board));
                    oppBest = oppHand.getStrength();
                    
                    if (ourBest > oppBest) 
                    {
                        HP[index][ahead] += 1;
                    }
                    else if (ourBest = oppBest) 
                    {
                        HP[index][tied] += 1;
                    }
                    else 
                    {
                        HP[index][behind] += 1;
                    }
                }
                
            }
            double posPotential = (HP[behind][ahead] + HP[behind][tied] / 2 + HP[tied][ahead] / 2) / (HPTotal[behind] + HPTotal[tied]);
            double negPotential = (HP[ahead][behind] + HP[tied][behind] / 2 + HP[ahead][tied] / 2) / (HPTotal[ahead] + HPTotal[tied]);
            return ((HandStrength (ourCards, boardCards)) * (1 - negPotential) + (1 - HandStrength) * posPotential);
        }


   }