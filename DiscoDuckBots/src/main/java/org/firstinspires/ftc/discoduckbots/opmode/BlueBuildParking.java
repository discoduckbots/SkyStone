package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.discoduckbots.hardware.MotorBasedDragger;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueBuildParking", group="Linear Opmode")
public class BlueBuildParking extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    private MotorBasedDragger mDragger = null;


    @Override
    public void runOpMode() throws InterruptedException {
        // initialize hardware
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);
        DcMotor draggerMotor = hardwareMap.get(DcMotor.class, "dragger");
        mDragger = new MotorBasedDragger(draggerMotor);

        // wait for start
        waitForStart();
        runtime.reset();

        autonomousByEncoder();
    }


    private void autonomousByEncoder() {
        double autonomousSpeed = 0.10;


        sleep(2500);
        mMecanumDrivetrain.driveByDistance(53, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()) {
            telemetry.addData("Step 6", "Strafe left 55\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();
    }
}


