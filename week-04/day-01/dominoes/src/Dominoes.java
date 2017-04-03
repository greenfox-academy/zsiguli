import java.util.ArrayList;
import java.util.List;

public class Dominoes {
  static List<Domino> orderedDominoes = new ArrayList<>();

  static List<Domino> initializeDominoes() {
    List<Domino> dominoes = new ArrayList<>();
    dominoes.add(new Domino(5, 2));
    dominoes.add(new Domino(4, 6));
    dominoes.add(new Domino(1, 5));
    dominoes.add(new Domino(6, 7));
    dominoes.add(new Domino(2, 4));
    dominoes.add(new Domino(7, 1));
    return dominoes;
  }

  static List<Domino> orderDominoes(List<Domino> dominoes) {
    if (orderedDominoes.size() == 0) {
      orderedDominoes.add(dominoes.subList(0, 1).get(0));
    }
    return orderedDominoes;
  }

  public static void main(String[] args) {
    List<Domino> dominoes = initializeDominoes();
    List<Domino> orderedDominoes = orderDominoes(dominoes);
    System.out.println(orderedDominoes);
  }


}