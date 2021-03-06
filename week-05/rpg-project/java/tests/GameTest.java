import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
  @Test
  void testWhoIsThere() {
    Game.characters = new ArrayList<>();
    Game.characters.add(new Hero());
    Game.characters.add(new Skeleton(new Point(4, 3)));
    Game.characters.add(new Skeleton(new Point(5, 5)));
    Point testPoint = new Point(3, 4);
    assertEquals(Game.whoIsThere(testPoint).actualPosition, Game.characters.get(1).actualPosition);
  }
}