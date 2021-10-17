package zyx.romros;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Yelp {

    public static void main(String[] args) {
        //        System.out.println(Solution.findMostSimilarBusiness(10, List.of(new PositiveReview(1, 10),
        //                                                                        new PositiveReview(2, 10),
        //                                                                        new PositiveReview(1, 11),
        //                                                                        new PositiveReview(2, 11),
        //                                                                        new PositiveReview(3, 12),
        //                                                                        new PositiveReview(3, 12),
        //                                                                        new PositiveReview(3, 12))));

        /*
        1
        [
        new PositiveReview(3, 44),
        new PositiveReview(172, 44),
        new PositiveReview(172, 114),
        new PositiveReview(4, 1),
        new PositiveReview(4, 44),
         new PositiveReview(7, 44),
          new PositiveReview(7, 13),
           new PositiveReview(8, 44),
            new PositiveReview(8, 13),
            new PositiveReview(123, 1),
             new PositiveReview(2, 1),
              new PositiveReview(3, 1),
               new PositiveReview(8, 4),
                new PositiveReview(9, 44),
                 new PositiveReview(9, 4),
                 new PositiveReview(9, 114)
                 ]
         */

        System.out.println(Solution.findMostSimilarBusiness(1, List.of(
                new PositiveReview(3, 44), //+
                new PositiveReview(172, 44),
                new PositiveReview(172, 114),
                new PositiveReview(4, 1), //X 4
                new PositiveReview(4, 44), //+
                new PositiveReview(7, 44),
                new PositiveReview(7, 13),
                new PositiveReview(8, 44),
                new PositiveReview(8, 13),
                new PositiveReview(123, 1), //X 123
                new PositiveReview(2, 1), //X 2
                new PositiveReview(3, 1), //X 3
                new PositiveReview(8, 4),
                new PositiveReview(9, 44),
                new PositiveReview(9, 4),
                new PositiveReview(9, 114))));
    }
}

class PositiveReview {
    Integer userId;
    Integer businessId;

    public PositiveReview(Integer userId, Integer businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getBusinessId() {
        return this.businessId;
    }

    @Override
    public String toString() {
        return "PositiveReview{" +
               "userId=" + userId +
               ", " + businessId +
               '}';
    }
}

class Solution {
    /*
    Sample Input
        {
            "businessOfInterestId": 10,
            "positiveReviews": [
                PositiveReview(
                    "userId": 1,
                    "businessId": 10
                ),
                PositiveReview(
                    "userId": 2,
                    "businessId": 10
                ),
                PositiveReview(
                    "userId": 1,
                    "businessId": 11
                ),
                PositiveReview(
                    "userId": 2,
                    "businessId": 11
                ),
                PositiveReview(
                    "userId": 1,
                    "businessId": 12
                ),
                PositiveReview(
                    "userId": 2,
                    "businessId": 12
                ),
                PositiveReview(
                    "userId": 3,
                    "businessId": 12
                )
            ]
        }
    Sample Output
        11
    Business Similarity Score against business 10:
        11: 2 / (2 + 2 - 2) = 1.0
        12: 2 / (2 + 3 - 2) = 0.667
    */
    public static Integer findMostSimilarBusiness(Integer businessOfInterestId, List<PositiveReview> positiveReviews) {

        Set<Integer> businessOfInterestReviewUserIds = positiveReviews.stream()
                .filter(pr -> pr.getBusinessId().equals(businessOfInterestId))
                .map(PositiveReview::getUserId)
                .collect(Collectors.toSet());

        List<PositiveReview> otherBusinessesPositiveReview = positiveReviews.stream()
                .filter(pr -> !pr.getBusinessId().equals(businessOfInterestId))
                .collect(Collectors.toList());

        Map<Integer, Integer> allReviewsPerBusiness =
                otherBusinessesPositiveReview.stream()
                        .collect(toMap(PositiveReview::getBusinessId, pr -> 1, Integer::sum));

        Map<Integer, Integer> subsetReviewsPerBusiness =
                otherBusinessesPositiveReview.stream()
                        .filter(pr -> businessOfInterestReviewUserIds.contains(pr.getUserId()))
                        .collect(toMap(PositiveReview::getBusinessId, pr -> 1, Integer::sum));

        final AbstractMap.SimpleEntry<Integer, Double> answer = allReviewsPerBusiness.keySet().stream()
                .map(bId -> {
                    int a = subsetReviewsPerBusiness.getOrDefault(bId, 0);
                    int b = allReviewsPerBusiness.getOrDefault(bId, 0);
                    double score = (double) a / (double) b;
                    return new AbstractMap.SimpleEntry<>(bId, score);
                }).max(Comparator.comparingDouble((Map.Entry<Integer, Double> e) -> e.getValue()))
                .orElse(new AbstractMap.SimpleEntry<>(-1, 0.0));

        return answer.getKey();
    }
}
