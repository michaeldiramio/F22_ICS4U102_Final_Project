import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GuessingGame extends Minigame {

  public String getMinigameName() {
    return ("Guessing Game");
  }
	public void run(ArrayList<Player> players) {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    
    /*clear();
    wait(0.5);
    print("");
    halfPrint();*/       
    int[] tries = new int[players.size()];

    for (int i = 0; i < players.size(); i++) {
      int pin = random.nextInt(1000);
      print(players.get(i).getName() + "[P" + players.get(i).getNumber() + "] ' s turn!'");
      
      //Put code in here
      System.out.print(pin);
       wait(2.0);
      backspace(pin);
      //gets entry 
      System.out.print("guess the pin code: ");
      int entry = sc.nextInt();
      //while loop until you get a number wrong
    		while ( entry == pin ) {
          tries[i] = tries[i]+1;
          System.out.print(pin);
           wait(2.5); 
          backspace(pin);
          
    			System.out.println("\n correct");
          pin = random.nextInt(1000);
          System.out.print("\n");
          System.out.print(pin);
          wait(2.5); 
          backspace(pin);
    			System.out.print("guess the pin: ");
     			entry = sc.nextInt();
         
        
    		}
      //after getting wrong answer, its prints your score
    	System.out.println("incorrect, this is how many points you got:  "+ tries[i]);  
    }

    //Comparing tries and gives points to highest guess
    int highest = 0;
    for (int i = 0; i < players.size(); i++) {
      if (tries[i] > highest) {
        highest = tries[i];
      }
    }
    for (int i = 0; i < players.size(); i ++) {
      if (tries[i] == highest) {
        players.get(i).addPoints(1);
      }
    }
  }
    
public static void backspace(int number){
    for(int i = 0; i < number; i++){
    System.out.print("\b \b");
      }
}
}