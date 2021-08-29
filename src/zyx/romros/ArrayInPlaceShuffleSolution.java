package zyx.romros;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ArrayInPlaceShuffleSolution {

  private static Random rand = new Random();

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6};
    Map<Integer, Integer> map1 = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    Map<Integer, Integer> map3 = new HashMap<>();
    Map<Integer, Integer> map4 = new HashMap<>();
    Map<Integer, Integer> map5 = new HashMap<>();
    Map<Integer, Integer> map6 = new HashMap<>();
    for (int i = 0; i < 6000000; i++) {
      shuffle(array);
      map1.merge(array[0], 1, Integer::sum);
      map2.merge(array[1], 1, Integer::sum);
      map3.merge(array[2], 1, Integer::sum);
      map4.merge(array[3], 1, Integer::sum);
      map5.merge(array[4], 1, Integer::sum);
      map6.merge(array[5], 1, Integer::sum);
    }
    System.out.println(map1);
    System.out.println(map2);
    System.out.println(map3);
    System.out.println(map4);
    System.out.println(map5);
    System.out.println(map6);
  }

  private static void shuffle1(int[] array) {
    for (int i = 0; i < array.length; i++) {
      final int pos = getRandom1(0, array.length);
      final int tmp = array[i];
      array[i] = array[pos];
      array[pos] = tmp;
    }
  }

  private static int getRandom1(int floor, int ceiling) {
    return new Random().nextInt(ceiling) + floor;
  }

  private static int getRandom(int floor, int ceiling) {
    return rand.nextInt((ceiling - floor) + 1) + floor;
  }

  public static void shuffle(int[] array) {

    // if it's 1 or 0 items, just return
    if (array.length <= 1) {
      return;
    }

    // walk through from beginning to end
    for (int indexWeAreChoosingFor = 0; indexWeAreChoosingFor < array.length - 1; indexWeAreChoosingFor++) {

      // choose a random not-yet-placed item to place there
      // (could also be the item currently in that spot)
      // must be an item AFTER the current item, because the stuff
      // before has all already been placed
      int randomChoiceIndex = getRandom(indexWeAreChoosingFor, array.length - 1);

      // place our random choice in the spot by swapping
      if (randomChoiceIndex != indexWeAreChoosingFor) {
        int valueAtIndexWeChoseFor = array[indexWeAreChoosingFor];
        array[indexWeAreChoosingFor] = array[randomChoiceIndex];
        array[randomChoiceIndex] = valueAtIndexWeChoseFor;
      }
    }
  }

}
