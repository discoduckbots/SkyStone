package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class TwoWheelDrivetrain implements DrivetrainInterface {

    private static final double MAX_POWER = 1.0;
    private static final double MIN_POWER = -1.0;

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    public TwoWheelDrivetrain(DcMotor leftMotor, DcMotor rightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void drive(double speedX, double speedY, double rotation) {
        leftMotor.setPower(getLeftPower(speedY, rotation));
        rightMotor.setPower(getRightPower(speedY, rotation));
    }

    public double getLeftPower(double speedY, double rotation){

        double power = speedY + rotation;

        return Range.clip(power, MIN_POWER, MAX_POWER);
    }

    public double getRightPower(double speedY, double rotation){
        double power = speedY - rotation;

        return Range.clip(power, MIN_POWER, MAX_POWER);
    }

    @Override
    public void stop() {
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }

    public DcMotor getLeftMotor() {
        return leftMotor;
    }

    public DcMotor getRightMotor() {
        return rightMotor;
    }

}
