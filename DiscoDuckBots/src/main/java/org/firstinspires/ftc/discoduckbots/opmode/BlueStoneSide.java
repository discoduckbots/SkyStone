package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueStoneSide", group="Linear Opmode")
public class BlueStoneSide extends LinearOpMode {
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
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);


        // wait for sart
        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        mMecanumDrivetrain.forwardByTime(this, .5,.46);
        mMecanumDrivetrain.stop();
        mMecanumDrivetrain.strafeLeftByTime(this,.5,.7);
        mMecanumDrivetrain.stop();
    }
}
