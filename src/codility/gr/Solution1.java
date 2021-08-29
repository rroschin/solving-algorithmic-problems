package codility.gr;

public class Solution1 {

  public void solution(int N) {

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      sb.append('L');
    }
    String Ls = sb.toString();
    final char[] chars = Ls.toCharArray();
    for (int i = 0; i < chars.length - 1; i++) {
      System.out.println(chars[i]);
    }
    System.out.println(Ls);
/*
    char L = 'L';
    for (int i = 1; i < N + 1; i++) {
      if (i == N) {
        for (int j = 1; j < N + 1; j++) {
          System.out.print(L);
        }
      } else {
        System.out.println(L);
      }
    }
 */
  }

}
