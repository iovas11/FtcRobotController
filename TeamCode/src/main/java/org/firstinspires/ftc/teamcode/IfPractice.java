package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class IfPractice extends OpMode {

    @Override
    public void init() {


    }

    @Override
    public void loop() {
        boolean aButton = gamepad1.a; //PRESS =TRUE, DEPRESS = FALSE
        //true or false
        if (aButton) {
            telemetry.addData("A Button", "Pressed");

        }
        else { //otherwise
            telemetry.addData("A Button", "Not Pressed");
        }
        telemetry.addData("A Button State", aButton);
    }
}
