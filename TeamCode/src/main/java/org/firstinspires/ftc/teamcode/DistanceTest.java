package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBenchDistance;
@TeleOp
public class DistanceTest extends OpMode {
    double distance;
    TestBenchDistance bench = new TestBenchDistance();
    @Override
    public void init() {
        bench.init(hardwareMap);


    }


    @Override
    public void loop() {
        distance = bench.getDistance();

        if(distance<10) {
            telemetry.addLine("Too close!");
        }
        telemetry.addData("Distance" , bench.getDistance());

    }
}
