package coursera;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class VotingSystems {

    private static final String WINNER = "winner";
    private static final String LOSER = "loser";

    public static void main(String args[]) throws Exception {
        // Sample Test Case
        final var sample = Map.of(List.of("A", "B", "C"), 3,
                                  List.of("C", "B", "A"), 2,
                                  List.of("B", "C", "A"), 4,
                                  List.of("C", "A", "B"), 2);

        // Determine plurality winner (Part 1)
        System.out.println("The plurality winner is: " + getPluralityWinner(sample));
        assertEquals(getPluralityWinner(sample), "B");

        // Determine ranked choice winner (Part 2)
        System.out.println("The ranked choice winner is: " + getRankedChoiceWinner(sample));
        assertEquals(getRankedChoiceWinner(sample), "B");

        //test case 1
        final Map<List<String>, Integer> case1 = Map.of(List.of("A", "B"), 33,
                                                        List.of("B", "A"), 22);
        assertEquals(getPluralityWinner(case1), "A");
        assertEquals(getRankedChoiceWinner(case1), "A");

        //test case 2
        final Map<List<String>, Integer> case2 = Map.of(List.of("A", "B", "C"), 1,
                                                        List.of("C", "B", "A"), 1,
                                                        List.of("B", "C", "A"), 1,
                                                        List.of("C", "A", "B"), 1);
        assertEquals(getPluralityWinner(case2), "C");
        assertEquals(getRankedChoiceWinner(case2), "C");

        //test case 3
        final Map<List<String>, Integer> case3 = Map.of(List.of("A", "B", "C"), 1,
                                                        List.of("C", "B", "A"), 3,
                                                        List.of("B", "C", "A"), 1,
                                                        List.of("C", "A", "B"), 1,
                                                        List.of("A", "C", "B"), 3);
        assertEquals(getPluralityWinner(case3), "A");
        assertEquals(getRankedChoiceWinner(case3), "C");

        //test case 4
        final Map<List<String>, Integer> case4 = Map.of(List.of("A", "B", "C", "D", "E", "F"), 1,
                                                        List.of("C", "B", "A", "D", "E", "F"), 3,
                                                        List.of("B", "C", "A", "D", "E", "F"), 1,
                                                        List.of("C", "A", "B", "D", "E", "F"), 1,
                                                        List.of("A", "C", "B", "D", "E", "F"), 3,
                                                        List.of("D", "E", "F", "A", "B", "C"), 5);

        assertEquals(getPluralityWinner(case4), "D");
        assertEquals(getRankedChoiceWinner(case4), "C");

        //test case 5
        final Map<List<String>, Integer> case5 = Map.of(List.of("X"), 100);
        assertEquals(getPluralityWinner(case5), "X");
        assertEquals(getRankedChoiceWinner(case5), "X");

        // test case 6
        final Map<List<String>, Integer> case6 = new HashMap<>();
                case6.putAll(Map.of(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"), 3,
                       List.of("F", "M", "A", "D", "W", "J", "C", "Z", "R", "E", "Y", "P", "H", "V", "G", "K", "Q", "L", "S", "B", "X", "I", "U", "T", "O", "N"), 5,
                       List.of("V", "Y", "F", "C", "I", "D", "P", "G", "K", "N", "S", "R", "A", "Q", "O", "J", "U", "W", "E", "Z", "B", "M", "X", "T", "H", "L"), 2,
                       List.of("U", "N", "L", "J", "V", "Y", "D", "M", "S", "X", "E", "B", "A", "F", "Z", "I", "T", "Q", "O", "C", "P", "H", "G", "R", "W", "K"), 3,
                       List.of("U", "T", "R", "I", "G", "K", "F", "V", "D", "O", "L", "P", "C", "Q", "S", "H", "X", "Y", "W", "M", "N", "E", "A", "J", "B", "Z"), 1,
                       List.of("A", "F", "X", "O", "G", "B", "W", "C", "L", "S", "D", "H", "R", "P", "J", "Q", "Y", "K", "U", "M", "V", "N", "T", "Z", "I", "E"), 2,
                       List.of("A", "P", "K", "F", "M", "J", "Z", "H", "U", "Q", "E", "W", "X", "V", "G", "R", "C", "B", "Y", "N", "T", "L", "I", "D", "S", "O"), 6,
                       List.of("C", "G", "D", "P", "V", "L", "A", "H", "J", "N", "Q", "Y", "S", "O", "T", "E", "R", "Z", "U", "F", "W", "X", "K", "B", "I", "M"), 9,
                       List.of("L", "E", "G", "D", "A", "B", "Z", "K", "J", "R", "W", "U", "I", "O", "X", "P", "Y", "F", "H", "N", "T", "Q", "C", "S", "M", "V"), 2,
                       List.of("N", "G", "S", "U", "V", "Z", "D", "R", "W", "B", "J", "K", "E", "Q", "O", "A", "Y", "L", "C", "H", "T", "X", "M", "F", "P", "I"), 7));
        case6.putAll(Map.of(List.of("V", "P", "M", "S", "K", "D", "W", "O", "I", "B", "L", "Y", "C", "G", "X", "N", "Z", "H", "T", "A", "R", "E", "Q", "J", "F", "U"), 8,
                            List.of("K", "D", "U", "V", "T", "N", "A", "H", "L", "I", "S", "J", "P", "C", "Z", "M", "Y", "B", "W", "O", "R", "Q", "E", "F", "X", "G"), 4,
                            List.of("L", "X", "S", "J", "Q", "M", "A", "K", "Z", "T", "F", "W", "H", "D", "V", "G", "E", "C", "P", "O", "N", "R", "U", "B", "I", "Y"), 8,
                            List.of("Q", "J", "X", "A", "O", "E", "V", "I", "G", "M", "C", "N", "R", "B", "Y", "W", "K", "T", "S", "F", "L", "U", "P", "H", "D", "Z"), 6,
                            List.of("U", "I", "L", "J", "M", "T", "N", "E", "W", "D", "S", "V", "G", "H", "R", "P", "A", "K", "Q", "O", "Z", "F", "X", "Y", "B", "C"), 7,
                            List.of("X", "U", "B", "H", "J", "Z", "Y", "R", "A", "P", "T", "S", "Q", "V", "C", "L", "M", "O", "I", "K", "D", "W", "G", "E", "N", "F"), 6,
                            List.of("V", "Y", "K", "G", "N", "X", "U", "A", "R", "Q", "S", "J", "W", "M", "Z", "C", "L", "H", "O", "F", "P", "I", "E", "T", "D", "B"), 5,
                            List.of("M", "H", "P", "Q", "D", "U", "J", "X", "G", "C", "W", "B", "F", "I", "Z", "O", "K", "S", "R", "Y", "T", "E", "A", "V", "L", "N"), 3,
                            List.of("B", "R", "H", "G", "W", "C", "X", "A", "Y", "F", "K", "J", "T", "N", "V", "M", "S", "D", "U", "Q", "Z", "O", "P", "E", "I", "L"), 10,
                            List.of("Z", "X", "W", "T", "G", "O", "P", "E", "I", "Q", "F", "J", "K", "H", "Y", "S", "L", "V", "M", "D", "R", "U", "C", "B", "A", "N"), 4));
        case6.putAll(Map.of(List.of("D", "H", "P", "Q", "R", "F", "K", "G", "X", "E", "A", "B", "Z", "T", "N", "S", "I", "Y", "L", "C", "W", "J", "V", "U", "M", "O"), 5,
                            List.of("J", "M", "S", "R", "G", "F", "U", "V", "P", "X", "B", "K", "H", "C", "I", "N", "D", "Y", "O", "Q", "A", "Z", "L", "W", "T", "E"), 10,
                            List.of("N", "K", "Y", "P", "R", "B", "F", "H", "C", "V", "I", "D", "J", "G", "W", "L", "M", "Z", "X", "E", "Q", "T", "S", "U", "A", "O"), 6,
                            List.of("E", "J", "P", "S", "N", "W", "T", "Q", "G", "B", "Y", "L", "V", "F", "K", "I", "H", "U", "C", "X", "A", "Z", "M", "O", "R", "D"), 2));
        assertEquals(getPluralityWinner(case6), "V");
        assertEquals(getRankedChoiceWinner(case6), "J");
    }

    // implement this method for Part 1
    public static String getPluralityWinner(Map<List<String>, Integer> ballots) {

        return calculateRoundCandidateByType(ballots, WINNER).getKey();
    }

    // implement this method for Part 2

    public static String getRankedChoiceWinner(Map<List<String>, Integer> ballots) {
        final Map<List<String>, Integer> copyOfBallots = copyInputToMutableCollections(ballots);

        final int totalVotes = copyOfBallots.values().stream().mapToInt(v -> v).sum();

        while (copyOfBallots.size() != 1) {
            final Map.Entry<String, Integer> roundWinner = calculateRoundCandidateByType(copyOfBallots, WINNER);

            if (roundWinner.getValue() > totalVotes / 2) { //round winner has more than 50% of total votes -> they are the winner!
                return roundWinner.getKey();

            } else { //remove round loser and prepare ballots for next round
                final String roundOneLoser = calculateRoundCandidateByType(copyOfBallots, LOSER).getKey();
                copyOfBallots.forEach((k, v) -> k.remove(roundOneLoser));
            }
        }

        return copyOfBallots.keySet().stream().findFirst().orElseThrow(IllegalStateException::new).get(0);
    }

    private static Map.Entry<String, Integer> calculateRoundCandidateByType(final Map<List<String>, Integer> ballots, final String type) {
        final Stream<Map.Entry<String, Integer>> firstGroupedByVotes = ballots.entrySet()
                .stream()
                .map(e -> new SimpleEntry<>(e.getKey().get(0), e.getValue()))
                .collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)))
                .entrySet()
                .stream();
        final Comparator<Map.Entry<String, Integer>> sortFunction = comparingInt((Map.Entry<String, Integer> e) -> e.getValue())
                .thenComparing(Map.Entry.<String, Integer>comparingByKey().reversed());

        if ("winner".equals(type)) {
            return firstGroupedByVotes.max(sortFunction).orElseThrow(IllegalStateException::new);
        } else { //loser
            return firstGroupedByVotes.min(sortFunction).orElseThrow(IllegalStateException::new);
        }
    }

    private static Map<List<String>, Integer> copyInputToMutableCollections(final Map<List<String>, Integer> map) {
        Map<List<String>, Integer> copy = new HashMap<>(map.size());
        for (final Map.Entry<List<String>, Integer> e : map.entrySet()) {
            copy.put(new ArrayList<>(e.getKey()), e.getValue());
        }
        return copy;
    }

    public static void assertEquals(final Object actual, final Object expected) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError(actual + " is not equal to " + expected);
        }
    }

}
