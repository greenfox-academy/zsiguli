import java.util.Arrays;
import java.util.List;

public class Extension {
  int add(int a, int b) {
    return a + b;
  }

  int maxOfThree(int a, int b, int c) {
    int max = a;
    if (b > max) {max = b;}
    if (c > max) {max = c;}
    return max;
  }

  int median(List<Integer> pool) {
    int size = pool.size();
    if (size % 2 == 1) {
      return pool.get(size / 2);
    } else {
      return (pool.get(size / 2 - 1) + pool.get(size / 2)) / 2;
    }
  }

  boolean isVowel(char c) {
    return Arrays.asList('a', 'u', 'o', 'e', 'i').contains(c);
  }

  String translate(String hungarian) {
    String teve = hungarian;
    int length = teve.length();
    for (int i = 0; i < length; i++) {
      char c = teve.charAt(i);
      if (isVowel(c)) {
        teve = String.join(c + "v" + c, teve.split(""+c));
        i+=2;
        length+=2;
      }
    }
    return teve;
  }
}