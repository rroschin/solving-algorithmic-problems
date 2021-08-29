package codility.gr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Solution2Test {

  @Test
  public void run1() {
    //given
    int N = 670;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(6750, actual);
  }

  @Test
  public void run2() {
    //given
    int N = 268;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(5268, actual);
  }

  @Test
  public void run3() {
    //given
    int N = 0;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(50, actual);
  }

  @Test
  public void run4() {
    //given
    int N = -999;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(-5999, actual);
  }

  @Test
  public void run5() {
    //given
    int N = 9;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(95, actual);
  }

  @Test
  public void run6() {
    //given
    int N = 3;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(53, actual);
  }

  @Test
  public void run7() {
    //given
    int N = -4;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(-45, actual);
  }

  @Test
  public void run8() {
    //given
    int N = 55550;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(555550, actual);
  }

  @Test
  public void run9() {
    //given
    int N = -55550;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(-555505, actual);
  }
  @Test
  public void run10() {
    //given
    int N = 8000;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(85000, actual);
  }

  @Test
  public void run11() {
    //given
    int N = 59;
    //when
    int actual = new Solution2().solution(N);
    //then
    assertEquals(595, actual);
  }

}
