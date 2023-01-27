import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessing extends Minigame {

  public String getMinigameName(){
    return ("Number Guesser");
  }
  
  public void run(ArrayList<Player> players) {
    int amountOfP = players.size();
    for(int i = 0; i < amountOfP; i++) {
      System.out.println("Player " + (i+1) + "! [" + players.get(i).getName() + "] It's your turn!");
      //make the random stuff
      Random randGen = new Random();
      Scanner userIn = new Scanner(System.in);
      
      wait(3.0);
      System.out.println("Pick a number between 1 - 10!");
      int guess = userIn.nextInt();
      wait(1.0);
      int random = randGen.nextInt(9) + 1;
      System.out.println(random);

      if(guess == random){
        System.out.println("Wow! You guessed the right number! You get one point!");
        players.get(i).addPoints(1);
      } else {
        System.out.println("You guessed the wrong number, you get no points."); 
        wait(4.0);
      }
      clear();
    }
  }
}