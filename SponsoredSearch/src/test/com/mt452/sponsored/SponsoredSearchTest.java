package com.mt452.sponsored;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Created by mt452 on 03.01.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SponsoredSearchTest {
    @Test
    public void test01_init() {
        SponsoredSearch sponsoredSearch = new SponsoredSearch();
        sponsoredSearch.main(new String[] {});
    }
}
