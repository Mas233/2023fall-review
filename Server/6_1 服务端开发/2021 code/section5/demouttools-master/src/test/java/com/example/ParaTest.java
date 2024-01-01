package com.example;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParaTest {
    private Calculate calculate;

    private int input1;
    private int input2;
    private int expected;

    @Parameters
    @SuppressWarnings("unchecked")
    public static Collection prepareData() {
        Object[][] objects = {{-1, -2, -3}, {0, 2, 2}, {-1, 1, 0}, {1, 2, 3}};
        return Arrays.asList(objects);
    }

    public ParaTest(int input1, int input2, int expected) {
        this.input1 = input1;
        this.input2 = input2;
        this.expected = expected;
    }

    @Before
    public void setUp() throws Exception {
        calculate = new Calculate();
    }

    @Test
    public void testAdd() {
        int result = calculate.add(input1, input2);
        Assert.assertEquals(expected, result);
    }

}
