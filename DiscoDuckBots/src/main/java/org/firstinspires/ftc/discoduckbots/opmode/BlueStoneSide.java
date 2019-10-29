package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.IntakeWheels;
import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueStoneSide", group="Linear Opmode")
public class BlueStoneSide extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    private IntakeWheels mIntakeWheels = null;


    @Override
    public void runOpMode() throws InterruptedException {


        // initialize
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);
        DcMotor intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        DcMotor intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        mIntakeWheels = new IntakeWheels(intakeLeft, intakeRight);

        // wait for sart
        waitForStart();
        runtime.reset();


        competition2Autonomous();


       // competition1Autonomous();
    }

    private void competition1Autonomous() {
        // run until the end of the match (driver presses STOP)
        mMecanumDrivetrain.forwardByTime(this, .5,.46);
        mMecanumDrivetrain.stop();
        mMecanumDrivetrain.strafeRightByTime(this, .5,.5);
        mMecanumDrivetrain.stop();
        mMecanumDrivetrain.strafeLeftByTime(this,.5,.7);
        mMecanumDrivetrain.stop();
    }

    private void competition2Autonomous() {
        moveTowardsTheBlock();

        int position = findSkystonePosition();

        moveTowardsStone(position);

        takeTheStone();

        goToBuildingSite();

        dropStone();

        parkUnderBridge();
    }

    private void parkUnderBridge() {
        mMecanumDrivetrain.strafeRightByTime(this,.5,.2);
    }

    private void dropStone() {
        mIntakeWheels.spinOutwardByTime(this, 1);
    }

    private void goToBuildingSite() {
        mMecanumDrivetrain.backwardByTime(this, .5, .1);
        mMecanumDrivetrain.strafeLeftByTime(this, .5, 1);
    }

    private void takeTheStone() {
        mIntakeWheels.spinInwardByTime(this, 1);
    }

    private void moveTowardsStone(int position) {

    }

    private int findSkystonePosition() {
        return 1;
    }

    private void moveTowardsTheBlock() {
        mMecanumDrivetrain.forwardByTime(this,.5,.55);
    }
}
