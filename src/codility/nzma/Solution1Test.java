package codility.nzma;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Solution11Test {

  @Test
  public void run1() {
    //given
    int[] states = {1, 0, 0, 0, 0, 1, 0, 0};
    int days = 1;

    //when
    List<Integer> actual = new Solution1().cellCompete(states, days);
    //then
    assertEquals(asList(0, 1, 0, 0, 1, 0, 1, 0), actual);
  }

  @Test
  public void run2() {
    //given
    int[] states = {1, 1, 1, 0, 1, 1, 1, 1};
    int days = 2;

    //when
    List<Integer> actual = new Solution1().cellCompete(states, days);
    //then
    assertEquals(asList(0, 0, 0, 0, 0, 1, 1, 0), actual);
  }

  @Test
  public void run3() {
    //given
    int[] states = {1};
    int days = 1;

    //when
    List<Integer> actual = new Solution1().cellCompete(states, days);
    //then
    assertEquals(asList(0), actual);
  }

  @Test
  public void run4() {
    //given
    int[] states = {1};
    int days = 2;

    //when
    List<Integer> actual = new Solution1().cellCompete(states, days);
    //then
    assertEquals(asList(0), actual);
  }

  @Test
  public void run5() {
    //given
    int[] states = {1, 1};
    int days = 3;

    //when
    List<Integer> actual = new Solution1().cellCompete(states, days);
    //then
    assertEquals(asList(1, 1), actual);
  }

  @Test
  public void run6() {
    //given
    int[] states = {0, 1};
    int days = 4;

    //when
    List<Integer> actual = new Solution1().cellCompete(states, days);
    //then
    assertEquals(asList(0, 1), actual);
  }

}
