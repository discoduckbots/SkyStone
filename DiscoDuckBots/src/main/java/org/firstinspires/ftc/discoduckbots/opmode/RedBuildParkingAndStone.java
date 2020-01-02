package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.IntakeWheels;
import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.discoduckbots.hardware.MotorBasedDragger;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="RedBuildParkingAndStone", group="Linear Opmode")
public class RedBuildParkingAndStone extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    private MotorBasedDragger mDragger = null;
    private IntakeWheels mIntakeWheels = null;


    @Override
    public void runOpMode() throws InterruptedException {
    }


    private void autonomousByEncoder() {
        double autonomousSpeed = 0.10;

        //facing forwards
        sleep(2000);
        mMecanumDrivetrain.driveByDistance(40, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        mIntakeWheels.spinInward();
        mMecanumDrivetrain.driveByDistance(60, MecanumDrivetrain.DIRECTION_FORWARD, autonomousSpeed);
        mIntakeWheels.stop();
        mMecanumDrivetrain.driveByDistance(60, MecanumDrivetrain.DIRECTION_REVERSE, autonomousSpeed);
        mMecanumDrivetrain.driveByDistance(40, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, autonomousSpeed);
        mIntakeWheels.spinOutward();
        sleep(50);
        mMecanumDrivetrain.driveByDistance(53, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()) {
            telemetry.addData("Step 6", "Strafe Right 55\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();
    }
    }
