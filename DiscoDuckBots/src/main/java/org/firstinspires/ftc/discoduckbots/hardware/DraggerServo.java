package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

public class DraggerServo {
    private CRServo mServo;

    public DraggerServo(CRServo servo) {
        mServo = servo;
    }

    public void up(LinearOpMode opMode) {
        mServo.setPower(-1);
        opMode.sleep(4000);
        mServo.setPower(0);
    }

    public void down(LinearOpMode opMode) {
        mServo.setPower(1);
        opMode.sleep(4000);
        mServo.setPower(0);
    }

    public void moveUp() {
        mServo.setPower(-1);
    }
    public void moveDown() {
        mServo.setPower(1);
    }

    public void stop() {
        mServo.setPower(0);
    }
}

