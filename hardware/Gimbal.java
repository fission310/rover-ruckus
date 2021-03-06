package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

/**
 * Gimbal is the class that is used to define all of the hardware for a robot's phone gimbal.
 * Gimbal must be instantiated, then initialized using <code>init()</code> before being used.
 */
public class Gimbal extends Mechanism {

    /* CONSTANTS */
    private final double MIN_POS = .0;
    private final double NEUTRAL_POS = 0.5;
    private final double MAX_POS = 1;

    /* Hardware members */
    private ServoImplEx horizontal_gimbal;
    private ServoImplEx vertical_gimbal;

    /**
     * Default constructor for Gimbal.
     */
    public Gimbal(){ }
    /**
     * Overloaded constructor for Gimbal. Sets the OpMode context.
     *
     * @param opMode    the LinearOpMode that is currently running
     */
    public Gimbal(LinearOpMode opMode){
        this.opMode = opMode;
    }

    /**
     * Initializes gimbal hardware.
     * @param hwMap        robot's hardware map
     */
    public void init(HardwareMap hwMap) {
        // Retrieve gimbal from hardware map and set to initial position
        horizontal_gimbal = hwMap.get(ServoImplEx.class, RCConfig.HORZ_PHONE_GIMBAL);
        vertical_gimbal = hwMap.get(ServoImplEx.class, RCConfig.VERT_PHONE_GIMBAL);
        setSamplingPos();
    }

    /**
     * Set the gimbal to the sampling position during hang.
     */
    public void setSamplingPos() {
        horizontal_gimbal.setPosition(.79);
        vertical_gimbal.setPosition(.70);
    }
    public void setLandedSamplingPos() {
        horizontal_gimbal.setPosition(.69);
        vertical_gimbal.setPosition(.50);
    }

    /**
     * Set the gimbal to the left position.
     */
    public void setHorizontalLeft() {
        horizontal_gimbal.setPosition(MIN_POS);
    }
    public void setVerticalLeft() { vertical_gimbal.setPosition(MIN_POS); }

    /**
     * Set the gimbal to the right position.
     */
    public void setHorizontalRight() {
        horizontal_gimbal.setPosition(MAX_POS);
    }
    public void setVerticalRight() { vertical_gimbal.setPosition(MAX_POS);}

    /**
     * Set the gimbal to the neutral position.
     */
    public void setHorizontalNeutral() {
        horizontal_gimbal.setPosition(NEUTRAL_POS);
    }
    public void setVerticalNeutral() { vertical_gimbal.setPosition(NEUTRAL_POS); }

    public void setHorizontalPosition(double value) {
        horizontal_gimbal.setPosition(value);
    }
    public void setVerticalPosition(double value) {
        vertical_gimbal.setPosition(value);
    }

    public double getHorizontalPosition() { return horizontal_gimbal.getPosition(); }
    public double getVerticalPosition() { return vertical_gimbal.getPosition(); }

}