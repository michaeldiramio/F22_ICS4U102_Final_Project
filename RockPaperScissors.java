import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors extends Minigame {

  public String getMinigameName() {
    return ("Rock, Paper, Scissors");
  }

	public void run(ArrayList<Player> players) {
    //Scanner
    Scanner userIn = new Scanner(System.in);
    //Putting the players into a group
    ArrayList<Player> inGameP = new ArrayList<Player>();
    for (int i = 0; i < players.size(); i++) {
      inGameP.add(players.get(i));
    }

    //Beginning the game round
    Boolean winnerFound = false;
    while (!winnerFound) {
      //Splitting into teams
      String[] teams;
      
      if (inGameP.size() % 2 != 0) {
        
        //Bot will be needed
        teams = new String[(inGameP.size() + 1) / 2];
        /*Teams will look like: T1: [P1,P2] T2: [P3,Bot]
          Then they will be split up by the comma*/
        int count = 0;
        for (int i = 0; i < teams.length; i = i + 1) {
          teams[i] = count + "," + (count + 1);
          count = count + 2;
        }
        teams[(teams.length - 1)] = Integer.toString((inGameP.size() - 1)) + ",bot";
        
      } else {
        
        //Bot will not be needed
        teams = new String[inGameP.size() / 2];
        /*Teams will look like: T1: [P1,P2] T2: [P3,P4]
          Then they will be split up by the comma*/
        int count = 0;
        for (int i = 0; i < teams.length; i++) {
          teams[i] = count + "," + (count + 1);
          count = count + 2;
        }
        
      }
      
      //Actual game
      for (int i = 0; i < teams.length; i++) {
        //Opponent 1 and 2
        String[] opponents = teams[i].split(",");
        String[] answers = new String[2];
        //If bot is needed
        if (opponents[1].equals("bot")) {
          Player o1 = inGameP.get(Integer.parseInt(opponents[0]));
          String o2 = "bot";

          //Display
          clear();
          print("Rock, Paper, Scissors");
          print("");
          print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS bot");
          print("");
          //Player's answer
          print(o1.getName() + "'s Turn");
          print("Rock (1), Paper (2), or Scissors (3)?");
          halfPrint(">");
          int answer = userIn.nextInt();
          while (answer != 1 && answer != 2 && answer != 3) {
            clear();
            print("Rock, Paper, Scissors");
            print("");
            print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS bot");
            print("");
            //Player's answer
            print(o1.getName() + "'s Turn");
            print("INVALID ANSWER!");
            print("Rock (1), Paper (2), or Scissors (3)?");
            halfPrint(">");
            answer = userIn.nextInt();
          }
          if (answer == 1) {
            answer[0] = "Rock O";
          } else if (answer == 2) {
            answer[0] = "Paper _";
          } else {
            answer[0] = "Scissors 8<";
          }
          //Bot's answer
            print("Rock, Paper, Scissors");
            print("");
            print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS bot");
            print("");
            //Player's answer
            print("bot's Turn");
            print("Rock (1), Paper (2), or Scissors (3)?");
            print("");
            halfPrint("Bot is thinking");
            
            wait(1.0);
            halfPrint(".");
            wait(1.0);
            halfPrint(".");
            wait(1.0);
            halfPrint(".");
            wait(1.0);
            clear();
          
          
        } else {
          Player o1 = inGameP.get(Integer.parseInt(opponents[0]));
          Player o2 = inGameP.get(Integer.parseInt(opponents[1]));
        }
        
        
        
        
      }
      wait(5.0);
    }
    
	}
}