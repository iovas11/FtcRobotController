package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.sun.tools.javac.tree.DCTree;

@TeleOp (name = "intakeTransferTeleOp", group = "linear OpMode")
public class IntakeTransferTeleOp extends LinearOpMode {

    private DcMotor motorIntake = null;

    private DcMotor motorTransfer = null;


    @Override
    public void runOpMode () {
        motorIntake = hardwareMap.get(DcMotor.class, "motor_intake");
        motorIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorTransfer = hardwareMap.get(DcMotor.class, "transfer_stang");

        motorTransfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("status", "initializat.start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.circle) {
                motorIntake.setPower(0.0);
            }
            else if (gamepad1.right_bumper) {
                motorIntake.setPower(1.0);
            }
            else if (gamepad1.left_bumper) {
                motorIntake.setPower(-1.0);
            }
            else {
                motorIntake.setPower(0.0);
            }

        }
         if (gamepad1.dpad_left) {

             motorTransfer.setPower(0.8);
         }
         else if (gamepad1.dpad_right) {
             motorTransfer.setPower(-0.8);

         }
         else {
             motorTransfer.setPower(0.0);
         }
         telemetry.addData("putere intake: ", motorIntake.getPower());
         telemetry.addData("putere transfer stang: ", motorTransfer.getPower());
         telemetry.update();
    }
}
