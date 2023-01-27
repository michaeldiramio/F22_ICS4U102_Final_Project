import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class FastFigures extends Minigame {

  //all my privates
  private Scanner scan = new Scanner(System.in);
  private Random randGen = new Random();
  private int count = 0;
  private boolean gameLoop = true;
  private int a = 0;
  private int b = 0;
  private int answer = a + b;
  private int userIn;
  private boolean streak;
  private int streakCount = 0;
  private Long beforeTime;
  private Long afterTime;
  private Long timeTaken;
  private double timeTakenDub;
  private double totalTime;
  private int points;
  private int playerSize;
  private double[] playerTime = new double[10];
  private double shortest = 1000000000;
  private int whichPlayer;
  //this just tells main what the minigame name is
  public String getMinigameName() {
    return ("Fast Figures");
  }

  //my void run
	public void run(ArrayList<Player> players) {
    //checks how many players and puts it in and int
    playerSize = players.size();
    //runs for however many players there are
    for(int i = 0; i < playerSize; i++) {
      //resetting all my ints
      count = 0;
      streakCount = 0;
      totalTime = 0;
      clear();
      //I quite like that it says the players name, compliments to the chef (me)
      System.out.println("Welcome to Fast Figures, " + players.get(i).getName());
      print("\nThe Goal of the game is to answer questions as fast as you can\nIf you get one wrong, it counts as double the time you took\n");
      print("\nReady? Go!");
      wait(10.0);
      //for loops for five rounds of questions
      for(int j = 0; j < 5; j++) {
        wait(5.0);
        clear();
        a = (randGen.nextInt(11));
        b = (randGen.nextInt(11));
        //answer
        answer = a + b;
        //prompt
        print(a + " + " + b + "\n");
        wait(1.0);
        clear();
        //figuring out the time it took
        beforeTime = System.currentTimeMillis();
        userIn = scan.nextInt();
        scan.nextLine();
        afterTime = System.currentTimeMillis();
        timeTaken = (afterTime - beforeTime);
        timeTakenDub = (double)(timeTaken);
        //this is an anti cheat
        if((timeTakenDub) < 200) {
          print("That was a little too fast...\n");
          print("I'm gonna add an extra 3 seconds\n");
          streak = false;
          totalTime = totalTime + 3000;
        } else {
          //if they got it right
          if(userIn == answer) {
            print("\nGood Job, You Took " + (timeTakenDub / 1000) + " seconds!\n");
            totalTime = totalTime + timeTakenDub;
            count++;
            streak = true;
            System.out.println(count + " right\n");
            
          } else {
            print("\nWrong, you took " + (timeTakenDub / 1000) + " seconds. But! it's x2 since it's wrong(" + ((timeTakenDub / 1000 * 2)) + ") :/\n");
            totalTime = totalTime + (timeTakenDub * 2);
            streak = false;
            
          }
        }
        if(streak == true) {
          streakCount++;
          System.out.println("Streak of " + streakCount);
        } else if(!streak) {
          print("Streak Broken!");
          streakCount = 0;
        }
      }
      wait(2.0);
      clear();
      playerTime[i] = totalTime;
      System.out.println("\nYou Got " + count + " right");
      System.out.println("\nIt took you " + (totalTime / 1000) + " seconds overall\n");
      
      wait(5.0);
      System.out.println("");
      
    }
    for(int i = 0; i < playerSize; i++) {
      if(shortest > playerTime[i]) {
        shortest = playerTime[i];
        whichPlayer = i;
      } else {
        
      }
    }
    clear();
    System.out.println("The winner is " + players.get(whichPlayer).getName() + " with a time of " + (shortest / 1000) + " seconds!\n");
    for(int i = 0; i < playerSize; i++) {
      System.out.println(players.get(i).getName() + ": " + (playerTime[i] / 1000) + " Seconds");
    }
    players.get(whichPlayer).addPoints(2);
    wait(10.0);
	}
}