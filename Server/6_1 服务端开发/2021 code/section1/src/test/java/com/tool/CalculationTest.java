package com.tool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculationTest {

    private Calculation calculation = null;

    @Before
    public void setUp() throws Exception {
        calculation = new Calculation();
    }

    @After
    public void tearDown() throws Exception {
        calculation = null;
    }

    @Test
    public void add() {
        // @Ignore 是用来忽略一个测试用例的
        int actual = calculation.add(1, 9);
        int expected = 10;
        assertEquals(expected, actual);
    }
}