package com.mt452.sponsored;

import java.util.List;
import java.util.Map;

/**
 * Created by mt452 on 03.01.2019.
 */
public interface BaseBidder {
    // Title is used as uuid, can not be changed
    String getTitle();

    void setAmount(double amount);
    double getAmount();

    void setBudget(double budget);
    double getBudget();

    void setBudgetAllocation(char budgetAllocation);
    char getBudgetAllocation();

    void addKeywords(String[] keywords, double[] bids);
    List<Double> addKeywords(String[] keywords);
    void addKeyword(String keyword, double bid);
    Map<String, Double> getKeywords();
    double getBid(String keyword);
}
