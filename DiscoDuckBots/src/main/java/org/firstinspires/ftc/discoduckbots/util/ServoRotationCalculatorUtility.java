package org.firstinspires.ftc.discoduckbots.util;

public class ServoRotationCalculatorUtility {

    public double calculatePosition(double degreeRotation, double maxTravel){

        if (degreeRotation > maxTravel) {
            throw new IllegalArgumentException("rotation cannot exceed max travel");
        }

        return degreeRotation / maxTravel;
    }

}
