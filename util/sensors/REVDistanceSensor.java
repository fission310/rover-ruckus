package org.firstinspires.ftc.teamcode.util.sensors;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class REVDistanceSensor {

    public DistanceSensor distanceSensor;
    public Rev2mDistanceSensor sensorTimeOfFlight;

    public REVDistanceSensor() { }

    public void init(HardwareMap hwMap, String deviceName) {
        distanceSensor = hwMap.get(DistanceSensor.class, deviceName);
        sensorTimeOfFlight = (Rev2mDistanceSensor)distanceSensor;
    }

    public double getDistanceMM() { return distanceSensor.getDistance(DistanceUnit.MM); }
    public double getDistanceCM() { return distanceSensor.getDistance(DistanceUnit.CM); }
    public double getDistanceMeter() { return distanceSensor.getDistance(DistanceUnit.METER); }
    public double getDistanceInch() { return distanceSensor.getDistance(DistanceUnit.INCH); }
}
