package org.firstinspires.ftc.teamcode.camera;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;


@TeleOp(name = "fjakslf", group = "16481")
public class Distance extends LinearOpMode{
    AprilTagProcessor camera;
    @Override
    public void runOpMode() throws InterruptedException {
        camera = new AprilTagProcessor.Builder()
                .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .build();
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(camera)
                .build();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        dashboard.startCameraStream(visionPortal, 24);

        telemetry.addLine("Init complete. Press start.");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            List<AprilTagDetection> detections = camera.getDetections();
            if (!detections.isEmpty()){
                AprilTagDetection detection = camera.getDetections().get(0);
                AprilTagPoseFtc ftcPose = detection.ftcPose;

                telemetry.addData("distance", ftcPose.range);
                telemetry.addData("Tag ID", detection.id);
                telemetry.addData("Bearing", ftcPose.bearing);
                telemetry.addData("Bearing", ftcPose.elevation);
                telemetry.addData("Yaw", ftcPose.yaw);
                telemetry.addData("Pitch", ftcPose.pitch);
                telemetry.addData("Roll", ftcPose.roll);
            }
            else {
                telemetry.addLine("No detections");
            }
            telemetry.update();
        }
        FtcDashboard.getInstance().stopCameraStream();
    }
}