import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
  private ArrayList<Player> players = new ArrayList<Player>();
  private Minigame[] minigames = new Minigame[2];
  private Scanner userIn;
  private Random rdm = new Random();

   public void clear() {
    //Clears the console
    System.out.print("\033[H\033[2J"); 
    System.out.flush();
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

  public void wait(double amount) {
    //For a custom pause
    double miliseconds = amount * 1000;
    int intify = (int) Math.round(miliseconds);
    try {
      Thread.sleep(intify);
    } catch(Exception e) {
    };
  }
  
  public void run() {
    //----------------------
    //SPECIFY MINIGAMES HERE!

    minigames[0] = new RockPaperScissors();
    minigames[1] = new Minesweeper();
  
    //----------------------
    
    int amountOfP; //Amount of players
    int amountOfR; //Amount of rounds
    this.userIn = new Scanner(System.in);
    String response;
    
    //Creating players
    clear();
    print("");
    print("\t\tWelcome to Minigame Mania!");
    print("");
    print("How many players are there? (Recommended: 4-10)");
    halfPrint("> ");
    response = userIn.nextLine();
    amountOfP = Integer.parseInt(response);
    if (amountOfP <= 1) {
      print("Invalid amount!");
      wait(1.0);
      while (amountOfP <= 1) {
        clear();
        print("");
        print("\t\tWelcome to Minigame Mania!");
        print("");
        print("How many players are there? (Recommended: 4-10)");
        halfPrint("> ");
        response = userIn.nextLine();
        amountOfP = Integer.parseInt(response);
      }
    }
    clear();
    for (int i = 0; i < amountOfP; i++) {
      print("\t\tWelcome to Minigame Mania!");
      print("");
      print("P" + (i + 1) + " name:");
      halfPrint("> ");
      String name = userIn.nextLine();
      Player temp = new Player(name, (i + 1), 0);
      players.add(temp);
      clear();
    }

    //Generating rounds
    print("");
    print("\t\tWelcome to Minigame Mania!");
    print("");
    print("How many rounds do you want to play? (Max: " + minigames.length + ")");
    halfPrint("> ");
    response = userIn.nextLine();
    amountOfR = Integer.parseInt(response);
    //Making sure there's enough minigames for the rounds
    if (amountOfR > minigames.length || amountOfR <= 0) {
      print("Invalid amount!");
      wait(1.0);
      while (amountOfR > minigames.length || amountOfR <= 0) {
        clear();
        print("");
        print("\t\tWelcome to Minigame Mania!");
        print("");
        print("How many rounds do you want to play? (Max: " + minigames.length + ")");
        halfPrint("> ");
        response = userIn.nextLine();
        amountOfR = Integer.parseInt(response);
      }
    }
   
    clear();
    wait(1.5);

    //Printing players
    for (int i = 0; i < amountOfP; i++) {
      halfPrint(players.get(i).getName() + " [P" + (i + 1) + "]");
      if (i != amountOfP - 2) {
        halfPrint(", ");
      } else {
        halfPrint(", and ");
      }
    }
    print("");
    wait(1.5);
    print(amountOfR + " rounds");
    wait(1.5);
    print("BEGIN!");
    wait(1.5);
    clear();

    //Actual game loop
    int roundNum = 0;
    while (roundNum < amountOfR) {
      roundNum = roundNum + 1;
      //Choosing a random minigame
      int random = rdm.nextInt(minigames.length);
      //Making sure it's a valid number
      while (minigames[random] == null) {
        random = rdm.nextInt(minigames.length);
      }
      //Announcing the game
      print("Minigame chosen: " + minigames[random].getMinigameName());
      wait(1.5);
      clear();
      //Running the game
      minigames[random].run(players);
    }

    //Finding the winner
    Player[] winner = new Player[1];
    for (int i = 0; i < amountOfP; i++) {
      if (i == 0) {
        winner[0] = players.get(i);
      } else {
        if (players.get(i).getPoints() > winner[0].getPoints()) {
          winner[0] = players.get(i);
        }
      }
    }
    clear();
    wait(0.5);
    halfPrint("The winner is.");
    wait(0.5);
    halfPrint(" .");
    wait(0.5);
    halfPrint(" .");
    wait(0.5);
    print("");
    print("");
    print("\t" + winner[0].getName() + " [P" + winner[0].getNumber() + "], WITH " + winner[0].getPoints() + " POINTS!");
    wait(2.0);
  }
  
	public static void main(String[] args) {
	  Main m = new Main();
    m.run();
	}
	
	
}