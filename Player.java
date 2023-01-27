
public class Player {
  private String name;
  private int number;
  private int points = 0;

  //this is a player
  public Player(String name, int number, int points) {
    this.name = name;
    this.number = number;
    this.points = points;
  }

  //getting points
  public int getPoints() {
    return this.points;
  }

  //getting number
  public int getNumber() {
    return this.number;
  }

  //getting name
  public String getName() {
    return this.name;
  }
  
  //adding points
  public void addPoints(int amount) {
    this.points = this.points + amount;
  }

  public void resetPoints() {
    this.points = 0;
  }
}