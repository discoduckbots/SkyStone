package org.firstinspires.ftc.discoduckbots.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.discoduckbots.hardware.Arm;
import org.firstinspires.ftc.discoduckbots.hardware.IntakeWheels;
import org.firstinspires.ftc.discoduckbots.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.discoduckbots.util.TensorFlowSkystoneFinder;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueStoneSide-TF", group="Linear Opmode")
public class BlueStoneSideWithTensorflow extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private static final String VUFORIA_KEY = "AYSk32L/////AAABmbhg8GZe7kWfmZUNbwUuIPIF4dklwa5nY6Be4MuPWPpva8SYxSc/pUq/kc9kdl8Bh7w7t8PjWaJGfLRGug7l/wswCDj2V2Ag+hsG2zUDnAY55qbbiTzIjyt2qJzfYIK5Ipojsz7KmEiAWC7DUf9C64jez6LEDJEYYwtR+W2RrTl0DRRYpVmMGk31aF5ZbHC77dTEvpT5xCGAC35F2R53bYW9eUbDMiQWnfKTKOxLA8oEsA5pI42IJhZvFqfSFYsTaLp7DymS8b3QVwn4jOvWMh+sdloU9f1fE14yolR4wcIzbiFcSA2eJTGYfwUcopLlpZsE4A3XdKRx/AIExFADF5qAaAW02wMILYxWQYXDBQ4m";

    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrivetrain mMecanumDrivetrain = null;
    private IntakeWheels mIntakeWheels = null;
    private Arm mArm = null;

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private TensorFlowSkystoneFinder skystoneFinder = new TensorFlowSkystoneFinder();

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

        DcMotor linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        Servo wrist = hardwareMap.get(Servo.class, "wrist");
        Servo grabber = hardwareMap.get(Servo.class, "grabber");
        mArm = new Arm(linearSlide, wrist, grabber);

        initTensorflow(telemetry);

        waitForStart();
        runtime.reset();

        autonomousByEncoder();
    }

    public void autonomousByEncoder(){
        double autonomousSpeed = .25;
        int strafeOffset = 0;

        mArm.flip();
        mArm.release();

        //01. Get Tensorflow Data to Determine Dice Roll & set Strafe Offset
        List<Recognition> recognitionList = tfod.getRecognitions();

        Integer diceRoll = skystoneFinder.getSkystoneDiceRoll(telemetry, recognitionList, false);

        if (Integer.valueOf(2).equals(diceRoll)){
            strafeOffset = 9;
        }
        else if (Integer.valueOf(1).equals(diceRoll)){
            strafeOffset = 16;
        }

        telemetry.addData("number of recognitions: ", recognitionList.size());
        telemetry.addData("die roll: ", diceRoll);
        telemetry.addData("strafe offset: ", strafeOffset);
        telemetry.update();

        //02. Strafe Right by Offset Amount
        mMecanumDrivetrain.driveByDistance(strafeOffset, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
//            telemetry.addData("Step 2", "Strafe Right by Offset: " + strafeOffset);
//            telemetry.update();
        }

        //03. Intake Wheels In
        mIntakeWheels.spinInward();

        //04. Drive Forward 50 Inches
        mMecanumDrivetrain.driveByDistance(60, MecanumDrivetrain.DIRECTION_FORWARD, .15);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
//            telemetry.addData("Step 5", "Drive Forward 50\"");
            telemetry.addData("number of recognitions: ", recognitionList.size());
            telemetry.addData("die roll: ", diceRoll);
            telemetry.addData("strafe offset: ", strafeOffset);
            telemetry.addData("Encoder Values: ", mMecanumDrivetrain.getEncoderCounts());
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //05. Intake Wheels Stop
        mIntakeWheels.stop();

        //06. Drive Reverse 25 Inches
        mMecanumDrivetrain.driveByDistance(25, MecanumDrivetrain.DIRECTION_REVERSE, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 6", "Drive Reverse 25\"");
//            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //07. Strafe Left 42 Inches + Strafe Offset
        mMecanumDrivetrain.driveByDistance(42 + strafeOffset, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 7", "Strafe Left 42\"");
//            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        mArm.grab();

        //08. Intake Wheels Out
        mIntakeWheels.spinOutwardByTime(this, 1.5);

        mArm.release();

        //09. Intake Wheels Stop
        mIntakeWheels.stop();

        //10. Strafe Right 66 + Strafe Offset Inches
        mMecanumDrivetrain.driveByDistance(63 + strafeOffset, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 10", "Strafe Right 66\"");
//            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //11. Intake Wheels In
        mIntakeWheels.spinInward();

        //12. Drive Forward 25 Inches
        mMecanumDrivetrain.driveByDistance(32, MecanumDrivetrain.DIRECTION_FORWARD, .15);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
//            telemetry.addData("Step 12", "Drive Forward 35\"");
            telemetry.addData("Encoder Values: ", mMecanumDrivetrain.getEncoderCounts());
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //13. Intake Wheels Stop
        mIntakeWheels.stop();

        //14. Drive Reverse 25 Inches
        mMecanumDrivetrain.driveByDistance(28, MecanumDrivetrain.DIRECTION_REVERSE, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 14", "Drive Reverse 25\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        //15. Strafe Left 66 + Strafe Offset Inches
        mMecanumDrivetrain.driveByDistance(67 + strafeOffset, MecanumDrivetrain.DIRECTION_STRAFE_LEFT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 15", "Strafe Left 66\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();

        mArm.release();

        //16. Intake Wheels Out
        mIntakeWheels.spinOutwardByTime(this, 1.5);

        //17. Intake Wheels Stop
        mIntakeWheels.stop();

        //18. Strafe Right 18 Inches
        mMecanumDrivetrain.driveByDistance(18, MecanumDrivetrain.DIRECTION_STRAFE_RIGHT, autonomousSpeed);
        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
            telemetry.addData("Step 18", "Strafe Right 18\"");
            telemetry.update();
        }
        mMecanumDrivetrain.stop();
//        //19. Drive Forward 6 Inches
//        mMecanumDrivetrain.driveByDistance(6, MecanumDrivetrain.DIRECTION_FORWARD, autonomousSpeed);
//        while (opModeIsActive() && mMecanumDrivetrain.isMoving()){
//            telemetry.addData("Step 19", "Drive Forward 6\"");
//            telemetry.update();
//        }
//        mMecanumDrivetrain.stop();
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia(Telemetry telemetry) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTensorflow(Telemetry telemetry) {
        initVuforia(telemetry);

        if (!ClassFactory.getInstance().canCreateTFObjectDetector()) {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);

        if (tfod != null) {
            tfod.activate();
        }else {
            telemetry.addData("TDOD is null", "");
            telemetry.update();
        }
    }
}