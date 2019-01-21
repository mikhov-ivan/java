package com.mt452.sponsored;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by mt452 on 03.01.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BidderTest {
    private Map<String, BaseBidder> bidders;
    private BaseBidder bidder;

    @Before
    public void initBidders() {
        bidders = new HashMap<>();
    }

    @Test
    public void test01_init() {
        bidders.put("B1", new Bidder("B1", 452.0, 35.0));
        bidders.put("B2", new Bidder("B2", 3500.0, 135.0));
        bidders.put("B3", new Bidder("B3", 3000.0, 0.0));

        assertThat(bidders.get("B1").getTitle(), equalTo("B1"));
        assertThat(bidders.get("B1").getAmount(), equalTo(452.0));
        bidders.get("B1").setAmount(570.05);
        assertThat(bidders.get("B1").getAmount(), equalTo(570.05));

        assertThat(bidders.get("B1").getBudget(), equalTo(35.0));
        bidders.get("B1").setBudgetAllocation('y');
        assertThat(bidders.get("B1").getBudgetAllocation(), equalTo('y'));

        assertThat(bidders.get("B2").getTitle(), equalTo("B2"));
        assertThat(bidders.get("B2").getAmount(), equalTo(3500.0));
        assertThat(bidders.get("B2").getBudget(), equalTo(135.0));
        bidders.get("B2").setBudget(7500.0);
        assertThat(bidders.get("B2").getBudget(), equalTo(7500.0));
        assertThat(bidders.get("B2").getBudgetAllocation(), equalTo('m'));

        assertThat(bidders.get("B3").getTitle(), equalTo("B3"));
        assertThat(bidders.get("B3").getAmount(), equalTo(3000.0));
        assertThat(bidders.get("B3").getBudget(), equalTo(0.0));
        assertThat(bidders.get("B3").getBudgetAllocation(), equalTo('m'));
    }

    @Test
    public void test02_keywords() {
        bidders.put("B4", new Bidder("B4", 1452.0, 0.75));
        bidder = bidders.get("B4");

        bidder.addKeyword("Accident", 0.5);
        bidder.addKeyword("Assurance", 0.9);
        bidder.addKeywords(new String[] {"Flowers", "Present", "Accident", "Assurance"});

        Map<String, Double> keywords = bidder.getKeywords();
        assertThat(keywords.size(), is(4));
        assertTrue(keywords.keySet().containsAll(Arrays.asList(new String[] {
                "accident", "assurance", "flowers", "present"
        })));
    }
}
