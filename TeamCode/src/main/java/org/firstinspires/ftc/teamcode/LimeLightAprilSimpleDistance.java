package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.mechanisms.TestBench;

@TeleOp
public class LimeLightAprilSimpleDistance extends OpMode {
    private Limelight3A limelight3A;
    TestBench bench = new TestBench();
    private double distance;

    @Override
    public void init() {
        bench.init(hardwareMap);
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(0); // April tag 0 pipeline
    }

    @Override
    public void loop() {
        //get yaw from control hub IMU
        YawPitchRollAngles orientation = bench.getOrientation();
        limelight3A.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));

        //get latest limelight result pipeline 8 for April tag 0
        LLResult llResult = limelight3A.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            telemetry.addData("Calculated Distance", distance);
            telemetry.addData("target x", llResult.getTx());
            telemetry.addData("target area", llResult.getTa());
            telemetry.addData("bot pose", botpose.toString());
        }

    }
}
