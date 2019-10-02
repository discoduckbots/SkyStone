package org.firstinspires.ftc.discoduckbots.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberUtilityTest {

    private static final double LARGEST = 10.0;
    private static final double NOT_LARGEST = 1.0;

    @Test
    public void findMax_whenFirstNumberIsLargest_ShouldReturnFirstNumber() {
        double result = NumberUtility.findMax(LARGEST, NOT_LARGEST, NOT_LARGEST, NOT_LARGEST);

        assertEquals(LARGEST, result, 0.0);
    }

    @Test
    public void findMax_whenSecondNumberIsLargest_ShouldReturnSecondNumber() {
        double result = NumberUtility.findMax(NOT_LARGEST, LARGEST, NOT_LARGEST, NOT_LARGEST);

        assertEquals(LARGEST, result, 0.0);
    }

    @Test
    public void findMax_whenThirdNumberIsLargest_ShouldReturnThirdNumber() {
        double result = NumberUtility.findMax(NOT_LARGEST, NOT_LARGEST, LARGEST, NOT_LARGEST);

        assertEquals(LARGEST, result, 0.0);
    }

    @Test
    public void findMax_whenFourthNumberIsLargest_ShouldReturnFourthNumber() {
        double result = NumberUtility.findMax(NOT_LARGEST, NOT_LARGEST, NOT_LARGEST, LARGEST);

        assertEquals(LARGEST, result, 0.0);
    }
}