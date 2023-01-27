import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper extends Minigame {

  public String getMinigameName(){
    return ("Minesweeper");
  }


  public void run(ArrayList<Player> players) {
    Random randGen = new Random();
    Scanner userIn = new Scanner(System.in);

    int userCoordX = 0;
    int userCoordY = 0;
    int mine = 0;
    int size = randGen.nextInt(2) + 3;
    int amountOfP = players.size();
    int[][] grid = new int[size][size];
    String input;

    //print the grid on the screen
    for(int i = 0; i < amountOfP; i++) {
      for(int row = 0; row < grid.length; row++) {
		    for(int col = 0; col < grid[row].length; col++) {
          grid[row][col] = randGen.nextInt(10);
				  System.out.print("[ " + "X" + " ]");
		    }
		    System.out.println();
		  }
      print("Player " + (i+1) + "! [" + players.get(i).getName() + "] It's your turn!");
      wait(0.5);
      print("Select a square! Enter a co-ordinate [X,Y]"); 
      print("[INCLUDE COMMA]");
      //get coordinates and turn it into an integer
      input = userIn.nextLine();
      String xInput = input.substring(input.indexOf(",") - 1 , input.indexOf(","));
      userCoordX = Integer.parseInt(xInput);
      userCoordX = userCoordX - 1;
      String yInput = input.substring(input.indexOf(",") + 1 , input.length());
      userCoordY = Integer.parseInt(yInput);
      userCoordY = userCoordY - 1;

      //see if there is a mine in chosen spot
      if(grid[userCoordY][userCoordX] >= 7){
        mine = 1;
      } else {
        mine = 0;
      }
    
      wait(1.0);
      //tell user if they hit or missed a mine
      if (mine == 1){
      print("You chose (" + (userCoordX+1) + "," + (userCoordY+1) + ")! This spot had a mine in it! You unfortunately don't get any points.");
      } else {
        print("You chose (" + (userCoordX+1) + "," + (userCoordY+1) + ")! This spot was clear! You gained a point!");
        players.get(i).addPoints(1);
      }

      wait(4.0);
      clear();
    }
  } 
}