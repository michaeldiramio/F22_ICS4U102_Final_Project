import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Random;

public class Trivia extends Minigame {

  public String getMinigameName(){
    return ("Trivia");
  }

  public void run(ArrayList<Player> players) {
    int amountOfP = players.size();
    for(int i = 0; i < amountOfP; i++) {
    Random randGen = new Random();
    Scanner userIn = new Scanner(System.in);
    int qSize = 0;
    int aSize = 0;
    //file input stuffs
    try {
      Scanner questions = new Scanner(new File("triviaquestions.txt"));
      Scanner answers = new Scanner(new File("triviaanswers.txt"));
      //get answer file size
      while(answers.hasNextLine()){
        answers.nextLine();
        aSize++;
      }
      while(questions.hasNextLine()){
        questions.nextLine();
        qSize++;
      }

      System.out.println("Player " + (i+1) + "! [" + players.get(i).getName() + "] It's your turn!");
      System.out.println();
      wait(3.0);
      
      print("Welcome to the Trivia Game!");
      wait(1.5);
      print("Please input the answer to the upcoming question, if it's correct, you get two points!");
      wait(4.0);
      clear();

      //randomize the questions
      
      ArrayList<String> qData = new ArrayList<>(Files.readAllLines(Paths.get("triviaquestions.txt")));

      ArrayList<String> aData = new ArrayList<>(Files.readAllLines(Paths.get("triviaanswers.txt")));
      
      questions.close();
      answers.close();

      int qChoose = randGen.nextInt(qSize);
      String qPrint = qData.get(qChoose);
      String aPrint = aData.get(qChoose);

      //print the stuff
    
      print("Answer this question!");
      System.out.println("[MUST HAVE CORRECT SPELLING]");
      wait(2.0);
      print(qPrint);
      //user input for question
      String input = userIn.nextLine();
    
      if(input.equalsIgnoreCase(aPrint)){
        System.out.println("You got the right answer! You get two points!");
        players.get(i).addPoints(2);
        wait(4.0);
        } else {
        System.out.println("You unfortunately got the wrong answer.");
        wait(4.0);
        }

      clear();

      } catch(Exception e){
        e.printStackTrace();
        wait(55.0);
      }
    }
  } 
}