package com.mt452.sponsored;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mt452 on 03.01.2019.
 */
public class SearchEngine implements SearchEngineInterface {
    private static final int SPONSORED_SLOTS_PER_PAGE = 5;
    private static final int SLOTS_PER_PAGE = 20;

    private Map<String, BaseBidder> bidders;
    private List<String> keywords;
    private List<Long> winners;
    private Auction auction;

    public SearchEngine(Auction auction) {
        this.auction = auction;
        bidders = new HashMap<>();
        keywords = new ArrayList<>();
        winners = new ArrayList<>();
    }

    @Override
    public List<Slot> handleQuery(String searchQuery) {
        System.out.println("=== Search query processing: " + searchQuery);

        // Todo: Evaluate weights (relevance) through auction
        // Todo: Rank results
        // Todo: Rank bidders (get scores)

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(searchQuery);
        while (matcher.find()) keywords.add(matcher.group());
        //winners = auction.evaluate(bidders, keywords, SPONSORED_SLOTS_PER_PAGE);

        List<Slot> searchResults = new ArrayList<>();
        searchResults.add(new Slot3("Accident insurance 1", "https://check24.de"));
        searchResults.add(new Slot3("Accident insurance 2", "https://mt452.com"));

        return searchResults;
    }

    @Override
    public void addBidder(BaseBidder bidder) {
        bidders.put(bidder.getTitle(), bidder);
    }
}
