package zyx.romros;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class TwoMoviesSolution {

  public static void main(String[] args) {

    int flightLength = 90;
    int[] movieLengths = {10, 45, 30, 20, 65, 75, 90, 100, 20, 80};

    boolean has1 = hasTwoMovies1(flightLength, movieLengths);
    System.out.println(has1);

    boolean has2 = hasTwoMovies2(flightLength, movieLengths);
    System.out.println(has2);

    boolean has3 = canTwoMoviesFillFlight(movieLengths, flightLength);
    System.out.println(has3);
  }

  public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {

    // movie lengths we've seen so far
    Set<Integer> movieLengthsSeen = new HashSet<>();

    for (int firstMovieLength : movieLengths) {

      int matchingSecondMovieLength = flightLength - firstMovieLength;
      if (movieLengthsSeen.contains(matchingSecondMovieLength)) {
        return true;
      }

      movieLengthsSeen.add(firstMovieLength);
    }

    // we never found a match, so return false
    return false;
  }

  private static boolean hasTwoMovies2(int flightLength, int[] movieLengths) {
    Set<Integer> set = IntStream.of(movieLengths).boxed().collect(toSet());
    for (final int firstMovie : movieLengths) {
      if (set.contains(flightLength - firstMovie)) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasTwoMovies1(int flightLength, int[] movieLengths) { //O(n2)
    for (int i = 0; i < movieLengths.length; i++) {
      for (int j = 0; j < movieLengths.length; j++) {
        if (i != j && movieLengths[i] + movieLengths[j] == flightLength) {
          return true;
        }
      }
    }
    return false;
  }

}
