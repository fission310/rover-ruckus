package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.hardware.mecanum.HardwareMecanum;
import org.firstinspires.ftc.teamcode.util.signals.BackgroundColorManager;

import static java.lang.Math.abs;

/**
 * TeleopMecanumArcade is the primary TeleOp OpMode for mecanum drivetrains. All driver-controlled actions should
 * be defined in this class.
 */
@Disabled
@TeleOp(name = "Teleop: Arcade Mecanum [Test]", group = "Teleop")
public class TeleopMecanumArcade extends OpMode {

    private final double ANALOG_THRESHOLD = 0.15;
    private final double SLOW_MULTIPLIER = 0.5;
    private final double LINEAR_SLIDES_SLOW_MULTIPLIER = 0.5;

    /* Private OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    /* Robot hardware map */
    private HardwareMecanum robot = new HardwareMecanum();

    /* Robot controller's background manager */
    private BackgroundColorManager background = new BackgroundColorManager();

    /* Holds Gamepad 1 joystick's values */
    double yInput, xInput, slideInput;
    double slowYInput, slowXInput, slowSlide;

    /* Handle time complexities */
    boolean aButtonPressed, bButtonPressed, xButtonPressed, yButtonPressed, leftStickPressed, rightBumperPressed, leftBumperPressed;
    boolean aButton, bButton, xButton, yButton, leftStick, rightStick, rightBumper, leftBumper;

    /* Handle button positions */
    boolean drivetrainSlowMode;

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.imuInit(hardwareMap);
        background.init(hardwareMap);
        background.setGreenBackground();
    }

    /**
     * Runs continuously while OpMode is waiting to start.
     * @see OpMode#init_loop()
     */
    @Override
    public void init_loop() {
        telemetry.addData("Status:", "Waiting to start");
        telemetry.addData("Gyro Is Calibrated", robot.imuCalibrated());
        telemetry.addData("imu angle",robot.imuAngle());
        telemetry.update();
    }

    /**
     * Runs once when the OpMode starts. Starts the OpMode's runtime counter.
     * @see OpMode#loop()
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /**
     * Runs continuously while the OpMode is active. Defines the driver-controlled actions
     * according to gamepad input.
     * @see OpMode#loop()
     */
    @Override
    public void loop() {
        // Adds runtime data to telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        /**
         * Gamepad 1
         */

        /**
         * Controls the drivetrain via the left and right analog sticks || Slow mode = left stick button
         */
        yInput = gamepad1.left_stick_y;
        xInput = gamepad1.right_stick_x;
        slideInput = gamepad1.left_stick_x;
        if (abs(gamepad1.left_stick_x) < ANALOG_THRESHOLD) slideInput = 0.0;

        slowYInput = Range.clip(yInput * SLOW_MULTIPLIER, -1.0, 1.0);
        slowXInput = Range.clip(xInput * SLOW_MULTIPLIER, -1.0, 1.0);
        slowSlide = Range.clip(slideInput * SLOW_MULTIPLIER, -1.0, 1.0);

        if (gamepad1.left_stick_button)
            if(!leftStickPressed) {
                drivetrainSlowMode = !drivetrainSlowMode;
                leftStickPressed = true;
            } else {}
        else leftStickPressed = false;

        if (drivetrainSlowMode) {
            background.setOrangeBackground();
            robot.drivetrain.driveVector(yInput, slideInput, xInput);
        } else {
            background.setGreenBackground();
            robot.drivetrain.driveTrig(slideInput, yInput, xInput);
        }

        /**
         * Telemetry
         */
        double[] positions = robot.drivetrain.getPositions();
        telemetry.addData("Encoder counts", "lf: %.2f | rf: %.2f | lb: %.2f | rb: %.2f",
                positions[0],
                positions[1],
                positions[2],
                positions[3]);
    }

    @Override
    public void stop() {
        background.resetBackgroundColor();
    }
}