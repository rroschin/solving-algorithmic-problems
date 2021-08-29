package codility.builingsandbanners;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  public void run1() {
    //given
    int[] A = {3, 1, 4};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(10, actual);
  }

  @Test
  public void run2() {
    //given
    int[] A = {5, 3, 2, 4};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(17, actual);
  }

  @Test
  public void run3() {
    //given
    int[] A = {5, 3, 5, 2, 1};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(19, actual);
  }

  @Test
  public void run4() {
    //given
    int[] A = {7, 7, 3, 7, 7};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(35, actual);
  }
  @Test
  public void run5() {
    //given
    int[] A = {1, 1, 7, 6, 6, 6};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(30, actual);
  }

}
