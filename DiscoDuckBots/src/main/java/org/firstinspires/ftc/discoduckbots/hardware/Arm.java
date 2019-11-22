package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    private DcMotor mLinearSlide;
    private Servo mWrist;
    private Servo mGrabber;

    private final static int GRABBER_UP = 0;
    private final static int GRABBER_DOWN = 1;

    public Arm (DcMotor linearSlide, Servo wrist, Servo grabber){
        mLinearSlide = linearSlide;
        mWrist = wrist;
        mGrabber = grabber;
    }

    public void moveVertical(double power){
        mLinearSlide.setPower(power);
    }

    public void grab(){
        mGrabber.setPosition(1);
    }

    public void release() {
        mGrabber.setPosition(0);
    }
    public void flip(){
        mWrist.setDirection(Servo.Direction.FORWARD);
        mWrist.setPosition(1.0);
    }

    public void flop(){
        mWrist.setDirection(Servo.Direction.REVERSE);
        mWrist.setPosition(0.0);
    }

    public void stop() {
        mLinearSlide.setPower(0);
    }
}
