package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name = "Double Hub")
public class DoubleHub extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        MultipleTelemetry multipleTelemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry()
        );

        // All ports default to NONE, buses default to empty
        SRSHub.Config config = new SRSHub.Config();
        SRSHub.Config config2 = new SRSHub.Config();


        config.addI2CDevice(
                1,
                new SRSHub.VL53L5CX(SRSHub.VL53L5CX.Resolution.GRID_8x8)
        );

        config2.addI2CDevice(
                1,
                new SRSHub.VL53L5CX(SRSHub.VL53L5CX.Resolution.GRID_8x8)
        );

        RobotLog.clearGlobalWarningMsg();

        SRSHub hub = hardwareMap.get(
                SRSHub.class,
                "srs"
        );
        SRSHub hub2 = hardwareMap.get(
                SRSHub.class,
                "srs2"
        );

        hub.init(config);
        hub2.init(config2);

        while (!hub.ready() || !hub2.ready());
        multipleTelemetry.addData("srshub connected", hub.getVersion());
        multipleTelemetry.addData("srshub connected", hub2.getVersion());


        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            hub.update();
            hub2.update();

            if (hub.disconnected()|| hub2.disconnected()) {
                multipleTelemetry.addLine("srshub disconnected");

            } else {


                SRSHub.VL53L5CX multiZone = hub.getI2CDevice(
                        1,
                        SRSHub.VL53L5CX.class
                );
                SRSHub.VL53L5CX multiZone2 = hub2.getI2CDevice(
                        1,
                        SRSHub.VL53L5CX.class
                );

                if (!multiZone.disconnected && !multiZone2.disconnected) {
                    short[] distances = multiZone.distances;
                    short[] distances2 = multiZone2.distances;
                    StringBuilder gridBuilder = new StringBuilder("\n"); // Start with a newline

                    // Check if the distances array actually has 64 values to prevent crashes
                    if (distances.length == 64 && distances2.length==64) {
                        // Loop through all 64 distances
                        for (int i = 0; i < 64; i++) {
                            // Append each formatted distance
                            gridBuilder.append(String.format("%5d ", distances[i]));



                            // If we've just added the 8th item in a row, add a newline
                            // to start the next row of the grid.
                            if ((i + 1) % 8 == 0) {
                                gridBuilder.append("\n");
                            }
                        }
                        gridBuilder.append("\n");
                        for (int i = 0; i < 64; i++) {
                            // Append each formatted distance
                            gridBuilder.append(String.format("%5d ", distances2[i]));



                            // If we've just added the 8th item in a row, add a newline
                            // to start the next row of the grid.
                            if ((i + 1) % 8 == 0) {
                                gridBuilder.append("\n");
                            }
                        }
                        multipleTelemetry.addData("Distances (mm) 8x8 Grid", gridBuilder.toString());
                    } else {
                        multipleTelemetry.addData("Error", "Expected 64 distance values, but got %d", distances.length);
                        multipleTelemetry.addData("Error", "Expected 64 distance2 values, but got %d", distances2.length);
                    }
                }
                multipleTelemetry.addData("Didnt work","");
            }

            multipleTelemetry.update();
        }
    }
}