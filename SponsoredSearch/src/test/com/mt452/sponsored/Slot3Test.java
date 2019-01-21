package com.mt452.sponsored;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by mt452 on 03.01.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Slot3Test {
    @Test
    public void test01_init() {
        Slot3 slot1 = new Slot3("Test Slot 1", "Slot initialisation test", "localhost");
        Slot3 slot2 = new Slot3("Test Slot 2", "localhost:8080");

        assertThat(slot1.getTitle(), equalTo("Test Slot 1"));
        assertThat(slot2.getTitle(), equalTo("Test Slot 2"));

        assertThat(slot1.getUrl(), equalTo("localhost"));
        assertThat(slot2.getUrl(), equalTo("localhost:8080"));

        assertThat(slot1.getDescription(), equalTo("Slot initialisation test"));
        assertThat(slot2.getDescription(), equalTo(""));

        assertThat(slot1.toString(), equalTo("[Test Slot 1] Slot initialisation test -> localhost"));
        assertThat(slot2.toString(), equalTo("[Test Slot 2] -> localhost:8080"));
    }
}
