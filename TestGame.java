import java.util.ArrayList;
import java.util.Scanner;

public class TestGame extends Minigame {

  public String getMinigameName() {
    return ("Test");
  }

	public void run(ArrayList<Player> players) {
    clear();
		int amountOfP = players.size();
    for (int i = 0; i < amountOfP; i++) {
      print(players.get(i).getName());
    }
    players.get(0).addPoints(2);
    wait(1.0);
	}
}