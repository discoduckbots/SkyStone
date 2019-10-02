package org.firstinspires.ftc.discoduckbots.util;

public class NumberUtility {

    public static double findMax(double v1, double v2, double v3, double v4) {
        double max = v1;

        double[] values = new double[4];
        values[0] = v1;
        values[1] = v2;
        values[2] = v3;
        values[3] = v4;

        for (double value : values){
            if (value > max){
                max = value;
            }
        }

        return max;
    }
}
