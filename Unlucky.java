import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Unlucky extends Minigame {
  
  private int biggest = 0;
  private int whichPlayer;
  private int smallest = 100;
  private int lastPlace;

  public String getMinigameName() {
    return ("Unlucky");
  }

  public void run(ArrayList<Player> players) {
    clear();
    print("Unlucky :/");
    for(int i = 0; i < players.size(); i++) {
      if(biggest < players.get(i).getPoints()) {
        biggest = players.get(i).getPoints();
        whichPlayer = i;
      } else {
        
      }
    }
    for(int i = 0; i < players.size(); i++) {
      if(smallest > players.get(i).getPoints()) {
        smallest = players.get(i).getPoints();
        lastPlace = i;
      } else {
        
      }
    }
    
    print("The person in first, " + players.get(whichPlayer).getName() + " will now be swapping points with " + players.get(lastPlace).getName());

    wait(3.0);
    players.get(whichPlayer).resetPoints();
    players.get(whichPlayer).addPoints(smallest);
    players.get(lastPlace).resetPoints();
    players.get(lastPlace).addPoints(biggest);

    System.out.println("\nThe points are now: ");
    for(int i = 0; i < players.size(); i++) {
      print(players.get(i).getName() + ": " + players.get(i).getPoints());
    }

    wait(7.0);
    
  }
}