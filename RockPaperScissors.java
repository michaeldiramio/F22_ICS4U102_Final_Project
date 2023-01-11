import java.util.ArrayList;
import java.util.Scanner;

public class RockPaperScissors extends Minigame {

  public String getMinigameName() {
    return ("Rock, Paper, Scissors");
  }

	public void run(ArrayList<Player> players) {
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
        /*for (int i = 0; i < (inGameP.size() - 1); i = i + 2) {
          int count = 0;
          for (int j = i; j < (i + 2); j++) {
            if (count == 0) {
              teams[i] = j + ",";
              count++;
            } else {
              teams[i] = teams[i] + j;
            }
          }
        }
        */
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
        /*for (int i = 0; i < (inGameP.size() - 1); i = i + 2) {
          int count = 0;
          for (int j = i; j < (i + 2); j++) {
            if (count == 0) {
              teams[i] = j + ",";
              count++;
            } else {
              teams[i] = teams[i] + j;
            }
          }
        }*/
      }
      //Actual game
      for (int i = 0; i < teams.length; i++) {
        String opponentOne;
        String opponentTwo;
      }
      wait(5.0);
    }
    
	}
}