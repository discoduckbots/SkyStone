package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.IntakeWheels;
import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueStoneSide", group="Linear Opmode")
public class BlueStoneSide extends LinearOpMode {
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

        autonomousByEncoder();
    }

    public void autonomousByEncoder(){
        double autonomousSpeed = .25;

        //01. Intake Wheels In
        mIntakeWheels.spinInward();

        //02. Drive Forward 50 Inches
        mMecanumDrivetrain.driveByDistance(50, MecanumDrivetrain.DIRECTION_FORWARD, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 2", "Drive Forward 50\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();


        //03. Intake Wheels Stop
        mIntakeWheels.stop();

        //04. Drive Reverse 25 Inches
        mMecanumDrivetrain.driveByDistance(25, MecanumDrivetrain.DIRECTION_REVERSE, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 4", "Drive Reverse 25\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //05. Strafe Left 42 Inches
        mMecanumDrivetrain.driveByDistance(42, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 5", "Strafe Left 42\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //06. Intake Wheels Out
        mIntakeWheels.spinOutwardByTime(this, 2);

        //07. Intake Wheels Stop
        mIntakeWheels.stop();

        //08. Strafe Right 66 Inches
        mMecanumDrivetrain.driveByDistance(66, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 8", "Strafe Right 66\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //09. Intake Wheels In
        mIntakeWheels.spinInward();

        //10. Drive Forward 35 Inches
        mMecanumDrivetrain.driveByDistance(35, MecanumDrivetrain.DIRECTION_FORWARD, 0.2);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 10", "Drive Forward 35\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //11. Intake Wheels Stop
        mIntakeWheels.stop();

        //12. Drive Reverse 25 Inches
        mMecanumDrivetrain.driveByDistance(35, MecanumDrivetrain.DIRECTION_REVERSE, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 12", "Drive Reverse 15\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //13. Strafe Left 66 Inches
        mMecanumDrivetrain.driveByDistance(66, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 13", "Strafe Left 66\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //14. Intake Wheels Out
        mIntakeWheels.spinOutwardByTime(this, 2);

        //15. Intake Wheels Stop
        mIntakeWheels.stop();

        //16. Strafe Right 18 Inches
        mMecanumDrivetrain.driveByDistance(18, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 16", "Strafe Right 18\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //17. Drive Forward 5 Inches
        mMecanumDrivetrain.driveByDistance(8, MecanumDrivetrain.DIRECTION_FORWARD, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 17", "Drive Forward 5\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();
    }

    public void autonomousByTime(){
        //moveTowardsTheBlock();
        mMecanumDrivetrain.forwardByTime(this, 0.5, 0.25);

        //takeTheStone();
        mIntakeWheels.spinInwardByTime(this, 1);
        mIntakeWheels.stop();

        //goToBuildingSite();
        mMecanumDrivetrain.backwardByTime(this, .5, .89);
        mMecanumDrivetrain.strafeLeftByTime(this, .5, 1.4);
        mMecanumDrivetrain.stop();

        //dropStone();
        mIntakeWheels.spinOutwardByTime(this, 1);

        //goToSecondStone();
        mMecanumDrivetrain.strafeRightByTime(this,.5,2);
        mMecanumDrivetrain.backwardByTime(this,.3,1);
        mMecanumDrivetrain.forwardByTime(this,.5,.28);

        //takeTheStone();
        mIntakeWheels.spinInwardByTime(this, 1);
        mIntakeWheels.stop();

        //goToBuildingSite2();
        mMecanumDrivetrain.backwardByTime(this,.5,.89);
        mMecanumDrivetrain.strafeLeftByTime(this,.5,2);
        mMecanumDrivetrain.stop();

        //dropStone();
        mIntakeWheels.spinOutwardByTime(this, 1);

        //parkUnderBridge();
        mMecanumDrivetrain.strafeRightByTime(this,.5,.5);
        mMecanumDrivetrain.forwardByTime(this,.2,.85);
        mMecanumDrivetrain.stop();
    }
}
