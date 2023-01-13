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
    Random rdm = new Random();
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
      //Getting rid of anything that's null
      for (int i = 0; i < inGameP.size(); i++) {
        Player temp = inGameP.get(i);
        if (temp == null) {
          inGameP.remove(i);
        }
      }
      //Is there only one player left?
      if (inGameP.size() == 1) {
        winnerFound = true;
      }
      
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
        Boolean tie = true;
        while (tie) {
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
            print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
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
              print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
              halfPrint(">");
              answer = userIn.nextInt();
            }
            if (answer == 1) {
              answers[0] = "O"; //Rock
            } else if (answer == 2) {
              answers[0] = "_"; //Paper
            } else {
              answers[0] = "*<"; //Scissors
            }
            clear();
            //Bot's answer
            print("Rock, Paper, Scissors");
            print("");
            print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS bot");
            print("");
            //Player's answer
            print("bot's Turn");
            print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
            print("");
            halfPrint("Bot is thinking");
            
            wait(0.5);
            halfPrint(".");
            wait(0.5);
            halfPrint(".");
            wait(0.5);
            halfPrint(".");
            wait(0.5);
            answer = rdm.nextInt(3) + 1;
            if (answer == 1) {
              answers[1] = "O"; //Rock
            } else if (answer == 2) {
              answers[1] = "_"; //Paper
            } else {
              answers[1] = ">8"; //Scissors
            }
            clear();
            //Animation
            for (int j = 0; j < 5; j++) {
              print("Rock, Paper, Scissors");
              print("");
              print(o1.getName() + "\t\t\tbot");
              print("");
              if (j % 2 != 0) {
                print("");
                print("O\t\t\tO");
              } else {
                print("O\t\t\tO");
                print("");
              }
              wait(0.5);
              clear();
            }
            print("Rock, Paper, Scissors");
            print("");
            print(o1.getName() + "\t\t\tbot");
            print("");
            print("");
            print(answers[0] + "\t\t\t" + answers[1]);
            wait(1.5);
            if (answers[0].equals(answers[1])) {
              //If tied
              print("TIE! Go again.");
              wait(1.0);
              //Restarts
            } else if ((answers[0].equals("O") && answers[1].equals(">8")) || (answers[0].equals("8<") && answers[1].equals("_")) || (answers[0].equals("_") && answers[1].equals("O"))) {
              //If O1 wins
              print(o1.getName() + " WINS!");
              wait(1.0);
              tie = false;
            } else {
              //If bot wins
              print("bot WINS!");
              wait(1.0);
              inGameP.set(Integer.parseInt(opponents[0]), null);
              tie = false;
            }
            wait(1.0);
          } else {
            //If there is no bot
            Player o1 = inGameP.get(Integer.parseInt(opponents[0]));
            Player o2 = inGameP.get(Integer.parseInt(opponents[1]));

            //Display
            clear();
            print("Rock, Paper, Scissors");
            print("");
            print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS " + o2.getName() + " [P" + o2.getNumber() + "]");
            print("");
            //O1's answer
            print(o1.getName() + "'s Turn");
            print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
            print("");
            print("[Don't let the other player see your answer!]");
            halfPrint(">");
            int answer = userIn.nextInt();
            while (answer != 1 && answer != 2 && answer != 3) {
              clear();
              print("Rock, Paper, Scissors");
              print("");
              print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS bot" + o2.getName() + " [P" + o2.getNumber() + "]");
              print("");
              //Player's answer
              print(o1.getName() + "'s Turn");
              print("INVALID ANSWER!");
              print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
              print("");
              print("[Don't let the other player see your answer!]");
              halfPrint(">");
              answer = userIn.nextInt();
            }
            if (answer == 1) {
              answers[0] = "O"; //Rock
            } else if (answer == 2) {
              answers[0] = "_"; //Paper
            } else {
              answers[0] = "8<"; //Scissors
            }
            clear();
            //O2's answer
            print("Rock, Paper, Scissors");
            print("");
            print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS "+ o2.getName() + " [P" + o2.getNumber() + "]");
            print("");
            print(o2.getName() + "'s Turn");
            print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
            print("");
            print("[Don't let the other player see your answer!]");
            halfPrint(">");
            answer = userIn.nextInt();
            while (answer != 1 && answer != 2 && answer != 3) {
              clear();
              print("Rock, Paper, Scissors");
              print("");
              print("\t\t" + o1.getName() + " [P" + o1.getNumber() + "] VS bot");
              print("");
              //Player's answer
              print(o1.getName() + "'s Turn");
              print("INVALID ANSWER!");
              print("Rock (1),\tPaper (2),\tor\tScissors (3)?");
              print("");
              print("[Don't let the other player see your answer!");
              halfPrint(">");
              answer = userIn.nextInt();
            }
            if (answer == 1) {
              answers[1] = "O"; //Rock
            } else if (answer == 2) {
              answers[1] = "_"; //Paper
            } else {
              answers[1] = ">8"; //Scissors
            }
            clear();
            //Animation
            for (int j = 0; j < 5; j++) {
              print("Rock, Paper, Scissors");
              print("");
              print(o1.getName() + "\t\t\t" + o2.getName());
              print("");
              if (j % 2 != 0) {
                print("");
                print("O\t\t\tO");
              } else {
                print("O\t\t\tO");
                print("");
              }
              wait(0.5);
              clear();
            }
            print("Rock, Paper, Scissors");
            print("");
            print(o1.getName() + "\t\t\t" + o2.getName());
            print("");
            print("");
            print(answers[0] + "\t\t\t" + answers[1]);
            wait(1.5);
            if (answers[0].equals(answers[1])) {
              //If tied
              print("TIE! Go again.");
              wait(2.0);
              //Restarts
            } else if ((answers[0].equals("O") && answers[1].equals(">8")) || (answers[0].equals("8<") && answers[1].equals("_")) || (answers[0].equals("_") && answers[1].equals("O"))) {
              //If O1 wins
              print(o1.getName() + " WINS!");
              wait(2.0);
              inGameP.set(Integer.parseInt(opponents[1]), null);
              tie = false;
            } else {
              //If bot wins
              print(o2.getName() +" WINS!");
              wait(2.0);
              inGameP.set(Integer.parseInt(opponents[0]), null);
              tie = false;
            }
          }
        }
      }
      for (int i = 0; i < inGameP.size(); i++) {
        Player temp = inGameP.get(i);
        if (temp == null) {
          inGameP.remove(i);
        }
      }
      if (inGameP.size() == 1) {
        winnerFound = true;
      }
      wait(1.0);
    }

    clear();
    print("Rock, Paper, Scissors");
    print("");
    print(inGameP.get(0).getName() + " IS THE WINNER!");
    wait(1.0);
    print("+2 points");
    inGameP.get(0).addPoints(2);
    wait(1.0);
    print("They now have " + inGameP.get(0).getPoints() + " points");
    wait(3.0);
    
	}
}