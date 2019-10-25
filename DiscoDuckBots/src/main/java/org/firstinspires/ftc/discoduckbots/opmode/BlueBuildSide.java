package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueBuildSide", group="Linear Opmode")
public class BlueBuildSide extends LinearOpMode {
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
        Servo dragger  = hardwareMap.get(Servo.class, "dragger");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);


        // wait for start
        waitForStart();
        runtime.reset();

        mMecanumDrivetrain.strafeRightByTime(this,.5,3);
        mMecanumDrivetrain.backwardByTime(this, .5,3);
        grabFoundation(dragger);
        mMecanumDrivetrain.forwardByTime(this, .5, 3);
        releaseFoundation(dragger);
        mMecanumDrivetrain.strafeLeftByTime(this,1,3);
        mMecanumDrivetrain.stop();
        // run until the end of the match (driver presses STOP)

    }

    private void releaseFoundation(Servo dragger) {
        dragger.setDirection(Servo.Direction.FORWARD);
        dragger.setPosition(0.0);
    }

    private void grabFoundation(Servo dragger) {
        dragger.setDirection(Servo.Direction.FORWARD);
        dragger.setPosition(1.0);

    }
}
