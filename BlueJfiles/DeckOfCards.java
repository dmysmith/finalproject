import java.util.ArrayList;
import java.util.*;
import java.util.Collections;
public class DeckOfCards
{
        private ArrayList<Card> deck=new ArrayList<Card>();
        private Ace aceOfSpades=new Ace("spades");
        private Ace aceOfDiamonds=new Ace("diamonds");
        private Ace aceOfHearts=new Ace("hearts");
        private Ace aceOfClubs=new Ace("clubs");
        private King kingOfSpades=new King("spades");
        private King kingOfDiamonds=new King("diamonds");
        private King kingOfHearts=new King("hearts");
        private King kingOfClubs=new King("clubs");
        private Queen queenOfSpades=new Queen("spades");
        private Queen queenOfDiamonds=new Queen("diamonds");
        private Queen queenOfHearts=new Queen("hearts");
        private Queen queenOfClubs=new Queen("clubs");
        private Jack jackOfSpades=new Jack("spades");
        private Jack jackOfDiamonds=new Jack("diamonds");
        private Jack jackOfHearts=new Jack("hearts");
        private Jack jackOfClubs=new Jack("clubs");
        private Ten tenOfSpades=new Ten("spades");
        private Ten tenOfDiamonds=new Ten("diamonds");
        private Ten tenOfHearts=new Ten("hearts");
        private Ten tenOfClubs=new Ten("clubs");
        private Nine nineOfSpades=new Nine("spades");
        private  Nine nineOfDiamonds=new Nine("diamonds");
        private  Nine nineOfHearts=new Nine("hearts");
        private Nine nineOfClubs=new Nine("clubs");
        private Eight eightOfSpades=new Eight("spades");
        private Eight eightOfDiamonds=new Eight("diamonds");
        private Eight eightOfHearts=new Eight("hearts");
        private Eight eightOfClubs=new Eight("clubs");
        private Seven sevenOfSpades=new Seven("spades");
        private Seven sevenOfDiamonds=new Seven("diamonds");
        private Seven sevenOfHearts=new Seven("hearts");
        private Seven sevenOfClubs=new Seven("clubs");
        private Six sixOfSpades=new Six("spades");
        private Six sixOfDiamonds=new Six("diamonds");
        private Six sixOfHearts=new Six("hearts");
        private Six sixOfClubs=new Six("clubs");
        private  Five fiveOfSpades=new Five("spades");
        private Five fiveOfDiamonds=new Five("diamonds");
        private Five fiveOfHearts=new Five("hearts");
        private Five fiveOfClubs=new Five("clubs");
        private Four fourOfSpades=new Four("spades");
        private Four fourOfDiamonds=new Four("diamonds");
        private Four fourOfHearts=new Four("hearts");
        private Four fourOfClubs=new Four("clubs");
        private Three threeOfSpades=new Three("spades");
        private Three threeOfDiamonds=new Three("diamonds");
        private Three threeOfHearts=new Three("hearts");
        private Three threeOfClubs=new Three("clubs");
        private Two twoOfSpades=new Two("spades");
        private Two twoOfDiamonds=new Two("diamonds");
        private Two twoOfHearts=new Two("hearts");
        private Two twoOfClubs=new Two("clubs");
        
    
        public DeckOfCards()
        {
          
            
            deck.add(aceOfSpades);
            deck.add(aceOfDiamonds);
            deck.add(aceOfHearts);
            deck.add(aceOfClubs);
            deck.add(kingOfSpades);
            deck.add(kingOfDiamonds);
            deck.add(kingOfHearts);
            deck.add(kingOfClubs);
            deck.add(queenOfSpades);
            deck.add(queenOfDiamonds);
            deck.add(queenOfHearts);
            deck.add(queenOfClubs);
            deck.add(jackOfSpades);
            deck.add(jackOfDiamonds);
            deck.add(jackOfHearts);
            deck.add(jackOfClubs);
            deck.add(tenOfSpades);
            deck.add(tenOfDiamonds);
            deck.add(tenOfHearts);
            deck.add(tenOfClubs);
            deck.add(nineOfSpades);
            deck.add(nineOfDiamonds);
            deck.add(nineOfHearts);
            deck.add(nineOfClubs);
            deck.add(eightOfSpades);
            deck.add(eightOfDiamonds);
            deck.add(eightOfHearts);
            deck.add(eightOfClubs);
            deck.add(sevenOfSpades);
            deck.add(sevenOfDiamonds);
            deck.add(sevenOfHearts);
            deck.add(sevenOfClubs);
            deck.add(sixOfSpades);
            deck.add(sixOfDiamonds);
            deck.add(sixOfHearts);
            deck.add(sixOfClubs);
            deck.add(fiveOfSpades);
            deck.add(fiveOfDiamonds);
            deck.add(fiveOfHearts);
            deck.add(fiveOfClubs);
            deck.add(fourOfSpades);
            deck.add(fourOfDiamonds);
            deck.add(fourOfHearts);
            deck.add(fourOfClubs);
            deck.add(threeOfSpades);
            deck.add(threeOfDiamonds);
            deck.add(threeOfHearts);
            deck.add(threeOfClubs);
            deck.add(twoOfSpades);
            deck.add(twoOfDiamonds);
            deck.add(twoOfHearts);
            deck.add(twoOfClubs);
        }
        
        public ArrayList<Card> returnDeck()
        {
            return deck;
        }   
        
        public void shuffleDeck()
        {
            //Collections.shuffle(deck);
            //Using the Fisher Algorithm 
            //Based off of http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
            for (int i = 0; i < deck.size() - 1; i++) 
            {
                int j = (int) (Math.random() * 52);
                Card temp = deck.get(i);
                deck.set(i, deck.get(j));
                deck.set(j, temp);
            }
        }
        
        public Card deal()
        {
            Card card;
            card=deck.get(0);
            deck.remove(0);
            return card;
        }    
}
