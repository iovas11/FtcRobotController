package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.mechanisms.TestBenchIMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp
public class imuPractice extends OpMode {
    TestBenchIMU bench = new TestBenchIMU();
    double heading;

    @Override
    public void init() {
        bench.init(hardwareMap);
    }


    @Override
    public void loop() {
        telemetry.addData("Heading" , bench.getHeading(AngleUnit.DEGREES));
        heading = bench.getHeading(AngleUnit.DEGREES);

        if(heading < 0.5 && heading > -0.5) {
            bench.setMotor(0.0);
        }
        else if (heading > 0.5) {
            bench.setMotor(0.5);
        }
        else {
            bench.setMotor(-0.5);
        }
    }
}
