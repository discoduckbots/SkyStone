package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TestMecanum OpMode", group="Linear Opmode")
@Disabled
public class TestTeleop extends LinearOpMode {


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        DcMotor frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        mMecanumDrivetrain = new MecanumDrivetrain(telemetry, frontLeft, frontRight, backLeft, backRight);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        // TODO : how are our motors connected ? Are any reversed ?
        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(gamepad1.a){
                mMecanumDrivetrain.strafeRightByRevolution(1,.5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad1.b){
                mMecanumDrivetrain.strafeRightByRevolution(5,.5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }

            if(gamepad1.x){
                mMecanumDrivetrain.strafeRightByRevolution(1,1);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad1.y){
                mMecanumDrivetrain.strafeRightByRevolution(1,.1);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad2.a){
                mMecanumDrivetrain.moveForwardByRevolution(1,.5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad2.b){
                mMecanumDrivetrain.moveForwardByRevolution(25,.5);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }

            if(gamepad2.x){
                mMecanumDrivetrain.moveForwardByRevolution(1,1);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
            if(gamepad2.y){
                mMecanumDrivetrain.moveForwardByRevolution(1,.1);
                while (opModeIsActive() && mMecanumDrivetrain.isMoving())  {
                    idle();
                }
                mMecanumDrivetrain.stop();
            }
        }

        telemetry.addData("MecanumDrivetrainTeleOp" , "Stopping");
        mMecanumDrivetrain.stop();
    }
}
