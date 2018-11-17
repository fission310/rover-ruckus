package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.HardwareTank;
import org.firstinspires.ftc.teamcode.util.vision.VisionManager;
import org.firstinspires.ftc.teamcode.hardware.Constants;

@Autonomous(name="Auton: Depot Everything", group="Auton")
public class AutonMain extends LinearOpMode {
    /* Private OpMode members */
    private ElapsedTime     runtime = new ElapsedTime();

    /* Robot hardware */
    private HardwareTank robot = new HardwareTank(this);

    /**
     * Runs the autonomous routine.
     */
    @Override
    public void runOpMode() {
        // Initialize CV
        VisionManager visionManager = new VisionManager();
        visionManager.goldAlignInit(hardwareMap);

        // Initialize robot
        robot.init(hardwareMap);
        robot.drivetrain.encoderInit();

        // Wait until we're told to go
        waitForStart();
        runtime.reset();  // Start counting run time from now.

        robot.land();
        sleep(2000);
        robot.drivetrain.turn(0.3, 15.0, 3.0);
        robot.drivetrain.driveToPos(0.3, -4, -4, 4.0);
        robot.drivetrain.turn(0.3, -105.0, 5.0);
        robot.sample(visionManager);
        robot.drivetrain.driveToPos(0.3, 12,12,3);
        robot.drivetrain.turn(0.3, 105.0, 3.0);
        robot.drivetrain.driveToPos(0.5, 24,24,3);
        robot.drivetrain.driveToPos(0.5, -50,-50,5);

        // Stop CV
        visionManager.goldAlignStop();

    }
}
