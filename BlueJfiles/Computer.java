import java.util.ArrayList;
import java.util.Scanner;  
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.lang.Math;
public class Computer extends Player
{
    private int money;
    private int bet; 
    private ArrayList<Card> individHand=new ArrayList<Card>();
    private ArrayList<Card> hand=new ArrayList<Card>();
    private static ArrayList<String> test= new ArrayList<String>();
    private static String[] possHands=new String[169];
    private static double[] dealtHandChances=new double[169];
    private static int[][] numberedHands=new int[3][169];
    private static Scanner scan, keyb;
    private double fold;
    private double call;
    private double raise1;
    private double raise2;
    private double raise3;
    private double raise4;
    private double chooser;
    private Hand handNow;
    public Computer(int buyin)
    {
        super(buyin);
        money=buyin;
        
        
    }
    
    public int makeBet(int bigBlind, int valueToCall, boolean canRaise,int betRound)
    {
        handNow= new Hand(returnWholeHand());
        if(betRound==1)
            changeScaleForInitialBet();
        else if(betRound==2)
            changeScaleAfterFlop();
        else if(betRound==3)
            changeScaleAfterTurn();
        else
            changeScaleAfterRiver();
        chooser=Math.random()*100;
        System.out.println("chooser " +chooser);
        if(valueToCall>0)
        {
            if(chooser<fold)
                return 0;
            else if(chooser<call)
            {
                adjustMoney(valueToCall);
                return valueToCall;
            }
            else if(canRaise)
            {
                if(chooser<raise1)
                {
                    adjustMoney(valueToCall+bigBlind);
                    return valueToCall+bigBlind;
                }
                else if(chooser<raise2)
                {
                    adjustMoney(valueToCall+2*bigBlind);
                    return valueToCall+2*bigBlind;
                }
                else if(chooser<raise3)
                {
                    adjustMoney(valueToCall+3*bigBlind);
                    return valueToCall+3*bigBlind;
                }
                else 
                {
                    adjustMoney(valueToCall+4*bigBlind); 
                    return valueToCall+4*bigBlind;    
                }
            }
            else
            {
                adjustMoney(valueToCall);
                return valueToCall;
            }
        }
        else
        {
            if(chooser<call)
            {
                adjustMoney(valueToCall);
                return valueToCall;
            }
            else if(chooser<raise1)
            {
                adjustMoney(valueToCall+bigBlind);
                return valueToCall+bigBlind;
            }
            else if(chooser<raise2)
            {
                adjustMoney(valueToCall+2*bigBlind);
                return valueToCall+2*bigBlind;
            }
            else if(chooser<raise3)
            {
                adjustMoney(valueToCall+3*bigBlind);
                return valueToCall+3*bigBlind;
            }
            else
            {
                adjustMoney(valueToCall+4*bigBlind);
                return valueToCall+4*bigBlind;
            }
            
        }
        
    }    
    
   
    
    public void changeScaleForInitialBet()
    {
        ArrayList<Card> individ=getIndividHand();
        try
        {
            importChances();
        }
        catch (Exception e) 
         {
            e.printStackTrace();
        }
        int handRank=-1;
        int suit=0;
        fold=40.0;
        call=75.0;
        raise1=88.0;
        raise2=93.0;
        raise3=97.0;
        raise4=100.0;
        double prob;
        if(individ.get(0).returnSuit().equals(individ.get(1).returnSuit()))
            suit=1;
        for(int i=0; i<169;i++)
        {
            if((individ.get(0).returnValue()==numberedHands[1][i])&&(individ.get(1).returnValue()==numberedHands[0][i])&&(suit==numberedHands[2][i]))
                handRank=i;
        }
        prob=dealtHandChances[handRank];
        
        if(prob<40.0)
        {
            fold=95.0;
            call=99.0;
            raise1=99.5;
            raise2=99.7;
            raise3=99.9;
        }    
        else if(prob>=40.0&&prob<50.0)
        {
            fold+=5*(50-prob);
            call+=2*(50-prob);
            raise1+=(50-prob);
            raise2+=.5*(50-prob);
            raise3+=.25*(50-prob);
        }
        else if(prob>=50.0&&prob<60.0)
        {
            fold-=2.5*(prob-50);
            call-=2.5*(prob-50);
            raise1-=1.3*(prob-50);
            raise2-=.8*(prob-50);
            raise3-=.2*(prob-50);
        }
        else if(prob>=60.0&&prob<70.0)
        {
            fold=15.0;
            call=50.0;
            raise1=75.0;
            raise2=85.0;
            raise3=95.0;
        }
        else if(prob>=70.0&&prob<80.0)
        {
            fold=3.0;
            call=38.0;
            raise1=64.0;
            raise2=80.0;
            raise3=92.0;
        }
        else if(prob>=80.0)
        {
            fold=0.0;
            call=25.0;
            raise1=50.0;
            raise2=70.0;
            raise3=90.0;
        }
        
    }    
    public void changeScaleAfterFlop()
    {
        ArrayList<Card> individ=getIndividHand();
        int strength=handNow.getStrength();
         boolean hasIndivid=handNow.containsIndivid(individ);
        if(strength>5)
        {
            fold=0;
            call=15;
            raise1=40;
            raise2=60;
            raise3=85;
        }
        else if(strength==4)
        {
            if(hasIndivid)
            {
                fold=0;
                call=25;
                raise1=50;
                raise2=75;
                raise3=90;
            }
            else
            {
                fold=0;
                call=80;
                raise1=90;
                raise2=95;
                raise3=98;
            }
        }
        else if(strength==3)
        {          
                fold=0;
                call=35;
                raise1=60;
                raise2=85;
                raise3=95;
        }
        else if(strength==2)
        {
            if(hasIndivid)
            {
                fold=0;
                call=50;
                raise1=80;
                raise2=90;
                raise3=95;
            }
            else
            {
                fold=50;
                call=80;
                raise1=90;
                raise2=95;
                raise3=98;
            }
        }
        else
        {
            if(getIndividHand().get(1).returnValue()>12)
            {
                fold=60;
                call=90;
                raise1=97;
                raise2=99;
                raise3=99.5;
            }
            else
            {
                fold=90;
                call=99;
                raise1=99.5;
                raise2=99.8;
                raise3=99.9;
            }
        }
    }
    public void changeScaleAfterTurn()
    {
        ArrayList<Card> individ=getIndividHand();
        int strength=handNow.getStrength();
         boolean hasIndivid=handNow.containsIndivid(individ);
        if(strength>5)
        {
            fold=0;
            call=15;
            raise1=40;
            raise2=60;
            raise3=85;
        }
        else if(strength==4)
        {
            if(hasIndivid)
            {
                fold=0;
                call=25;
                raise1=50;
                raise2=75;
                raise3=90;
            }
            else
            {
                fold=0;
                call=80;
                raise1=90;
                raise2=95;
                raise3=98;
            }
        }
        else if(strength==3)
        {          
                fold=0;
                call=35;
                raise1=60;
                raise2=85;
                raise3=95;
        }
        else if(strength==2)
        {
            if(hasIndivid)
            {
                fold=0;
                call=50;
                raise1=80;
                raise2=90;
                raise3=95;
            }
            else
            {
                fold=50;
                call=80;
                raise1=90;
                raise2=95;
                raise3=98;
            }
        }
        else
        {
            if(getIndividHand().get(1).returnValue()>12)
            {
                fold=60;
                call=90;
                raise1=97;
                raise2=99;
                raise3=99.5;
            }
            else
            {
                fold=90;
                call=99;
                raise1=99.5;
                raise2=99.8;
                raise3=99.9;
            }
        }
    }
    public void changeScaleAfterRiver()
    {
  
         ArrayList<Card> individ=getIndividHand();
        int strength=handNow.getStrength();
         boolean hasIndivid=handNow.containsIndivid(individ);
        if(strength==1)
        {
            if(individ.get(1).returnValue()>10)
            {
                fold=80.0;
                call=90.0;
                raise1=96.0;
                raise2=99.0;
                raise3=99.8;
             }
            else
            {
                fold=90.0;
                call=98.0;
                raise1=99.0;
                raise2=99.8;
                raise3=99.9;
            }
        }
        else if(hasIndivid)
        {
            if(strength==2)
            {
                fold=30;
                call=80;
                raise1=93;
                raise2=98;
                raise3=99.5;
            }
            else if(strength==3)
            {
                fold=20;
                call=75;
                raise1=90;
                raise2=97;
                raise3=99;
            }
            else if(strength==4)
            {
                fold=10;
                call=60;
                raise1=80;
                raise2=95;
                raise3=98;
            }
            else if(strength==5)
            {
                fold=5;
                call=50;
                raise1=75;
                raise2=85;
                raise3=95;
            }
            else if(strength==6)
            {
                fold=0;
                call=20;
                raise1=40;
                raise2=60;
                raise3=80;
            }
            else if(strength==7)
            {
                fold=0;
                call=10;
                raise1=35;
                raise2=50;
                raise3=75;
            }
            else if(strength==8)
            {
                 fold=0;
                 call=5;
                 raise1=20;
                 raise2=45;
                 raise3=65;
            }
            else if(strength==9)
            {
                 fold=0;
                 call=5;
                 raise1=15;
                 raise2=30;
                 raise3=40;
            }
            else if(strength==10)
            {
                 fold=0;
                 call=5;
                 raise1=10;
                 raise2=25;
                 raise3=35;
            }
        }
        else
        {
            if(individ.get(1).returnValue()>11)
            {
                if(strength<4)
                {
                    fold=80.0;
                    call=90.0;
                    raise1=96.0;
                    raise2=99.0;
                    raise3=99.8;
                }
                else
                {
                    fold=40.0;
                    call=80.0;
                    raise1=90.0;
                    raise2=96.0;
                    raise3=98.5;
                }
            }
             
            else
            {
                fold=90.0;
                call=98.0;
                raise1=99.0;
                raise2=99.8;
                raise3=99.9;
            }
        
    }
        
        
    }
    public static  void importChances() throws IOException 
    {
        
            scan=new Scanner(new File("Percents.txt"));
            int i=0;
            while (scan.hasNextDouble())
            {
                 dealtHandChances[i]=scan.nextDouble();
                 i++;
            }
            
            keyb = new Scanner(new File("Hands.txt"));
            
            int j=0;
            while(j<169)
            {
                possHands[j]=keyb.nextLine();
                j++;
            }
            
            String str;
            String x;
            String y;
            String z;
            for(int k=0; k<169; k++)
            {
                str=possHands[k];
                x=str.substring(0,1);
                if(x.equals("A"))
                    numberedHands[0][k]=14;
                else if(x.equals("K"))
                    numberedHands[0][k]=13;
                else if(x.equals("Q"))
                    numberedHands[0][k]=12;
                else if(x.equals("J"))
                    numberedHands[0][k]=11;
                else if(x.equals("T"))
                    numberedHands[0][k]=10;  
                else if(x.equals("9"))
                    numberedHands[0][k]=9;
                else if(x.equals("8"))
                    numberedHands[0][k]=8;   
                else if(x.equals("7"))
                    numberedHands[0][k]=7;    
                else if(x.equals("6"))
                    numberedHands[0][k]=6;  
                else if(x.equals("5"))
                    numberedHands[0][k]=5;   
                else if(x.equals("4"))
                    numberedHands[0][k]=4;
                else if(x.equals("3"))
                    numberedHands[0][k]=3;
                else if(x.equals("2"))
                    numberedHands[0][k]=2;        
                y=str.substring(1,2);
                if(y.equals("A"))
                    numberedHands[1][k]=14;
                else if(y.equals("K"))
                    numberedHands[1][k]=13;
                else if(y.equals("Q"))
                    numberedHands[1][k]=12;
                else if(y.equals("J"))
                    numberedHands[1][k]=11;
                else if(y.equals("T"))
                    numberedHands[1][k]=10;  
                else if(y.equals("9"))
                    numberedHands[1][k]=9;
                else if(y.equals("8"))
                    numberedHands[1][k]=8;   
                else if(y.equals("7"))
                    numberedHands[1][k]=7;    
                else if(y.equals("6"))
                    numberedHands[1][k]=6;  
                else if(y.equals("5"))
                    numberedHands[1][k]=5;   
                else if(y.equals("4"))
                    numberedHands[1][k]=4;
                else if(y.equals("3"))
                    numberedHands[1][k]=3;
                else if(y.equals("2"))
                    numberedHands[1][k]=2; 
                z=str.substring(2);
                if(z.equals("o"))
                    numberedHands[2][k]=0;
                else
                    numberedHands[2][k]=1;
                }

    }   
}

