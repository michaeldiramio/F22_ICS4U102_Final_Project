import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Wheel extends Minigame {

  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_RESET = "\u001B[0m";

  public String getMinigameName() {
    return ("Wheel Of Fortune");
  }

  public void run(ArrayList<Player> players) {

    // Scanners
    String[] piece = new String[8];
    Random rdm = new Random();
    Scanner userIn = new Scanner(System.in);

    //Defining the wheel
    piece[0] = "Good";
    piece[1] = "Bad";
    piece[2] = "Neutral";
    piece[3] = "Very Good";
    piece[4] = "Very Bad";
    piece[5] = "Neutral";
    piece[6] = "Good";
    piece[7] = "Bad";
    
    for (int i = 0; i < players.size(); i++) {
      //How long will it spin?
      int spin =  rdm.nextInt(16) + 2;
      //Setting the stats
      int a = 0;
      int b = 1;
      int c = 2;
      int d = 3;
      int e = 4;
      int f = 5;
      int g = 6;
      int h = 7;

      //Printing
      print("Wheel Of Fortune");
      print(players.get(i).getName() + "'s turn'");
      print("\t\t\t\tVVV");
      print("\t\t\t\t" + piece[a]);
      print("");
      print("\t\t" + piece[b] + "\t\t\t\t" + piece[c]);
      print("");
      print(piece[d] + "\t\t\t\t\t\t\t" + piece[e]);
      print("");
      print("\t\t" + piece[f] + "\t\t\t\t" + piece[g]);
      print("");
      print("\t\t\t\t" + piece[g]);
      print("");
      print("Type 'spin' to spin the wheel");
      halfPrint("> ");
      //Player input
      String prompt = userIn.nextLine();
      while(!prompt.equalsIgnoreCase("spin")) {
        print("Type 'spin'");
        halfPrint("> ");
        prompt = userIn.nextLine();
      }
      clear();

      //SPINNING THE WHEEL!
      for (int j = 0; j < spin; j++) {
        print("Wheel Of Fortune");
        print(players.get(i).getName() + "'s turn'");
        print("\t\t\t\tVVV");
        if (a == 7) {
          a = 0;
        } else {
          a = a + 1;
        }
        if (b == 7) {
          b = 0;
        } else {
          b = b + 1;
        }
        if (c == 7) {
          c = 0;
        } else {
          c = c + 1;
        }
        if (d == 7) {
          d = 0;
        } else {
          d = d + 1;
        }
        if (e == 7) {
          e = 0;
        } else {
          e = e + 1;
        }
        if (f == 7) {
          f = 0;
        } else {
          f = f + 1;
        }
        if (g == 7) {
          g = 0;
        } else {
          g = g + 1;
        }
        if (h == 7) {
          h = 0;
        } else {
          h = h + 1;
        }
        print("\t\t\t\t" + piece[a]);
        print("");
        print("\t\t" + piece[b] + "\t\t\t\t" + piece[c]);
        print("");
        print(piece[d] + "\t\t\t\t\t\t\t" + piece[e]);
        print("");
        print("\t\t" + piece[f] + "\t\t\t\t" + piece[g]);
        print("");
        print("\t\t\t\t" + piece[g]);
        wait(1.0);
        clear();
      }
      //Printing
      print("Wheel Of Fortune");
      print(players.get(i).getName() + "'s turn'");
      print("\t\t\t\tVVV");
      print("\t\t\t\t" + piece[a]);
      print("");
      print("\t\t" + piece[b] + "\t\t\t\t" + piece[c]);
      print("");
      print(piece[d] + "\t\t\t\t\t\t\t\t" + piece[e]);
      print("");
      print("\t\t" + piece[f] + "\t\t\t\t" + piece[g]);
      print("");
      print("\t\t\t\t" + piece[g]);
      print("");
      print("Your prize is..." + piece[a] + "!");

      //Calculating Points
      int points = 0;
      if (piece[a].equals("Good")) {
        points = rdm.nextInt(3) + 1;
      } else if (piece[a].equals("Very Good")) {
        points = rdm.nextInt(3) + 3;
      } else if (piece[a].equals("Bad")) {
        points = rdm.nextInt(3) + 1;
        points = points * -1;
      } else if (piece[a].equals("Very Bad")) {
        points = rdm.nextInt(3) + 3;
        points = points * -1;
      } else if (piece[a].equals("Neutral")) {
        points = 0;
      }
      wait(1.0);
      if (points < 0) {
        halfPrint(players.get(i).getName() + " lost " + (points * -1) + " point");
        if (points == -1) {
          print("!");
        } else {
          print("s!");
        }
      } else {
        halfPrint(players.get(i).getName() + " got " + (points * -1) + " point");
        if (points == 1) {
          print("!");
        } else {
          print("s!");
        }
      }
      print("New points: " + points);
      players.get(i).addPoints(points);
      wait(3.0);
      clear();
    }
  }

}