package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.discoduckbots.util.NumberUtility;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumMotor {
    // TODO: What is the diameter of the wheels in inches
    private static final double WHEEL_DIAMETER = 1;
    private DcMotor mFrontLeft;
    private DcMotor mFrontRight;
    private DcMotor mBackLeft;
    private DcMotor mBackRight;
    private Telemetry mTelemetry;

    /**
     * Creates a mecanum motor using the 4 individual motors passed in as the arguments
     * @param telemetry : Telemetry to send messages to the Driver Control
     * @param frontLeft : Front left motor
     * @param frontRight : Front right motor
     * @param backLeft : Back left motor
     * @param backRight : Back right motor
     */
    public MecanumMotor(Telemetry telemetry,
                        DcMotor frontLeft, DcMotor frontRight,
                        DcMotor backLeft, DcMotor backRight ) {
        mTelemetry = telemetry;
        mFrontLeft = frontLeft;
        mFrontRight = frontRight;
        mBackLeft = backLeft;
        mBackRight = backRight;
    }

    /**
     * This function makes the mecanum motor drive using the joystick
     * @param speedX - the x value of the joystick controlling straf
     * @param speedY - the y value of the joystick controlling the forward/backwards motion
     * @param rotation - the x value of the joystick controlling the rotation
     */
    public void drive(double speedX, double speedY, double rotation) {
        double fl = speedX + speedY + rotation;
        double fr = -speedX + speedY - rotation;
        double bl= -speedX + speedY + rotation;
        double br = speedX + speedY - rotation;

        double max = NumberUtility.findMax(fl, fr, bl, br);
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

    /**
     * This function stops the mechanum motor
     */
    public void stop() {
        mFrontLeft.setPower(0);
        mFrontRight.setPower(0);
        mBackLeft.setPower(0);
        mBackRight.setPower(0);

    }

    // TODO: Not sure whether encoders will work for mecanum wheels
    // Added this to try it out

    /**
     * This function moves the mecanum motor forward or backward by specifying the distance to move
     * @param distance - positive value in inches to move forward
     *                 - negative value in inches to move backwards
     */
    public void driveByDistance(int distance) {
        int direction = 1;
        // if the distance is negative, move all the wheels in opposite direction
        if (distance < 1) direction = -1;

        // convert the absolute distance to number of rotations
        int target = convertDistanceToTarget(Math.abs(distance));

        mFrontLeft.setTargetPosition(target * direction);
        mFrontRight.setTargetPosition(target * direction);
        mBackLeft.setTargetPosition(target * direction);
        mBackRight.setTargetPosition(target * direction);
    }

    /**
     * This function moves the mecanum motor sideways by specifying the distance to move
     * @param distance - positive value in inches to straf to the right
     *                 - negative value in inches to straf to the left
     */
    public void strafByDistance(int distance) {
        int direction = 1;
        // if the distance is negative, move all the wheels in opposite direction
        if (distance < 1) direction = -1;

        // convert the absolute distance to number of rotations
        int target = convertDistanceToTarget(Math.abs(distance));

        mFrontLeft.setTargetPosition(target * direction);
        mFrontRight.setTargetPosition(target * direction * (-1));
        mBackLeft.setTargetPosition(target * direction * (-1));
        mBackRight.setTargetPosition(target * direction);
    }

    private int convertDistanceToTarget(int distance) {
        return (int)(distance / (Math.PI * WHEEL_DIAMETER));
    }
}
