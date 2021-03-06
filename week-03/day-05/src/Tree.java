import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Tree {
  final static int FRAME_DIMENSION = 500;
  final static int INITIAL_LENGTH = 80;

  public static void main(String[] args) {
    JFrame jFrame = new JFrame("Drawing");
    jFrame.setSize(new Dimension(FRAME_DIMENSION, FRAME_DIMENSION));
    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    jFrame.add(new ImagePanel());
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }

  static class ImagePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.setBackground(new Color(003442));
      g.setColor(Color.YELLOW);
      mainDraw(g, FRAME_DIMENSION / 2, (int) (FRAME_DIMENSION * .8), INITIAL_LENGTH, 90);
    }
  }

  public static void mainDraw(Graphics g, int x, int y, int len, int angle) {
    if (len < 5) {
      return;
    } else {
      g.drawLine(x, y, x + xComponent(angle, len), y - yComponent(angle, len));
      x = x + xComponent(angle, len);
      y = y - yComponent(angle, len);
      for (int i = -20; i <= 20; i += 20) {
        mainDraw(g, x, y, (int) (len * .72), angle + i);
      }
    }
  }

  public static int xComponent(int angle, int len) {
    return (int) (Math.cos(Math.toRadians(angle)) * len);
  }

  public static int yComponent(int angle, int len) {
    return (int) (Math.sin(Math.toRadians(angle)) * len);
  }
}
