package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.discoduckbots.util.NumberUtility;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrivetrain implements DrivetrainInterface {
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
    public MecanumDrivetrain(Telemetry telemetry,
                             DcMotor frontLeft, DcMotor frontRight,
                             DcMotor backLeft, DcMotor backRight ) {
        mTelemetry = telemetry;
        mFrontLeft = frontLeft;
        mFrontRight = frontRight;
        mBackLeft = backLeft;
        mBackRight = backRight;

        mBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        mBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        mFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        mFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        mBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    /**
     * This function makes the mecanum motor drive using the joystick
     * @param speedX - the x value of the joystick controlling straf
     * @param speedY - the y value of the joystick controlling the forward/backwards motion
     * @param rotation - the x value of the joystick controlling the rotation
     */
    public void drive(double speedX, double speedY, double rotation) {
        mTelemetry.addData("speedX", speedX);
        mTelemetry.addData("speedY", speedY);
        mTelemetry.addData("rotation", rotation);
        mTelemetry.update();

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
    }

    /**
     * This function stops the mecanum motor
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
     * After calling this function make sure to add the following to wait for the
     * robot to finish moving
     * while (opModeIsActive() && mecanumMotor.isBusy())  {
     *     idle();
     * }
     * @param distance - positive value in inches to move forward
     *                 - negative value in inches to move backwards
     */
    public void driveByDistance(int power, int distance) {
        int direction = 1;
        // if the distance is negative, move all the wheels in opposite direction
        if (distance < 1) direction = -1;

        // convert the absolute distance to number of rotations
        int target = convertDistanceToTarget(Math.abs(distance));
        mTelemetry.addData("driveByDistance: ", "target: ", target);

        // set the power of the motors according to the direction they need to go to straf
        mFrontLeft.setPower(power * direction );
        mFrontRight.setPower(power * direction);
        mBackLeft.setPower(power * direction);
        mBackRight.setPower(power * direction);

        // set all motors except the front left to go without encoder
        mFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // use the front left - just picked random to measure the rotations
        // and set the rotations to the distance to go
        mFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mFrontLeft.setTargetPosition(target);
    }

    /**
     * This function moves the mecanum motor sideways by specifying the distance to move
     * After calling this function make sure to add the following to wait for the
     * robot to finish moving
     * while (opModeIsActive() && mecanumMotor.isBusy())  {
     *   idle();
     * }
     * @param distance - positive value in inches to straf to the right
     *                 - negative value in inches to straf to the left
     */
    // https://stemrobotics.cs.pdx.edu/node/4746
    public void strafByDistance(int power, int distance) {
        int direction = 1;
        // if the distance is negative, move all the wheels in opposite direction
        if (distance < 1) direction = -1;

        // convert the absolute distance to number of rotations
        int target = convertDistanceToTarget(Math.abs(distance));
        mTelemetry.addData("strafByDistance: ", "target: ", target);

        // set the power of the motors according to the direction they need to go to straf
        mFrontLeft.setPower(power * direction );
        mFrontRight.setPower(power * direction * (-1));
        mBackLeft.setPower(power * direction * (-1));
        mBackRight.setPower(power * direction);

        // set all motors except the front left to go without encoder
        mFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // use the front left - just picked random to measure the rotations
        // and set the rotations to the distance to go
        mFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mFrontLeft.setTargetPosition(target);
    }

    private int convertDistanceToTarget(int distance) {
        return (int)(distance / (Math.PI * WHEEL_DIAMETER));
    }

    /**
     * Returns if atleast one of the wheels is moving
     * @return
     */
    public boolean isMoving() {
        // TODO should this be all && or || ?
        return mFrontLeft.isBusy() && mFrontRight.isBusy() && mBackRight.isBusy() && mBackLeft.isBusy();
    }

    public void frontLeftForward(double power) {
        mFrontLeft.setPower(power);
        mFrontRight.setPower(0);
        mBackLeft.setPower(0);
        mBackRight.setPower(0);
    }

    public void  frontRightForward (double power)  {
        mFrontRight.setPower(power);
        mFrontLeft.setPower(0);
        mBackRight.setPower(0);
        mBackLeft.setPower(0);
    }

    public void backLeftForward(double power) {
        mBackLeft.setPower(power);
        mBackRight.setPower(0);
        mFrontLeft.setPower(0);
        mFrontRight.setPower(0);
    }

    public void  backRightForward (double power)  {
        mBackRight.setPower(power);
        mBackLeft.setPower(0);
        mFrontLeft.setPower(0);
        mFrontRight.setPower(0);
    }

    public  void strafeRightByRevolution (int revolutions, double power) {
        mFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFrontRight.setTargetPosition(revolutions);
        mFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mFrontLeft.setPower(power);
        mBackRight.setPower(power);
        mFrontRight.setPower(power * (-1));
        mBackLeft.setPower(power * (-1));
    }

    public  void moveForwardByRevolution (int revolutions, double power) {
        mFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFrontRight.setTargetPosition(revolutions);
        mFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //mFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //mFrontLeft.setPower(power);
        mBackRight.setPower(power);
        mFrontRight.setPower(power);
        mBackLeft.setPower(power);
    }
}
