package codility.missinginteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  public void run1() {
    //given
    int[] A = {1, 3, 6, 4, 1, 2};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(5, actual);
  }

  @Test
  public void run2() {
    //given
    int[] A = {1, 2, 3};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(4, actual);
  }

  @Test
  public void run3() {
    //given
    int[] A = {-1, -3};
    //when
    int actual = new Solution().solution(A);
    //then
    assertEquals(1, actual);
  }

}
