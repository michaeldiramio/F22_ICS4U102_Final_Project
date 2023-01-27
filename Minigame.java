import java.util.ArrayList;
import java.util.Scanner;

public class Minigame {

  public void clear() {
    //Clears the console
    System.out.print("\033[H\033[2J"); 
    System.out.flush();
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

  public void print(String phrase) {
    //Lazy way of printing text
    if (phrase != null) {
      System.out.println(phrase);
    } else {
      System.out.println();
    }
  }

  public void halfPrint(String phrase) {
    //Lazy way of printing text
    if (phrase != null) {
      System.out.print(phrase);
    } else {
      System.out.print("");
    }
  }
  
  public String getMinigameName() {
    return ("Title");
  }
  
	public void run(ArrayList<Player> players) {
		//Do the game here!
	}
}