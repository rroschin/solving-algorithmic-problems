package codility.nzma;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Solution2Test {

  @Test
  public void run1() {
    //given
    int[] arr = {2, 4, 6, 8, 10};

    //when
    int actual = new Solution2().generalizedGCD(arr.length, arr);
    //then
    assertEquals(2, actual);
  }

  @Test
  public void run2() {
    //given
    int[] arr = {2, 3, 4, 5, 6};

    //when
    int actual = new Solution2().generalizedGCD(arr.length, arr);
    //then
    assertEquals(1, actual);
  }
  @Test
  public void run3() {
    //given
    int[] arr = {6,18,27};

    //when
    int actual = new Solution2().generalizedGCD(arr.length, arr);
    //then
    assertEquals(3, actual);
  }

}
