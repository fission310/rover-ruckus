package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.Mechanism;
import org.firstinspires.ftc.teamcode.hardware.RCConfig;


/**
 * RackNPinonLift is the class that is used to define all of the hardware for a robot's hanging mechanism.
 * RackNPinonLift must be instantiated, then initialized using <code>init()</code> before being used.
 *
 * This class also contains autonomous actions involving hanging.
 */
public class RackNPinonLift extends Mechanism {

    /* CONSTANTS */

    /* Hardware members */
    private DcMotor rackMotor;
    /**
     * Default constructor for Acquirer.
     */
    public RackNPinonLift(){

    }
    /**
     * Overloaded constructor for Lift. Sets the OpMode context.
     *
     * @param opMode    the LinearOpMode that is currently running
     */
    public RackNPinonLift(LinearOpMode opMode){
        this.opMode = opMode;
    }

    /**
     * Initializes lift hardware.
     * @param hwMap        robot's hardware map
     */
    public void init(HardwareMap hwMap) {
        // Retrieve servos from hardware map and assign to instance vars

        // Retrieve motor from hardware map and assign to instance vars
        rackMotor = hwMap.dcMotor.get(RCConfig.RACKANDPINION);

        // Set braking behavior
        rackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set polarity
        rackMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set initial power
        rackMotor.setPower(0);
    }

    /**
     * Sets power for rack motor.
     */
    public void setRackPower(double power) {
        rackMotor.setPower(power);
    }

    /**
     * Sets power for rack motor based on encoder values.
     */
    public void rackToPos(double power) {
        rackMotor.setPower(power);
    }

}