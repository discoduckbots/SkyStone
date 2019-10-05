package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumMotor;

public class SkystoneAutonomous extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumMotor mMecanumMotor = null;


    @Override
    public void runOpMode() throws InterruptedException {


        // initialize

        // wait for sart
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            moveTowardsTheBlock();

            int position = findSkystonePosition();

            moveTowardsStone(position);

            takeTheStone();

            goToBuildingSite();

            dropStone();

            parkUnderBridge();

        }

        // Cleanup
    }

    private void parkUnderBridge() {

    }

    private void dropStone() {

    }

    private void goToBuildingSite() {

    }

    private void takeTheStone() {

    }

    private void moveTowardsStone(int position) {

    }

    private int findSkystonePosition() {
        return 0;
    }

    private void moveTowardsTheBlock() {



    }
}
