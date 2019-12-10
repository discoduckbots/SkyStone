package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.Arm;
import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Test OpMode", group="Linear Opmode")
public class TestTeleop extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Arm  mArm = null;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        DcMotor linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        Servo wrist = hardwareMap.get(Servo.class, "wrist");
        Servo grabber = hardwareMap.get(Servo.class, "grabber");
        mArm = new Arm(linearSlide, wrist, grabber);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            mArm.moveVertical(gamepad2.left_stick_y);

            if (gamepad2.x) {
                mArm.flop();
            }

            if (gamepad2.y) {
                mArm.flip();
            }

            if (gamepad2.a) {
                mArm.grab();
            }

            if (gamepad2.b) {
                mArm.release();
            }
        }

        telemetry.addData("TestTeleop" , "Stopping");
    }
}
