package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorBasedDragger {

   private DcMotor mDraggerMotor = null;

   public MotorBasedDragger (DcMotor motor) {
       mDraggerMotor = motor;
   }
   public void move(double power ) {
       mDraggerMotor.setPower(power);
   }

    public void stop(){

       mDraggerMotor.setPower(0);
    }
}

