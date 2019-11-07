package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="RedBuildSide", group="Linear Opmode")
public class RedBuildSide extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    private CRServo mDragger = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // initialize hardware
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);
        mDragger  = hardwareMap.get(CRServo.class, "dragger");

        // wait for start
        waitForStart();
        runtime.reset();

        autonomousByTime();
    }

    private void autonomousByTime(){
        //Strafe to Position
        mMecanumDrivetrain.strafeLeftByTime(this,.3,2.4);

        //Drive to foundation
        mMecanumDrivetrain.backwardByTime(this, .5,.68);
        mMecanumDrivetrain.stop();
        sleep(10);

        //Grab Foundation
        grabFoundation();

        //Pull into Build Area
        mMecanumDrivetrain.forwardByTime(this, .5, 1.4);
        mMecanumDrivetrain.stop();
        sleep(10);

        //Release Foundation
        releaseFoundation();

        //Park under Bridge
        mMecanumDrivetrain.strafeRightByTime(this,.5,1.5);
        mMecanumDrivetrain.stop();
    }

    private void releaseFoundation() {
        mDragger.setPower(-1);
        sleep(4000);
        mDragger.setPower(0);
    }

    private void grabFoundation() {
        mDragger.setPower(1);
        sleep(4000);
    }
}


