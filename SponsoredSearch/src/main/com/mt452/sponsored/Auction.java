package com.mt452.sponsored;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by mt452 on 03.01.2019.
 */
public abstract class Auction {
    private Map<String, Double> scores;
    private List<Map.Entry<String, Double>> bestScores;
    private List<String> winners;

    public Auction() {
        scores = new HashMap<>();
    }

    public List<String> evaluate(Map<String, BaseBidder> bidders, List<String> keywords, int sponsoredSlotsNum) {
        scores = new HashMap<>();
        bidders.keySet().stream().forEach(b -> {
            if (bidders.get(b).getAmount() > 0) {
                // Todo: Check if amount is big enough for at least one click
                // Todo: Check if budget was not exceeded
                final double tmpScore = keywords.stream().mapToDouble(keyword -> {
                    final double weight = getCTR();
                    return bidders.get(b).getBid(keyword.toLowerCase()) * weight;
                }).sum();

                if (tmpScore > 0.0) scores.putIfAbsent(b, tmpScore);
            }
        });

        bestScores = new ArrayList<>(scores.entrySet());
        bestScores = bestScores.stream().filter(score -> score.getValue() > 0.0).collect(Collectors.toList());
        bestScores.sort(Map.Entry.comparingByValue());

        final int n = bidders.size();
        final int m = Math.min(bestScores.size(), sponsoredSlotsNum);
        winners = bestScores.subList(bestScores.size() - m, bestScores.size())
                .stream().map(Map.Entry::getKey).collect(Collectors.toList());

        Collections.reverse(winners);
        return winners;
    }

    public abstract double getCTR();
}
