package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;

public class BuildingAutonomous extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;

    @Override
    public void runOpMode() throws InterruptedException {


        // initialize

        // wait for start
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

        }

        // Cleanup
    }
}
