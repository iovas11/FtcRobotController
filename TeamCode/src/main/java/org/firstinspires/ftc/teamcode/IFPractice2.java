package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class IFPractice2 extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {

        double motorSpeed = gamepad1.left_stick_y;

     if (!gamepad1.a)   {
         motorSpeed *= 0.5 ;
     }

        telemetry.addData("Left Stick value", motorSpeed);
    }
}

/*

AND - && if (leftY < 0.5 && leftY >0) {
OR - || if (leftY < 0 || righty < 0) {
NOT - ! if (!clawClosed) {

 */
