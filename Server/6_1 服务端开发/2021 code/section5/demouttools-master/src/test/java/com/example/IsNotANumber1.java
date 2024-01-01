package com.example;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsNotANumber1 extends TypeSafeMatcher<Double> {
    @Override
    public boolean matchesSafely(Double number) {
        return number.isNaN();
    }

    public void describeTo(Description description) {
        description.appendText("this is not a number");
    }

    @Factory
    public static Matcher notANumber1() {
        return new IsNotANumber1();
    }
}
