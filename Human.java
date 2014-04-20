import java.util.ArrayList;
import java.util.*;
public class Human extends Player
{
    private int money;
   
    private Scanner scan=new Scanner(System.in);
    public Human(int buyin)
    {
       super(buyin);
       money=buyin; 
    }
    
    public int makeBet(int bigBlind, int needToCall, boolean canRaise )
    {       
        if(needToCall>0&&canRaise)
        {
            System.out.println("Enter 0 to fold\n " + "Enter " +needToCall + " to call\n " + "Enter up until " + money + "to raise, \n must be an interval of " + bigBlind);
        }
        else if(needToCall==0&&canRaise)
        {
            System.out.println("Enter 0 to check\n " + "Enter up until " + money + "to raise, \n must be an interval of " + bigBlind);
        }
        else if(needToCall>0&&(canRaise==false))
        {
            System.out.println("Enter 0 to fold\n " + "Enter " +needToCall + " to call\n ");
        }
        
       
        int choice= scan.nextInt();
        if(canRaise)
        {
            while(choice<0 || (choice>money) ||(((choice-needToCall)%bigBlind!=0)&&choice!=needToCall&&choice!=0))
            {
                System.out.println("Invalid bet, try again:");
                choice=scan.nextInt();
            }    
        }
        else
        {
            while(choice!=0&&choice!=needToCall)
            {
                System.out.println("Invalid bet, try again:");
                choice=scan.nextInt();
            }  
        }
        adjustMoney(choice);
        return choice;   
    }  

}
