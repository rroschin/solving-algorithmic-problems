package codility.gr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Solution3Test {

  @Test
  public void run1() {
    //given
    int[] A = {5};
    int X = 4;
    int Y = 0;
    int Z = 3;
    //when
    int actual = new Solution3().solution(A, X, Y, Z);
    //then
    assertEquals(-1, actual);
  }

  @Test
  public void run2() {
    //given
    int[] A = {2, 8, 4, 3, 2};
    int X = 7;
    int Y = 11;
    int Z = 3;
    //when
    int actual = new Solution3().solution(A, X, Y, Z);
    //then
    assertEquals(8, actual);
  }

  @Test
  public void run3() {
    //given
    int[] A = {3, 1, 3};
    int X = 4;
    int Y = 0;
    int Z = 3;
    //when
    int actual = new Solution3().solution(A, X, Y, Z);
    //then
    assertEquals(-1, actual);
  }

  @Test
  public void run4() {
    //given
    int[] A = {11, 3, 7};
    int X = 7;
    int Y = 11;
    int Z = 3;
    //when
    int actual = new Solution3().solution(A, X, Y, Z);
    //then
    assertEquals(-1, actual);
  }

}
