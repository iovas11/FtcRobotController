package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
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

        //initialize mecanum drive train and other hardware
        driveTrain = new MecanumDrive();

        //initialize PID controller
        double kp = 0.03;
        double ki = 0.001;
        double kd = 0.001;
        pidController = new PIDController(kp,ki,kd);
        pidController.setTarget(TARGET_ANGLE);

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


    private double getCurrentAngle() {
        //get current angle using gyro sensor or other orientation sensor
        //return the current angle in degrees
    }
}

class MecanumDriveTrain {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    public MecanumDriveTrain(HardwareMap hardwareMap) {
        //initialize motors using hardwareMap
        frontLeftMotor=hardwareMap.dcMotor.get("frontLeft");
        frontRightMotor=hardwareMap.dcMotor.get("frontRight");
        rearLeftMotor=hardwareMap.dcMotor.get("rearLeft");
        rearRightMotor = hardwareMap.dcMotor.get("rearRight");

        //set motor directions and zero power behavior
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setSpeeds(double x , double y ,double rotation) {
        double frontLeftPower = y + x + rotation;
        double frontRightPower = y -x - rotation;
        double rearLeftPower = y - x + rotation;
        double rearRightPower = y + x - rotation;

        /*Scale the motor powers to ensure they are within the acceptable
        range of -1 to 1 */
        Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
        Math.max(Math.abs(rearLeftPower), Math.abs(rearRightPower))
    };

    if (maxPower > 1.0) {
        frontLeftPower /= maxPower;
        frontRightPower /= maxPower;
        rearLeftPower /= maxPower;
        rearRightPower /= maxPower;
    }

    // Set motor powers
        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        rearLeftMotor.setPower(rearLeftPower);
        rearRightMotor.setPower(rearRightPower);
}


class PIDController {
    private double kp;
    private double ki;
    private double kd;
    private double target;
    private double integral;
    private double previousError;
    private double previousTime;

    public PIDController(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        target = 0.0;
        integral = 0.0;
        previousError = 0.0;
        previousTime = 0.0;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double calculateOutput(double current, double time) {
        double error = target - current;
        double deltaTime = time - previousTime;
        double derivative = (error - previousError) / deltaTime;

        integral += error * deltaTime;

        double output = kp * error + ki * integral + kd * derivative;

        previousError = error;
        previousTime = time;

        return output;
    }

}
