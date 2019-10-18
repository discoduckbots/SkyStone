package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    private DcMotor mLinearSlide;
    private Servo mWrist;
    private Servo mGrabber;

    public Arm (DcMotor linearSlide, Servo wrist, Servo grabber){
        mLinearSlide = linearSlide;
        mWrist = wrist;
        mGrabber = grabber;
    }

    public void lift(double power){
        mLinearSlide.setPower(power);
    }

    public void grab(){

    }

    public void release(){

    }
    public void flip(){
        mWrist.setDirection(Servo.Direction.FORWARD);
        mWrist.setPosition(1.0);
    }

    public void flop(){
        mWrist.setDirection(Servo.Direction.REVERSE);
        mWrist.setPosition(0.0);
    }
}
