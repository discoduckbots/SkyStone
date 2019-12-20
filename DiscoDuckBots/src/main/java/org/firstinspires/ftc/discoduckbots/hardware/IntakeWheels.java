package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class IntakeWheels {

    public static final double INTAKE_THROTTLE = 0.45;

    private DcMotor mLeftIntake;
    private DcMotor mRightIntake;

    public IntakeWheels(DcMotor leftIntake, DcMotor rightIntake){
        mLeftIntake = leftIntake;
        mRightIntake = rightIntake;

        mRightIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        mLeftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void spinInward(){
        mLeftIntake.setPower(1 * INTAKE_THROTTLE);
        mRightIntake.setPower(1 * INTAKE_THROTTLE);
    }

    public void spinOutward (){
        mLeftIntake.setPower(-.5);
        mRightIntake.setPower(-.5);
    }

    public void stop(){
        mLeftIntake.setPower(0);
        mRightIntake.setPower(0);
    }


    public void spinInwardByTime(LinearOpMode opMode, double time) {
        spinInward();
        opMode.sleep((long)(time * 1000));
    }

    public void spinOutwardByTime(LinearOpMode opMode, double time) {
        spinOutward();
        opMode.sleep((long)(time * 1000));
    }

}
