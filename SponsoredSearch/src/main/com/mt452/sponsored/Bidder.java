package com.mt452.sponsored;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by mt452 on 03.01.2019.
 */
public class Bidder implements BaseBidder {
    private String title;
    private double amount;
    private double budget;
    private char budgetAllocation;
    private Map<String, Double> keywords;

    public Bidder(String title, double amount, double budget) {
        this.title = title;
        this.amount = amount;
        this.budget = budget;
        budgetAllocation = 'm'; // per month
        keywords = new HashMap<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public double getBudget() {
        return budget;
    }

    @Override
    public void setBudgetAllocation(char budgetAllocation) {
        this.budgetAllocation = budgetAllocation;
    }

    @Override
    public char getBudgetAllocation() {
        return budgetAllocation;
    }

    @Override
    public void addKeyword(String keyword, double bid) {
        keywords.putIfAbsent(keyword.toLowerCase(), bid);
    }

    @Override
    public void addKeywords(String[] keywords, double[] bids) {
        for (int i = 0; i < keywords.length; ++i) {
            // Todo: Pass pairs of keyword - value (index alignment)
            this.keywords.putIfAbsent(keywords[i].toLowerCase(), bids[i]);
        }
    }

    @Override
    public List<Double> addKeywords(String[] keywords) {
        return Arrays.asList(keywords).stream().map(s -> {
            final double bid = ThreadLocalRandom.current().nextDouble(0.1, 1.5);
            this.keywords.putIfAbsent(s.toLowerCase(), bid);
            return bid;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, Double> getKeywords() {
        return keywords;
    }

    @Override
    public double getBid(String keyword) {
        return keywords.containsKey(keyword) ? keywords.get(keyword) : 0.0;
    }
}
