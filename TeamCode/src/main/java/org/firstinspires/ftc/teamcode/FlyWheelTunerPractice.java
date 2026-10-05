package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class FlyWheelTunerPractice extends OpMode {

    public DcMotorEx flywheelMotor;
    public double highVelocity = 1500;
    public double lowVelocity = 900;
    public double curTargetVelocity= highVelocity;
    double F = 0;
    double P = 0;
    double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};
    int stepIndex = 1;
    @Override
    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "motor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P , 0, 0, F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        telemetry.addLine("init complete");

    }

    @Override
    public void loop() {
        //get all our gamepad commands
        //set target velocity
        //update telemetry
        if(gamepad1.yWasPressed()) {
            if(curTargetVelocity == highVelocity) {
                curTargetVelocity = lowVelocity;
            }
        }
        else {curTargetVelocity = highVelocity;}

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }
        if (gamepad1.dpadLeftWasPressed()) {
            F -= stepSizes[stepIndex];
        }
        if (gamepad1.dpadRightWasPressed()) {
            F += stepSizes[stepIndex];
        }

        if(gamepad1.dpadDownWasPressed()) {
            P += stepSizes[stepIndex];
        }

        if(gamepad1.dpadUpWasPressed()) {
            P -= stepSizes[stepIndex];
        }

        //set new PIDF coefficients
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P , 0, 0, F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        //set velocity
        flywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity = flywheelMotor.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("target velocity", curTargetVelocity);
        telemetry.addData("current velocity", curVelocity);
        telemetry.addData("error", "%.2f", error);
        telemetry.addLine("---------------------------");
        telemetry.addData("tuning P", "%.4f (D-Pad U/D", P);
        telemetry.addData("tunning F", "%.4f (D-Pad L/R", F);
        telemetry.addData("Step size", "%.4f (B button", stepSizes[stepIndex]);


    }
}
