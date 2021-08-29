package zyx.romros;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCloudSolution {



  public static void main(String[] args) {
    String text = "After beating the eggs, Dana read the next step: "
                  + "Add milk and eggs, then add flour and sugar.";

    Map<String, Integer> wordCloud = createWordCloud(text);
    System.out.println(wordCloud);

  }

  private static Map<String, Integer> createWordCloud(String text) {
    var cloud = new HashMap<String, Integer>();
    String[] words = text.split(" ");
    for (String word : words) {
      final String cleanWord = word.replace(".", "")//
                                   .replace(",", "")//
                                   .replace(":", "")//
                                   .toLowerCase();
      cloud.merge(cleanWord, 1, Integer::sum);
    }
    return cloud;
  }

}
