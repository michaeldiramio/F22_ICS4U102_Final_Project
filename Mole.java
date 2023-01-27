import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Mole extends Minigame {
  Scanner userIn = new Scanner(System.in);
  boolean gameLoop = false;
  private Random randGen = new Random();
  private int points = 0;
  

  public String getMinigameName() {
    return ("Whack A Mole");
  }

   public void wait(double amount) {
    //For a custom pause
    double miliseconds = amount * 1000;
    int intify = (int) Math.round(miliseconds);
    try {
      Thread.sleep(intify);
    } catch(Exception e) {
    };
  }

  public void run(ArrayList<Player> players) {
    for(int i = 0; i < players.size(); i++) {
    System.out.println(
        "Welcome to Whack-A-Mole! The game is simple. A row of numbers 1 through 9 will appear on the screen. The 'Mole' is the slot with a 0. Quickly type the number that SHOULD be there to whack it! The time in between each round gradually decreases, so stay sharp! Press the Y key to continue when you're ready.");
      System.out.println();
      System.out.println("Player " + i + " turn!");

    String gamestart = userIn.nextLine();
    if (gamestart.equalsIgnoreCase("Y")) {
      gameLoop = true;
      clear();
    }

    while (gameLoop == true) {
      int randomizedNum = randGen.nextInt(10);
      int playerResponse;
      

      for(int x = 1; x <= 10; x++) {
        if (x != randomizedNum) {
          System.out.println("[" + x + "]");
        } else {
          print("0");
        }
      }

      playerResponse = userIn.nextInt();
      userIn.nextLine();

      if(playerResponse == randomizedNum) {
        System.out.println("Got it!");
        points++;
        wait(2.0);
        clear();
        print("You have " + points + " points!");
      } else {
        print("You FAILED!!");
        players.get(i).addPoints(points);
        gameLoop = false;
      }
      
    }
    
    }
  }
  }
