package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CalculateTest {
    private Calculate calculate;

    @Before
    public void setUp() throws Exception {
        calculate = new Calculate();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAdd() {
        int result = calculate.add(7, 8);
        assertEquals(15, result);
    }

    @Test
    public void substract() {
//        fail("no found");
    }

    @Test
    public void multiply() {
        int result = calculate.multiply(7, 8);
        assertThat(result, is(56));
    }

    @Test(expected = ArithmeticException.class)
    public void divide() {
        calculate.divide(3, 0);
    }

    @Test
    public void testArray() {
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] b = {1, 2, 3, 4, 5};
//        assertEquals(a,b);
        assertArrayEquals("------", a, b);
        //assertThat(new int[] {1, 2}, equalTo(new int[] {1, 2}));
    }

    @Test(timeout = 5)
    @Ignore
    public void testSquareRoot() {
        calculate.squareRoot(3);
    }

    @Test
    public void testDivide() {
        int result = calculate.divide(20, 1);

//        assertThat(result, anyOf(not(15), not(5),not(99)));
        assertThat(result, anything());
    }
}