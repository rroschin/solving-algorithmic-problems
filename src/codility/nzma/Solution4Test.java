package codility.nzma;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Solution4Test {

  @Test
  public void run1() {
    //given
    List<Character> list = asList('z', 'z', 'c', 'b', 'z', 'c', 'h', 'f', 'i', 'h', 'i');

    //when
    List<Integer> actual = new Solution4().lengthEachScene(list);
    //then
    assertEquals(asList(6, 5), actual);
  }
  @Test
  public void run2() {
    //given
    List<Character> list = asList('a','b','c');

    //when
    List<Integer> actual = new Solution4().lengthEachScene(list);
    //then
    assertEquals(asList(1,1,1), actual);
  }

}
