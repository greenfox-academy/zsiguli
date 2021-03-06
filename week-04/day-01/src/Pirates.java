import java.util.ArrayList;
import java.util.List;

public class Pirates {
  static int birthCounter;
  static List<Pirates> alreadyBornPirates = new ArrayList<>();

  String name;
  int rumCounter;
  int id;
  boolean alive;

  public Pirates(String name) {
    this.id = birthCounter;
    rumCounter = 0;
    alive = true;

    this.name = name;

    alreadyBornPirates.add(this);
    birthCounter++;
  }

  public Pirates() {
    this.id = birthCounter;
    rumCounter = 0;
    alive = true;

    this.name = "Jack";

    alreadyBornPirates.add(this);
    birthCounter++;
  }

  public void drinkMoreRum(int rum) {
    if (alive) {
      rumCounter = rumCounter + rum;
    } else {
      System.out.println("he's dead");
    }
  }

  public void drinkOneRum() {
    if (alive) {
      rumCounter++;
    } else {
      System.out.println("he's dead");
    }
  }

  public static void everyOneDrinksARum() {
    for (Pirates pirate : alreadyBornPirates) {
      if (pirate.alive) {
        pirate.rumCounter++;
      } else {
        System.out.println("he's dead");
      }
    }
  }

  public void howsItGoingMate() {
    if (alive) {
      if (rumCounter < 5) {
        System.out.println("Pour me anudder!");
      } else {
        System.out.println("Arghh, I'ma Pirate. How d'ya d'ink its goin?");
        rumCounter = 0;
      }
    } else {
      System.out.println("he's dead");
    }
  }

  public void die() {
    alive = false;
  }

  public void brawl(Pirates opponent) {
    if (alive && opponent.alive) {
      int winner = (int) (Math.random() * 3);
      System.out.println(winner);
      switch (winner) {
        case 0:
          rumCounter = 0;
          opponent.rumCounter = 0;
          break;
        case 1:
          opponent.die();
          break;
        case 2:
          die();
          break;
      }
    } else {
      System.out.println("oops someone is dead");
    }
  }
}
