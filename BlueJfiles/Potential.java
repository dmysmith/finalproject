

import java.util.ArrayList;
import TexasHoldemRunner.java;
public class Potential
{
    float Neg;
    float Pos;
}

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
    
    // ranks any given hand
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
    
    /**
     * This is the new hand strength algorithm (comprised of HandStrength, HandPotential, and EffectiveHandStrength). For a quick overview, see 
     * http://www.quickiwiki.com/en/Poker_Effective_Hand_Strength_(EHS)_algorithm
     * 
     * EHS = HS * (1 - NPOT) + (1 - HS) * PPOT
     */
    /**
     * TODO: 
     * replace pseudocode with real code! Wahoo!
     */
    
        public float HandStrength(Hand ourcards, Hand boardcards) 
        {
        int ahead, tied, behind;
        ahead = 0; tied = 0; behind = 0;
        ourrank = getStrength(ourcards, boardcards);
        //Consider each possible two card combination of the remaining cards.
        //TODO: track possible remaining cards
        for (int i = 0; i < CombinationUnplayedCards().size; i++) {
            opprank = getStrength(CombinationUnplayedCards[i], boardcards)
            if (ourrank>opprank) ahead += 1
            else if (ourrank==opprank) tied += 1
            else behind += 1 
            }
            handstrength=(ahead+tied/2)/(ahead+tied+behind);
            return(handstrength);
        }
       
        //takes in the current list of unplayed cards, which can be updated within the other methods, as well as the hand of otherstuff, which can be the new cards
        //picked up at every round.  just wasn't sure if it would be one card or several cards, so i just inputted a Hand.
        public ArrayList<Pair> CombinationUnplayedCards (Hand otherStuff, ArrayList<Card> currentUnplayed) {
            ArrayList<Pair> possibleCardPairs = new ArrayList<Pair>();
            ArrayList<Card> unPlayedCards = new ArrayList<Card>();
            for (int i = 0; i < currentUnplayed.size(); i++)
            {
                unplayedCards.add (currentUnplayed.get(i));//constructs the list of stuff from which we compute our combinations.
            }
            
            for (int i = 0; i < otherStuff.size(); i++)
            {
                unplayedCards.remove (otherStuff.get(i));//adjusts this list to account for whatever just happened during this round.  for example, according to what
                //new card the player picks up
            }
            
            for (int i = 0; i < unplayedCards.size(); i++)//generates all combinations, as Pairs.
            {
                for (int j = i +1; j < unplayedCards.size; j++)
                combinationUnplayedCards.add (new Pair(unplayedCards.get(i), unplayedCards.get(j)));
            }
            
            return combinationUnplayedCards;
        }
        
        public float HandPotential(Hand ourcards, Hand boardcards){ 
            // Hand potential array, each index represents ahead, tied, and behind.

            int[][] HP = new int[3][3];
            //initialize to 0
            for (int i = 0; i < 3; i++)
            {
                for (inf j = 0; j < 3; j++)
                    HP[i][j] = 0;
            }
            int[] HPTotal = new int[3]; //initialize to 0
            for (int k = 0; k < 3; k++)
                HPTotal[k] = 0;
            ourrank = getStrength(ourcards,boardcards);
            
            //Consider all two card combinations of the remaining cards for the opponent.
            int index, ahead, behind, tied;
            ahead = 0; behind = 1; tied = 2;
            for (int i = 0; i < CombinationUnplayedCards().size; i++){
                opprank = getStrength(CombinationUnplayedCards[i],boardcards)
                if(ourrank>opprank) index = ahead;
                else if(ourrank=opprank) index = tied;
                else index = behind;
                HPTotal[index] += 1;
                int CardsToRandomize;
                // All possible board cards to come.
                switch (TexasHoldemRunner.betRound) {
                    case 1: CardsToRandomize = 5;
                        break;
                    case 2: CardsToRandomize = 2;
                        break;
                    case 3: CardsToRandomize = 1;
                        break;
                    default: CardsToRandomize = 0;
                        break;
                }
                for each case(turn,river){ //Final 5-card board
                    board = [boardcards,turn,river]
                    ourbest = getStrength(ourcards,board)
                    oppbest = getStrength(oppcards,board)
                    if(ourbest>oppbest) HP[index][ahead]+=1
                    else if(ourbest=oppbest) HP[index][tied]+=1
                    else HP[index][behind]+=1
                }
                */
            }
            //Ppot: were behind but moved ahead.
            float Ppot = (HP[behind][ahead]+HP[behind][tied]/2+HP[tied][ahead]/2)/(HPTotal[behind]+HPTotal[tied]);
            //Npot: were ahead but fell behind.
            float Npot = (HP[ahead][behind]+HP[tied][behind]/2+HP[ahead][tied]/2)/(HPTotal[ahead]+HPTotal[tied]);
            Potential result = {
                Pos = Ppot;
                Neg = Npot;
            }
            return(Potential);
        }
        
        // incorporates handstrength and handpotential into a single score.
        public float EffectiveHandStrength()
    {
        return HandStrength * (1 - Npot) + (1 - HandStrength) * Ppot;
    }

    
    public void printStrength()
    {
        if(strength==10)
            System.out.println("Royal Flush");
        else if(strength==9)
            System.out.println("Straight Flush");
        else if(strength==8)
            System.out.println("Four Of A Kind");
        else if(strength==7)
            System.out.println("Full House");
        else if(strength==6)
            System.out.println("Flush");
        else if(strength==5)
            System.out.println("Straight");
        else if(strength==4)
            System.out.println("Three Of A Kind");
        else if(strength==3)
            System.out.println("Two Pair");
        else if(strength==2)
            System.out.println("Pair");
        else
            System.out.println("High Card");
    }        
    public ArrayList<Card> getBestHand()
    {
        
        return bestHand;
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
