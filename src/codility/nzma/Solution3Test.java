package codility.nzma;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Solution3Test {

  @Test
  public void run1() {
    //given
    List<List<Integer>> list = asList(//
                                      asList(1, 1, 0, 0),//
                                      asList(0, 0, 0, 0),//
                                      asList(0, 0, 1, 1),//
                                      asList(0, 0, 0, 0)//
    );
    int rows = 4;
    int cols = 4;

    //when
    int actual = new Solution3().numberAmazonTreasureTrucks(rows, cols, list);
    //then
    assertEquals(2, actual);
  }

  @Test
  public void run2() {
    //given
    List<List<Integer>> list = asList(//
                                      asList(1, 1, 0, 0),//
                                      asList(1, 0, 0, 1),//
                                      asList(1, 0, 0, 1),//
                                      asList(0, 1, 0, 1)//
    );
    int rows = 4;
    int cols = 4;

    //when
    int actual = new Solution3().numberAmazonTreasureTrucks(rows, cols, list);
    //then
    assertEquals(3, actual);
  }

  @Test
  public void run3() {
    //given
    List<List<Integer>> list = asList(//
                                      asList(1, 0, 0, 0),//
                                      asList(0, 1, 0, 0),//
                                      asList(0, 0, 1, 0),//
                                      asList(0, 0, 0, 1)//
    );
    int rows = 4;
    int cols = 4;

    //when
    int actual = new Solution3().numberAmazonTreasureTrucks(rows, cols, list);
    //then
    assertEquals(4, actual);
  }
  @Test
  public void run4() {
    //given
    List<List<Integer>> list = asList(//
                                      asList(1, 1, 0, 0),//
                                      asList(0, 0, 1, 0),//
                                      asList(0, 0, 0, 0),//
                                      asList(1, 0, 1, 1),//
                                      asList(1, 1, 1, 1)//
    );
    int rows = 5;
    int cols = 4;

    //when
    int actual = new Solution3().numberAmazonTreasureTrucks(rows, cols, list);
    //then
    assertEquals(3, actual);
  }

}
