package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.IntakeWheels;
import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="RedStoneSide", group="Linear Opmode")
public class RedStoneSide extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    private IntakeWheels mIntakeWheels = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // initialize hardware
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);

        DcMotor intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        DcMotor intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        mIntakeWheels = new IntakeWheels(intakeLeft, intakeRight);

        waitForStart();
        runtime.reset();

        autonomousByTime();
    }

    public void autonomousByTime(){
        //moveTowardsTheBlock();
        mMecanumDrivetrain.forwardByTime(this, 0.5, 0.25);

        //takeTheStone();
        mIntakeWheels.spinInwardByTime(this, 1);
        mIntakeWheels.stop();

        //goToBuildingSite();
        mMecanumDrivetrain.backwardByTime(this, .5, .89);
        mMecanumDrivetrain.strafeRightByTime(this, .5, 1.5);
        mMecanumDrivetrain.stop();

        //dropStone();
        mIntakeWheels.spinOutwardByTime(this, 1);

        //goToSecondStone();
        mMecanumDrivetrain.strafeLeftByTime(this,.5,2.1);
        mMecanumDrivetrain.backwardByTime(this,.5,.5);
        mMecanumDrivetrain.forwardByTime(this,.5,.25);

        //takeTheStone();
        mIntakeWheels.spinInwardByTime(this, 1);
        mIntakeWheels.stop();

        //goToBuildingSite2();
        mMecanumDrivetrain.backwardByTime(this,.5,.89);
        mMecanumDrivetrain.strafeRightByTime(this,.5,2);
        mMecanumDrivetrain.stop();

        //dropStone();
        mIntakeWheels.spinOutwardByTime(this, 1);

        //parkUnderBridge();
        mMecanumDrivetrain.strafeLeftByTime(this,.5,.35);
        mMecanumDrivetrain.forwardByTime(this,.2,.7);
        mMecanumDrivetrain.stop();
    }
}