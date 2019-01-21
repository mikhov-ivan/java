package com.mt452.sponsored;

import java.util.List;

/**
 * Created by mt452 on 03.01.2019.
 */
public interface SearchEngineInterface {
    void addBidder(BaseBidder bidder);
    List<Slot> handleQuery(String searchQuery);
}
