package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.util.AutoTransitioner;

@Autonomous(name = "AutoTransitioningAuto")
public class AutoTransitioningAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initializing Here", true);
        telemetry.update();

        AutoTransitioner.transitionOnStop(this, "Teleop: Main Slide");
        // AutoTransitioner used before waitForStart()
        waitForStart();


        telemetry.addData("Timer", new Func<Double>() {
            @Override
            public Double value() {
                return getRuntime();
            }
        });
        while (opModeIsActive()) {
            telemetry.update();
        }
    }
}
