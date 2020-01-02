package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.discoduckbots.util.ServoRotationCalculatorUtility;

public class CapstoneServo {

    private static final double GOBILDA_SERVO_MAX_TRAVEL = 280.0;
    private static final double HITEC485HB_SERVO_MAX_TRAVEL = 190.5;

    private Servo capstoneRotatorServo;

    public CapstoneServo (Servo capstoneRotatorServo){
        this.capstoneRotatorServo = capstoneRotatorServo;
    }

    public void rotateIntoScoringPosition(){
        ServoRotationCalculatorUtility rotationCalculator = new ServoRotationCalculatorUtility();
        double position = rotationCalculator.calculatePosition(85, HITEC485HB_SERVO_MAX_TRAVEL);
        capstoneRotatorServo.setDirection(Servo.Direction.FORWARD);
        capstoneRotatorServo.setPosition(position);
    }

    public void returnToStartPosition(){
        capstoneRotatorServo.setPosition(0.0);
    }
}
