package org.firstinspires.ftc.discoduckbots.util;

import org.junit.Test;

import static org.junit.Assert.*;
public class ServoRotationCalculatorUtilityTest {

    private static final double DELTA = 0.01;

    @Test
    public void calculatePosition_whenMaxTravelIsSameAsDegreeRotation_ShouldReturn1() {
        ServoRotationCalculatorUtility servoRotationCalculatorUtility = new ServoRotationCalculatorUtility();

        double result = servoRotationCalculatorUtility.calculatePosition(180, 180);

        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void calculatePosition_whenMaxTravelIs180AndDegreeRotationIs90_ShouldReturnZeroPointFive() {
        ServoRotationCalculatorUtility servoRotationCalculatorUtility = new ServoRotationCalculatorUtility();

        double result = servoRotationCalculatorUtility.calculatePosition(90, 180);

        assertEquals(0.5, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePosition_whenMaxTravelIsLessThanDegreeRotation_ShouldThrowError(){
        ServoRotationCalculatorUtility servoRotationCalculatorUtility = new ServoRotationCalculatorUtility();

        double result = servoRotationCalculatorUtility.calculatePosition(90, 80);
    }

    @Test
    public void calculatePosition_whenMaxTravelIs280AndRotationIs90_ShouldReturn0Point3214(){
        ServoRotationCalculatorUtility servoRotationCalculatorUtility = new ServoRotationCalculatorUtility();

        double result = servoRotationCalculatorUtility.calculatePosition(90, 280);

        assertEquals(0.3214, result, DELTA);
    }
}