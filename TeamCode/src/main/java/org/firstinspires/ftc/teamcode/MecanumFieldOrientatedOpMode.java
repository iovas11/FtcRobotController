package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

public class MecanumFieldOrientatedOpMode extends OpMode {
MecanumDrive drive = new MecanumDrive();
double forward, strafe, rotate;
    @Override
    public void init() {
        drive.init(hardwareMap);

    }


    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward,strafe,rotate);


    }
}
 /*all this helps with making the robot drive in the hps pov
 if the robot is facing north and the hp is facing south
 the directions [forward reverse left strafe right strafe]
 of the robot are going to be the same as the ones of the
 human player.
  */