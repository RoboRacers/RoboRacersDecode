package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name = "Triple Hub")
public class TripleBus extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        MultipleTelemetry multipleTelemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry()
        );

        // All ports default to NONE, buses default to empty
        SRSHub.Config config = new SRSHub.Config();


        config.addI2CDevice(
                1,
                new SRSHub.VL53L5CX(SRSHub.VL53L5CX.Resolution.GRID_4x4)
        );

        config.addI2CDevice(
                2,
                new SRSHub.VL53L5CX(SRSHub.VL53L5CX.Resolution.GRID_4x4)
        );
        config.addI2CDevice(
                3,
                new SRSHub.VL53L5CX(SRSHub.VL53L5CX.Resolution.GRID_4x4)
        );

        RobotLog.clearGlobalWarningMsg();

        SRSHub hub = hardwareMap.get(
                SRSHub.class,
                "srs"
        );

        hub.init(config);

        while (!hub.ready());
        multipleTelemetry.addData("srshub connected", hub.getVersion());


        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            hub.update();

            if (hub.disconnected()) {
                multipleTelemetry.addLine("srshub disconnected");

            } else {


                SRSHub.VL53L5CX multiZone = hub.getI2CDevice(
                        1,
                        SRSHub.VL53L5CX.class
                );
                SRSHub.VL53L5CX multiZone2 = hub.getI2CDevice(
                        2,
                        SRSHub.VL53L5CX.class
                );
                SRSHub.VL53L5CX multiZone3 = hub.getI2CDevice(
                        3,
                        SRSHub.VL53L5CX.class
                );


                if (!multiZone.disconnected && !multiZone2.disconnected && !multiZone3.disconnected) {
                    short[] distances = multiZone.distances;
                    short[] distances2 = multiZone2.distances;
                    short[] distances3 = multiZone3.distances;
                    StringBuilder gridBuilder = new StringBuilder("\n"); // Start with a newline

                    // Check if the distances array actually has 64 values to prevent crashes
                    if (distances.length == 16 && distances2.length==16 && distances3.length==16) {
                        // Loop through all 64 distances
                        for (int i = 0; i < 16; i++) {
                            // Append each formatted distance
                            gridBuilder.append(String.format("%5d ", distances[i]));



                            // If we've just added the 8th item in a row, add a newline
                            // to start the next row of the grid.
                            if ((i + 1) % 4 == 0) {
                                gridBuilder.append("\n");
                            }
                        }
                        gridBuilder.append("\n");
                        for (int i = 0; i < 16; i++) {
                            // Append each formatted distance
                            gridBuilder.append(String.format("%5d ", distances2[i]));



                            // If we've just added the 8th item in a row, add a newline
                            // to start the next row of the grid.
                            if ((i + 1) % 4 == 0) {
                                gridBuilder.append("\n");
                            }
                        }
                        gridBuilder.append("\n");
                        for (int i = 0; i < 16; i++) {
                            // Append each formatted distance
                            gridBuilder.append(String.format("%5d ", distances3[i]));



                            // If we've just added the 8th item in a row, add a newline
                            // to start the next row of the grid.
                            if ((i + 1) % 4 == 0) {
                                gridBuilder.append("\n");
                            }
                        }
                        multipleTelemetry.addData("Distances (mm) 8x8 Grid", gridBuilder.toString());
                    } else {
                        multipleTelemetry.addData("Error", "Expected 64 distance values, but got %d", distances.length);
                        multipleTelemetry.addData("Error", "Expected 64 distance2 values, but got %d", distances2.length);
                        multipleTelemetry.addData("Error", "Expected 64 distance2 values, but got %d", distances3.length);

                    }
                }
                multipleTelemetry.addData("Didnt work","");
            }

            multipleTelemetry.update();
        }
    }
}