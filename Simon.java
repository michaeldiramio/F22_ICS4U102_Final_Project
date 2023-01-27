import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Simon extends Minigame {

  // Will need this scanner
  private Scanner userIn = new Scanner(System.in);

  private Random randGen = new Random();

  // The "fR" stands for "File Reader"...I think
  private Scanner fR;

  // Use this to bring the prompts into play
  private String prompt;
  private ArrayList <String> prompts = new ArrayList <>();

  // Replace test game here to acquire the proper "welcome" screen
  public String getMinigameName() {
    return ("Simon Says");
  }

	public void run(ArrayList<Player> players) {

    // Main menu, sort of
    System.out.println("Welcome to Simon Says!");
    System.out.println();
    System.out.println("Here's how you play:");
    System.out.println("- A prompt will appear on the screen, and you will have five seconds to look at it. Then it will disappear, and you will have to type it on the screen yourself. If you don't, you will be eliminated. You can ignore upper case and lower case; just type the prompt!");
    System.out.println("- Players take their turn one at a time, until they make a mistake. Every time they get a prompt correct, they earn a point.");
    System.out.println("- Make sure you type 'Simon says' when it is your turn to type the prompt. If you type something that Simon didn't authorize, you're out. If you remember to add the 'Simon says' part of the prompt, you won't be eliminated.");
    System.out.println("- One last thing: your answer will only be counted if you press 'Enter' after completing it.");
    System.out.println("- The player with the most points at the end wins! Good luck!");
    System.out.println();
    System.out.println("Type 'Go' here to start the game.");
    
    // This starts the game
    boolean gameOn = false;
    boolean valid = true;
    while (valid == true) {
      String ready = userIn.nextLine();
      if (ready.equalsIgnoreCase ("Go")) {
        gameOn = true;
        valid = false;
      } else {
        System.out.println("You just need to type 'Go'.");
      }
    }
    
    try {
        // Now I'm sure it stands for "File Reader"
        fR = new Scanner(new File ("SimonSays.txt"));
        int count = 1;
  
        // Need an array list for this part:
        while (fR.hasNextLine()) {
          String phrase = fR.nextLine();
          prompts.add(phrase);
        }
        
        System.out.println(prompts.get(3));
        
        clear();
        
        while(fR.hasNextLine()) {
          prompts.add(fR.nextLine());
        }
        clear();

        
      } catch (Exception e) {
        print("error has occured");
        e.printStackTrace();
        wait(60.0);
        wait(1.0);
      }

    for (int i = 0; i < players.size(); i++) {
      gameOn = true;
      double waitTime = 5.0;
       while (gameOn == true) {

        // First things first: clear the console
        clear();
        
        // ----------STEP ONE: PLAYERS----------
          System.out.println(players.get(i).getName() + "'s turn!");
         
          // ----------STEP TWO: PROMPTS----------
          // Actually, we start by implementing this so everything else can work
          
          // The prompt itself! (And how long you have to read it)
          int rand = (randGen.nextInt(prompts.size()));
          prompt = prompts.get(rand);
          System.out.println("Simon says: " + prompt);
          wait (waitTime);
          clear();
      
          // Type the next line
          String repeat = userIn.nextLine();
  
          // ----------STEP THREE: ELIMINATION----------
        
          // Success?
          if (repeat.equalsIgnoreCase ("Simon says: " + prompt)) {
            // Success!
            players.get(i).addPoints(1);
            System.out.println("Nice!");
            if (waitTime != 0.6) {
              waitTime = waitTime - 0.4;
            }
            wait(1.0);
            clear();
          } else {
            // If you don't get it right, this happens:
            System.out.println("Sorry! That's not it.");
            wait(2.0);
            gameOn = false;
          }
    
          fR.close();
          wait(1.0);
        
        
        // ----------STEP FOUR: VICTORY----------

        /*
          to add points to a player just do:
          players.get(insertNumber).addPoints(1);
        */
      }
    }    
    // clear();
		// int amountOfP = players.size();
    // for (int i = 0; i < amountOfP; i++) {
      // print(players.get(i).getName());
    // }
    // players.get(0).addPoints(2);
    // wait(1.0);
	}
}



