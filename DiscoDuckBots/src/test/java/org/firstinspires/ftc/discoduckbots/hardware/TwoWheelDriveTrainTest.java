package org.firstinspires.ftc.discoduckbots.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TwoWheelDriveTrainTest {


    private static final double DELTA = 0.0;

    private static final double STOPPED_POWER = 0.0;
    private static final double FULL_SPEED_FORWARD = 1.0;
    private static final double FULL_SPEED_BACKWARD = -1.0;

    private static final double NO_ROTATION = 0.0;
    private static final double FULL_ROTATE_LEFT = -1.0;
    private static final double FULL_ROTATE_RIGHT = 1.0;


    TwoWheelDrivetrain twoWheelDrivetrain = new TwoWheelDrivetrain(Mockito.mock(DcMotor.class), Mockito.mock(DcMotor.class));

    @Test
    public void getLeftPower_whenNotMovingForwardAndFullTurnLeft_LeftPowerShouldEqualMinusOne(){
        double result = twoWheelDrivetrain.getLeftPower(STOPPED_POWER, FULL_ROTATE_LEFT);

        assertEquals(-1.0, result, DELTA);
    }

    @Test
    public void getLeftPower_whenMovingForwardFullSpeedAndNotTurning_LeftPowerShouldEqualOne(){
        double result = twoWheelDrivetrain.getLeftPower(FULL_SPEED_FORWARD, NO_ROTATION);

        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void getLeftPower_whenMovingForwardFullSpeedAndFullTurnRight_LeftPowerShouldEqualOne(){
        double result = twoWheelDrivetrain.getLeftPower(FULL_SPEED_FORWARD, FULL_ROTATE_RIGHT);

        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void getLeftPower_whenMovingBackwardFullSpeedAndFullTurnLeft_LeftPowerShouldEqualMinusOne(){
        double result = twoWheelDrivetrain.getLeftPower(FULL_SPEED_BACKWARD, FULL_ROTATE_LEFT);

        assertEquals(-1.0, result, DELTA);
    }

    @Test
    public void getRightPower_WhenMovingForwardFullSpeedAndNotTurning_RightPowerShouldEqualOne(){
        double result = twoWheelDrivetrain.getRightPower(FULL_SPEED_FORWARD, NO_ROTATION);

        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void getRightPower_WhenNotMovingForwardAndFullTurnLeft_RightPowerShouldEqualOne(){
        double result = twoWheelDrivetrain.getRightPower(STOPPED_POWER, FULL_ROTATE_LEFT);

        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void getRightPower_WhenNotMovingForwardAndFullTurnRight_RightPowerShouldEqualMinusOne(){
        double result = twoWheelDrivetrain.getRightPower(STOPPED_POWER, FULL_ROTATE_RIGHT);

        assertEquals(-1.0, result, DELTA);
    }

    @Test
    public void getRightPower_WhenMovingForwardFullSpeedAndFullTurnRight_RightPowerShouldEqualZero(){
        double result = twoWheelDrivetrain.getRightPower(FULL_SPEED_FORWARD, FULL_ROTATE_RIGHT);

        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void getRightPower_WhenMovingForwardFullSpeedAndFullTurnLeft_RightPowerShouldEqualOne(){
        double result = twoWheelDrivetrain.getRightPower(FULL_SPEED_FORWARD, FULL_ROTATE_LEFT);

        assertEquals(1.0, result, DELTA);
    }
}