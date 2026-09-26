package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class VariablePractice extends OpMode {

    @Override
    public void init() {
        int motorAngle = 90;
        int teamNumber = 23014;
        double motorSpeed = 0.75;
        boolean clawClosed = true;
        String teamname = "StrixNovus";

        telemetry.addData("Team Number" , teamNumber);
        telemetry.addData("motor speed", motorSpeed);
        telemetry.addData("claw closed", clawClosed);
        telemetry.addData("Name", teamname);
        telemetry.addData("Motor angle" , motorAngle);
    }


    @Override
    public void loop() {

    }
}
