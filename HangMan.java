import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class HangMan extends Minigame {

  private Scanner scan = new Scanner(System.in);
  private Random randGen = new Random();
  private int playerSize;
  private boolean gameLoop;
  private int[] hangCzarArray = new int[10];
  private int czar;
  private String word;
  private int guesses;
  private boolean round;
  private boolean valid;
  private String guess;
  private boolean validRo;
  private String[] wordArr;
  private int[] wordArrCheck;
  private int wordCheck;
  private boolean teamWin;
  private boolean czarWin;

  //name of the game
  public String getMinigameName() {
    return ("Hangman");
  }

  //running the code
  public void run(ArrayList<Player> players) {
    //resetting booleans and playersize
    playerSize = players.size();
    teamWin = false;
    czarWin = false;
    gameLoop = true;
    while(gameLoop == true) {
      guesses = 6;
      for(int i = 0; i < hangCzarArray.length; i++) {
        hangCzarArray[i] = 0;
      }
      //choosing the czar
      czar = (randGen.nextInt(playerSize));
      hangCzarArray[czar] = 1;
      for(int i = 0; i < playerSize; i++) {
        
      }
      //prompts
      clear();
      print("HangMan!\n");
      print("Maximun characters: 10\n");
      print("Please only lower case?");
      wait(3.0);
      clear();
      print("The czar this round is " + players.get(czar).getName());
      wait(3.0);
      //getting the word
      print( "\n" + players.get(czar).getName() + ", input a word now");
      valid = true;
      //validator for inputting the czar word
      while(valid == true) {
        word = scan.nextLine();
        if(word.length() == 0) {
          print("\nNo characters? Try again\n");
        } else if(word.length() > 10) {
          print("\nToo long, try again\n");
        } else {
          print("Banger\n");
          wordArr = new String[word.length()];
          wordArrCheck = new int[wordArr.length];
          wordCheck = 0;
          //this makes the array for the word, and changes them into underscores for now
          for(int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i+1);
            if(letter.equalsIgnoreCase(" ")) {
              wordArr[i] = " ";
            } else {
              wordArr[i] = "_";
            }
          }
          valid = false;
        }
      }
  
      clear();
      //Starts the round
      round = true;
      while(round == true) {
        //resets the checking array
        for(int i = 0; i < wordArrCheck.length; i++) {
          wordArrCheck[i] = 0;
        }
        wordCheck = 0;
        //prompts
        clear();
        print("You have " + guesses + " wrong answers left\n");
        print("\nWord(s): ");
        for(int i = 0; i < wordArr.length; i++) {
            System.out.print(wordArr[i]);
        }
        wait(1.0);
        print("\n\nwrite a letter");
        validRo = true;
        while(validRo == true) {
          //validator, checks if the guess was correct
          guess = scan.nextLine();
          if(guess.length() == 0) {
            print("\nPlease write a letter\n");
          } else if(guess.length() > 1) {
            print("\nOnly one letter!\n" );
          } else {
            if(word.contains(guess)) {
              for(int i = 0; i < wordArr.length; i++) {
                String letter = word.substring(i, i+1);
                if(letter.equalsIgnoreCase(guess)) {
                  wordArr[i] = guess;
                }
              }
              print("\nCorrect\n");
              validRo = false;
            } else {
              print("\nNope\n");
              guesses--;
              validRo = false;
            }
            
          }
        }
        //checks to see if the word has been completed
        for(int i = 0; i < wordArr.length; i++) {
          if(wordArr[i].equalsIgnoreCase("_")) {
            wordArrCheck[i] = 0;
          } else {
            wordArrCheck[i] = 1;
          }
        }
        for(int i = 0; i < wordArrCheck.length; i++) {
          wordCheck = wordCheck + wordArrCheck[i];
        }
        if(wordCheck == wordArrCheck.length) {
          teamWin = true;
        } else {
          teamWin = false;
        }
        //the guessing team won
        if(teamWin == true) {
          print("You guessed the phrase " + word + " correctly");
          wait(3.0);
          teamWin = true;
          round = false;
        } else {
          
        }
        //the czar won
        wait(2.0);
        if(guesses == 0) {
          print("Wrong, the phrase was " + word);
          wait(3.0);
          czarWin = true;
          round = false;
        }
        
      }
      //This gives points to the guessing team if they win
      if(teamWin == true) {
        clear();
        print("\nThe guessing team won:\n");
        for(int i = 0; i < hangCzarArray.length; i++) {
          if(i == 0) {
            System.out.println("- " + players.get(i).getName());
            players.get(i).addPoints(2);
          } else {
            
          }
        }
        wait(5.0);
        gameLoop = false;
        //gives point to the czar if they win
      } else if(czarWin == true) {
        clear();
        print("\nThe czar won:\n");
        for(int i = 0; i < hangCzarArray.length; i++) {
          if(i == 1) {
            System.out.println(players.get(i).getName());
            players.get(i).addPoints(2);
          } else {
            
          }
        }
        wait(5.0);
        gameLoop = false;
      }

      
      
    }

    

    
    

  }


  
}