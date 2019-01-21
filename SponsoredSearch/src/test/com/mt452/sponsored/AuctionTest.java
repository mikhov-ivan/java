package com.mt452.sponsored;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by mt452 on 04.01.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionTest {
    private static final int SPONSORED_SLOTS_PER_PAGE = 5;

    private Map<String, BaseBidder> bidders;
    private List<String> keywords;
    private List<String> winners;
    private Auction auction;

    @Before
    public void initBidders() {
        bidders = new HashMap<>();
        bidders.put("B1", new Bidder("B1", 452.0, 35.0));
        bidders.put("B2", new Bidder("B2", 3500.0, 135.0));
        bidders.put("B3", new Bidder("B3", 3000.0, 0.0));
        bidders.put("B4", new Bidder("B4", 3000.0, 0.0));
        bidders.put("B5", new Bidder("B5", 3000.0, 0.0));
        bidders.put("B6", new Bidder("B6", 3000.0, 0.0));
        bidders.put("B7", new Bidder("B7", 3000.0, 0.0));
        bidders.put("B8", new Bidder("B8", 3000.0, 0.0));
        bidders.put("B9", new Bidder("B9", 3000.0, 0.0));

        bidders.get("B1").addKeywords(new String[] {"Insurance", "Accident", "Health"}, new double[] {0.5, 0.03, 0.75});
        bidders.get("B2").addKeywords(new String[] {"Insurance", "Flowers"}, new double[] {0.04, 0.45});
        bidders.get("B3").addKeywords(new String[] {"Health"}, new double[] {0.78});
        bidders.get("B4").addKeywords(new String[] {"Flowers"}, new double[] {1.2});
        bidders.get("B5").addKeywords(new String[] {"Insurance", "1"}, new double[] {1.75, 0.3});
        bidders.get("B6").addKeywords(new String[] {"Benefits", "Safe"}, new double[] {0.34, 0.5});
        bidders.get("B7").addKeywords(new String[] {"Day", "Valentine's"}, new double[] {0.1, 0.2});
        bidders.get("B8").addKeywords(new String[] {"1", "2", "3"}, new double[] {0.3, 0.2, 0.1});
        bidders.get("B9").addKeywords(new String[] {"1"}, new double[] {10.05});
    }

    @Test
    public void test02_evaluate_rankByBid1() {
        auction = new RankByBid1();

        keywords = Arrays.asList(new String("Accident insurance 1").split("\\s"));
        winners = auction.evaluate(bidders, keywords, SPONSORED_SLOTS_PER_PAGE);
        assertThat(winners.size(), is(5));
        assertTrue(winners.containsAll(Arrays.asList(new String[] {"B9", "B5", "B1", "B8", "B2"})));

        keywords = Arrays.asList(new String("Wild flowers").split("\\s"));
        winners = auction.evaluate(bidders, keywords, SPONSORED_SLOTS_PER_PAGE);
        assertThat(winners.size(), is(2));
        assertTrue(winners.containsAll(Arrays.asList(new String[] {"B2", "B4"})));
    }

    @Test
    public void test03_evaluate_rankByRevenue1() {
        auction = new RankByRevenue1();

        keywords = Arrays.asList(new String("Accident insurance 1").split("\\s"));
        winners = auction.evaluate(bidders, keywords, SPONSORED_SLOTS_PER_PAGE);
        // Todo: Assert results of ranking by revenue

        keywords = Arrays.asList(new String("Wild flowers").split("\\s"));
        winners = auction.evaluate(bidders, keywords, SPONSORED_SLOTS_PER_PAGE);
        // Todo: Assert results of ranking by revenue
    }
}
