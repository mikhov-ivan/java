package com.mt452.sponsored;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by mt452 on 03.01.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchEngineTest {
    @Test
    public void test01_bidders() {
        Auction rankByBid1 = new RankByBid1();
        SearchEngine searchEngine = new SearchEngine(rankByBid1);

        BaseBidder bidder = new Bidder("B4", 1452.0, 0.75);
        searchEngine.addBidder(bidder);
    }

    @Test
    public void test02_query() {
        Auction rankByBid1 = new RankByBid1();
        SearchEngine searchEngine = new SearchEngine(rankByBid1);
        List<Slot> result = searchEngine.handleQuery("Accident insurance");
        // Todo: Assert result
    }
}
