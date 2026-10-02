package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

public class MecanumDriveWithPID extends LinearOpMode {
    private MecanumDrive driveTrain;
    private PIDController pidController;
    private IMU imu;
    private void initializeHardware() {
        driveTrain = new MecanumDrive();
        pidController = new PIDController(0.05, 0.0, 0.01);

    }
    private double getCurrentAngle() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    private final double TARGET_ANGLE = 0.0; //desired angle

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        waitForStart();

        while(opModeIsActive()) {
            //Calculate PID output
            double pidOutput = pidController.calculateOutput(getCurrentAngle(), getRuntime());

            //apply pid output to adjust motor speeds
            driveTrain.drive(
                    -gamepad1.left_stick_y, //forward backward
                    gamepad1.left_stick_x, // left right
                    gamepad1.right_stick_x + pidOutput //turn rotate
            );

            telemetry.addData("PID Output", pidOutput);
            telemetry.update();

            idle();
        }
    }

}
