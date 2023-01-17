import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Wordle extends Minigame {

  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_RESET = "\u001B[0m";

  public String getMinigameName() {
    return ("Wordle");
  }

  public void run(ArrayList<Player> players) {

    // Scanners
    ArrayList<String> dictionary = new ArrayList<String>();
    Random rdm = new Random();
    Scanner userIn = new Scanner(System.in);

    // Loading the words
    Boolean loaded = false;
    while (!loaded) {
      // Trying to load
      try {
        Scanner file = new Scanner(new File("wordle.txt"));
        // Adding to the dictionary
        while (file.hasNextLine()) {
          dictionary.add(file.nextLine());
        }
        // Closing the file
        file.close();
        // Successfully loaded!
        loaded = true;
      } catch (Exception e) {
        // A problem has occurred
        halfPrint("[ERROR] Problem loading dictionary! Retrying");
        wait(0.2);
        halfPrint(".");
        wait(0.2);
        halfPrint(".");
        wait(0.2);
        halfPrint(".");
        clear();
      }

      // The actual game
      int[] guesses = new int[players.size()];
      // Looping through the players
      for (int i = 0; i < players.size(); i++) {

        // Loading a random word + splitting it into an array
        String temp = dictionary.get(rdm.nextInt(dictionary.size()));
        String[] word = new String[5];
        String[] userGuess = new String[5];
        int[] check = new int[5];
        ArrayList<String> pastWords = new ArrayList<String>();
        ArrayList<String> pastChecks = new ArrayList<String>();

        // Game loop
        Boolean foundWord = false;
        while (!foundWord && guesses[i] < 6) {

          // Resetting check array
          for (int j = 0; j < 5; j++) {
            check[j] = 0;
          }

          // Resetting word array
          for (int j = 0; j < 5; j++) {
            word[j] = temp.substring(j, j + 1);
          }

          // Player's guess
          Boolean valid = false;
          String input = "";
          while (!valid) {
            Boolean messageSent = false;
            // Display
            print("Wordle");
            print("");
            print(players.get(i).getName() + "'s turn");
            print("Guesses: " + guesses[i]);
            //printing out past guesses
            for (int j = 0; j < pastWords.size(); j++) {
              String code = pastChecks.get(j);
              for (int k = 0; k < 5; k++) {
                if (code.substring(k, k + 1).equals("0")) { //Printing red
                  halfPrint(ANSI_RED + pastWords.get(j).substring(k, k + 1) + ANSI_RESET);
                } else if (code.substring(k, k + 1).equals("1")) { //Printing yellow
                  halfPrint(ANSI_YELLOW + pastWords.get(j).substring(k, k + 1) + ANSI_RESET);
                } else { //Printin green
                  halfPrint(ANSI_GREEN + pastWords.get(j).substring(k, k + 1) + ANSI_RESET);
                }
              }
               print("");
            }
            print("5 letters. Guess!");
            halfPrint("> ");

            // User Input + Input check
            input = userIn.nextLine();

            if (input.contains("dev")) {
              // Cheatword
              System.out.println(temp);
            } else if (input.length() > 5) {
              // Too long
              print("");
              print("Too long! Please pick a 5 letter word.");
              wait(1.0);
              clear();
              messageSent = true;
            } else if (input.length() < 5) {
              // Too short
              print("");
              print("Too short! Please pick a 5 letter word.");
              wait(1.0);
              clear();
              messageSent = true;
            }
            // Is it a valid word?
            for (int j = 0; j < dictionary.size(); j++) {
              // (TESTING) print(dictionary.get(j));
              if (input.equalsIgnoreCase(dictionary.get(j))) {
                valid = true;
                break;
              }
            }
            if (!valid && !messageSent) {
              // If it's not valid, tell 'em!
              print("");
              print("Please input an ACTUAL WORD!");
              wait(1.0);
              clear();
            }
          }

          // Converting the user's guess into an array
          for (int j = 0; j < input.length(); j++) {
            userGuess[j] = input.substring(j, j + 1);
          }

          //Documenting guess
          pastWords.add(input);

          // green, yellow, red check system
          // Green == 2
          // Yellow == 1
          // Red == 0
          for (int j = 0; j < 5; j++) {
            // Checking for green first
            if (word[j] != null && word[j].equalsIgnoreCase(userGuess[j])) {
              check[j] = 2;
              // Making sure it won't be reused for yellow
              word[j] = null;
            } else {
              // Checking for yellow
              for (int k = 0; k < 5; k++) {
                if (word[k] != null && userGuess[j].equalsIgnoreCase(word[k])) {
                  check[j] = 1;
                  word[k] = null;
                }
              }
            }
          }

          //Documenting check
          String code = "";
          for (int j = 0; j < 5; j++) {
            code = code + check[j];
          }
          pastChecks.add(code);

          // Printing out
          Boolean correct = true;
          for (int j = 0; j < 5; j++) {
            if (check[j] == 2) { // Print green
              halfPrint(ANSI_GREEN + userGuess[j] + ANSI_RESET);
            } else if (check[j] == 1) { // Print yellow + no longer correct
              halfPrint(ANSI_YELLOW + userGuess[j] + ANSI_RESET);
              correct = false;
            } else { // Print red + no longer correct
              halfPrint(ANSI_RED + userGuess[j] + ANSI_RESET);
              correct = false;
            }
          }

          // checking guesses + adding a guess
          if (guesses[i] + 1 == 6) {
            print("");
            print("Ran out of guesses. The word was:");
            print(temp);
            wait(3.0);
          }
          guesses[i] += 1;

          if (correct == true) {
            print("");
            print("CORRECT!");
            wait(3.0);
            foundWord = true;
          }
          
          wait(0.1);
          clear();
        }
      }

      //Giving points
      int highscore = 6;
      for (int i = 0; i < players.size(); i++) {
        if (guesses[i] < highscore) {
          highscore = guesses[i];
        }
      }
      //Winners
      ArrayList<Player> winners = new ArrayList<Player>();
      for (int i = 0; i < players.size(); i++) {
        if (guesses[i] == highscore) {
          winners.add(players.get(i));
        }
      }

      if (winners.size() > 1) {
        //Multiple winners
        print("Wordle");
        print("");
        for (int i = 0; i < winners.size(); i++) {
          halfPrint(winners.get(i).getName() + " [P" + winners.get(i).getNumber() + "]");
          if (i == (winners.size() - 1)) {
            //Last
            //Do nothing
          } else if (i == (winners.size() - 2)) {
            //Second last
            halfPrint(", and ");
          } else {
            //Anything else
            halfPrint(",");
          }
        }
        wait(1.0);
        print("It took them each " + ANSI_GREEN + highscore + ANSI_RESET + " tries to guess the word!");
        wait(1.0);
        print("+1 point each");
        for (int i = 0; i < winners.size(); i++) {
          winners.get(i).addPoints(1);
        }
      } else {
        //Only one winner
        print("Wordle");
        print("");
        print(winners.get(0).getName() + " [P" + winners.get(0).getNumber() + "] IS THE WINNER!");
        wait(1.0);
        print("It took them " + ANSI_GREEN + highscore + ANSI_RESET + " tries to guess the word!");
        wait(1.0);
        print("+2 points");
        winners.get(0).addPoints(2);
      }

      wait(5.0);
      clear();
    }

  }

}