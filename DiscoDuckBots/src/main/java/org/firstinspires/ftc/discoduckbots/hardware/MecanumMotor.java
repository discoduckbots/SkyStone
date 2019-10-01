package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumMotor {
    // TODO: What is the diameter of the wheels in inches
    private static final double WHEEL_DIAMETER = 1;
    private DcMotor mFrontLeft;
    private DcMotor mFrontRight;
    private DcMotor mBackLeft;
    private DcMotor mBackRight;
    Telemetry mTelemetry;

    public MecanumMotor(Telemetry telemetry,
                        DcMotor frontLeft, DcMotor frontRight,
                        DcMotor backLeft, DcMotor backRight ) {
        mTelemetry = telemetry;
        mFrontLeft = frontLeft;
        mFrontRight = frontRight;
        mBackLeft = backLeft;
        mBackRight = backRight;
    }

    public void drive(double speedX, double speedY, double rotation) {
        double fl = speedX + speedY + rotation;
        double fr = -speedX + speedY - rotation;
        double bl= -speedX + speedY + rotation;
        double br = speedX + speedY - rotation;

        double max = findMax(fl, fr, bl, br);
        if (max > 1) {
            fl = fl / max;
            fr = fr / max;
            bl = bl / max;
            br = br / max;
        }

        mFrontRight.setPower(fr);
        mFrontLeft.setPower(fl);
        mBackRight.setPower(br);
        mBackLeft.setPower(bl);

        mTelemetry.addData("Motors",
                "frontL (%.2f), frontR (%.2f), backL (%.2f), backR (%.2f)",
                fl, fr, bl, br);

    }

    private double findMax(double v1, double v2, double v3, double v4) {
        double[] values = new double[4];
        values[0] = v1;
        values[1] = v2;
        values[0] = v3;
        values[0] = v4;

        double max = values[0];
        for (double value : values) {
            if (value > max) max = value;
        }
        return max;
    }

    public void stop() {
        mFrontLeft.setPower(0);
        mFrontRight.setPower(0);
        mBackLeft.setPower(0);
        mBackRight.setPower(0);

    }

    public void driveByDistance(int distance) {
        int target = convertDistanceToTarget(distance);
        mFrontLeft.setTargetPosition(target);
        mFrontRight.setTargetPosition(target);
        mBackLeft.setTargetPosition(target);
        mBackRight.setTargetPosition(target);
    }

    private int convertDistanceToTarget(int distance) {
        return (int)(distance / (Math.PI * WHEEL_DIAMETER));
    }
}
