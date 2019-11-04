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
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;


    @Override
    public void runOpMode() throws InterruptedException {



        // initialize
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        CRServo dragger  = hardwareMap.get(CRServo.class, "dragger");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);


        // wait for start
        waitForStart();
        runtime.reset();
        mMecanumDrivetrain.backwardByTime(this, .5,.6);
        mMecanumDrivetrain.stop();
        sleep(10);

        grabFoundation(dragger);
        mMecanumDrivetrain.forwardByTime(this, .5, 1.3);
        mMecanumDrivetrain.stop();
        sleep(10);

        releaseFoundation(dragger);
        mMecanumDrivetrain.strafeRightByTime(this,.5,1.5);
        mMecanumDrivetrain.stop();
        // run until the end of the match (driver presses STOP)

    }

    private void releaseFoundation(CRServo dragger) {
        dragger.setPower(-1);
        sleep(4000);
        dragger.setPower(0);


    }

    private void grabFoundation(CRServo dragger) {
        dragger.setPower(1);
        sleep(4000);

    }
}


