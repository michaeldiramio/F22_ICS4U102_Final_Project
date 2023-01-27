import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class GuessGame extends Minigame {

  public String getMinigameName() {
    return ("The Guessing Game");
  }
  
  Scanner userIn = new Scanner(System.in);

  Random rand = new Random(10);

  int points = 0;

  public void run(ArrayList<Player> players) {
    for(int i = 0; i < players.size(); i++) {
    points = 0;

    int randNum = rand.nextInt(10);
       
      print("You'll see a number on the screen for a split second! Type it quick!");

      wait(5.0);


    System.out.println(randNum);

   
    wait(1.0);  

    clear();

    int response = userIn.nextInt();
    userIn.nextLine();

    if(randNum == response) {
      System.out.println("You get a point!");
      points++;
      print("You have " + points + " points!");
    } else {
      System.out.println("YOU FAIL!!!!");
      print("You ended with " + points + " points!");
    }

      
    }
  }
}