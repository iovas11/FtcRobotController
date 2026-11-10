package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp (name = "intake teleop", group = "liniar OpMode")
    public class intakeTeleOp extends LinearOpMode {
        private DcMotor motorIntake = null  ;

        @Override
    public void runOpMode () {
            motorIntake = hardwareMap.get(DcMotor.class, "motor_Intake");
            motorIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            telemetry.addData("Status", "initializat");
            telemetry.update();

            waitForStart();

            while(opModeIsActive()) {

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

                telemetry.addData("putere intake", motorIntake.getPower());


            }

        }
    }

