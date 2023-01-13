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

    //Scanners
    ArrayList<String> dictionary = new ArrayList<String>();
    Random rdm = new Random();
    Scanner userIn = new Scanner(System.in);

    //Loading the words
    Boolean loaded = false;
    while (!loaded) {
      //Trying to load
      try {
        Scanner file = new Scanner(new File("wordle.txt"));
        //Adding to the dictionary
        while(file.hasNextLine()){
          dictionary.add(file.nextLine());
        }
        //Closing the file
        file.close();
        //Successfully loaded!
        loaded = true;
      } catch (Exception e) {
        //A problem has occurred
        halfPrint("[ERROR] Problem loading dictionary! Retrying");
        wait(0.2);
        halfPrint(".");
        wait(0.2);
        halfPrint(".");
        wait(0.2);
        halfPrint(".");
        clear();
      }

      //The actual game
      int[] guesses = new int[players.size()];
      //Looping through the players
      for (int i = 0; i < players.size(); i++) {
        
        //Loading a random word + splitting it into an array
        String temp = dictionary.get(rdm.nextInt(dictionary.size()));
        String[] word = new String[5];
        for (int j = 0; j < 5; j++) {
          word[j] = temp.substring(j, j + 1);
        }

        //Game loop
        Boolean foundWord = false;
        while (!foundWord && guesses[i] != 6) {

          //Player's guess
          Boolean valid = false;
          String input;
          while (!valid) {
            //Display
            print("Wordle");
            print("");
            print(players.get(i).getName() + "'s turn");
            print("");
            print("5 letters. Guess!");
            halfPrint(">");
            
            //User Input + Input check
            input = userIn.nextLine();

            if(input.contains("dev")){
              //Cheatword
              System.out.println(temp);
            }else if (input.length() > 5) {
              //Too long
              print("");
              print("Too long! Please pick a 5 letter word.");
              wait(1.0);
              clear();
            } else if (input.length() < 5) {
              //Too short
              print("");
              print("Too short! Please pick a 5 letter word.");
              wait(1.0);
              clear();
            }
            //Is it a valid word?
            for(int j = 0; j < dictionary.size(); j++){
              if(input.contains(dictionary.get(i))){
                valid = true;
              }
            }
            if(!valid){
              //If it's not valid, tell 'em!
              print("");
              print("Please input an ACTUAL WORD!");
              wait(1.0);
              clear();
            }
          }

          //Make the green, yellow, red system
          
          wait(1.0);
        }
      }
      
    }
    

    

  }
    
}