package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DraggerServo {
    private CRServo mServo;
    private CRServo mServo2;

    public DraggerServo(CRServo servo, CRServo servo2) {
        mServo = servo;
        mServo2 = servo2;
        mServo2.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void up(LinearOpMode opMode) {
        mServo.setPower(-1);
        mServo2.setPower(-1);
        opMode.sleep(4000);
        mServo.setPower(0);
        mServo2.setPower(0);
    }

    public void down(LinearOpMode opMode) {
        mServo.setPower(1);
        mServo2.setPower(1);
        opMode.sleep(4000);
        mServo.setPower(0);
        mServo2.setPower(0);
    }

    public void moveUp() {
        mServo.setPower(-1);
        mServo2.setPower(-1);
    }
    public void moveDown() {
        mServo.setPower(1);
        mServo2.setPower(1);
    }

    public void stop() {
        mServo.setPower(0);
        mServo2.setPower(0);
    }
}

