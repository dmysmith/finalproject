/*
 * TO DO
 * make comp bet for flop river and final
 * fix comp makeBet to distinguish between each type of bet
 * make allin
 * fix breakTie
 * add modes to change bet
 * make all printing
 * neaten up
 * FIX HOW HAND DOESNT TRANFER 
 */



import java.util.*;

public class TexasHoldemRunner
{
    private static int pot;
    private static int rounds;
    private static int valueOfBigBlind;
    private static int betRound;
    private static int buyIn;
    private static boolean compIsBigBlind;
    private static boolean playerIsBigBlind;
    private static boolean allIn;
    private static boolean keepPlaying;
    private static boolean dontEnd;
    private static boolean playerWon;
    private static boolean computerWon;
    private static boolean computerMadeLastBet;
    private static boolean compTurn;
    private static DeckOfCards deck;
    private static Computer computer;
    private static Human player;
    private static Hand computerHand;
    private static Hand playerHand;
    private static ArrayList<Card> tableCards;
    
    public static void main (String [] args)
    {
        Scanner scan=new Scanner(System.in);
        Scanner scanned=new Scanner(System.in);
        buyIn=5000;
        rounds=0;
        valueOfBigBlind=200;
        keepPlaying=true;
        boolean ready=false;
        compIsBigBlind=true;
        compTurn=true;
        dontEnd=true;
        computerWon=false;
        playerWon=false;
        playerIsBigBlind=false;
        int helper=(int)(Math.random()*2);
        computer=new Computer(buyIn);
        player=new Human(buyIn);
        
        if (helper==1)
        {
            compIsBigBlind=false;
            playerIsBigBlind=true;
            compTurn=false;
        }    
            
        System.out.println("Welcome to Texas Hold'em\n");
        System.out.println();
        System.out.println("The Big Blind is: " + valueOfBigBlind);
        System.out.println("The buyin is: " +buyIn);
        System.out.println();
        
        if(compIsBigBlind)
             System.out.println("The computer is the Big Blind first and the player is the small blind");
        else
             System.out.println("The player is the Big Blind first and the computer is the small blind");
             
         System.out.println();   
         
        while(dontEnd)
        {
            deck=new DeckOfCards();
            tableCards=new ArrayList<Card>();
            betRound=1;
            
            System.out.println("We will now take the money for the blinds"); 
            System.out.println();
           
            pot=0;
            pot+=computer.playBlind(compIsBigBlind, valueOfBigBlind);
            pot+=player.playBlind(playerIsBigBlind, valueOfBigBlind);
            
            System.out.println("Your Money: " + player.getMoney());
            System.out.println("Computer's Money: " + computer.getMoney());
            System.out.println("Pot: "+pot);
            System.out.println();
            
            System.out.println("Would you like to continue?(y/n)");
            String decision=scanned.nextLine();
            if(decision.equals("n"))
                keepPlaying=false;
            
             
            if(computer.isAllIn()||player.isAllIn())
            {
                allIn();
            }     
            
            System.out.println();
            System.out.println("Deck Shuffles");      
            System.out.println();
            
            deck.shuffleDeck();
            if(keepPlaying)
            {
                if(compIsBigBlind)
                {
                    computer.addToHand(deck.deal());
                    player.addToHand(deck.deal());
                    computer.addToHand(deck.deal());
                    player.addToHand(deck.deal());
                    System.out.println("Your cards: "+ player.getIndividHand());
                    System.out.println("*hidden* CompCards: "+ computer.getIndividHand());
                    compTurn=false;
                    makeOriginalBet();
                    System.out.println("Your Money: " + player.getMoney());
                    System.out.println("Computer's Money: " + computer.getMoney());
                    System.out.println("Pot: "+pot);
                }
                else
                {
                
                    player.addToHand(deck.deal());
                    computer.addToHand(deck.deal());
                    player.addToHand(deck.deal());
                    computer.addToHand(deck.deal());
                    System.out.println("Playercards: "+ player.getIndividHand());
                    System.out.println("*hidden* CompCards: "+ computer.getIndividHand());
                    compTurn=true;
                    makeOriginalBet();
                    System.out.println("Your Money: " + player.getMoney());
                    System.out.println("Computer's Money: " + computer.getMoney());
                    System.out.println("Pot: "+pot);
                }    
            }
            
            if(computer.isAllIn()||player.isAllIn())
            {
                allIn();
            }     
            
            if(keepPlaying)
            {
                System.out.println();
                tableCards.add(deck.deal());
                tableCards.add(deck.deal());
                tableCards.add(deck.deal());
                System.out.println("Table Cards: " + tableCards);
                betRound=2;
                makeBet();
                System.out.println("Your Money: " + player.getMoney());
                System.out.println("Computer's Money: " + computer.getMoney());
                System.out.println("Pot: "+pot);
            }
   
            
            if(computer.isAllIn()||player.isAllIn())
            {
                allIn();
            }     
        
            if(keepPlaying)
            {
                System.out.println();
                tableCards.add(deck.deal());
                System.out.println("Table Cards: " + tableCards);
                betRound=3;
                makeBet();
                System.out.println("Your Money: " + player.getMoney());
                System.out.println("Computer's Money: " + computer.getMoney());
                System.out.println("Pot: "+pot);
            }
            
           
            if(computer.isAllIn()||player.isAllIn())
            {
                allIn();
            }     
       
            if(keepPlaying)
            {
                System.out.println();
                tableCards.add(deck.deal());
                System.out.println("Table Cards: " + tableCards);
                betRound=4;
                makeBet();
                System.out.println("Your Money: " + player.getMoney());
                System.out.println("Computer's Money: " + computer.getMoney());
                System.out.println("Pot: "+pot);
            }
            
            
            if(computer.isAllIn()||player.isAllIn())
            {
                allIn();
            }     
            System.out.println();
            distributeMoney();
            computer.clearHand();
            player.clearHand();
        
            boolean holder=compIsBigBlind;
            compIsBigBlind=playerIsBigBlind;
            playerIsBigBlind=holder;
            rounds++; 
            ready=false;
            keepPlaying=true;
            if(rounds%10==0)
                valueOfBigBlind+=100;
            }
       
    }
    
    public static void makeOriginalBet()
    {
        int compBet=0;
        int playerBet=0;
        if(compTurn)
        {
            computerMadeLastBet=false;
            compBet=computer.makeBet(valueOfBigBlind,valueOfBigBlind/2,true,1);
            pot+=compBet;
            
            if(compBet>0)
            {
                if(compBet==(valueOfBigBlind/2))
                {
                    computerMadeLastBet=false;
                    playerBet=player.makeBet(valueOfBigBlind, 0,true);
                    pot+=playerBet;
                    if(playerBet>0)
                    {
                        computerMadeLastBet=false;
                        compBet=computer.makeBet(valueOfBigBlind,playerBet,false,1);
                        pot+=compBet;
                        
                        if(compBet==0)
                        {
                            keepPlaying=false;
                            playerWon=true;
                        }    
                    }
                }    
                else if(compBet>(valueOfBigBlind/2))
                {
                    computerMadeLastBet=true;
                    playerBet=player.makeBet(valueOfBigBlind,(compBet-(valueOfBigBlind/2)),true);
                    pot+=playerBet;
                    
                    if(playerBet>0)
                    {
                        if(playerBet>(compBet-(valueOfBigBlind/2)))
                        {
                            computerMadeLastBet=false;
                            compBet=computer.makeBet(valueOfBigBlind,playerBet-compBet,false,1);
                            pot+=compBet;
                            
                            if(compBet==0)
                            {
                                keepPlaying=false;
                                playerWon=true;
                            }    
                        }
                        
                    }
                    else
                    {
                        computerWon=true;
                        keepPlaying=false;
                    }    
                }    
                    
            }
            else
            {
                keepPlaying=false;
                playerWon=true;
            }
        }
        else
        {
            computerMadeLastBet=true;
            playerBet=player.makeBet(valueOfBigBlind,(valueOfBigBlind/2),true);
            pot+=playerBet;
            computerMadeLastBet=false;
            if(playerBet>0)
            {
                if(playerBet==(valueOfBigBlind/2))
                {
                    compBet=computer.makeBet(valueOfBigBlind,0,true,1);
                    pot+=compBet;
                    
                    if(compBet>0)
                    {
                        playerBet=player.makeBet(valueOfBigBlind, compBet,false);
                        pot+=playerBet;
                        
                        if(playerBet==0)
                        {
                            keepPlaying=false;
                            computerWon=true;
                        }    
                    }
                }    
                else if(playerBet>(valueOfBigBlind/2))
                {
                    computerMadeLastBet=false;
                    compBet=computer.makeBet(valueOfBigBlind, playerBet-(valueOfBigBlind/2),true,1);
                    pot+=compBet;
                    
                    if(compBet>0)
                    {
                        if(compBet>(playerBet-(valueOfBigBlind/2)))
                        {
                            computerMadeLastBet=true;
                            playerBet=player.makeBet(valueOfBigBlind, (compBet-playerBet),false);
                            pot+=playerBet;
                            
                            if(playerBet==0)
                            {
                                computerWon=true;
                                keepPlaying=false;
                            }    
                        }
                        
                    }
                    else
                    {
                        playerWon=true;
                        keepPlaying=false;
                    }
                }    
                    
            }
            else
            {
                computerWon=true;
                keepPlaying=false;
            }
        }    
        System.out.println("playerbet: " +playerBet);
        System.out.println("computerbet: " + compBet);
    }
    public static void makeBet()
    {
        computer.getWholeHand(tableCards);
        if(computerMadeLastBet)
        {
            compTurn=false;
        }
        else
        {   
            compTurn=true;
        }
        int compBet=0;
        int playerBet=0;
        if(compTurn)
        {
            computerMadeLastBet=true;
            compBet=computer.makeBet(valueOfBigBlind,0,true,betRound);
            pot+=compBet;
            
            if(compBet>0)
            {
                playerBet=player.makeBet(valueOfBigBlind,compBet,true);
                pot+=playerBet;
               
                if(playerBet>0)
                {
                    if(playerBet>compBet)
                    {
                        computerMadeLastBet=false;
                        compBet=computer.makeBet(valueOfBigBlind,playerBet-compBet,false,betRound);
                        pot+=compBet;
                        
                        if(compBet==0)
                        {
                           keepPlaying=false;
                           playerWon=true;
                        }    
                    }   
                }
                else
                {
                    computerWon=true;
                    keepPlaying=false;
                }    
            }    
            else
            {
                playerBet=player.makeBet(valueOfBigBlind,0,true);
                pot+=playerBet;
                
                if(playerBet>0)
                {
                    computerMadeLastBet=false;
                    compBet=computer.makeBet(valueOfBigBlind,playerBet,false,betRound);
                    pot+=compBet;
                   
                    if(compBet==0)
                    {
                        keepPlaying=false;
                        playerWon=true;
                    }
                }
                
            }
                    
        }
        else
        {
            computerMadeLastBet=false;
            playerBet=player.makeBet(valueOfBigBlind,0,true);
            pot+=playerBet;
            
            if(playerBet>0)
            {
                compBet=computer.makeBet(valueOfBigBlind,playerBet,true,betRound);
                pot+=compBet;
               
                if(compBet>0)
                {
                    if(compBet>playerBet)
                    {
                        computerMadeLastBet=true;
                        playerBet=player.makeBet(valueOfBigBlind,compBet-playerBet,false);
                        pot+=playerBet;
                        
                        if(playerBet==0)
                        {
                           keepPlaying=false;
                           computerWon=true;
                        }    
                    }   
                }
                else
                {
                    playerWon=true;
                    keepPlaying=false;
                }    
            }    
            else
            {
                compBet=computer.makeBet(valueOfBigBlind,0,true,betRound);
                pot+=compBet;
                
                if(compBet>0)
                {
                    computerMadeLastBet=true;
                    playerBet=player.makeBet(valueOfBigBlind,compBet,false);
                    pot+=playerBet;
                    
                    if(playerBet==0)
                    {
                        keepPlaying=false;
                        computerWon=true;
                    }
                }
                
            }
                 
        }    
    }    
    public static void distributeMoney()
    {
        Player winner=getWinner();
        if(winner==null)
        {
            player.addMoney(pot/2);
            computer.addMoney(pot/2);
        }
        else
        {
            winner.addMoney(pot);
        }
        if(winner.equals(computer))
            System.out.println("computer won");
        else
            System.out.println("player won");
            
    }    
    
    public static Player getWinner()
    {
        Player winner=new Player();
        if (computerWon)
            winner=computer;
        else if(playerWon)
            winner=player;
        else 
        {
            
            computerHand=new Hand(computer.getWholeHand(tableCards));
            playerHand=new Hand(player.getWholeHand(tableCards));
            winner=compareHands(computerHand, playerHand);//null if tie
            
        }    
        if(betRound==4)
        {
            if(winner.equals(computer))
            {
                System.out.println("computer hand"+ computerHand );
                computerHand.printStrength();
            }
            else
            {
                System.out.println("player hand" + playerHand);
                playerHand.printStrength();
            }
       
        } 
        computerWon=false;
        playerWon=false;
        return winner;
    }
    
    public static Player compareHands(Hand comp, Hand play)
    {
        int playerStrength=play.getStrength();
        int computerStrength=comp.getStrength();
        if(playerStrength>computerStrength)
            return player;
        else if(computerStrength>playerStrength)
            return computer;
        else
            return breakTie(comp, play, computerStrength);
    }    
    
    //FIX
    public static Player breakTie(Hand comp, Hand play, int strength)
    {
        ArrayList <Card> compBest=new ArrayList<Card>();
        ArrayList <Card> playBest=new ArrayList<Card>();
        compBest=comp.getBestHand();
        playBest=play.getBestHand();
        if((compBest.get(compBest.size()-1).returnValue())>(playBest.get(playBest.size()-1).returnValue()))
                return computer;
            else if((compBest.get(compBest.size()-1).returnValue())<(playBest.get(playBest.size()-1).returnValue()))   
                return player;
            else return null;
       
    }
    
    
    public static void allIn()
    {
        
        
        
        keepPlaying=false;
    }    
    
}    