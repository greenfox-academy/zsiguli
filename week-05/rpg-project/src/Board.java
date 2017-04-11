import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {
  static final int WIDTH_IN_SQUARES = 15;
  static final int HEIGHT_IN_SQUARES = 8;
  static final int DIMENSION = 72;
  static final int[][] MAP = {
          {0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1},
          {0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
          {1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
          {1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
          {0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
          {0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0},
          {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0},
          {0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0}
  };

  Character hero1 = new Hero();
  int[] temp1 = getRandomPosition();
  Character skeleton1 = new Skeleton(0, temp1[0] * DIMENSION, temp1[1] * DIMENSION);
  int[] temp2 = getRandomPosition();
  Character skeleton2 = new Skeleton(0, temp2[0] * DIMENSION, temp2[1] * DIMENSION);
  int[] temp3 = getRandomPosition();
  Character skeleton3 = new Skeleton(0, temp3[0] * DIMENSION, temp3[1] * DIMENSION);

  int heroX;
  int heroY;

  public Board() {
    heroX = 0;
    heroY = 0;

    setPreferredSize(new Dimension(WIDTH_IN_SQUARES * DIMENSION, HEIGHT_IN_SQUARES * DIMENSION));
    setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    renderMap(graphics);
    renderSkeleton(graphics, (Skeleton) skeleton1);
    renderSkeleton(graphics, (Skeleton) skeleton2);
    renderSkeleton(graphics, (Skeleton) skeleton3);
    renderHero(graphics);
  }

  public static void boardMain() {
    JFrame frame = new JFrame("RPG Game");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    frame.addKeyListener(board);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if (heroY > 0) {
        if (MAP[hero1.actualPositionY - 1][hero1.actualPositionX] != 1) {
          heroY -= DIMENSION;
          --hero1.actualPositionY;
        }
      }
      hero1.setOrientation("hero-up");
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if (heroY < (HEIGHT_IN_SQUARES * DIMENSION) - DIMENSION) {
        if (MAP[hero1.actualPositionY + 1][hero1.actualPositionX] != 1) {
          heroY += DIMENSION;
          ++hero1.actualPositionY;
        }
      }
      hero1.setOrientation("hero-down");
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (MAP[hero1.actualPositionY][hero1.actualPositionX + 1] != 1) {
        if (heroX < (WIDTH_IN_SQUARES * DIMENSION) - DIMENSION) {
          heroX += DIMENSION;
          ++hero1.actualPositionX;
        }
      }
      hero1.setOrientation("hero-right");
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (MAP[hero1.actualPositionY][hero1.actualPositionX - 1] != 1) {
        if (heroX > 0) {
          heroX -= DIMENSION;
          --hero1.actualPositionX;
        }
      }
      hero1.setOrientation("hero-left");
    }
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }


  public void renderMap(Graphics graphics) {
    for (int i = 0; i < WIDTH_IN_SQUARES; ++i) {
      for (int j = 0; j < HEIGHT_IN_SQUARES; ++j) {
        if (MAP[j][i] == 0) {
          PositionedImage floor = new PositionedImage("img/floor.png", i * DIMENSION, j * DIMENSION);
          floor.draw(graphics);
        } else if (MAP[j][i] == 1) {
          PositionedImage wall = new PositionedImage("img/wall.png", i * DIMENSION, j * DIMENSION);
          wall.draw(graphics);
        }
      }
    }
  }

  public void renderHero(Graphics graphics) {
    PositionedImage hero = new PositionedImage("img/" + hero1.getOrientation() + ".png", heroX, heroY);
    hero.draw(graphics);
  }

  public void renderSkeleton(Graphics graphics, Skeleton skeleton) {
    PositionedImage skeletonImg = new PositionedImage("img/skeleton.png", skeleton.getActualPositionY(), skeleton.getActualPositionX());
    skeletonImg.draw(graphics);
  }

  public int[] getRandomPosition() {
    int[] randomPosition = new int[2];
    do {
      randomPosition[0] = (int) (Math.random() * HEIGHT_IN_SQUARES);
      randomPosition[1] = (int) (Math.random() * WIDTH_IN_SQUARES);
    } while (MAP[randomPosition[0]][randomPosition[1]] == 1);
    return randomPosition;
  }
}
