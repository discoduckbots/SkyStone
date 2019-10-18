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

    public void lift(){

    }

    public void lower(){

    }

    public void grab(){
        mGrabber.setPosition(1);
    }

    public void release() {
        mGrabber.setPosition(0);
    }
    public void flip(){

    }

    public void flop(){

    }
}
